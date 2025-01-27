package com.matariky.jobs.jobsService.assetitm.inventory.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Sets;
import com.matariky.annotation.Lock;
import com.matariky.aop.LockPoint;
import com.matariky.bo.ReaderTaskBo;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.constant.DictKey;
import com.matariky.commonservice.commondict.constant.DictTypeKey;
import com.matariky.commonservice.commondict.mapper.CommonDictMapper;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.message.bean.Message;
import com.matariky.commonservice.message.mapper.MessageMapper;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.constant.RedisKey;
import com.matariky.id.SnowflakeIdWorker;
import com.matariky.jobs.jobsService.bean.form.JobForm;
import com.matariky.jobs.jobsService.service.JobService;
import com.matariky.jobs.jobsService.assetitm.base.bean.TapeLabel;
import com.matariky.jobs.jobsService.assetitm.base.bean.TapeRack;
import com.matariky.jobs.jobsService.assetitm.base.bean.TapeReader;
import com.matariky.jobs.jobsService.assetitm.base.mapper.JobTapeLabelMapper;
import com.matariky.jobs.jobsService.assetitm.base.mapper.JobTapeRackMapper;
import com.matariky.jobs.jobsService.assetitm.base.mapper.JobTapeReaderMapper;
import com.matariky.jobs.jobsService.assetitm.inout.bean.TapeInout;
import com.matariky.jobs.jobsService.assetitm.inout.mapper.JobTapeInoutMapper;
import com.matariky.jobs.jobsService.assetitm.inventory.bean.TapeInventoryResult;
import com.matariky.jobs.jobsService.assetitm.inventory.bean.TapeInventorySubtask;
import com.matariky.jobs.jobsService.assetitm.inventory.bean.TapeInventoryTask;
import com.matariky.jobs.jobsService.assetitm.inventory.job.TapeInventoryResultJob;
import com.matariky.jobs.jobsService.assetitm.inventory.mapper.JobTapeInventoryResultMapper;
import com.matariky.jobs.jobsService.assetitm.inventory.mapper.JobTapeInventorySubtaskMapper;
import com.matariky.jobs.jobsService.assetitm.inventory.mapper.JobTapeInventoryTaskMapper;
import com.matariky.jobs.jobsService.assetitm.stock.bean.TapeStock;
import com.matariky.jobs.jobsService.assetitm.stock.mapper.JobTapeStockMapper;
import com.matariky.jobs.jobsService.assetitm.stock.vo.TapeStockLabelVo;
import com.matariky.redis.LuaScript;
import com.matariky.redis.RedisUtils;
import com.matariky.redis.redisson.LockKey;
import com.matariky.utils.DateUtil;
import com.matariky.utils.JacksonUtils;
import com.matariky.utils.NumberUtils;
import com.matariky.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description: 磁带盘点 Task Service 类
 * @author: bo.chen
 * @create: 2023/9/24 15:00
 **/
@Service
public class TapeInventoryTaskJobService {

    private final static Logger logger = LoggerFactory.getLogger(TapeInventoryTaskJobService.class);

    @Autowired
    private CommonDictMapper commonDictMapper;

    @Autowired
    private JobTapeInventoryTaskMapper tapeInventoryTaskMapper;

    @Autowired
    private JobTapeStockMapper tapeStockMapper;

    @Autowired
    private JobTapeInventorySubtaskMapper tapeInventorySubtaskMapper;

    @Autowired
    private JobTapeInventoryResultMapper tapeInventoryResultMapper;

    @Autowired
    private JobTapeReaderMapper tapeReaderMapper;

    @Autowired
    private JobTapeRackMapper tapeRackMapper;

    @Autowired
    private CommonDictService commonDictService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private JobTapeLabelMapper tapeLabelMapper;

    @Autowired
    private JobService jobService;

    @Autowired
    private JobTapeInoutMapper tapeInoutMapper;

    @Autowired
    private MessageMapper messageMapper;

