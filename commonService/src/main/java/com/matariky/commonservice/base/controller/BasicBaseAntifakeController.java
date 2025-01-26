package com.matariky.commonservice.base.controller;

import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.bean.BasicBaseAntifake;
import com.matariky.commonservice.base.service.BasicBaseAntifakeService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;
import com.matariky.redis.RedisUtils;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.TokenUtils;
import io.jsonwebtoken.io.IOException;
import io.swagger.annotations.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Controller
 *
 * @author AUTOMATION
 */
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
@Api(value = "防伪认证 Information ", tags = "防伪认证 Information ")
public class BasicBaseAntifakeController {

    @Value("${message.locale}")
    String locale;
    @Autowired
    private BasicBaseAntifakeService basicBaseAntifakeService;
    @Autowired
    RedisUtils redisUtils;
    @Value("${iot.url}")
    private String iotUrl;


    @ApiOperation("列表")
    @GetMapping("/basicBaseAntifake/list")
    public AjaxResult list(BasicBaseAntifake bean, @ApiParam(value = "Page Index", required = true) @RequestParam("index") int pageIndex, @ApiParam(value = "Page Size", required = true) @RequestParam("perPage") int perPage) {
        if (com.matariky.utils.StringUtils.isNotEmpty(bean.getValidationTimeStart())) {
            bean.setValidationTimeEnd(bean.getValidationTimeStart() + " 00:00:00");
        }
        if (com.matariky.utils.StringUtils.isNotEmpty(bean.getValidationTimeEnd())) {
            bean.setValidationTimeEnd(bean.getValidationTimeEnd() + " 23:59:59");
        }
        PageInfo<BasicBaseAntifake> page = new PageInfo<BasicBaseAntifake>(basicBaseAntifakeService.getBasicBaseAntifakeAll(bean, pageIndex, perPage));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }


    @ApiOperation("保存")
    @PostMapping(value = "/basicBaseAntifake")
    public AjaxResult save(@RequestBody BasicBaseAntifake bean) {
        try {
            basicBaseAntifakeService.createBasicBaseAntifakeWithOrg(bean);
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
        } catch (Exception e) {
            throw new QslException(e.getMessage());
        }
    }

    @ApiOperation("Update")
    @PutMapping(value = "/basicBaseAntifake")
    public AjaxResult update(@RequestBody BasicBaseAntifake bean) {
        try {
            basicBaseAntifakeService.updateBasicBaseAntifake(bean);
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
        } catch (Exception e) {
            throw new QslException(e.getMessage());
        }
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/basicBaseAntifake")
    public AjaxResult del(String id) {
        try {
            basicBaseAntifakeService.delBasicBaseAntifakeById(Long.parseLong(id));
            return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
        } catch (Exception e) {
            throw new QslException(e.getMessage());
        }
    }

    @ApiOperation("详情")
    @GetMapping(value = "/basicBaseAntifake/{basicBaseAntifakeId}")
    public AjaxResult getOne(@PathVariable("/basicBaseAntifakeId") Long id) {
        BasicBaseAntifake info = basicBaseAntifakeService.getBasicBaseAntifakeById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, info);
    }

    @PostMapping("/v0/authenticate")
    @ApiOperation(value = "Authenticate", notes = "Authenticate multiple tags Json or gzip")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "samemasterkey", value = " Wether 使用统一的密钥,默认false", required = false, dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(name = "checkforshorttid", value = " Wether 区分是短的Tid,默认false", required = false, dataType = "boolean", paramType = "query")
    }
    )
    public String authenticate2(@RequestParam(value = "samemasterkey", required = false, defaultValue = "false") boolean samemasterkey, @RequestParam(value = "checkforshorttid", required = false, defaultValue = "false") boolean checkforshorttid, @ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt, HttpServletRequest request) {
        String userId = TokenUtils.extractUserIdFromToken(jwt);
        String keycloakToken = redisUtils.getKeycloakToken(userId);
        if (StringUtils.isEmpty(keycloakToken))
            throw new QslException(MessageKey.KEYCLOAK_TOKEN_NOT_EXIST);
        HttpClient httpclient = new DefaultHttpClient();
        // Prepare a request object
        String authUrl = iotUrl + "/v0/authenticate?samemasterkey=" + samemasterkey + "&checkforshorttid=" + checkforshorttid;
        HttpPost getTokenPost = new HttpPost(authUrl);
        getTokenPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        getTokenPost.setHeader("Authorization", "Bearer " + keycloakToken);


        // Execute the request
        HttpResponse response;
        try {
            response = httpclient.execute(getTokenPost);
            if (response.getStatusLine().getStatusCode() != HttpStatus.OK.value()) {
                throw new QslException(MessageKey.KEYCLOAK_TOKEN_NOT_EXIST);
            } else {
                HttpEntity entity = response.getEntity();

                // If the response does not enclose an entity, there is no need
                // to worry about connection release
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    try {

                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(instream));
                        // do something useful with the response
                        String result = reader.readLine();
                        basicBaseAntifakeService.persistIOTAuthenticateResult(result, request, authUrl);
                        return result;

                    } catch (IOException ex) {

                        // In case of an IOException the connection will be released
                        // back to the connection manager automatically
//				         throw ex;
                        return null;
                    } catch (RuntimeException ex) {

                        // In case of an unexpected exception you may want to abort
                        // the HTTP request in order to shut down the underlying
                        // connection and release it back to the connection manager.
                        getTokenPost.abort();
//				         throw ex;
                        return null;
                    } finally {

                        // Closing the input stream will trigger connection release
                        instream.close();
                        httpclient.getConnectionManager().shutdown();
                    }

                    // When HttpClient instance is no longer needed,
                    // shut down the connection manager to ensure
                    // immediate deallocation of all system resources

                }
            }
        } catch (java.io.IOException e) {
            return null;
        }
        // Examine the response status
        // Get hold of the response entity
        return null;
    }

}
