package com.tugalsan.api.gui.client.browser;
//import elemental2.dom.MessageEvent;


public class TGC_BrowserTabUtils {

    public static native void addEventListener_visibilitychange() /*-{
        $doc.addEventListener("visibilitychange", () => {
            var tabIsVisible = !document.hidden
            if (document.hidden) {
                tabIsVisible = false;
            } else {
              tabIsVisible = true;
            }
            console.log("tabIsVisible:" + tabIsVisible)
        });   
    }-*/;

//@Deprecated //TODO BroadCast NOT WORKING, no need add another dep for it
//    final private static TGC_Log d = TGC_Log.of(TGC_BrowserTabUtils.class);
//    public static BroadcastChannel addBroadCastChannel(CharSequence channelName, TGS_Func_In1<MessageEvent<Object>> exe) {
//        var bc = new BroadcastChannel(channelName.toString());
//        d.cr("onModuleLoad", "bc.name", bc.name);
//        bc.onmessage = (MessageEvent<Object> e) -> {
//            var msg = String.valueOf(e.data);
//            d.cr("addBroadCastChannel.onmessage.onInvoke", "msg", msg);
//            return msg;
//        };
//        return bc;
//    }
//
//    public static Object broadCast(BroadcastChannel bc, Object msg) {
//        return bc.postMessage(msg);
//    }
}
