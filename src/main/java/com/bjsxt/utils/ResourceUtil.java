package com.bjsxt.utils;

public class ResourceUtil {
    public ResourceUtil() {
    }

//    public static void CreateResourceRecycle(String strFileUrl) {
//        Connection writeConnection = null;
//
//        try {
//            writeConnection = DruidUtil.getRandomWriteConnection();
//            Map<String, Object> map = new HashMap();
//            map.put("path", strFileUrl);
//            map.put("id", UUID.randomUUID().toString());
//            DruidUtil.beginTransaction(writeConnection);
//            DruidUtil.save(writeConnection, map, "ct_resource_recycle");
//            DruidUtil.commitTransaction(writeConnection);
//        } catch (Exception var6) {
//            var6.printStackTrace();
//        } finally {
//            DruidUtil.close(writeConnection);
//            writeConnection = null;
//        }
//
//    }

    public static String getImageKeyByType(int iType) {
        String strType = "normal";
        switch(iType) {
            case -1:
                strType = "normal";
                break;
            case 0:
                strType = "thumbnail";
                break;
            case 1:
                strType = "poster";
                break;
            case 2:
                strType = "stills";
                break;
            case 3:
                strType = "icon";
                break;
            case 4:
                strType = "title";
                break;
            case 5:
                strType = "ad";
                break;
            case 6:
                strType = "sketch";
                break;
            case 7:
                strType = "background";
                break;
            case 9:
                strType = "channel";
                break;
            case 10:
                strType = "channel_bw";
                break;
            case 11:
                strType = "channel_logo";
                break;
            case 12:
                strType = "channel_name";
                break;
            case 13:
                strType = "guide";
                break;
            case 14:
                strType = "cover";
                break;
            case 99:
                strType = "other";
        }

        return strType;
    }
}
