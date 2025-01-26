package com.matariky.bizservice.assetitm.base.bean.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.matariky.commonservice.base.mapper.BasicBaseRfidInfoMapper;
import com.matariky.commonservice.base.vo.TapeRfidCreateCNExeclReqVo;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TapeRfidCreateCNExeclListener extends AnalysisEventListener<TapeRfidCreateCNExeclReqVo> {

    private List<TapeRfidCreateCNExeclReqVo> lists;
    private StringBuffer errorMessage;

    private BasicBaseRfidInfoMapper basicBaseRfidInfoMapper;

    private String jwt;

    private HttpServletRequest request;

    private String tenantId;

    public TapeRfidCreateCNExeclListener(List<TapeRfidCreateCNExeclReqVo> lists,
                                         StringBuffer errorMessage, BasicBaseRfidInfoMapper basicBaseRfidInfoMapper, String jwt, HttpServletRequest request, String tenantId) {
        this.lists = lists;
        this.errorMessage = errorMessage;
        this.jwt = jwt;
        this.request = request;
        this.tenantId = tenantId;
        this.errorMessage = errorMessage;
        this.basicBaseRfidInfoMapper = basicBaseRfidInfoMapper;
        this.tenantId = tenantId;
    }

    @Override
    public void invoke(TapeRfidCreateCNExeclReqVo vo, AnalysisContext analysisContext) {
        StringBuffer errorInfo = validInfo(vo);
        ReadRowHolder readRowHolder = analysisContext.readRowHolder();
        Integer index = readRowHolder.getRowIndex() + 1;
        if (StringUtils.isNotEmpty(errorInfo)) {
            errorMessage.append("Row NO." + index + "has error:" + errorInfo + "\t\n");
        } else {
            lists.add(vo);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


    public StringBuffer validInfo(TapeRfidCreateCNExeclReqVo tapeRfidCreateCNExeclReqVo) {
        StringBuffer errorInfo = new StringBuffer();
        return errorInfo;
    }

}
