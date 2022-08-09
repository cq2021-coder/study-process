package com.cq.studyprocess.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 公共服务
 *
 * @author 程崎
 * @since 2022/08/08
 */
public interface CommonService {

    /**
     * 上传
     *
     * @param file 文件
     * @return {@link String}
     */
    String upload(MultipartFile file);

    /**
     * 下载
     *
     * @param name     名字
     * @param response 响应
     * @throws IOException ioexception
     */
    void download(String name, HttpServletResponse response) throws IOException;

    /**
     * 删除文件
     *
     * @param name 名字
     * @return boolean
     */
    boolean deleteFile(String name);


}
