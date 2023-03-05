package com.binoofactory.alma.almabe.api.common.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.binoofactory.alma.almabe.api.common.model.entity.BfFile;

public interface FileService {

    BfFile uploadAllowedFile(MultipartFile file, HttpServletRequest httpServletRequest);

    BfFile findById(long id, HttpServletRequest httpServletRequest);
}
