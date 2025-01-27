package com.matariky.commonservice.base.controller;

import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.base.service.BasicBaseGoodsBatchService;
import com.matariky.commonservice.base.vo.*;
import com.matariky.utils.AjaxResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controller
 *
 * @author AUTOMATION
 */
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
public class BasicBaseGoodsBatchController {

    @Value("${message.locale}")
    String locale;
    @Autowired
    private BasicBaseGoodsBatchService basicBaseGoodsBatchService;

    /**
     * Pagination
     *
     * @param vo
     * @return
     */
    @GetMapping(value = "/basicBaseGoodsBatch/list")
    public AjaxResult list(@Validated BasicBaseGoodsBatchListVO vo) {
        PageInfo<BasicBaseGoodsBatchResVO> page = new PageInfo<>(
                basicBaseGoodsBatchService.getBasicBaseGoodsBatchAll(vo));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

    /**
     * Save Operation
     *
     * @param vo
     * @return
     */
    @PostMapping(value = "/basicBaseGoodsBatch")
    public AjaxResult save(@RequestBody BasicBaseGoodsBatchAddVO vo) {
        basicBaseGoodsBatchService.createBasicBaseGoodsBatch(vo);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    /**
     * Update Operation
     *
     * @param updateVO
     * @return
     */
    @PutMapping(value = "/basicBaseGoodsBatch")
    public AjaxResult update(@RequestBody BasicBaseGoodsBatchUpdateVO updateVO) {
        basicBaseGoodsBatchService.updateBasicBaseGoodsBatch(updateVO);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    /**
     * Delete Operation
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/basicBaseGoodsBatch/{id}")
    public AjaxResult del(@PathVariable Long id) {
        basicBaseGoodsBatchService.delBasicBaseGoodsBatchById(id);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    /**
     * Query One Detail Operation
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/basicBaseGoodsBatch/{basicBaseGoodsBatchId}")
    public AjaxResult getOne(@PathVariable("basicBaseGoodsBatchId") Long id) {
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS,
                basicBaseGoodsBatchService.getBasicBaseGoodsBatchById(id));
    }

    @ApiOperation(" Batch Binding")
    @PostMapping(value = "/basicBaseGoodsBatch/rfidBinding")
    public AjaxResult batchBind(@RequestBody @Validated BatchGoodsBindVO vo) {
        basicBaseGoodsBatchService.batchGoodsBind(vo);
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
    }

    /**
     * Item Batch Detail
     *
     * @param vo
     * @return
     */
    @GetMapping(value = "/basicBaseGoodsBatch/info")
    public AjaxResult goodsBatchInfo(@Validated BatchInfoVO vo) {
        PageInfo<GoodsBatchInfoVO> page = new PageInfo<>(basicBaseGoodsBatchService.goodsBatchInfo(vo));
        return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
    }

}
