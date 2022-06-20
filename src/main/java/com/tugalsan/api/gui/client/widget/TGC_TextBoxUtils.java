package com.tugalsan.api.gui.client.widget;

import com.google.gwt.event.logical.shared.*;
import com.google.gwt.user.client.ui.*;
import com.tugalsan.api.executable.client.*;

public class TGC_TextBoxUtils {

    public static void onChange(TextBox tb, TGS_Executable exe) {
        tb.addValueChangeHandler((ValueChangeEvent<String> event) -> {
            exe.execute();
        });
    }

    public static void setTypeNumber(TextBox tb) {
        tb.getElement().setAttribute("type", "number");
    }
}
