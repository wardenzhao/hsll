package cn.thinkjoy.hsll.util;

/**
 * Created by warden on 17/7/24.
 */
public class WeiXinUtil {

    private static final String appid = "wx9b84b203e2737d8b";
    private static final String appsecret = "203539525bc8f7852ab80fdb57ed684d";
    private static final String domain="http://192.168.199.32/mobile";

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
