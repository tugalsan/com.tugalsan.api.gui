package com.tugalsan.api.gui.client.widget;

import com.google.gwt.user.client.ui.*;
import com.tugalsan.api.icon.client.*;
import com.tugalsan.api.string.client.*;

public class TGC_ButtonUtils {

    public static record Config(String urlImageUp, String urlImageDown, boolean urlImageVertical) {

    }

    public static PushButton create(Config config) {
        return create(config, null);
    }

    public static PushButton create(Config config, CharSequence label) {
        PushButton pb;
        if (config == null) {
            pb = new PushButton(label.toString());
        } else {
            pb = new PushButton(new Image(config.urlImageUp), new Image(config.urlImageDown));
            if (label != null) {
                var innerHtml = pb.getElement().getInnerHTML();
                if (config.urlImageVertical) {
                    innerHtml = TGS_StringUtils.concat("<div><center>", innerHtml, "</center><label style='cursor:pointer'><center>", label, "</center></label></div>");
                } else {
                    innerHtml = TGS_StringUtils.concat("<table><tr><td>", innerHtml, "</td><td><label style='cursor:pointer'>", label, "</label></td></tr></table>");
                }
                pb.getElement().setInnerHTML(innerHtml);
            }
        }
        return pb;
    }

    @Deprecated
    public static PushButton create(HTML label) {
        var pb = new PushButton();
//        var innerHtml = pb.getElement().getInnerHTML();
//        innerHtml = TGS_StringUtils.concat("<table><tr><td>", innerHtml, "</td><td><label style='cursor:pointer'>", label.getHTML(), "</label></td></tr></table>");
        var innerHtml = label.getHTML();
        pb.getElement().setInnerHTML(innerHtml);
        return pb;
    }

    @Deprecated
    public static PushButton createGray(CharSequence label, Double fontSize) {
        return create(TGC_GrayScaleUtils.create(label, fontSize));
    }

    public static PushButton createIcon(CharSequence fullIconClassName) {
        return createIcon(fullIconClassName, null);
    }

    //EXAMPLE: btnFilter = TGC_ButtonUtils.createIcon("icon icon-filter", "Süz");
    public static PushButton createIcon(CharSequence fullIconClassName, CharSequence text) {
        var btn = new PushButton();
        setIcon(btn, fullIconClassName, text);
        return btn;
    }

    public static PushButton createIcon(CharSequence fullIconClassName0, CharSequence fullIconClassName1, CharSequence text) {
        var btn = new PushButton();
        setIcon(btn, fullIconClassName0, fullIconClassName1, text);
        return btn;
    }

    public static void setIcon(CustomButton btn, CharSequence fullIconClassName0, CharSequence fullIconClassName1, CharSequence text) {
        btn.getUpFace().setHTML(TGS_IconUtils.createSpan(fullIconClassName0, TGS_IconUtils.createSpan(fullIconClassName1, text)));
    }

    public static void setIcon(CustomButton btn, CharSequence fullIconClassName, CharSequence text) {
        btn.getUpFace().setHTML(TGS_IconUtils.createSpan(fullIconClassName, text));
    }

//    public static void setIcon(CustomButton btn, String fullIconClassName, String text) {
//        btn.getUpFace().setHTML(TGC_IconUtils.createSpan(fullIconClassName, text));
//        btn.getUpFace().setHTML(TGC_IconUtils.createSpan(fullIconClassName, text));
//        btn.getDownFace().setHTML(TGC_IconUtils.createSpan(fullIconClassName, text));
//        
//    }
    public static ToggleButton createIconToggle(CharSequence fullIconClassName, CharSequence text) {
        var btn = new ToggleButton();
        btn.getUpFace().setHTML(TGS_IconUtils.createSpan(fullIconClassName, text));
        return btn;
    }
}
