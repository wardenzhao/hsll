package cn.thinkjoy.hsll.util;

/**
 * Created by warden on 17/7/24.
 */
public class WeiXinUtil {

    private static final String appid = "wxe1daa1b3f5a65eb0";
    private static final String appsecret = "4053b1c6a2e6a7f6d830cca2f79292e1";
    private static final String domain="http://172.16.222.194:8080/hsll";

    public static String getAppid() {
        return appid;
    }

    public static String getAppsecret() {
        return appsecret;
    }

    public static String getDomain() {
        return domain;
    }
}
