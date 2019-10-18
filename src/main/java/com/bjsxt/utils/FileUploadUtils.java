package com.bjsxt.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
/**
 *
 * @date 2017年9月6日
 * @author lcx
 * @Description: 文件上传处理
 */

public class FileUploadUtils {
    private static long maxFileSize = 200000000;// 默认限制为200M
    private static String encoding = "utf-8";// 编码格式
    private static List prepairedItems = null;



    /**
     * 得到文件路径
     *
     * @param fileNameWithPath
     * @return
     */
    public static String getFilePath(String fileNameWithPath) {
        String filePath = "";
        fileNameWithPath = fileNameWithPath.replace("/", File.separator);
        int idx = fileNameWithPath.lastIndexOf(File.separator);
        if (idx != -1) {
            filePath = fileNameWithPath.substring(0, idx);
        }
        return filePath;
    }


    /**
     * 将内容写入文件
     * @param context 内容
     * @param fileName 目标文件
     * @throws Exception
     */
    public static void writeFile(String context,String fileName) throws Exception{
        byte[] bytes=context.getBytes();
        File file=new File(fileName);
        FileOutputStream fop = new FileOutputStream(file);
        BufferedOutputStream out = new BufferedOutputStream(fop);
        if(!file.exists()){
            file.mkdirs();
        }
        out.write(bytes);
        out.flush();
        out.close();
    }

    /**
     * 复制文件
     *
     * @param srcFileName
     *            要复制的源文件
     * @param destFileName
     *            目标文件
     * @throws Exception
     */
    public static void copyFile(String srcFileName, String destFileName)
            throws Exception {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(new File(
                    srcFileName)));
            String destPath = getFilePath(destFileName);
            File pathFile = new File(destPath);
            if (!pathFile.exists()){
                pathFile.mkdirs();
            }
            out = new BufferedOutputStream(new FileOutputStream(new File(
                    destFileName)));
            int len;
            byte[] b = new byte[1024];
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            System.out.println("复制文件失败：" + e.getMessage());
            throw e;
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (Exception ex) {
            }
            try {
                if (out != null)
                    out.close();
            } catch (Exception ex) {
            }
        }
    }

    /**
     *
     * @param time 公文创建时间
     * @param personID 创建用户id
     * @param oldFileName 旧文件名称
     * @return
     * @throws Exception
     * 返回文件名
     * @author lcx
     */
    private static String getDocPath(long time,String personID,String oldFileName)throws Exception{
        String affix="";
        if(!StringUtils.isEmpty(oldFileName) && oldFileName.lastIndexOf(".")>-1){
            affix = oldFileName.substring(oldFileName.lastIndexOf("."));
        }
        return "doc$".concat(String.valueOf(time)).concat("$").concat(personID).concat("$").concat("newDoc")+affix;
    }

    /**
     *
     * @param request
     * @param resourcePath 源文件地址
     * @param savePath 保存地址
     * @return
     * 文件修改后上传保存，如果源文件不是空，则覆盖源文件，如果源文件为空，则创建新文件
     * @author lcx
     */
    @SuppressWarnings("null")
    public static boolean upload(HttpServletRequest request,String resourcePath,String savePath){
        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        Map<String, MultipartFile> map = multipartRequest.getFileMap();

        List<MultipartFile> fileList = new ArrayList<MultipartFile>();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            fileList.add(map.get(key));
        }
        String uploadMethod = request.getParameter("uploadMethod");
        if (savePath == null || savePath.equals(""))
            throw new RuntimeException("没有设置文件在服务器上的保存路径");
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            if (!savePathFile.mkdirs())
                throw new RuntimeException("创建文件上传路径(" + savePath + ")失败");
        }
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);// 检查表单中是否包含文件
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        fileItemFactory.setSizeThreshold(4096);// 设置缓存临时文件的临界值
        fileItemFactory.setRepository(savePathFile);
        ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
        upload.setHeaderEncoding(encoding);
        upload.setSizeMax(maxFileSize);

        List items = null;
        if(prepairedItems != null){
            items = prepairedItems;
        }else{
            try {
                items =  ((ServletFileUpload) upload).parseRequest(request);
            } catch (FileUploadException e) {

                e.printStackTrace();
            }

        }

        for (int i = 0; i < fileList.size(); i++) {
            CommonsMultipartFile  dfi = (CommonsMultipartFile ) fileList.get(i);
            DiskFileItem fi = (DiskFileItem)dfi.getFileItem();
            String srcFileName = dfi.getName().trim().replaceAll("\\s*", "");
            try {
                //输出到文件目录
                fi.write(new File("D:\\111\\123"+i+".doc"));
            } catch (Exception e) {

                e.printStackTrace();
            }
            long fileSize = dfi.getSize();
            double fitFileSize = 0;
            String fileSizeStr = "";
            DecimalFormat df = new DecimalFormat("#.00");
            if((double)fileSize > (1024*1024)){
                fitFileSize = (double)fileSize / (1024*1024);
                fileSizeStr = df.format(fitFileSize) + "M";
            }else{
                fitFileSize = (double)fileSize / 1024;
                if(fitFileSize < 1)
                    fileSizeStr = "0" + df.format(fitFileSize) + "kb";
                else
                    fileSizeStr = df.format(fitFileSize) + "kb";
            }
            System.out.println("文件大小是："+fileSizeStr);
        }
        return false;
    }

}

