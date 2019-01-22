package org.flyonsky.boot.autoconfigure.cat;

import com.dianping.cat.CatConstants;

public class CatConstantsExt extends CatConstants{
    /**
     * Type 关键字
     */
    public static final String Type_URL_METHOD = "URL.method";
    public static final String Type_URL_CLIENT = "URL.client";
    public static final String Type_URL_FORWORD = "URL.forword";

    public static final String Type_Service = "Service";
    public static final String Type_Service_METHOD= "Service.method";
    public static final String Type_Service_CLIENT = "Service.client";

    public static final String Type_SQL = "SQL";
    public static final String Type_SQL_METHOD= "SQL.method";
    public static final String Type_SQL_CLIENT = "SQL.client";

    public static final String Type_Cache = "Cache";
    public static final String Type_Cache_METHOD= "Cache.method";
    public static final String Type_Cache_CLIENT = "Cache.client";

    public static final String Type_Call = "Call";
    public static final String Type_Call_METHOD= "Call.method";
    public static final String Type_Call_CLIENT = "Call.client";

    /**
     * http header 关键字
     */
    public static final String CAT_HTTP_HEADER_ROOT_MESSAGE_ID = "X-CAT-ROOT-MESSAGE-ID";
    public static final String CAT_HTTP_HEADER_PARENT_MESSAGE_ID = "X-CAT-ROOT-PARENT-ID";
    public static final String CAT_HTTP_HEADER_CHILD_MESSAGE_ID = "X-CAT-ROOT-CHILD-ID";
}
