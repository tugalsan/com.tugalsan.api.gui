package com.tugalsan.api.gui.client.browser;

import com.tugalsan.api.executable.client.TGS_ExecutableType1;
import com.tugalsan.api.log.client.TGC_Log;
import elemental2.dom.BroadcastChannel;
import elemental2.dom.MessageEvent;

@Deprecated //TODO NOT WORKING
public class TGC_BrowserTabUtils {

    final private static TGC_Log d = TGC_Log.of(TGC_BrowserTabUtils.class);

    public static BroadcastChannel addBroadCastChannel(CharSequence channelName, TGS_ExecutableType1<MessageEvent<Object>> exe) {
        var bc = new BroadcastChannel(channelName.toString());
        d.cr("onModuleLoad", "bc.name", bc.name);
        bc.onmessage = (MessageEvent<Object> e) -> {
            var msg = String.valueOf(e.data);
            d.cr("addBroadCastChannel.onmessage.onInvoke", "msg", msg);
            return msg;
        };
        return bc;
    }

    public static Object broadCast(BroadcastChannel bc, Object msg) {
        return bc.postMessage(msg);
    }
}
