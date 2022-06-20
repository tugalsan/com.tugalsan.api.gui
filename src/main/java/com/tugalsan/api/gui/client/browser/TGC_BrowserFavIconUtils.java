package com.tugalsan.api.gui.client.browser;

public class TGC_BrowserFavIconUtils {

    public static native void set(CharSequence href) /*-{
        $wnd.setFavicon(href);
    }-*/;
}
