package org.top.thymeboot.common.utils;

import java.util.UUID;

public class UUIDUtils {

    /**
     * 生成UUID
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 生成UUID不去除-
     */
    public static String getUUIDPLUS(){
        return UUID.randomUUID().toString();
    }

    /**
     * 生成16位UUID
     */
    public static String getSixteenUUID(){
        return UUID.randomUUID().toString().replace("-","").substring(16);
    }
}