    /**
     * @Description: Start Task
     * @Author: bo.chen
     * @Date: 2023/9/22 15:15
     * @param taskId
     **/
    @Transactional(rollbackFor = Exception.class)
    @Lock(keyMethod = "this.getRackLockKeys")
    public void start(Long taskId) throws Exception {
        TapeInventoryTask inventoryTask = null;
        int quantity = NumberUtils.INTEGER_ZERO;
        try {
            /** Query 盘点 Task Information **/
            inventoryTask = tapeInventoryTaskMapper
                    .selectOne(tapeInventoryTaskMapper.qw().eq(TapeInventoryTask::getId, taskId)
                            .eq(TapeInventoryTask::getDeleteTime, NumberUtils.INTEGER_ZERO));
            if (Objects.isNull(inventoryTask)) {
                logger.error("盘点 Task 不存在！taskId={}", taskId);
                return;
            }
            switch (inventoryTask.getProcessStatus()) {
                case 4:
                    logger.error("盘点 Task 已结束！taskId={}", taskId);
                    return;
                case 2:
                    logger.error("盘点 Task 正在扫描中！taskId={}", taskId);
                    return;
                default:
                    break;
            }
            if (NumberUtils.INTEGER_TWO.equals(inventoryTask.getStatus())) {
                logger.error("盘点 Task 已 Deactivate！taskId={}", taskId);
                return;
            }
            if (DateUtil.toLocalDateTime(inventoryTask.getStartTime()).plusMinutes(NumberUtils.INTEGER_MINUS_ONE)
                    .isAfter(LocalDateTime.now())) {
                logger.error("盘点 Task 未到 Start  Time ！taskId={}, startTime={}", taskId, inventoryTask.getStartTime());
                return;
            }

            /** Query 盘点 Library 库存 Quantity **/
            TapeInventoryTask finalInventoryTask = inventoryTask;
            quantity = NumberUtils.ifNullToZero(tapeStockMapper.selectSumColumnValue(wrapper -> wrapper
                    .likeRight(TapeStock::getLocationId, finalInventoryTask.getLocationId())
                    .in(CollectionUtils.isNotEmpty(finalInventoryTask.getLibraryIds()), TapeStock::getLibraryId,
                            finalInventoryTask.getLibraryIds())
                    .eq(TapeStock::getDeleteTime, NumberUtils.LONG_ZERO), TapeStock::getQuantity));
            /** Query 正在盘点中的 Task Name **/
            String inInventoryTaskName = tapeInventoryTaskMapper.selectInInventoryTaskName(
                    inventoryTask.getLocationId().split(",")[NumberUtils.INTEGER_ZERO],
                    inventoryTask.getLocationId(),
                    CollectionUtils.isNotEmpty(inventoryTask.getLibraryIds())
                            ? JacksonUtils.toJsonString(inventoryTask.getLibraryIds())
                            : null,
                    taskId);
            if (StringUtils.isNotEmpty(inInventoryTaskName)) {
                logger.error("盘点 Task    Library 正在做盘点！inInventoryTaskName={}", inInventoryTaskName);
                handleInventoryStartFail(inventoryTask, quantity, MessageKey.TAPE_INVENTORY_TASK_RACK_ING,
                        inInventoryTaskName);
                return;
            }
            /** 取消差异 Task **/
            cancelDiscrepancySubtask(inventoryTask.getLocationId(), inventoryTask.getLibraryIds(),
                    inventoryTask.getTaskName());
            /** Reader **/
            List<TapeReader> readerList = tapeReaderMapper.selectList(tapeReaderMapper.qw()
                    .likeRight(TapeReader::getLocationId, inventoryTask.getLocationId())
                    .in(CollectionUtils.isNotEmpty(inventoryTask.getLibraryIds()), TapeReader::getRackId,
                            inventoryTask.getLibraryIds())
                    .eq(TapeReader::getDeleteTime, NumberUtils.LONG_ZERO));
            if (CollectionUtils.isEmpty(readerList)) {
                logger.error("盘点   Library 未 Query 到  Reader ！taskId={}", taskId);
                handleInventoryStartFail(inventoryTask, quantity, MessageKey.TAPE_LIBRARY_NOT_BIND_READER);
                return;
            }
            /** Query Library 正在做In/Out库的 Task **/
            List<TapeInout> inoutList = tapeInoutMapper
                    .selectList(tapeInoutMapper.qw().likeRight(TapeInout::getLocationId, inventoryTask.getLocationId())
                            .in(CollectionUtils.isNotEmpty(inventoryTask.getLibraryIds()), TapeInout::getLibraryId,
                                    inventoryTask.getLibraryIds())
                            .eq(TapeInout::getStatus, NumberUtils.INTEGER_ONE)
                            .eq(TapeInout::getDeleteTime, NumberUtils.INTEGER_ZERO)
                            .select(TapeInout::getType, TapeInout::getSerialNo));
            if (CollectionUtils.isNotEmpty(inoutList)) {
                logger.error("盘点 Task    Library 正在做In/Out库！inoutSerialNos={}", JacksonUtils.toJsonString(inoutList));
                TapeInout firstInout = inoutList.get(NumberUtils.INTEGER_ZERO);
                if (NumberUtils.INTEGER_ONE.equals(firstInout.getType())) {
                    handleInventoryStartFail(inventoryTask, quantity, MessageKey.TAPE_INVENTORY_RACK_INBOUND_ING,
                            firstInout.getSerialNo());
                } else {
                    handleInventoryStartFail(inventoryTask, quantity, MessageKey.TAPE_INVENTORY_RACK_OUTBOUND_ING,
                            firstInout.getSerialNo());
                }
                return;
            }
            for (TapeReader reader : readerList) {
                ReaderTaskBo taskBo = redisUtils.get(RedisKey.READER_EXECUTION_TASK + reader.getCode());
                if (Objects.nonNull(taskBo) && NumberUtils.INTEGER_TWO.equals(taskBo.getApplication())
                        && !NumberUtils.INTEGER_TREE.equals(taskBo.getType())) {
                    if (NumberUtils.INTEGER_ONE.equals(taskBo.getType())) {
                        handleInventoryStartFail(inventoryTask, quantity, MessageKey.TAPE_INVENTORY_RACK_INBOUND_ING,
                                StringUtils.EMPTY);
                    } else {
                        handleInventoryStartFail(inventoryTask, quantity, MessageKey.TAPE_INVENTORY_RACK_OUTBOUND_ING,
                                StringUtils.EMPTY);
                    }
                    return;
                }
            }
            TapeInventorySubtask tapeInventorySubtask = new TapeInventorySubtask();
            tapeInventorySubtask.setId(SnowflakeIdWorker.getId());
            tapeInventorySubtask.setTaskId(taskId);
            tapeInventorySubtask.setQuantity(NumberUtils.ifNullToZero(quantity));
            tapeInventorySubtask.setCreateTime(System.currentTimeMillis());
            tapeInventorySubtask.setCreatedBy(inventoryTask.getCreatedBy());
            tapeInventorySubtask.setStatus(NumberUtils.INTEGER_ZERO);
            /** Save 盘点子 Task **/
            tapeInventorySubtaskMapper.insert(tapeInventorySubtask);
            /** Update主 Task Quantity和 Status **/
            tapeInventoryTaskMapper.update(tapeInventoryTaskMapper.uw().eq(TapeInventoryTask::getId, taskId)
                    .set(TapeInventoryTask::getProcessStatus, NumberUtils.INTEGER_TWO)
                    .set(TapeInventoryTask::getQuantity, NumberUtils.ifNullToZero(quantity))
                    .set(TapeInventoryTask::getActualQuantity, null)
                    .set(TapeInventoryTask::getUpdateTime, System.currentTimeMillis())
                    .set(TapeInventoryTask::getLastSubtaskId, tapeInventorySubtask.getId())
                    .set(TapeInventoryTask::getUpdatedBy, NumberUtils.LONG_MINUS_ONE));
            /** 最大 Read Time **/
            int maxTimes = commonDictService.getReaderMaxTimes(inventoryTask.getTenantId());
            /** Delete Read Label 结果集 **/
            redisUtils.delete(RedisKey.READER_TASK_EPC + inventoryTask.getTenantId());
            List<String> keys = new ArrayList<>();
            keys.add(RedisKey.TASK_STOCK_EPC + taskId);
            List<Object> values = new ArrayList<>();
            /** Configuration缓存过期 Time **/
            values.add(maxTimes);
            /** 0表示空库存 **/
            values.add(NumberUtils.INTEGER_ZERO);
            /** 缓存 Reader 正在执行的 Task id **/
            readerList.stream().forEach(reader -> {
                keys.add(RedisKey.READER_EXECUTION_TASK + reader.getCode());
                values.add(new ReaderTaskBo(taskId, 3, maxTimes, reader.getBrandModel(),
                        finalInventoryTask.getApplication()));
            });
            keys.add(RedisKey.TASK_READER_CODE + taskId);
            values.add(readerList.get(NumberUtils.INTEGER_ZERO).getCode());
            /** 缓存 Reader Task **/
            redisUtils.executeLua(LuaScript.SAVE_READER_TASK, keys, values.stream().toArray());
            JobForm jobForm = new JobForm();
            jobForm.setCronExpression(DateUtil.getCron(LocalDateTime.now().plusSeconds(maxTimes)));
            jobForm.setJobClassName(TapeInventoryResultJob.class.getName());
            jobForm.setJobGroupName(tapeInventorySubtask.getId().toString());
            /** Add 执行结果处理 Task **/
            jobService.addJob(jobForm);
        } catch (Exception e) {
            handleInventoryStartFail(inventoryTask, quantity, DictKey.SYSTEM_ERROR);
            logger.error("盘点 Task  Start 异常！taskId={}", taskId, e);
        }
    }

