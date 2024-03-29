package com.tugalsan.api.gui.client.widget;

import com.google.gwt.event.logical.shared.*;
import com.google.gwt.user.client.ui.*;
import com.tugalsan.api.runnable.client.*;

public class TGC_TextBoxUtils {

    public static void onChange(TextBox tb, TGS_Runnable exe) {
        tb.addValueChangeHandler((ValueChangeEvent<String> event) -> {
            exe.run();
        });
    }

    public static void setTypeNumber(TextBox tb) {
        tb.getElement().setAttribute("type", "number");
    }
}
