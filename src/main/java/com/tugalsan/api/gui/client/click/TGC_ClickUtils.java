package com.tugalsan.api.gui.client.click;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.tugalsan.api.executable.client.TGS_Executable;
import com.tugalsan.api.executable.client.TGS_ExecutableType1;

public class TGC_ClickUtils {

    public static void add(PushButton w, TGS_Executable exe) {
        w.addClickHandler(e -> {
            if (exe != null) {
                exe.execute();
            }
        });
    }

    public static void add(ListBox w, TGS_Executable singleClick, TGS_Executable doubleClick) {
        w.addClickHandler(e -> {
            if (singleClick != null) {
                singleClick.execute();
            }
        });
        w.addDoubleClickHandler(e -> {
            if (doubleClick != null) {
                doubleClick.execute();
            }
        });
    }

    public static void add(TextBox w, TGS_Executable exe) {
        w.addClickHandler(e -> {
            if (exe != null) {
                exe.execute();
            }
        });
    }

    public static void add(CheckBox w, TGS_Executable exe) {
        w.addClickHandler(e -> {
            if (exe != null) {
                exe.execute();
            }
        });
    }

    public static void add(ToggleButton w, TGS_ExecutableType1<Boolean> exe) {
        w.addClickHandler(e -> {
            if (exe != null) {
                var tb = (ToggleButton) e.getSource();
                exe.execute(tb.isDown());
            }
        });
    }

    public static void add(Image w, TGS_Executable exe) {
        w.addClickHandler(e -> {
            if (exe != null) {
//                var image = (Image) e.getSource();
                exe.execute();
            }
        });
    }

    public static void add(HTML html, TGS_Executable exe) {
        html.addClickHandler(e -> {
            if (exe != null) {
                exe.execute();
            }
        });
    }

    public static void add(Canvas canvas, TGS_Executable exe) {
        canvas.addClickHandler(e -> {
            if (exe != null) {
                exe.execute();
            }
        });
    }
}
