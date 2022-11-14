package com.haha.ds;

public class DbContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源
     */
    public static void setDbType(DBTypeEnum dbTypeEnum) {
        contextHolder.set(dbTypeEnum.getValue());
    }

    /**
     * 设置数据源
     */
    public static void setDbType(String dbType) {
        contextHolder.set(dbType);
    }

    /**
     * 取得当前数据源
     */
    public static String getDbType() {
        return contextHolder.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clearDbType() {
        contextHolder.remove();
    }
}
