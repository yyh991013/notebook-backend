package com.notebook.notebookbackend.controller;

import com.notebook.notebookbackend.dto.RecordAddDTO;
import com.notebook.notebookbackend.dto.RecordEditDTO;
import com.notebook.notebookbackend.error.BusinessException;
import com.notebook.notebookbackend.response.CommonResponse;
import com.notebook.notebookbackend.service.RecordService;
import com.notebook.notebookbackend.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/record")
public class RecordController {
    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/record/all")
    public CommonResponse getAll(HttpServletRequest request) throws BusinessException {
        String userNameFromToken = TokenUtil.getUserNameFromToken(request.getHeader(TokenUtil.getTokenHeader()));
        return CommonResponse.create(recordService.getAll(userNameFromToken));
    }

    @PostMapping("/record/get")
    public CommonResponse get(@RequestParam(name = "id") int id) throws BusinessException {
        return CommonResponse.create(recordService.get(id));
    }

    @PostMapping("/record/add")
    public CommonResponse add(HttpServletRequest request, @RequestBody RecordAddDTO recordAddData) throws BusinessException {
        String userNameFromToken = TokenUtil.getUserNameFromToken(request.getHeader(TokenUtil.getTokenHeader()));
        recordService.add(recordAddData, userNameFromToken);
        return CommonResponse.success();
    }

    @PostMapping("/record/edit")
    public CommonResponse edit(@RequestBody RecordEditDTO recordEditData) throws BusinessException {
        recordService.edit(recordEditData);
        return CommonResponse.success();
    }

    @PostMapping("/record/remove")
    public CommonResponse remove(@RequestParam(name = "id") int id) throws BusinessException {
        recordService.remove(id);
        return CommonResponse.success();
    }
}
