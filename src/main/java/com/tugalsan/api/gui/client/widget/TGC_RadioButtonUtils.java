package com.tugalsan.api.gui.client.widget;

import com.google.gwt.user.client.ui.RadioButton;
import com.tugalsan.api.icon.client.TGS_IconUtils;

public class TGC_RadioButtonUtils {

    public static RadioButton createIcon(CharSequence radioGroup, CharSequence iconClassName, CharSequence text) {
        return new RadioButton(radioGroup.toString(), TGS_IconUtils.createSpan(TGS_IconUtils.CLASS_CLOCK(), text, true, false), true);
    }
}
