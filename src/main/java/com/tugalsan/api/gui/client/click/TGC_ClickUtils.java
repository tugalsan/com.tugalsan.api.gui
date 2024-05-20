package com.tugalsan.api.gui.client.click;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.tugalsan.api.log.client.TGC_Log;
import com.tugalsan.api.runnable.client.TGS_Runnable;
import com.tugalsan.api.runnable.client.TGS_RunnableType1;
import com.tugalsan.api.time.client.TGS_Time;
import com.tugalsan.api.tuple.client.TGS_Tuple2;
import java.util.ArrayList;
import java.util.List;

public class TGC_ClickUtils {

    private final static TGC_Log d = TGC_Log.of(TGC_ClickUtils.class);

    public static void add(PushButton w, TGS_Runnable exe) {
        w.addClickHandler(e -> {
            if (exe != null) {
                exe.run();
            }
        });
    }

    public static void add(ListBox w, TGS_Runnable singleClick, TGS_Runnable doubleClick) {
        w.addClickHandler(e -> {
            var now = TGS_Time.of();
            d.ci("add(ListBox)", "now", now.toString_timeOnly());
            List<TGS_Tuple2<ListBox, TGS_Time>> lastClick = new ArrayList();
            var found = lastClick.stream().filter(lc -> lc.value0.equals(w)).findAny().orElse(null);
            d.ci("add(ListBox)", "found", found);
            if (found == null) {
                lastClick.add(TGS_Tuple2.of(w, now));
                if (singleClick != null) {
                    d.ci("add(ListBox)", "run", "found not found");
                    singleClick.run();
                }
            } else {
                var now1SecondAgo = TGS_Time.ofSecondsAgo(1);
                d.ci("add(ListBox)", "now1SecondAgo", now1SecondAgo);
                if (found.value1.hasSmallerTimeThan(now1SecondAgo)) {
                    if (singleClick != null) {
                        d.ci("add(ListBox)", "run", found.value1.toString_timeOnly(), "found < 1seondAgo", now1SecondAgo.toString_timeOnly());
                        singleClick.run();
                    }
                } else {
                    d.ci("add(ListBox)", "cancel", found.value1.toString_timeOnly(), "found < 1seondAgo", now1SecondAgo.toString_timeOnly());
                }
                found.value1 = now;
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
