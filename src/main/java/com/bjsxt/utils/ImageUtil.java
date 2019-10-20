package com.bjsxt.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.*;

@Slf4j
public class ImageUtil {

    public ImageUtil() {
    }

    public static String convertStrImages2SpecialFormatJSON(String strImages) {
        JSONArray jsonArr;
        if (strImages != null) {
            jsonArr = JSONArray.parseArray(strImages);
        } else {
            jsonArr = new JSONArray();
        }

        Map<String, Object> mapImages = new HashMap();
        mapImages.put("map", new HashMap());
        mapImages.put("list", new ArrayList());

        for(int i = 0; i < jsonArr.size(); ++i) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            String strKey = ResourceUtil.getImageKeyByType(jsonObj.containsKey("type") ? jsonObj.getIntValue("type") : -1);
            if (((Map)mapImages.get("map")).containsKey(strKey)) {
                ((ArrayList)((Map)mapImages.get("map")).get(strKey)).add(i);
            } else {
                List<Integer> listIndex = new ArrayList();
                listIndex.add(i);
                ((Map)mapImages.get("map")).put(strKey, listIndex);
            }

            Map<String, Object> mapImageDetail = new HashMap();
            mapImageDetail.put("id", jsonObj.getLong("id"));
            mapImageDetail.put("fileUrl", jsonObj.getString("fileUrl"));
            mapImageDetail.put("height", jsonObj.getInteger("height"));
            mapImageDetail.put("width", jsonObj.getInteger("width"));
            mapImageDetail.put("size", jsonObj.getInteger("size"));
            mapImageDetail.put("extension", jsonObj.getString("extension"));
            mapImageDetail.put("enable", jsonObj.getBoolean("enable"));
            mapImageDetail.put("name", jsonObj.getString("name"));
            ((ArrayList)mapImages.get("list")).add(mapImageDetail);
        }

        return JSON.toJSONString(mapImages);
    }

    /**
     * 保存文件，直接以multipartFile形式
     * @param multipartFile
     * @param path 文件保存绝对路径
     * @return 返回文件名
     * @throws IOException
     */
    public static Map<String, Object> saveImg(MultipartFile multipartFile, String ImageUploadFolder, String path) throws IOException {

        String originalPath = path;
        path = ImageUploadFolder + path;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();

        //找到文件后缀
        String strServerImageFileName = multipartFile.getOriginalFilename();
        int iExtPosition = strServerImageFileName.lastIndexOf('.');
        String strImageFileExt = strServerImageFileName.substring(iExtPosition);

        // 生成文件名
        String fileName = UUID.randomUUID().toString() + strImageFileExt;
        File filePath = new File(path + File.separator + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath.getPath()));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
//        log.info("保存后的文件名 = {}", filePath.getPath());
        long lFileSize = 0;
        if (filePath.exists()) {
            FileInputStream fis = new FileInputStream(filePath);
            lFileSize = fis.available();
            fis.close();
        } else {
//            log.error("File not exist:" + filePath.getName());
        }

        Image img = ImageIO.read(filePath);

        Map<String, Object> qrMap = new HashMap();
        qrMap.put("fileUrl", originalPath + "/" + fileName);
        qrMap.put("enable", true);
        qrMap.put("name", strServerImageFileName);
        qrMap.put("width", img.getWidth(null));
        qrMap.put("height", img.getHeight(null));
        qrMap.put("size", lFileSize);
        qrMap.put("extension", strImageFileExt);

        return qrMap;
    }

}
