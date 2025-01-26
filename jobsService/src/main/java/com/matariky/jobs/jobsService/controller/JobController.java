package com.matariky.jobs.jobsService.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import com.matariky.jobs.jobsService.bean.form.*;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.commondict.constant.DictTypeKey;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;
import com.matariky.jobs.jobsService.assetitm.base.job.TapeRfidCreateTaskJob;
import com.matariky.jobs.jobsService.assetitm.base.job.TapeRfidPrintTaskJob;
import com.matariky.jobs.jobsService.assetitm.base.job.TapeRfidUploadTaskJob;
import com.matariky.jobs.jobsService.assetitm.inventory.job.TapeInventoryTaskJob;
import com.matariky.jobs.jobsService.bean.domain.JobAndTrigger;
import com.matariky.jobs.jobsService.bean.form.InventoryJobForm;
import com.matariky.jobs.jobsService.bean.form.JobForm;
import com.matariky.jobs.jobsService.bean.form.RfidCreateJobForm;
import com.matariky.jobs.jobsService.bean.form.RfidPrintJobForm;
import com.matariky.jobs.jobsService.bean.form.RfidUploadJobForm;
import com.matariky.jobs.jobsService.service.JobService;

import cn.hutool.core.util.StrUtil;

/**
 * <p>
 * Job Controller
 * </p>
 *
 * @package: com.matariky.jobs.jobsService.controller
 * @description: Job Controller
 * @version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/job/v1/tenant/{tenantId}")
public class JobController {
	@Value("${message.locale}")
	 String locale;

	 @Autowired CommonDictService commonDictService;

    private final JobService jobService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    /**
     * 保存定时 Task 
     */
    @PostMapping
    public Object addJob(@Valid  JobForm form, @PathVariable("tenantId") String tenantId) {
    	Boolean jobExists=jobService.getJob(form.getJobClassName(),form.getJobGroupName());
    	if(jobExists) {
    		return new ResponseEntity<JSONObject>(commonDictService.getServiceMessage(locale+"_SERVICE_CONSTANT_MESSAGE","JOB_EXIST",false,tenantId), HttpStatus.BAD_REQUEST);
    	}

        try {
            jobService.addJob(form);
            jobService.updateTenantId(form.getJobGroupName(),form.getJobClassName(),form.getTenantId());
        }
        catch (ClassNotFoundException cnfe) {
        	throw new QslException(MessageKey.JOB_CLASS_NOT_EXIST);
        }
        catch (Exception e) {
        	JSONObject jo =new JSONObject();
        	jo.put("code",500);
   		 	jo.put("message",e.getMessage());
   		 	jo.put("success",false);
            return new ResponseEntity<JSONObject>(jo, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JSONObject>(commonDictService.getServiceMessage(locale+"_SERVICE_CONSTANT_MESSAGE","OPERATION_SUCESSFUL",true,tenantId), HttpStatus.CREATED);
    }

    /**
     * 保存盘点定时 Task 
     */
    @PostMapping("addInventoryJob")
    public Object addInventoryJob(@Valid  @RequestBody InventoryJobForm form, @PathVariable("tenantId") String tenantId) {
        Boolean jobExists = jobService.getJob(TapeInventoryTaskJob.class.getName(), form.getTaskId().toString());
        if (jobExists) {
            return new ResponseEntity<JSONObject>(commonDictService.getServiceMessage(DictTypeKey.getFullKey(locale, DictTypeKey.SERVICE_MESSAGE),"JOB_EXIST",false,tenantId), HttpStatus.BAD_REQUEST);
        }
        try {
            jobService.addInventoryJob(form);
            jobService.updateTenantId(TapeInventoryTaskJob.class.getName(),  form.getTaskId().toString(), tenantId);
        } catch (Exception e) {
            JSONObject jo =new JSONObject();
            jo.put("code",500);
            jo.put("message",e.getMessage());
            jo.put("success",false);
            return new ResponseEntity<JSONObject>(jo, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JSONObject>(commonDictService.getServiceMessage(DictTypeKey.getFullKey(locale, DictTypeKey.SERVICE_MESSAGE),"OPERATION_SUCESSFUL",true,tenantId), HttpStatus.CREATED);
    }

    /**
     * 保存 Device 升级定时 Task 
     */
    @PostMapping("addDeviceUpgradeJob")
    public void addDeviceUpgradeJob(@RequestBody DeviceUpgradeForm form){
        try {
            jobService.addDeviceUpgradeJob(form);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 订单过期定时 Task 
     * @return
     */
    @PostConstruct
    public void addOrderExpireJob (){
        try {
            BaseJobForm form =new BaseJobForm();
            form.setCronExpression("0 */1 * * * ?");
           // form.setCronExpression("0 0 2 * * ?");
            jobService.addOrderExpireJob(form);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 保存 Label  Generation 定时 Task 
     */
    @PostMapping("addRfidCreateJob")
    public Object addRfidCreateJob(@Valid  @RequestBody RfidCreateJobForm form, @PathVariable("tenantId") String tenantId) {
        Boolean jobExists = jobService.getJob(TapeRfidCreateTaskJob.class.getName(), form.getTaskId().toString());
        if (jobExists) {
            return new ResponseEntity<JSONObject>(commonDictService.getServiceMessage(DictTypeKey.getFullKey(locale, DictTypeKey.SERVICE_MESSAGE),"JOB_EXIST",false,tenantId), HttpStatus.BAD_REQUEST);
        }
        try {
            jobService.addRfidCreateJob(form);
            jobService.updateTenantId(TapeRfidCreateTaskJob.class.getName(),  form.getTaskId().toString(), tenantId);
        } catch (Exception e) {
            JSONObject jo =new JSONObject();
            jo.put("code",500);
            jo.put("message",e.getMessage());
            jo.put("success",false);
            return new ResponseEntity<JSONObject>(jo, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JSONObject>(commonDictService.getServiceMessage(DictTypeKey.getFullKey(locale, DictTypeKey.SERVICE_MESSAGE),"OPERATION_SUCESSFUL",true,tenantId), HttpStatus.CREATED);
    }

    /**
     * 保存 Label  Print 定时 Task 
     */
    @PostMapping("addRfidPrintJob")
    public Object addRfidPrintJob(@Valid  @RequestBody RfidPrintJobForm form, @PathVariable("tenantId") String tenantId) {
        Boolean jobExists = jobService.getJob(TapeRfidPrintTaskJob.class.getName(), form.getTaskId().toString());
        if (jobExists) {
            return new ResponseEntity<JSONObject>(commonDictService.getServiceMessage(DictTypeKey.getFullKey(locale, DictTypeKey.SERVICE_MESSAGE),"JOB_EXIST",false,tenantId), HttpStatus.BAD_REQUEST);
        }
        try {
            jobService.addRfidPrintJob(form);
            jobService.updateTenantId(TapeRfidPrintTaskJob.class.getName(),  form.getTaskId().toString(), tenantId);
        } catch (Exception e) {
            JSONObject jo =new JSONObject();
            jo.put("code",500);
            jo.put("message",e.getMessage());
            jo.put("success",false);
            return new ResponseEntity<JSONObject>(jo, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JSONObject>(commonDictService.getServiceMessage(DictTypeKey.getFullKey(locale, DictTypeKey.SERVICE_MESSAGE),"OPERATION_SUCESSFUL",true,tenantId), HttpStatus.CREATED);
    }

    /**
     * 保存 Label Import定时 Task 
     */
    @PostMapping("addRfidUploadJob")
    public Object addRfidUploadJob(@Valid  @RequestBody RfidUploadJobForm form, @PathVariable("tenantId") String tenantId) {
        Boolean jobExists = jobService.getJob(TapeRfidUploadTaskJob.class.getName(), form.getTaskId().toString());
        if (jobExists) {
            return new ResponseEntity<JSONObject>(commonDictService.getServiceMessage(DictTypeKey.getFullKey(locale, DictTypeKey.SERVICE_MESSAGE),"JOB_EXIST",false,tenantId), HttpStatus.BAD_REQUEST);
        }
        try {
            jobService.addRfidUploadJob(form);
            jobService.updateTenantId(TapeRfidUploadTaskJob.class.getName(),  form.getTaskId().toString(), tenantId);
        } catch (Exception e) {
            JSONObject jo =new JSONObject();
            jo.put("code",500);
            jo.put("message",e.getMessage());
            jo.put("success",false);
            return new ResponseEntity<JSONObject>(jo, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JSONObject>(commonDictService.getServiceMessage(DictTypeKey.getFullKey(locale, DictTypeKey.SERVICE_MESSAGE),"OPERATION_SUCESSFUL",true,tenantId), HttpStatus.CREATED);
    }

    /**
     * 删除定时 Task 
     */
    @DeleteMapping
    public ResponseEntity<JSONObject> deleteJob(JobForm form, @PathVariable("tenantId") String tenantId) throws SchedulerException {
        if (StrUtil.hasBlank(form.getJobGroupName(), form.getJobClassName())) {
            return new ResponseEntity<JSONObject>(commonDictService.getServiceMessage(locale+"_SERVICE_CONSTANT_MESSAGE","EMPTY_PARAMETER",false,tenantId), HttpStatus.BAD_REQUEST);
        }

        jobService.deleteJob(form);
        return new ResponseEntity<JSONObject>(commonDictService.getServiceMessage(locale+"_SERVICE_CONSTANT_MESSAGE","DELETE_SUCCESSFUL",true,tenantId), HttpStatus.OK);
    }

    /**
     * 暂停定时 Task 
     */
    @PutMapping(params = "pause")
    public ResponseEntity<JSONObject> pauseJob( JobForm form , @PathVariable("tenantId") String tenantId) throws SchedulerException {
        if (StrUtil.hasBlank(form.getJobGroupName(), form.getJobClassName())) {
            return new ResponseEntity<JSONObject>(commonDictService.getServiceMessage(locale+"_SERVICE_CONSTANT_MESSAGE","EMPTY_PARAMETER",false,tenantId), HttpStatus.BAD_REQUEST);
        }

        jobService.pauseJob(form);
        return new ResponseEntity<JSONObject>(commonDictService.getServiceMessage(locale+"_SERVICE_CONSTANT_MESSAGE","PAUSE_SUCCESSFUL",true, tenantId), HttpStatus.OK);
    }

    /**
     * 恢复定时 Task 
     */
    @PutMapping(params = "resume")
    public ResponseEntity<JSONObject> resumeJob(JobForm form,@PathVariable("tenantId")String tenantId) throws SchedulerException {
        if (StrUtil.hasBlank(form.getJobGroupName(), form.getJobClassName())) {
            return new ResponseEntity<JSONObject>(commonDictService.getServiceMessage(locale+"_SERVICE_CONSTANT_MESSAGE","EMPTY_PARAMETER",false,tenantId), HttpStatus.BAD_REQUEST);
        }

        jobService.resumeJob(form);
        return new ResponseEntity<JSONObject>(commonDictService.getServiceMessage(locale+"_SERVICE_CONSTANT_MESSAGE","RECOVERY_SUCCESSFUL",true,tenantId), HttpStatus.OK);
    }

    /**
     * 修改定时 Task ，定时 Time 
     */
    @PutMapping(params = "cron")
    public ResponseEntity<JSONObject> cronJob(@Valid JobForm form,@PathVariable("tenantId") String tenantId) {
        try {
            jobService.cronJob(form);
        } catch (Exception e) {
        	JSONObject jo =new JSONObject();
        	jo.put("code",500);
   		 	jo.put("message",e.getMessage());
   		 	jo.put("success",false);
            return new ResponseEntity<JSONObject>(jo, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<JSONObject>(commonDictService.getServiceMessage(locale+"_SERVICE_CONSTANT_MESSAGE","UPDATE_SUCCESSFUL",true,tenantId), HttpStatus.OK);
    }

    @GetMapping
    public PageInfo<JobAndTrigger> jobList(
    		 @RequestParam Map<String, Object> map,
    		@PathVariable("tenantId") String tenantId) {

    	int pageIndex=1;
		int perPage=20;

		map.put("tenantId", tenantId);

		if(map.containsKey("index")) {
			pageIndex=Integer.parseInt(map.get("index").toString());
		}

		if(map.containsKey("perPage")) {
			perPage=Integer.parseInt(map.get("perPage").toString());
		}
		PageHelper.startPage(pageIndex, perPage);

        //PageInfo<JobAndTrigger> all = jobService.list(currentPage, pageSize,tenantId);
        //return ResponseEntity.ok(ApiResponse.ok(Dict.create().set("total", all.getTotal()).set("data", all.getList())));

		List<JobAndTrigger> jobList =jobService.getJobAndTriggerAll(map);

		PageInfo<JobAndTrigger> page= new PageInfo<JobAndTrigger>(jobList);

		return page;
    }

    @GetMapping("/select")
    public List<JobAndTrigger> select(@PathVariable("tenantId") String tenantId){
        Map<String, Object> map=new HashMap<>();
        map.put("tenantId", tenantId);
        List<JobAndTrigger> jobList =jobService.getJobAndTriggerAll(map);
        return jobList;
    }

}
