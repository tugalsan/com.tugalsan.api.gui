package com.tugalsan.api.gui.client.widget;

import com.tugalsan.api.string.client.*;

public class TGS_LinkUtils {

    public static String html(CharSequence text, CharSequence link) {
        return TGS_StringUtils.concat("<a class=\"AppModuleTitle\" href=\"", link, "\">", text, "</a>");
    }
}
