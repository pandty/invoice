package com.invoice.web.controller.common;

import com.invoice.common.config.Global;
import com.invoice.common.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author lijialin
 * @description 公共
 * @date 2020/6/29
 */
@RequestMapping("/common")
@Controller
public class CommonController extends BaseController {

    /**
     * 文件下载
     * @return
     */
    @RequestMapping(value = "/download")
    @ResponseBody
    public String downExcel(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String filename = request.getParameter("fileName");
        String isDelete = request.getParameter("delete");
        String downloadPath = Global.getDownloadPath() + filename;
        // 如果文件名不为空，则进行下载
        if (filename != null) {
            File file = new File(downloadPath);
            // 如果文件存在，则进行下载
            if (file.exists()) {
                // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    os.flush();
                    return "successfully";

                } catch (Exception e) {
                    System.out.println("Download  failed!");
                    return "failed";

                } finally {
                    //关闭文件流
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if ("true".equals(isDelete)){//删除源文件
                        file.delete();
                    }
                }
            }
        }
        return "文件不存在";
    }
}
