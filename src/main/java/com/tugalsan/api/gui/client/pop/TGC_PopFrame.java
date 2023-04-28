package com.tugalsan.api.gui.client.pop;

import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PushButton;
import com.tugalsan.api.log.client.TGC_Log;
import com.tugalsan.api.gui.client.click.TGC_ClickUtils;
import com.tugalsan.api.gui.client.key.TGC_KeyUtils;
import com.tugalsan.api.gui.client.dim.TGC_Dimension;
import com.tugalsan.api.gui.client.panel.TGC_PanelLayoutUtils;
import com.tugalsan.api.gui.client.widget.TGC_ButtonUtils;
import com.tugalsan.api.icon.client.TGS_IconUtils;
import com.tugalsan.api.runnable.client.TGS_Runnable;
import com.tugalsan.api.runnable.client.TGS_RunnableType1;
import com.tugalsan.api.url.client.TGS_Url;

public class TGC_PopFrame implements TGC_PopInterface {

    final private static TGC_Log d = TGC_Log.of(TGC_PopFrame.class);

    public TGC_PopFrame(TGC_Dimension dim,
            TGS_Url url_optional, CharSequence btnOkText,
            TGS_RunnableType1<TGC_PopFrame> onExe,
            TGS_Runnable onVisible_optional) {
        this(dim,
                url_optional, btnOkText,
                onExe, onVisible_optional,
                null
        );
    }

    public TGC_PopFrame(TGC_Dimension dim,
            TGS_Url url_optional, CharSequence btnOkText,
            TGS_RunnableType1<TGC_PopFrame> onExe,
            TGS_Runnable onVisible_optional, CharSequence iconClassExe_optional) {
        this.dim = dim;
        this.url = url_optional == null ? new TGS_Url("") : url_optional;
        this.btnOkText = btnOkText.toString();
        this.onExe = onExe;
        this.onVisible = onVisible_optional;
        this.iconClassExe = iconClassExe_optional == null ? null : iconClassExe_optional.toString();
        createWidgets();
        createPops();
        configInit();
        configActions();
        configFocus();
        configLayout();
        panelPopup.setVisible_focus = btnExe;
    }
    private String iconClassExe;
    private TGC_Dimension dim;
    final private TGS_Url url;
    final private String btnOkText;
    final public TGS_RunnableType1<TGC_PopFrame> onExe;
    final private TGS_Runnable onVisible;

    @Override
    public void createWidgets() {
        btnExe = TGC_ButtonUtils.createIcon(iconClassExe == null ? TGS_IconUtils.CLASS_CHECKMARK() : iconClassExe, btnOkText);
        frame = new Frame(url.toString());
    }
    public Frame frame;
    public PushButton btnExe;

    @Override
    public void createPops() {
    }

    @Override
    public void configInit() {
    }

    @Override
    public void configActions() {
        TGC_ClickUtils.add(btnExe, () -> onExe.run(this));
        TGC_KeyUtils.add(btnExe, () -> onExe.run(this), () -> onExe.run(this));
    }

    @Override
    public void configFocus() {
    }

    @Override
    public void configLayout() {
        frame.setWidth("100%");
        frame.setHeight("100%");
        panelPopup = new TGC_Pop(
                TGC_PanelLayoutUtils.createDockNorth(1,
                        btnExe,
                        frame
                ),
                dim, onVisible
        );
    }
    private TGC_Pop panelPopup;

    @Override
    public TGC_Pop getPop() {
        return panelPopup;
    }

    public void setUrl(TGS_Url newUrl) {
        url.setUrl(newUrl.getUrl());
        frame.setUrl(url.getUrl().toString());
    }
}
