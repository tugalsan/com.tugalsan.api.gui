package com.tugalsan.api.gui.client.widget;

import com.google.gwt.user.client.ui.HTML;
import com.tugalsan.api.icon.client.TGS_IconUtils;

public class TGC_HTMLUtils {

    public static HTML create(CharSequence iconClassName, CharSequence optional_text) {
        return set(new HTML(), iconClassName, optional_text);
    }

    public static HTML set(HTML html, CharSequence iconClassName, CharSequence optional_text) {
        html.setHTML(TGS_IconUtils.createSpan(iconClassName, optional_text));
        return html;
    }
}
