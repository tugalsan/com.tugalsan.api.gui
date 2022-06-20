package com.tugalsan.api.gui.client.key;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Event;
import com.tugalsan.api.executable.client.TGS_Executable;
import com.tugalsan.api.gui.client.dom.TGC_DOMUtils;
import com.tugalsan.api.list.client.*;
import com.tugalsan.api.log.client.TGC_Log;
import java.util.*;

public class TGC_KeyTriggerUtils {

    final private static TGC_Log d = TGC_Log.of(TGC_KeyTriggerUtils.class.getSimpleName());

    public static void add2Dom() {
        Element body = TGC_DOMUtils.getElementByTagName("body");
        Event.sinkEvents(body, Event.ONKEYDOWN | Event.ONKEYUP);
        Event.setEventListener(body, event -> {
            if (Event.ONKEYDOWN == event.getTypeInt()) {
                var ctrl = event.getCtrlKey();
                var shift = event.getShiftKey();
                if (ctrl == isCtrl && shift == isShift) {
                    return;
                }
                isPreCtrl = isCtrl;
                isPreShift = isShift;
                isCtrl = ctrl;
                isShift = shift;
                onTrigger.stream().forEachOrdered(t -> t.execute());
            }
            if (Event.ONKEYUP == event.getTypeInt()) {
                var ctrl = event.getCtrlKey();
                var shift = event.getShiftKey();
                if (ctrl == isCtrl && shift == isShift) {
                    return;
                }
                isPreCtrl = isCtrl;
                isPreShift = isShift;
                isCtrl = ctrl;
                isShift = shift;
                onTrigger.stream().forEachOrdered(t -> t.execute());
            }
        });
        onTrigger.add(() -> {
            if (isCtrl && isShift) {
                if (quickCtrlShift != null) {
                    quickCtrlShift.execute();
                }
                d.ci("add2Dom", "quickCtrlShift", "CS");
            } else if (isCtrl) {
                if (quickCtrl != null) {
                    quickCtrl.execute();
                }
                d.ci("add2Dom", "quickCtrl", "C");
            } else if (isShift) {
                if (quickShift != null) {
                    quickShift.execute();
                }
                d.ci("add2Dom", "quickShift", "S");
            } else {
                if (quickNull != null) {
                    quickNull.execute();
                }
                d.ci("add2Dom", "quickNull", "N");
            }
        });
    }

    public static boolean isPreCtrl = false;
    public static boolean isPreShift = false;
    public static boolean isCtrl = false;
    public static boolean isShift = false;
    final public static List<TGS_Executable> onTrigger = TGS_ListUtils.of();
    public static TGS_Executable quickCtrlShift = null;
    public static TGS_Executable quickCtrl = null;
    public static TGS_Executable quickShift = null;
    public static TGS_Executable quickNull = null;
}
