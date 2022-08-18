package com.tugalsan.api.gui.client.widget;

import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.tugalsan.api.time.client.TGS_Time;

public class TGC_DateBoxUtils {

    public static int minWidth() {
        return 80;
    }

    public static DateBox create() {
        var w = new DateBox();
        w.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd.MM.yyyy")));
        w.getDatePicker().setYearAndMonthDropdownVisible(true);
        w.getDatePicker().setYearArrowsVisible(true);
        w.setWidth(minWidth() + "px");
        return w;
    }

    public static void setDate(DateBox w, TGS_Time now) {
        w.getTextBox().setText(now.toString_dateOnly());
    }

    public static TGS_Time getDate(DateBox w) {
        return TGS_Time.ofDate(w.getTextBox().getText());
    }
}
