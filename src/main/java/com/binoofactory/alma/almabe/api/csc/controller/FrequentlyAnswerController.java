package com.binoofactory.alma.almabe.api.csc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.binoofactory.alma.almabe.api.csc.model.FrequentlyAnswer;
import com.binoofactory.alma.almabe.api.csc.service.FrequentlyAnswerService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/faqs/recently-answers")
@Api(tags = {"자주 찾는 질문"}, description = "자주 찾는 질문", basePath = "/faqs/recently-answers")
public class FrequentlyAnswerController {

    private final FrequentlyAnswerService service;

    @Autowired
    public FrequentlyAnswerController(FrequentlyAnswerService service) {
        this.service = service;
    }

    @PostMapping(value = "")
    @ApiOperation(value = "등록", notes = "등록")
    public ResponseEntity add(
        @RequestBody FrequentlyAnswer instance) throws Exception {
        return ResponseUtil.sendResponse(service.add(instance, null));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "수정", notes = "수정")
    public ResponseEntity modify(
        @RequestBody FrequentlyAnswer instance,
        @PathVariable("id") @ApiParam("식별번호") Long id,
        HttpServletRequest httpServletRequest) throws Exception {
        instance.setId(id);
        return ResponseUtil.sendResponse(service.edit(instance, httpServletRequest));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "삭제", notes = "삭제")
    public ResponseEntity remove(
        @PathVariable("id") @ApiParam("식별번호") Long id, HttpServletRequest httpServletRequest) throws Exception {
        service.remove(id, httpServletRequest);
        return ResponseUtil.sendResponse(null);
    }

    @GetMapping(value = "")
    @ApiOperation(value = "목록 조회", notes = "목록 조회")
    public ResponseEntity<BfListResponse<FrequentlyAnswer>> findAll(
        @RequestParam(value = "pageNo", required = false, defaultValue = "1") @ApiParam("페이지 번호") int pageNo,
        @RequestParam(value = "pageSize", required = false, defaultValue = "100") @ApiParam("한번에 보여줄 크기") int pageSize,
        HttpServletRequest request) throws Exception {

        return ResponseUtil.sendResponse(service.findAll(
            FrequentlyAnswer.builder().build(),
            new BfPage(pageNo, pageSize),
            request));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "단건 조회", notes = "단건 조회")
    public ResponseEntity<FrequentlyAnswer> findUserId(
        @PathVariable("id") @ApiParam("식별번호") long id, HttpServletRequest httpServletRequest)
        throws Exception {
        return ResponseUtil.sendResponse(service.find(id, httpServletRequest));
    }
}
