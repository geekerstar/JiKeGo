package com.jikego.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Geekerstar(jikewenku.com)
 * @Date: 2018/6/23 16:15
 * @Description:
 */
public interface IFileService {
    String upload(MultipartFile file, String path);
}