    /**
     * @Description: 盘点结束
     * @Author: bo.chen
     * @Date: 2023/9/22 10:26
     * @param subtaskId
     **/
    @Transactional(rollbackFor = Exception.class)
    @Lock(keyMethod = "this.getRackLockKeys")
    public void end(Long subtaskId) {
        TapeInventorySubtask inventorySubtask = null;
        TapeInventoryTask inventoryTask = null;
        try {
            /** Retrieve盘点子 Task **/
            inventorySubtask = tapeInventorySubtaskMapper
                    .selectOne(tapeInventorySubtaskMapper.qw().eq(TapeInventorySubtask::getId, subtaskId)
                            .eq(TapeInventorySubtask::getDeleteTime, NumberUtils.INTEGER_ZERO));
            if (Objects.isNull(inventorySubtask)) {
                logger.error("盘点子 Task 不存在！taskId={}", subtaskId);
                return;
            }
            /** Query 盘点 Task Information **/
            inventoryTask = tapeInventoryTaskMapper
                    .selectOne(tapeInventoryTaskMapper.qw().eq(TapeInventoryTask::getId, inventorySubtask.getTaskId())
                            .eq(TapeInventoryTask::getDeleteTime, NumberUtils.INTEGER_ZERO));
            if (Objects.isNull(inventoryTask)) {
                logger.error("盘点 Task 不存在！subtaskId={}, taskId={}", subtaskId, inventorySubtask.getTaskId());
                return;
            }
            if (!NumberUtils.INTEGER_ZERO.equals(inventorySubtask.getStatus())) {
                logger.error("盘点子 Task  Status 非法！subTask={}", JacksonUtils.toJsonString(inventorySubtask));
                handleInventoryEndFail(inventorySubtask, inventoryTask, MessageKey.COMMON_STATUS_ILLEGAL);
                return;
            }
            /** Reader **/
            List<TapeReader> readerList = tapeReaderMapper.selectList(tapeReaderMapper.qw()
                    .likeRight(TapeReader::getLocationId, inventoryTask.getLocationId())
                    .in(CollectionUtils.isNotEmpty(inventoryTask.getLibraryIds()), TapeReader::getRackId,
                            inventoryTask.getLibraryIds())
                    .eq(TapeReader::getDeleteTime, NumberUtils.LONG_ZERO));
            if (CollectionUtils.isEmpty(readerList)) {
                logger.error("盘点   Library 未 Query 到  Reader ！subtaskId={}, taskId={}", subtaskId,
                        inventorySubtask.getTaskId());
                handleInventoryEndFail(inventorySubtask, inventoryTask, MessageKey.TAPE_LIBRARY_NOT_BIND_READER);
                return;
            }
            Map<String, TapeReader> readerMap = readerList.stream()
                    .collect(Collectors.toMap(TapeReader::getCode, Function.identity()));
            /** Retrieve库存 Label **/
            Map<String, TapeStockLabelVo> stockLabelMap = getStockLabel(inventoryTask.getLocationId(),
                    inventoryTask.getLibraryIds());
            /** Retrieve扫描 Label Information **/
            Map<String, String> scanLabels = redisUtils
                    .hGetAll(RedisKey.READER_TASK_LABEL + inventorySubtask.getTaskId());
            /** Wether 存在差异 **/
            AtomicReference<Boolean> isDiscrepancy = new AtomicReference<>(Boolean.FALSE);
            if (CollectionUtils.isNotEmpty(stockLabelMap) || CollectionUtils.isNotEmpty(scanLabels)) {
                Map<String, Long> labelIdMap;
                if (CollectionUtils.isNotEmpty(scanLabels)) {
                    /** Retrieve不存在库存的EPC **/
                    Set<String> diffEpc = Sets.difference(scanLabels.keySet(), stockLabelMap.keySet());
                    if (CollectionUtils.isNotEmpty(diffEpc)) {
                        /** Retrieve Label 库 Label id **/
                        labelIdMap = tapeLabelMapper.selectToMap(tapeLabelMapper.qw().in(TapeLabel::getEpc, diffEpc),
                                TapeLabel::getEpc, TapeLabel::getId);
                    } else {
                        labelIdMap = Collections.emptyMap();
                    }
                } else {
                    labelIdMap = Collections.emptyMap();
                }
                TapeInventoryTask finalInventoryTask = inventoryTask;
                List<TapeInventoryResult> inventoryResultList = Sets.union(stockLabelMap.keySet(), scanLabels.keySet())
                        .stream().map(epc -> {
                            TapeInventoryResult result = new TapeInventoryResult();
                            result.setId(SnowflakeIdWorker.getId());
                            result.setTaskId(finalInventoryTask.getId());
                            result.setSubtaskId(subtaskId);
                            TapeStockLabelVo tapeStockLabelVo = stockLabelMap.get(epc);
                            /** 扫描 Label Information **/
                            String labelInfo = scanLabels.get(epc);
                            if (StringUtils.isNotEmpty(labelInfo)) {
                                String[] labelInfos = labelInfo.split(",");
                                if (labelInfos.length > NumberUtils.INTEGER_ONE) {
                                    result.setTid(labelInfos[NumberUtils.INTEGER_ONE]);
                                }
                                /** Reader **/
                                TapeReader tapeReader = readerMap.get(labelInfos[NumberUtils.INTEGER_ZERO]);
                                if (Objects.nonNull(tapeReader)) {
                                    result.setLocationId(tapeReader.getLocationId());
                                    result.setLibraryId(tapeReader.getRackId());
                                }
                                result.setReaderCode(labelInfos[NumberUtils.INTEGER_ZERO]);
                            }
                            if (Objects.nonNull(tapeStockLabelVo)) {
                                result.setLabelId(tapeStockLabelVo.getLabelId());
                                result.setLocationId(tapeStockLabelVo.getLocationId());
                                result.setLibraryId(tapeStockLabelVo.getLibraryId());
                                if (StringUtils.isNotEmpty(labelInfo)) {
                                    /** 正常 **/
                                    result.setResult(3);
                                    result.setIsValid(Boolean.TRUE);
                                } else {
                                    result.setResult(NumberUtils.INTEGER_ONE);
                                    result.setIsValid(Boolean.FALSE);
                                    if (!isDiscrepancy.get()) {
                                        isDiscrepancy.set(Boolean.TRUE);
                                    }
                                }
                            } else {
                                result.setLabelId(labelIdMap.get(epc));
                                /** 新发现 **/
                                result.setResult(NumberUtils.INTEGER_TWO);
                                result.setIsValid(Boolean.TRUE);
                                if (!isDiscrepancy.get()) {
                                    isDiscrepancy.set(Boolean.TRUE);
                                }
                            }
                            result.setEpc(epc);
                            result.setDeleteTime(NumberUtils.LONG_ZERO);
                            result.setCreateTime(System.currentTimeMillis());
                            result.setCreatedBy(NumberUtils.LONG_MINUS_ONE);
                            return result;
                        }).collect(Collectors.toList());
                /** 批量插入盘点结果 **/
                tapeInventoryResultMapper.insertBatchSomeColumn(inventoryResultList);
            }
            /** 盘点 Quantity **/
            int actualQuantity = CollectionUtils.isNotEmpty(scanLabels) ? scanLabels.size() : NumberUtils.INTEGER_ZERO;
            handleInventoryTask(inventorySubtask, inventoryTask, actualQuantity, isDiscrepancy.get());
            if (isDiscrepancy.get()) {
                try {
                    saveDiscrepancyMessage(inventoryTask);
                } catch (Exception e) {
                    logger.error(" Save 差异  Message 通知异常！inventoryTask={}", inventoryTask, e);
                }
            }
        } catch (Exception e) {
            logger.error("盘点结果处理异常！subtaskId={}", subtaskId, e);
            handleInventoryEndFail(inventorySubtask, inventoryTask, DictKey.SYSTEM_ERROR);
        } finally {
            if (Objects.nonNull(inventorySubtask)) {
                /** Delete 缓存扫描 Label Data **/
                redisUtils.delete(RedisKey.READER_TASK_LABEL + inventorySubtask.getTaskId());
            }
        }
    }

