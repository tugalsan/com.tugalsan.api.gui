package com.tugalsan.api.gui.client.theme;

import com.google.gwt.user.client.ui.Widget;
import java.util.Arrays;
import com.tugalsan.api.thread.client.TGC_ThreadUtils;

public class TGC_PanelStyleUtils {

    public static void warn(Widget w, int seconds) {
        red(w);
        TGC_ThreadUtils.execute_afterSeconds_afterGUIUpdate(t -> TGC_ThreadUtils.execute_afterGUIUpdate(() -> {
            remove(w);
        }), seconds);
    }

    public static void remove(Widget... widgets) {
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.removeStyleName(TGC_PanelBlack.class.getSimpleName());
            w.removeStyleName(TGC_PanelYellow.class.getSimpleName());
            w.removeStyleName(TGC_PanelRed.class.getSimpleName());
            w.removeStyleName(TGC_PanelGreen.class.getSimpleName());
            w.removeStyleName(TGC_PanelBlue.class.getSimpleName());
            w.removeStyleName(TGC_PanelGray.class.getSimpleName());
            w.removeStyleName(TGC_PanelPurple.class.getSimpleName());
            w.removeStyleName(TGC_PanelOrange.class.getSimpleName());
        });
    }

    public static void black(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.setStyleName(TGC_PanelBlack.class.getSimpleName());
        });
    }

    public static void yellow(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.setStyleName(TGC_PanelYellow.class.getSimpleName());
        });
    }

    public static void red(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.setStyleName(TGC_PanelRed.class.getSimpleName());
        });
    }

    public static void green(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.setStyleName(TGC_PanelGreen.class.getSimpleName());
        });
    }

    public static void blue(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.setStyleName(TGC_PanelBlue.class.getSimpleName());
        });
    }

    public static void gray(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.setStyleName(TGC_PanelGray.class.getSimpleName());
        });
    }

    public static void purple(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.setStyleName(TGC_PanelPurple.class.getSimpleName());
        });
    }

    public static void orange(Widget... widgets) {
        remove(widgets);
        Arrays.stream(widgets).forEachOrdered(w -> {
            w.setStyleName(TGC_PanelOrange.class.getSimpleName());
        });
    }
}
