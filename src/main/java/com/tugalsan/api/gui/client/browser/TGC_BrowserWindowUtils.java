package com.tugalsan.api.gui.client.browser;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.Window;
import java.util.List;
import com.tugalsan.api.charset.client.TGS_CharSetUTF8;
import com.tugalsan.api.executable.client.TGS_Executable;
import com.tugalsan.api.executable.client.TGS_ExecutableType1;
import com.tugalsan.api.list.client.*;
import com.tugalsan.api.log.client.TGC_Log;
import com.tugalsan.api.shape.client.TGS_ShapeDimension;
import com.tugalsan.api.thread.client.TGC_ThreadUtils;

public class TGC_BrowserWindowUtils {

    final private static TGC_Log d = TGC_Log.of(TGC_BrowserWindowUtils.class);

    public static void setTitle(CharSequence newTitle) {
        if (Document.get() != null) {
            Document.get().setTitle(newTitle == null ? TGS_CharSetUTF8.UTF8_HOURGLASS() : newTitle.toString());
        }
    }

    public static void openNew(CharSequence link) {
        Window.open(link.toString(), "_blank", null);
    }

    public static void openSame(CharSequence link) {
        Window.open(link.toString(), "_self", null);
    }

    public static void reload() {
        Window.Location.reload();
    }

    public static void reloadInSecs(int seconds) {
        TGC_ThreadUtils.execute_afterSeconds_afterGUIUpdate(t -> TGC_BrowserWindowUtils.reload(), seconds);
    }

    public static void addResizeHandler() {
        d.ci("addResizeHandler", "callled...");
        if (SYNC_lastResizeRequestMillis != 0L) {
            d.ci("addResizeHandler", "already inited", "skipping...");
            return;
        }
        Window.addResizeHandler(re -> {
            d.ci("addResizeHandler", "requesting...");
            var reqMillis = System.currentTimeMillis();
            SYNC_lastResizeRequestMillis = reqMillis;
            TGC_ThreadUtils.execute_afterSeconds_afterGUIUpdate(t -> {
                if (SYNC_lastResizeRequestMillis == reqMillis) {
                    d.ci("addResizeHandler", "exe_resizeHandlers.size()", exe_resizeHandlers.size());
                    exe_resizeHandlers.stream().forEachOrdered(exe -> {
                        exe.execute(new TGS_ShapeDimension(re.getWidth(), re.getHeight()));
                    });
                } else {
                    d.ci("addResizeHandler", "request skipped...");
                }
            }, 2);
        });
        d.ci("addResizeHandler", "init");
    }
    final public static List<TGS_ExecutableType1<TGS_ShapeDimension<Integer>>> exe_resizeHandlers = TGS_ListUtils.of();
    volatile private static long SYNC_lastResizeRequestMillis = 0;

    public static native void close()/*-{
        $wnd.close();
    }-*/;

    public static native boolean setClosingMessage() /*-{
        //GWT: Window.addWindowClosingHandler(e -> e.setMessage(text));
        window.onbeforeunload = function() {
            return 'Programı kapatmak istediğinizden emin misiniz?';
        };
    }-*/;

    public static void onClosing(TGS_Executable exe) {
        Window.addWindowClosingHandler(e -> exe.execute());
    }

    @Deprecated
    public static native boolean isClosable() /*-{
        if (window.opener) return true;
        return false;
    }-*/;

    public static void alert(CharSequence string) {
        Window.alert(string.toString());
    }

    public static native String href_locationPathname() /*-{
        return window.location.pathname;
    }-*/;

    public static native String href_documentReferrer() /*-{
        return "" + document.referrer;
    }-*/;

}