    /**
     * @Description: Save 盘点差异 Message
     * @Author: bo.chen
     * @Date: 2023/10/19 11:13
     * @param task
     **/
    private void saveDiscrepancyMessage(TapeInventoryTask task) {
        Message message = new Message();
        message.setId(SnowflakeIdWorker.getId());
        message.setTenantId(task.getTenantId());
        message.setOperatorOrgCode(task.getOperatorOrgCode());
        message.setOperatorSelfOrgCode(task.getOperatorSelfOrgCode());
        message.setContent(task.getTaskName());
        message.setMsgType(NumberUtils.INTEGER_ONE);
        message.setTarget(task.getId() + "_" + task.getLastSubtaskId());
        message.setCreatedBy(NumberUtils.LONG_MINUS_ONE);
        message.setCreateTime(System.currentTimeMillis());
        message.setDeleteTime(NumberUtils.LONG_ZERO);
        messageMapper.insert(message);
    }

    /**
     * @Description: 盘点 Start Error 处理
     * @Author: bo.chen
     * @Date: 2023/10/12 13:42
     * @param task
     * @param quantity
     **/
    private void handleInventoryStartFail(TapeInventoryTask task, int quantity, String messageKey, Object... args) {
        if (Objects.nonNull(task)) {
            TapeInventorySubtask tapeInventorySubtask = new TapeInventorySubtask();
            tapeInventorySubtask.setId(SnowflakeIdWorker.getId());
            tapeInventorySubtask.setTaskId(task.getId());
            tapeInventorySubtask.setQuantity(quantity);
            tapeInventorySubtask.setCreateTime(System.currentTimeMillis());
            tapeInventorySubtask.setCreatedBy(task.getCreatedBy());
            tapeInventorySubtask.setStatus(4);
            tapeInventorySubtask.setRemark(getMessage(task.getLocal(), task.getTenantId(), messageKey, args));
            /** Save 盘点子 Task **/
            tapeInventorySubtaskMapper.insert(tapeInventorySubtask);
            LambdaUpdateWrapper<TapeInventoryTask> updateTaskWrapper = tapeInventoryTaskMapper.uw()
                    .eq(TapeInventoryTask::getId, task.getId());
            if (isTaskEnd(tapeInventorySubtask.getCreateTime(), task)) {
                /** Task 已结束 **/
                updateTaskWrapper.set(TapeInventoryTask::getProcessStatus, 4)
                        .set(TapeInventoryTask::getQuantity, quantity)
                        .set(TapeInventoryTask::getActualQuantity, null);
            } else {
                updateTaskWrapper.set(TapeInventoryTask::getProcessStatus, NumberUtils.INTEGER_ONE);
            }
            updateTaskWrapper.set(TapeInventoryTask::getUpdateTime, System.currentTimeMillis())
                    .set(TapeInventoryTask::getLastSubtaskId, tapeInventorySubtask.getId())
                    .set(TapeInventoryTask::getUpdatedBy, NumberUtils.LONG_MINUS_ONE);
            /** Update主 Task Quantity和 Status **/
            tapeInventoryTaskMapper.update(updateTaskWrapper);
        }
    }

