package com.pi.miaosha.result;

/**
 * @program: miaosha
 * @author: Mr.Pi
 * @create: 2018-09-07 13:50
 **/
public enum  CodeEnums {
    //通用错误码
    SUCCESS(0,"success"),
    SERVER_ERROR(500100, "服务端异常"),
    BIND_ERROR(500101, "参数校验异常：%s"),
    PARAM_EMPTY(500111,"request传入参数为空"),
    //登录模块 5002XX
    SESSION_ERROR(500210, "Session不存在或者已经失效"),
    PASSWORD_EMPTY(500211, "登录密码不能为空"),
    MOBILE_EMPTY(500212, "手机号不能为空"),
    MOBILE_ERROR(500213, "手机号格式错误"),
    MOBILE_NOT_EXIST(500214, "手机号不存在"),
    PASSWORD_ERROR(500215, "密码错误"),
    //秒杀模块 5005xx
    MIAO_SHA_OVER(500500,"商品已经秒杀完毕"),
    REPEAT_MIAOSHA(500501,"不能重复秒杀")
    ;

    private int code;

    private String msg;

    CodeEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
