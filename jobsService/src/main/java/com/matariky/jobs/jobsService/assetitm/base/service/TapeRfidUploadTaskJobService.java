package com.matariky.jobs.jobsService.assetitm.base.service;

import com.github.pagehelper.util.StringUtil;
import com.matariky.commonservice.base.bean.BasicBaseCodingRules;
import com.matariky.commonservice.base.bean.BasicBaseCreaterfidFactory;
import com.matariky.commonservice.base.bean.BasicBaseGoods;
import com.matariky.commonservice.base.bean.BasicBaseRfidInfo;
import com.matariky.commonservice.base.mapper.BasicBaseCodingRulesMapper;
import com.matariky.commonservice.base.mapper.BasicBaseCreaterfidFactoryMapper;
import com.matariky.commonservice.base.mapper.BasicBaseGoodsMapper;
import com.matariky.commonservice.base.mapper.BasicBaseRfidInfoMapper;
import com.matariky.commonservice.minio.utils.MinioUtil;
import com.matariky.jobs.jobsService.assetitm.base.bean.TapeRfidCreateParameterTask;
import com.matariky.jobs.jobsService.assetitm.base.bean.TapeRfidCreateTask;
import com.matariky.jobs.jobsService.assetitm.base.mapper.JobRfidCreateTaskMapper;
import com.matariky.jobs.jobsService.assetitm.base.vo.BasicBaseRfidfactoryCNExeclReqVo;
import com.matariky.utils.CodeUtils;
import com.matariky.utils.EasyExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TapeRfidUploadTaskJobService {

    @Autowired
    private MinioUtil minioUtil;

    @Autowired
    private BasicBaseCodingRulesMapper basicBaseCodingrulesMapper;

    @Autowired
    private JobRfidCreateTaskMapper jobRfidCreateTaskMapper;

    @Autowired
    private BasicBaseRfidInfoMapper basicBaseRfidInfoMapper;

    @Autowired
    private BasicBaseCreaterfidFactoryMapper baseCreaterfidFactoryMapper;

    @Autowired
    private BasicBaseGoodsMapper basicBaseGoodsMapper;

    /**
     * @Description: Start Task
     * @Author: chenyajun
     * @Date: 2024/2/22 15:15
     * @param taskId
     **/
    @Transactional(rollbackFor = Exception.class)
    // @Lock(keyMethod = "this.getRackLockKeys")
    public void start(Long taskId) throws Exception {

        TapeRfidCreateTask tapeRfidCreateTask = jobRfidCreateTaskMapper.getBasicBaseRfidfactoryById(taskId);
        if (tapeRfidCreateTask != null) {
            String od_content = tapeRfidCreateTask.getOdFixedContent();
            String qr_content = tapeRfidCreateTask.getQrFixedContent();
            String fileUrl = "";
            if (StringUtil.isNotEmpty(od_content)) {
                od_content = od_content + "?";
            }
            if (StringUtil.isNotEmpty(qr_content)) {
                qr_content = qr_content + "?";
            }

            BasicBaseGoods basicBaseGoods = basicBaseGoodsMapper.selectById(tapeRfidCreateTask.getGoodsId());

            List<TapeRfidCreateParameterTask> tapeRfidCreateParameterTaskList = jobRfidCreateTaskMapper
                    .getBasicBaseRfidfactoryParameterById(taskId);

            for (TapeRfidCreateParameterTask tapeRfidCreateParameterTask : tapeRfidCreateParameterTaskList) {
                BasicBaseCodingRules baseCodingRules = basicBaseCodingrulesMapper
                        .selectById(tapeRfidCreateParameterTask.getParameterContent());

                if (tapeRfidCreateParameterTask.getType() != null && "od".equals(tapeRfidCreateParameterTask.getType())
                        && StringUtil.isNotEmpty(od_content)) {
                    od_content = od_content + tapeRfidCreateParameterTask.getParameterName() + "="
                            + baseCodingRules.getRulesName() + ",";
                } else if (tapeRfidCreateParameterTask.getType() != null
                        && "qr".equals(tapeRfidCreateParameterTask.getType()) && StringUtil.isNotEmpty(qr_content)) {
                    qr_content = qr_content + tapeRfidCreateParameterTask.getParameterName() + "="
                            + baseCodingRules.getRulesName() + ",";
                }
            }

            if (StringUtil.isNotEmpty(od_content)) {
                od_content = od_content.substring(0, od_content.length() - 1);
            }
            if (StringUtil.isNotEmpty(qr_content)) {
                qr_content = qr_content.substring(0, qr_content.length() - 1);
            }

            String passwdStr = "";
            if (tapeRfidCreateTask.getIsLockEpc() == 1) {
                if (StringUtil.isNotEmpty(tapeRfidCreateTask.getEpcPassword())
                        && "0".equals(tapeRfidCreateTask.getEpcPassword())) {
                    passwdStr = CodeUtils.randomHexGenerator();
                }
            }

            Long rulesId = 0L;
            if (StringUtil.isNotEmpty(tapeRfidCreateTask.getEpcRule())) {
                rulesId = Long.parseLong(tapeRfidCreateTask.getEpcRule());
            }
            BasicBaseCodingRules basicBaseCodingrules = new BasicBaseCodingRules();
            if (rulesId > 0) {
                basicBaseCodingrules = basicBaseCodingrulesMapper.selectById(rulesId);
                List<BasicBaseRfidfactoryCNExeclReqVo> baseRfidfactoryCNExeclReqVos = new ArrayList<>();

                for (int i = 0; i < tapeRfidCreateTask.getCreateNum(); i++) {
                    if (tapeRfidCreateTask.getIsLockEpc() == 1) {
                        if (StringUtil.isNotEmpty(tapeRfidCreateTask.getEpcPassword())
                                && "1".equals(tapeRfidCreateTask.getEpcPassword())) {
                            passwdStr = CodeUtils.randomHexGenerator();
                        }
                    }

                    String epcStr = basicBaseCodingrules.getUniqueCode() + CodeUtils.generateEpc(
                            basicBaseCodingrules.getCodingLength() - basicBaseCodingrules.getUniqueCode().length());
                    BasicBaseRfidInfo baseRfidInfo = new BasicBaseRfidInfo();
                    baseRfidInfo.setEpc(epcStr);
                    baseRfidInfo.setPassword(passwdStr);
                    baseRfidInfo.setOdContent(od_content);
                    baseRfidInfo.setQrContent(qr_content);
                    baseRfidInfo.setCreateBy(tapeRfidCreateTask.getCreateBy());
                    baseRfidInfo.setUpdateBy(tapeRfidCreateTask.getUpdateBy());
                    baseRfidInfo.setDeleteTime(tapeRfidCreateTask.getDeleteTime());
                    baseRfidInfo.setCreateTime(tapeRfidCreateTask.getCreateTime());
                    baseRfidInfo.setUpdateTime(tapeRfidCreateTask.getUpdateTime());
                    baseRfidInfo.setOperatorOrgCode(tapeRfidCreateTask.getOperatorOrgCode());
                    baseRfidInfo.setOperatorSelfOrgCode(tapeRfidCreateTask.getOperatorSelfOrgCode());
                    baseRfidInfo.setTenantId(tapeRfidCreateTask.getTenantId());

                    basicBaseRfidInfoMapper.createBasicBaseRfidInfo(baseRfidInfo);

                    BasicBaseRfidfactoryCNExeclReqVo baseRfidfactoryCNExeclReqVo = new BasicBaseRfidfactoryCNExeclReqVo();

                    baseRfidfactoryCNExeclReqVo.setEPC(epcStr);
                    baseRfidfactoryCNExeclReqVo.setPassword(passwdStr);
                    baseRfidfactoryCNExeclReqVo.setOdContent(od_content);
                    baseRfidfactoryCNExeclReqVo.setQrContent(qr_content);
                    if (basicBaseGoods != null) {
                        baseRfidfactoryCNExeclReqVo.setGoodsCode(basicBaseGoods.getGoodsCode());
                        baseRfidfactoryCNExeclReqVo.setGoodsName(basicBaseGoods.getGoodsName());
                    }
                    baseRfidfactoryCNExeclReqVos.add(baseRfidfactoryCNExeclReqVo);

                    BasicBaseCreaterfidFactory basicBaseCreaterfidFactory = new BasicBaseCreaterfidFactory();
                    basicBaseCreaterfidFactory.setRfidfactoryId(tapeRfidCreateTask.getId());
                    basicBaseCreaterfidFactory.setGoodsId(tapeRfidCreateTask.getGoodsId());
                    basicBaseCreaterfidFactory.setRfidId(baseRfidInfo.getId());
                    basicBaseCreaterfidFactory.setCreateBy(tapeRfidCreateTask.getCreateBy());
                    basicBaseCreaterfidFactory.setUpdateBy(tapeRfidCreateTask.getUpdateBy());
                    basicBaseCreaterfidFactory.setDeleteTime(tapeRfidCreateTask.getDeleteTime());
                    basicBaseCreaterfidFactory.setCreateTime(tapeRfidCreateTask.getCreateTime());
                    basicBaseCreaterfidFactory.setUpdateTime(tapeRfidCreateTask.getUpdateTime());
                    basicBaseCreaterfidFactory.setOperatorOrgCode(tapeRfidCreateTask.getOperatorOrgCode());
                    basicBaseCreaterfidFactory.setOperatorSelfOrgCode(tapeRfidCreateTask.getOperatorSelfOrgCode());
                    basicBaseCreaterfidFactory.setTenantId(tapeRfidCreateTask.getTenantId());
                    baseCreaterfidFactoryMapper.createBasicBaseCreaterfidFactory(basicBaseCreaterfidFactory);

                }
                EasyExcelUtil.exportExcelUploadPath(
                        tapeRfidCreateTask.getTaskName() + tapeRfidCreateTask.getTaskBatchCode(), "sheet",
                        BasicBaseRfidfactoryCNExeclReqVo.class, baseRfidfactoryCNExeclReqVos, minioUtil);

                fileUrl = "api/v1/tenant/1/file/downloadFile?bucket=rfidfactory&objectName="
                        + tapeRfidCreateTask.getTaskName() + tapeRfidCreateTask.getTaskBatchCode() + ".xlsx";

            }
            if (StringUtil.isNotEmpty(fileUrl)) {
                tapeRfidCreateTask.setIsFileCreate(1);
                tapeRfidCreateTask.setDownloadUrl(fileUrl);
                tapeRfidCreateTask.setTaskStatus(1);
                jobRfidCreateTaskMapper.updateBasicBaseRfidfactory(tapeRfidCreateTask);
            }
        }
    }

}
