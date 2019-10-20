package com.bjsxt.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * @Auther: 傲娇不穿胖次
 * @Date: 2019/1/8 09:28
 * @Description: format
 */
public class ResultUtil {


//    public static void formatObject(List<Map<String, Object>> lObject) {
//        if (null != lObject && lObject.size() > 0) {
//            for (Map<String, Object> mObject : lObject) {
//                formatObject(mObject);
//            }
//        }
//    }
//
//    public static void formatObject(Map<String, Object> mObject) {
//        if (null != mObject) {
//            Object oObject = mObject.get("image");
//            if (null != oObject && !"".equals(oObject)) {
//                try {
//                    JSONArray jObject = JSONArray.parseArray("" + oObject);
//                    mObject.put("image", jObject);
//                } catch (Exception e) {
//                    mObject.remove("image");
//                }
//            } else {
//                mObject.remove("image");
//            }
//            Object oObjects = mObject.get("images");
//            if (null != oObjects && !"".equals(oObjects)) {
//                try {
//                    JSONObject jObjects = JSONObject.parseObject("" + oObjects);
//                    mObject.put("images", jObjects);
//                } catch (Exception e) {
//                    mObject.remove("images");
//                }
//            } else {
//                mObject.remove("images");
//            }
//        }
//    }

    public static Object format(Object object){
        if(object instanceof ArrayList){
            return formatList(object);
        }else{
            return formatObject(object);
        }
    }

    private static Object formatList(Object target){
        List<Object> list = new ArrayList();
        if (null != target && !"".equals(target) ) {

            String toTarget = JSONObject.toJSONString(target);
            JSONArray jsonArray = JSON.parseArray(toTarget);
            for(Object jsonObject : jsonArray){
                JSONObject json = JSONObject.parseObject(jsonObject.toString());
                formatImage(json);
                list.add(json.clone());
            }

        }
        return list;
    }
    private static Object formatObject(Object target) {
        Object result = target;
        if (null != target && !"".equals(target)) {

            String toTarget = JSONObject.toJSONString(target);
            JSONObject json = JSON.parseObject(toTarget);
            formatImage(json);
            result = json.clone();
        }
        return result;
    }

    private static void formatImage(JSONObject json){
        Object oImage = json.get("image");
        if (null != oImage && !"".equals(oImage)) {
            try {
                JSONArray jImage = JSONArray.parseArray("" + json.get("image"));
                json.put("image", jImage);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("【出错了】");
            }

        }else{
            json.remove("image");
        }
        Object oImages = json.get("images");
        if (null != oImages && !"".equals(oImages)) {
            try {
                JSONObject jImages = JSONArray.parseObject("" + json.get("images"));
                json.put("images", jImages);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("【出错了】");
            }

        }else{
            json.remove("images");
        }
    }


}
