package com.tugalsan.api.gui.client.browser;

public class TGC_BrowserScreenUtils {

    public static native int depthColor() /*-{
        return screen.colorDepth;
    }-*/;

    public static native int depthPixel() /*-{
        return screen.pixelDepth;
    }-*/;
}
