package com.tugalsan.api.gui.client.widget;

import com.google.gwt.user.client.ui.CheckBox;
import com.tugalsan.api.icon.client.TGS_IconUtils;

public class TGC_CheckBoxUtils {

    public static record Config(String iconClassName, String label) {

    }

    public static CheckBox createIcon(Config iconAndLabel) {
        return createIcon(iconAndLabel.iconClassName(), iconAndLabel.label());
    }

    public static CheckBox createIcon(CharSequence iconClassName, CharSequence text) {
        return new CheckBox(TGS_IconUtils.createSpan(iconClassName, text, true, false), true);
    }
}