    /**
     * @Description: 盘点结束Failed处理
     * @Author: bo.chen
     * @Date: 2023/10/17 13:28
     * @param subtask
     * @param task
     * @param messageKey
     * @param args
     **/
    private void handleInventoryEndFail(TapeInventorySubtask subtask, TapeInventoryTask task, String messageKey,
            Object... args) {
        if (Objects.isNull(subtask) || Objects.isNull(task)) {
            return;
        }
        /** Retrieve Time 盘点 Quantity **/
        long actualQuantity = redisUtils.getHashSize(RedisKey.READER_TASK_LABEL + task.getId());
        /** Update子 Task Information **/
        tapeInventorySubtaskMapper.update(tapeInventorySubtaskMapper.uw()
                .eq(TapeInventorySubtask::getId, subtask.getId())
                .set(TapeInventorySubtask::getActualQuantity, actualQuantity)
                .set(TapeInventorySubtask::getStatus, 4)
                .set(TapeInventorySubtask::getRemark, getMessage(task.getLocal(), task.getTenantId(), messageKey, args))
                .set(TapeInventorySubtask::getUpdateTime, System.currentTimeMillis())
                .set(TapeInventorySubtask::getUpdatedBy, NumberUtils.INTEGER_MINUS_ONE));
        Integer processStatus;
        if (isTaskEnd(subtask.getCreateTime(), task)) {
            /** Task 结束 **/
            processStatus = 4;
        } else {
            /** Task To Be Started **/
            processStatus = 1;
        }
        /** Update主 Task Quantity和 Status **/
        tapeInventoryTaskMapper.update(tapeInventoryTaskMapper.uw()
                .eq(TapeInventoryTask::getId, subtask.getTaskId())
                .set(TapeInventoryTask::getProcessStatus, processStatus)
                .set(TapeInventoryTask::getActualQuantity, actualQuantity)
                .set(TapeInventoryTask::getUpdateTime, System.currentTimeMillis())
                .set(TapeInventoryTask::getUpdatedBy, NumberUtils.LONG_MINUS_ONE));
    }

