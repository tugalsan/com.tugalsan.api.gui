package com.tugalsan.api.gui.client.browser;

public class TGC_BrowserOrientationUtils {

    private TGC_BrowserOrientationUtils() {

    }

    public static native boolean isOrientationUndefined() /*-{
        return typeof window.orientation == 'undefined';
    }-*/;
}
