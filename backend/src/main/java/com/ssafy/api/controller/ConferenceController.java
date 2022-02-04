package com.ssafy.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "화상 및 출결 API", tags = {"Conference"})
@RestController
@RequestMapping("/api/v1")
public class ConferenceController {

    @PostMapping("/conference/create")
    @ApiOperation(value = "수업 시작/종료/인정시간 입력", notes = "")
}