    /**
     * @Description: 处理盘点 Task
     * @Author: bo.chen
     * @Date: 2023/9/25 16:26
     * @param subtask
     * @param task
     * @param actualQuantity
     * @param isDiscrepancy
     **/
    private void handleInventoryTask(TapeInventorySubtask subtask, TapeInventoryTask task, int actualQuantity,
            boolean isDiscrepancy) {
        if (Objects.isNull(subtask) || Objects.isNull(task)) {
            return;
        }
        /** Update子 Task Information **/
        tapeInventorySubtaskMapper.update(tapeInventorySubtaskMapper.uw()
                .eq(TapeInventorySubtask::getId, subtask.getId())
                .set(TapeInventorySubtask::getIsDiscrepancy, isDiscrepancy)
                .set(TapeInventorySubtask::getActualQuantity, actualQuantity)
                .set(TapeInventorySubtask::getStatus, isDiscrepancy ? NumberUtils.INTEGER_ONE : NumberUtils.INTEGER_TWO)
                .set(TapeInventorySubtask::getUpdateTime, System.currentTimeMillis())
                .set(TapeInventorySubtask::getUpdatedBy, NumberUtils.INTEGER_MINUS_ONE));
        Integer processStatus;
        if (isDiscrepancy) {
            /** 等待处理差异 **/
            processStatus = 3;
        } else if (isTaskEnd(subtask.getCreateTime(), task)) {
            /** Task 结束 **/
            processStatus = 4;
        } else {
            /** Task To Be Started **/
            processStatus = 1;
        }
        /** Update主 Task Quantity和 Status **/
        tapeInventoryTaskMapper.update(tapeInventoryTaskMapper.uw()
                .eq(TapeInventoryTask::getId, subtask.getTaskId())
                .set(TapeInventoryTask::getIsDiscrepancy, isDiscrepancy)
                .set(TapeInventoryTask::getProcessStatus, processStatus)
                .set(TapeInventoryTask::getActualQuantity, actualQuantity)
                .set(TapeInventoryTask::getUpdateTime, System.currentTimeMillis())
                .set(TapeInventoryTask::getUpdatedBy, NumberUtils.LONG_MINUS_ONE));
    }

