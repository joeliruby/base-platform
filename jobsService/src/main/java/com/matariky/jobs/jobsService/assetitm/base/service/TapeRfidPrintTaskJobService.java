package com.matariky.jobs.jobsService.assetitm.base.service;

import com.github.pagehelper.util.StringUtil;
import com.matariky.commonservice.base.bean.BasicBaseCodingRules;
import com.matariky.commonservice.base.bean.BasicBaseCreaterfidPrint;
import com.matariky.commonservice.base.bean.BasicBaseRfidInfo;
import com.matariky.commonservice.base.mapper.BasicBaseCodingRulesMapper;
import com.matariky.commonservice.base.mapper.BasicBaseCreaterfidPrintMapper;
import com.matariky.commonservice.base.mapper.BasicBaseRfidInfoMapper;
import com.matariky.jobs.jobsService.assetitm.base.bean.*;
import com.matariky.jobs.jobsService.assetitm.base.mapper.JobRfidPrintTaskMapper;
import com.matariky.utils.CodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TapeRfidPrintTaskJobService {


    private final static Logger logger = LoggerFactory.getLogger(TapeRfidCreateTaskJobService.class);

    @Autowired
    private BasicBaseCodingRulesMapper basicBaseCodingrulesMapper;

    @Autowired
    private JobRfidPrintTaskMapper jobRfidPrintTaskMapper;

    @Autowired
    private BasicBaseRfidInfoMapper basicBaseRfidInfoMapper;

    @Autowired
    private BasicBaseCreaterfidPrintMapper basicBaseCreaterfidPrintMapper;

    /**
     * @Description:  开始 Task 
     * @Author: chenyajun
     * @Date: 2024/2/22 15:15
     * @param taskId
     **/
    @Transactional(rollbackFor = Exception.class)
    //@Lock(keyMethod = "this.getRackLockKeys")
    public void start(Long taskId) throws Exception {

        TapeRfidPrintTask tapeRfidPrintTask = jobRfidPrintTaskMapper.getBasicBaseRfidPrintById(taskId);
        if(tapeRfidPrintTask!=null) {
            String od_content = tapeRfidPrintTask.getOdFixedContent();
            String qr_content = tapeRfidPrintTask.getQrFixedContent();

            if (StringUtil.isNotEmpty(od_content)) {
                od_content = od_content + "?";
            }
            if (StringUtil.isNotEmpty(qr_content)) {
                qr_content = qr_content + "?";
            }

            List<TapeRfidPrintParameterTask> tapeRfidCreateParameterTaskList = jobRfidPrintTaskMapper.getBasicBaseRfidPrintParameterById(taskId);

            for (TapeRfidPrintParameterTask tapeRfidPrintParameterTask : tapeRfidCreateParameterTaskList) {
                BasicBaseCodingRules baseCodingRules = basicBaseCodingrulesMapper.selectById(tapeRfidPrintParameterTask.getParameterContent());

                if (tapeRfidPrintParameterTask.getType() != null && "od".equals(tapeRfidPrintParameterTask.getType()) && baseCodingRules!=null) {
                    od_content = od_content + tapeRfidPrintParameterTask.getParameterName() + "=" + baseCodingRules.getUniqueCode()+ CodeUtils.generateEpc(baseCodingRules.getCodingLength()-baseCodingRules.getUniqueCode().length())+ ",";
                } else if (tapeRfidPrintParameterTask.getType() != null && "qr".equals(tapeRfidPrintParameterTask.getType()) && baseCodingRules!=null) {
                    qr_content = qr_content + tapeRfidPrintParameterTask.getParameterName() + "=" + baseCodingRules.getUniqueCode()+ CodeUtils.generateEpc(baseCodingRules.getCodingLength()-baseCodingRules.getUniqueCode().length()) + ",";
                }
            }

            if (StringUtil.isNotEmpty(od_content)) {
                od_content = od_content.substring(0, od_content.length() - 1);
            }
            if (StringUtil.isNotEmpty(qr_content)) {
                qr_content = qr_content.substring(0, qr_content.length() - 1);
            }


            String passwdStr = "";
            if (tapeRfidPrintTask.getIsLockEpc() == 1) {
                if (StringUtil.isNotEmpty(tapeRfidPrintTask.getEpcPassword()) && "0".equals(tapeRfidPrintTask.getEpcPassword())) {
                    passwdStr = CodeUtils.randomHexGenerator();
                }
            }

            Long rulesId=0L;
            if(StringUtil.isNotEmpty(tapeRfidPrintTask.getEpcRule())){
                rulesId=Long.parseLong(tapeRfidPrintTask.getEpcRule());
            }
            BasicBaseCodingRules basicBaseCodingrules=new BasicBaseCodingRules();
            if(rulesId>0){
                basicBaseCodingrules=basicBaseCodingrulesMapper.selectById(rulesId);
            }

            for(int i=0;i<tapeRfidPrintTask.getPrintNum();i++){
                BasicBaseRfidInfo baseRfidInfo=new BasicBaseRfidInfo();

                String epcStr="";
                if(rulesId>0) {
                    epcStr = basicBaseCodingrules.getUniqueCode()+ CodeUtils.generateEpc(basicBaseCodingrules.getCodingLength()-basicBaseCodingrules.getUniqueCode().length());
                    BasicBaseRfidInfo rfidInfo=basicBaseRfidInfoMapper.getBasicBaseRfidInfoByEpc(epcStr);
                    if(rfidInfo!=null){
                        i--;
                        continue;
                    }

                    baseRfidInfo.setEpc(epcStr);

                    if(tapeRfidPrintTask.getIsLockEpc()==1){
                        if(StringUtil.isNotEmpty(tapeRfidPrintTask.getEpcPassword()) && "1".equals(tapeRfidPrintTask.getEpcPassword())){
                            passwdStr=CodeUtils.randomHexGenerator();
                        }
                    }
                    if(StringUtil.isNotEmpty(passwdStr)) {
                        baseRfidInfo.setPassword(passwdStr);
                    }

                    baseRfidInfo.setOdContent(od_content);
                    baseRfidInfo.setQrContent(qr_content);
                    baseRfidInfo.setCreateBy(tapeRfidPrintTask.getCreateBy());
                    baseRfidInfo.setUpdateBy(tapeRfidPrintTask.getUpdateBy());
                    baseRfidInfo.setDeleteTime(tapeRfidPrintTask.getDeleteTime());
                    baseRfidInfo.setCreateTime(tapeRfidPrintTask.getCreateTime());
                    baseRfidInfo.setUpdateTime(tapeRfidPrintTask.getUpdateTime());
                    baseRfidInfo.setOperatorOrgCode(tapeRfidPrintTask.getOperatorOrgCode());
                    baseRfidInfo.setOperatorSelfOrgCode(tapeRfidPrintTask.getOperatorSelfOrgCode());
                    baseRfidInfo.setTenantId(tapeRfidPrintTask.getTenantId());

                    basicBaseRfidInfoMapper.createBasicBaseRfidInfo(baseRfidInfo);
                }



                TapeRfidPrintDetailTask tapeRfidPrintDetailTask=new TapeRfidPrintDetailTask();
                tapeRfidPrintDetailTask.setPrintId(tapeRfidPrintTask.getId());
                tapeRfidPrintDetailTask.setRfidId(baseRfidInfo.getId());
                tapeRfidPrintDetailTask.setEpc(epcStr);
                tapeRfidPrintDetailTask.setIsPrint(0);
                tapeRfidPrintDetailTask.setCreateBy(tapeRfidPrintTask.getCreateBy());
                tapeRfidPrintDetailTask.setUpdateBy(tapeRfidPrintTask.getUpdateBy());
                tapeRfidPrintDetailTask.setDeleteTime(tapeRfidPrintTask.getDeleteTime());
                tapeRfidPrintDetailTask.setCreateTime(tapeRfidPrintTask.getCreateTime());
                tapeRfidPrintDetailTask.setUpdateTime(tapeRfidPrintTask.getUpdateTime());
                tapeRfidPrintDetailTask.setOperatorOrgCode(tapeRfidPrintTask.getOperatorOrgCode());
                tapeRfidPrintDetailTask.setOperatorSelfOrgCode(tapeRfidPrintTask.getOperatorSelfOrgCode());
                jobRfidPrintTaskMapper.createBasicBaseRfidprintDetail(tapeRfidPrintDetailTask);


                BasicBaseCreaterfidPrint basicBaseCreaterfidPrint=new BasicBaseCreaterfidPrint();
                basicBaseCreaterfidPrint.setPrintId(tapeRfidPrintTask.getId());
                basicBaseCreaterfidPrint.setGoodsId(tapeRfidPrintTask.getGoodsId());
                basicBaseCreaterfidPrint.setRfidId(baseRfidInfo.getId());
                basicBaseCreaterfidPrint.setCreateBy(tapeRfidPrintTask.getCreateBy());
                basicBaseCreaterfidPrint.setUpdateBy(tapeRfidPrintTask.getUpdateBy());
                basicBaseCreaterfidPrint.setDeleteTime(tapeRfidPrintTask.getDeleteTime());
                basicBaseCreaterfidPrint.setCreateTime(tapeRfidPrintTask.getCreateTime());
                basicBaseCreaterfidPrint.setUpdateTime(tapeRfidPrintTask.getUpdateTime());
                basicBaseCreaterfidPrint.setOperatorOrgCode(tapeRfidPrintTask.getOperatorOrgCode());
                basicBaseCreaterfidPrint.setOperatorSelfOrgCode(tapeRfidPrintTask.getOperatorSelfOrgCode());
                basicBaseCreaterfidPrint.setTenantId(tapeRfidPrintTask.getTenantId());
                basicBaseCreaterfidPrintMapper.createBasicBaseCreaterfidPrint(basicBaseCreaterfidPrint);

            }
        }
    }
}
