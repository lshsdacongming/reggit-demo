package edu.czjt.reggie.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jinkun.tian on 2023/3/21
 */
//设计表现层返回结果的模型类，用于后端与前端进行数据格式统一，也称为前后端数据协议
// 用来存放共同使用的类，把这个返回结果类放入这个公共包
@Data
public class R<T> {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