    /**
     * @Description: Task Wether 结束
     * @Author: bo.chen
     * @Date: 2023/9/25 16:01
     * @param subtaskTime
     * @param inventoryTask
     * @return boolean
     **/
    private boolean isTaskEnd(Long subtaskTime, TapeInventoryTask inventoryTask) {
        if (inventoryTask.getTaskType() <= NumberUtils.INTEGER_TWO) {
            return Boolean.TRUE;
        }
        if (Objects.nonNull(inventoryTask.getEndTime())) {
            /** 主 Task Start Time **/
            LocalDateTime taskStartTime = DateUtil.toLocalDateTime(inventoryTask.getStartTime());
            /** 下次 Task Start Time **/
            LocalDateTime nextStartTime = DateUtil.toLocalDateTime(subtaskTime);
            /** 分钟跟主 Task Start Time 同步 **/
            nextStartTime.withMinute(taskStartTime.getMinute());
            /** 秒数跟主 Task Start Time 同步 **/
            nextStartTime.withSecond(taskStartTime.getSecond());
            if (inventoryTask.getIntervalUnit() == 1) {
                nextStartTime.plusHours(inventoryTask.getTimeInterval());
            } else if (inventoryTask.getIntervalUnit() == 2) {
                nextStartTime.plusDays(inventoryTask.getTimeInterval());
            } else if (inventoryTask.getIntervalUnit() == 3) {
                nextStartTime.plusWeeks(inventoryTask.getTimeInterval());
            } else {
                nextStartTime.plusMonths(inventoryTask.getTimeInterval());
            }
            return nextStartTime.isAfter(DateUtil.toLocalDateTime(inventoryTask.getEndTime()));
        }
        return Boolean.FALSE;
    }

    /**
     * @Description: Retrieve库存 Label Data
     * @Author: bo.chen
     * @Date: 2023/9/22 15:34
     * @param locationId
     * @param libraryIds
     * @return java.util.Map<java.lang.String,
     *         com.matariky.jobs.jobsService.assetitm.stock.vo.TapeStockLabelVo>
     **/
    private Map<String, TapeStockLabelVo> getStockLabel(String locationId, List<Long> libraryIds) {
        /** Retrieve库存已存在的 Label **/
        List<TapeStockLabelVo> stockLabelList = tapeStockMapper.selectStockLabelList(locationId, libraryIds);
        if (CollectionUtils.isNotEmpty(stockLabelList)) {
            return stockLabelList.stream()
                    .collect(Collectors.toMap(TapeStockLabelVo::getEpc, Function.identity(), (k1, k2) -> k1));
        } else {
            return Collections.emptyMap();
        }
    }

