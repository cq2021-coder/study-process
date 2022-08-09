package com.cq.studyprocess.service.impl;

import com.cq.studyprocess.exception.BusinessCode;
import com.cq.studyprocess.exception.BusinessException;
import com.cq.studyprocess.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 公共服务impl
 *
 * @author 程崎
 * @since 2022/08/08
 */
@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

    @Value("${file.path}")
    private String basePath;

    @Override
    public String upload(MultipartFile file) {
        log.info("basePath:{}", basePath);
        String originalFilename = file.getOriginalFilename();
        if (ObjectUtils.isEmpty(originalFilename)) {
            throw new BusinessException(BusinessCode.FILE_ERROR, "文件名为空！");
        }
        File catalogue = new File(basePath);
        if (!catalogue.exists()) {
            if (!catalogue.mkdirs()) {
                log.warn("false catalogue：{}", catalogue.getName());
                throw new BusinessException(BusinessCode.FILE_ERROR, "创建根目录错误！");
            }
        }
        String fileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String pathName = basePath + fileName;
        log.info("the file in {}", pathName);
        try {
            file.transferTo(new File(pathName));
        } catch (IOException e) {
            throw new BusinessException(BusinessCode.FILE_ERROR, "网络异常！请检查网络后重试！");
        }

        return fileName;
    }

    @Override
    public void download(String name, HttpServletResponse response) throws IOException {
        response.setContentType("/image/jpeg");
        ServletOutputStream outputStream = null;
        try (FileInputStream inputStream = new FileInputStream(basePath + name)) {
             outputStream = response.getOutputStream();
            int len;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
        } catch (IOException e) {
            throw new BusinessException(BusinessCode.FILE_ERROR, "文件读取时错误！");
        }finally {
            assert outputStream != null;
            outputStream.close();
        }
    }

    @Override
    public boolean deleteFile(String name) {
        return new File(basePath + name).delete();
    }
}
