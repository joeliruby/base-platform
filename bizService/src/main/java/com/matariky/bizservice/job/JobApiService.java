package com.matariky.bizservice.job;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.Method;
import com.matariky.exception.QslException;
import com.matariky.jobs.jobsService.bean.form.*;
import com.matariky.utils.JacksonUtils;
import com.matariky.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Objects;

@Service
@Component
public class JobApiService implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Value("${job.api.url}")
    private String baseUrl;

    public void addJob(InventoryJobForm inventoryJobForm, String tenantId, String sourceId, String token) {
        request(Method.POST, "addInventoryJob", tenantId, JacksonUtils.toJsonString(inventoryJobForm), sourceId, token);
    }

    public void addRfidJob(RfidCreateJobForm rfidJobForm, String tenantId, String sourceId, String token) {
        request(Method.POST, "addRfidCreateJob", tenantId, JacksonUtils.toJsonString(rfidJobForm), sourceId, token);
    }

    public void addRfidPrintJob(RfidPrintJobForm rfidPrintJobForm, String tenantId, String sourceId, String token) {
        request(Method.POST, "addRfidPrintJob", tenantId, JacksonUtils.toJsonString(rfidPrintJobForm), sourceId, token);
    }

    public void addRfidUploadJob(RfidUploadJobForm rfidUploadJobForm, String tenantId, String sourceId, String token) {
        request(Method.POST, "addRfidUploadJob", tenantId, JacksonUtils.toJsonString(rfidUploadJobForm), sourceId, token);
    }

    public void deleteJob(JobForm jobForm, String tenantId, String sourceId, String token) {
        request(Method.DELETE, "?jobClassName=" + jobForm.getJobClassName() + "&jobGroupName=" + jobForm.getJobGroupName(), tenantId,  JacksonUtils.toJsonString(jobForm), sourceId, token);
    }


    private void request(Method method, String methodName, String tenantId, String requestBody, String sourceId, String token) {
        String url = baseUrl + "/api/job/v1/tenant/" + tenantId + (StringUtils.isNotEmpty(methodName) ? ("/" + methodName) : StringUtils.EMPTY);
        HttpRequest httpRequest = HttpRequest.of(url).method(method);
        if (StringUtils.isNotEmpty(requestBody)) {
            httpRequest.body(requestBody);
        }
        httpRequest.header("Authorization", "Bearer " + token);
        httpRequest.header("Id", sourceId);
        String responseBody =  httpRequest.execute().body();
        Result result = JacksonUtils.toBean(responseBody, Result.class);
        if (Objects.isNull(result)) {
            throw new QslException(responseBody);
        }
        if (!result.getSuccess()) {
            throw new QslException(result.getMessage());
        }
    }
}
