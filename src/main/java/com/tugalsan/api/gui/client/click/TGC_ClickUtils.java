package com.tugalsan.api.gui.client.click;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.tugalsan.api.runnable.client.TGS_Runnable;
import com.tugalsan.api.runnable.client.TGS_RunnableType1;

public class TGC_ClickUtils {

    public static void add(PushButton w, TGS_Runnable exe) {
        w.addClickHandler(e -> {
            if (exe != null) {
                exe.run();
            }
        });
    }

    public static void add(ListBox w, TGS_Runnable singleClick, TGS_Runnable doubleClick) {
        w.addClickHandler(e -> {
            if (singleClick != null) {
                singleClick.run();
            }
        });
        w.addDoubleClickHandler(e -> {
            if (doubleClick != null) {
                doubleClick.run();
            }
        });
    }

    public static void add(TextBox w, TGS_Runnable exe) {
        w.addClickHandler(e -> {
            if (exe != null) {
                exe.run();
            }
        });
    }

    public static void add(CheckBox w, TGS_Runnable exe) {
        w.addClickHandler(e -> {
            if (exe != null) {
                exe.run();
            }
        });
    }

    public static void add(ToggleButton w, TGS_RunnableType1<Boolean> exe) {
        w.addClickHandler(e -> {
            if (exe != null) {
                var tb = (ToggleButton) e.getSource();
                exe.run(tb.isDown());
            }
        });
    }

    public static void add(Image w, TGS_Runnable exe) {
        w.addClickHandler(e -> {
            if (exe != null) {
//                var image = (Image) e.getSource();
                exe.run();
            }
        });
    }

    public static void add(HTML html, TGS_Runnable exe) {
        html.addClickHandler(e -> {
            if (exe != null) {
                exe.run();
            }
        });
    }

    public static void add(Canvas canvas, TGS_Runnable exe) {
        canvas.addClickHandler(e -> {
            if (exe != null) {
                exe.run();
            }
        });
    }
}
