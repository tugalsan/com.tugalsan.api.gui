package com.tugalsan.api.gui.client.card;

import com.tugalsan.api.callable.client.TGS_CallableType0Void;

public class TGC_Card {

    public CharSequence optional_urlImgForeground;
    public CharSequence optional_urlImgBackground;
    public CharSequence htmlContent;
    public TGS_CallableType0Void optionalAction;
    public boolean span_nocolor = false;

    public TGC_Card() {
        span_nocolor = true;
    }

    public TGC_Card(CharSequence optional_urlImgForeground, CharSequence optional_urlImgBackground, CharSequence htmlContent) {
        this.optional_urlImgForeground = optional_urlImgForeground;
        this.optional_urlImgBackground = optional_urlImgBackground;
        this.htmlContent = htmlContent;
    }

    public TGC_Card(CharSequence optional_urlImgForeground, CharSequence optional_urlImgBackground, CharSequence htmlContent, TGS_CallableType0Void optionalAction) {
        this(optional_urlImgForeground, optional_urlImgBackground, htmlContent);
        this.optionalAction = optionalAction;
    }
}