    /**
     * @Description: Retrieve Task 的 Library 的锁KEY
     * @Author: bo.chen
     * @Date: 2023/9/25 17:37
     * @param lockPoint
     * @return java.util.Set<java.lang.Long>
     **/
    public Set<String> getRackLockKeys(LockPoint lockPoint) {
        Long taskId;
        if ("start".equals(lockPoint.getMethod().getName())) {
            taskId = (Long) lockPoint.getArgs()[NumberUtils.INTEGER_ZERO];
        } else {
            taskId = tapeInventorySubtaskMapper.selectColumnValue((Long) lockPoint.getArgs()[NumberUtils.INTEGER_ZERO],
                    TapeInventorySubtask::getTaskId);
        }
        TapeInventoryTask task = tapeInventoryTaskMapper.selectById(taskId);
        if (Objects.nonNull(task)) {
            if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(task.getLibraryIds())) {
                return task.getLibraryIds().stream().map(rackId -> LockKey.TAPE_LIBRARY_LOCK_KEY + rackId)
                        .collect(Collectors.toSet());
            } else {
                List<Long> rackIdList = tapeRackMapper.selectColumnValues(tapeRackMapper.qw()
                        .likeRight(TapeRack::getLocationId, task.getLocationId())
                        .eq(TapeRack::getDeleteTime, NumberUtils.INTEGER_ZERO), TapeRack::getId);
                if (CollectionUtils.isNotEmpty(rackIdList)) {
                    return rackIdList.stream().map(rackId -> LockKey.TAPE_LIBRARY_LOCK_KEY + rackId)
                            .collect(Collectors.toSet());
                }
            }
        }
        return Collections.emptySet();
    }

    /**
     * @Description: 取消差异 Task
     * @Author: bo.chen
     * @Date: 2023/10/16 10:11
     * @param locationId
     * @param rackIds
     **/
    private void cancelDiscrepancySubtask(String locationId, List<Long> rackIds, String taskName) {
        List<TapeInventorySubtask> subtaskList = tapeInventorySubtaskMapper.selectDiscrepancySubtask(
                locationId.split(",")[NumberUtils.INTEGER_ZERO],
                locationId, CollectionUtils.isNotEmpty(rackIds) ? JacksonUtils.toJsonString(rackIds) : null);
        if (CollectionUtils.isNotEmpty(subtaskList)) {
            subtaskList.stream().forEach(subtask -> {
                TapeInventoryTask task = tapeInventoryTaskMapper.selectById(subtask.getTaskId());
                /** 取消子 Task **/
                tapeInventorySubtaskMapper
                        .update(tapeInventorySubtaskMapper.uw().eq(TapeInventorySubtask::getId, subtask.getId())
                                .eq(TapeInventorySubtask::getStatus, NumberUtils.LONG_ONE)
                                .set(TapeInventorySubtask::getActualQuantity, null)
                                .set(TapeInventorySubtask::getStatus, 4)
                                .set(TapeInventorySubtask::getRemark,
                                        getMessage(task.getLocal(), task.getTenantId(),
                                                MessageKey.TAPE_INVENTORY_DISCREPANCY_CONFLICT, taskName))
                                .set(TapeInventorySubtask::getUpdatedBy, NumberUtils.LONG_MINUS_ONE)
                                .set(TapeInventorySubtask::getUpdateTime, System.currentTimeMillis()));
                LambdaUpdateWrapper<TapeInventoryTask> updateTaskWrapper = tapeInventoryTaskMapper.uw()
                        .eq(TapeInventoryTask::getId, task.getId());
                /** 盘点 Task Wether 已结束 **/
                if (isTaskEnd(subtask.getCreateTime(), task)) {
                    updateTaskWrapper.set(TapeInventoryTask::getActualQuantity, null);
                    updateTaskWrapper.set(TapeInventoryTask::getProcessStatus, 4);
                } else {
                    updateTaskWrapper.set(TapeInventoryTask::getProcessStatus, 1);
                    updateTaskWrapper.set(TapeInventoryTask::getQuantity, null);
                    updateTaskWrapper.set(TapeInventoryTask::getActualQuantity, null);
                    updateTaskWrapper.set(TapeInventoryTask::getIsDiscrepancy, null);
                }
                updateTaskWrapper.set(TapeInventoryTask::getUpdatedBy, NumberUtils.LONG_MINUS_ONE);
                updateTaskWrapper.set(TapeInventoryTask::getUpdateTime, System.currentTimeMillis());
                updateTaskWrapper.eq(TapeInventoryTask::getProcessStatus, 3);
                tapeInventoryTaskMapper.update(updateTaskWrapper);
            });
        }
    }

    /**
     * @Description: Retrieve Message
     * @Author: bo.chen
     * @Date: 2023/10/16 16:28
     * @param local
     * @param tenantId
     * @param messageKey
     * @param args
     * @return java.lang.String
     **/
    private String getMessage(String local, String tenantId, String messageKey, Object... args) {
        CommonDict commonDict = commonDictMapper.getDictionaryItemByTypeAndKey(
                DictTypeKey.getFullKey(local, DictTypeKey.SERVICE_MESSAGE), messageKey, tenantId);
        if (Objects.nonNull(commonDict) && StringUtils.isNotEmpty(commonDict.getDictValue())) {
            if (Objects.nonNull(args) && args.length > NumberUtils.INTEGER_ZERO) {
                return String.format(commonDict.getDictValue(), args);
            } else {
                return commonDict.getDictValue();
            }
        }
        return messageKey;
    }
}
