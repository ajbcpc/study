package com.bjsxt.vo;

import lombok.Data;

/**
 * @Auther: 傲娇不穿胖次
 * @Date: 2018/12/9 17:22
 * @Description: http请求返回的最外层对象
 */
@Data
public class ResultVO<T> {

    /** 错误码 */
    private Integer code;

    /** 提示信息 */
    private String msg;

    /** 当前时间 */
    private long currentTime;

    /** 数据对象 */
    private T data;
}
