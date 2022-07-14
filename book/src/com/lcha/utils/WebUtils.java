package com.lcha.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {
    public static <T> T copyParatoBean(Map value, T obj){
        try {
            BeanUtils.populate(obj,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 将字符串转化成为int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue){
        if(strInt==null||strInt=="") return defaultValue;
        int i=defaultValue;
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
           // e.printStackTrace();
        }
        return i;
    }
}
