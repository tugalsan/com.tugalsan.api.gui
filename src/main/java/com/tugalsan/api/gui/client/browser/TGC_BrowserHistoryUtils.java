package com.tugalsan.api.gui.client.browser;

public class TGC_BrowserHistoryUtils {

    public static native int native_history_length() /*-{
        return history.length;
    }-*/;
}
