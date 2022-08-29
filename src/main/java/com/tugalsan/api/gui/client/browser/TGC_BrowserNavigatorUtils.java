package com.tugalsan.api.gui.client.browser;

import java.util.Arrays;
import com.tugalsan.api.log.client.TGC_Log;

public class TGC_BrowserNavigatorUtils {

    final private static TGC_Log d = TGC_Log.of(TGC_BrowserNavigatorUtils.class);

    public static native String userAgent() /*-{
        return navigator.userAgent.toLowerCase();
    }-*/;

    public static native String appName() /*-{
        return navigator.appName;
    }-*/;

    public static native String product() /*-{
        return navigator.product.toLowerCase();
    }-*/;

    public static native String appVersion() /*-{
        return navigator.appVersion.toLowerCase();
    }-*/;

    public static native String language() /*-{
        return navigator.language.toLowerCase();
    }-*/;

    public static native boolean onLine() /*-{
        return navigator.onLine;
    }-*/;

    public static native String platform() /*-{
        return navigator_platform.toLowerCase();
    }-*/;

    public static native boolean javaEnabled() /*-{
        return navigator.javaEnabled();
    }-*/;

    public static native boolean cookieEnabled() /*-{
        return navigator.cookieEnabled();
    }-*/;

    public static boolean chronium() {
        return userAgent().contains("chro");
    }

    public static boolean firefox() {
        return userAgent().contains("firefox");
    }

    public static boolean mobile() {
        if (TGC_BrowserOrientationUtils.isOrientationUndefined()) {
            d.ci("isMobile", "isMobile==false", "isOrientationUndefined==true");
            return false;
        }
        var userAgent = userAgent();
        d.ci("isMobile", "userAgent", userAgent);
        String[] mobileList = {
            "iphone", "ipad", "android", "blackberry",
            "nokia", "opera mini", "windows mobile",
            "windows phone", "iemobile"
        };
        if (Arrays.stream(mobileList).parallel().anyMatch(tag -> userAgent.contains(tag))) {
            d.ci("isMobile", "isMobile==true");
            return true;
        }
        d.ci("isMobile", "isMobile==false", "assumption");
        return false;
    }
}
