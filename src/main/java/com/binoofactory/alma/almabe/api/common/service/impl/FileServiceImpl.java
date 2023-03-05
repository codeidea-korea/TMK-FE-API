package com.binoofactory.alma.almabe.api.common.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import com.binoofactory.alma.almabe.api.common.components.BfFileUploadProvider;
import com.binoofactory.alma.almabe.api.common.model.entity.BfFile;
import com.binoofactory.alma.almabe.api.common.repos.jpa.FileRepos;
import com.binoofactory.alma.almabe.api.common.service.FileService;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepos repos;

    private final DateUtil dateUtil;

    private final BfFileUploadProvider bfFileUploadProvider;

    @Autowired
    public FileServiceImpl(FileRepos repos, DateUtil dateUtil, BfFileUploadProvider bfFileUploadProvider) {
        this.repos = repos;
        this.dateUtil = dateUtil;
        this.bfFileUploadProvider = bfFileUploadProvider;
    }

    @Override
    public BfFile uploadAllowedFile(MultipartFile file, HttpServletRequest httpServletRequest) {
        BfFile inputFile;
        try {
            inputFile = bfFileUploadProvider.save(file);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "업로드에 실패하였습니다.");
        }
        return repos.save(inputFile);
    }

    @Override
    public BfFile findById(long id, HttpServletRequest httpServletRequest) {

        return repos.getById(id);
    }
}
