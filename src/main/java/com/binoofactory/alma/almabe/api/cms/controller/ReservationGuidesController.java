package com.binoofactory.alma.almabe.api.cms.controller;

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

import com.binoofactory.alma.almabe.api.cms.model.entity.ReservationGuides;
import com.binoofactory.alma.almabe.api.cms.service.ReservationGuidesService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/contents/reservation/quides")
@Api(tags = {"예약 안내"}, description = "예약 안내", basePath = "/contents/reservation/quides")
public class ReservationGuidesController {

    private final ReservationGuidesService service;

    @Autowired
    public ReservationGuidesController(ReservationGuidesService service) {
        this.service = service;
    }

    @PostMapping(value = "")
    @ApiOperation(value = "등록", notes = "등록")
    public ResponseEntity add(
        @RequestBody ReservationGuides instance) throws Exception {
        return ResponseUtil.sendResponse(service.add(instance, null));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "수정", notes = "수정")
    public ResponseEntity modify(
        @RequestBody ReservationGuides instance,
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
    public ResponseEntity<BfListResponse<ReservationGuides>> findAll(
        @RequestParam(value = "pageNo", required = false, defaultValue = "1") @ApiParam("페이지 번호") int pageNo,
        @RequestParam(value = "pageSize", required = false, defaultValue = "100") @ApiParam("한번에 보여줄 크기") int pageSize,
        @RequestParam(value = "reservationsExhibitionTypeCode", required = false, defaultValue = "") @ApiParam("예약 전시 구분 코드") String reservationsExhibitionTypeCode,
        @RequestParam(value = "reservationGuideCode", required = false, defaultValue = "") @ApiParam("예약 가이드 코드") String reservationGuideCode,
        @RequestParam(value = "paymentTypeCode", required = false, defaultValue = "") @ApiParam("결제 구분 코드") String paymentTypeCode,
        @RequestParam(value = "contents", required = false, defaultValue = "") @ApiParam("내용") String contents,
        HttpServletRequest request) throws Exception {

        return ResponseUtil.sendResponse(service.findAll(
            ReservationGuides.builder()
            .reservationsExhibitionTypeCode(reservationsExhibitionTypeCode)
            .reservationGuideCode(reservationGuideCode)
            .paymentTypeCode(paymentTypeCode)
            .contents(contents)
            .build(),
            new BfPage(pageNo, pageSize),
            request));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "단건 조회", notes = "단건 조회")
    public ResponseEntity<ReservationGuides> findUserId(
        @PathVariable("id") @ApiParam("식별번호") long id, HttpServletRequest httpServletRequest)
        throws Exception {
        return ResponseUtil.sendResponse(service.find(id, httpServletRequest));
    }
}
