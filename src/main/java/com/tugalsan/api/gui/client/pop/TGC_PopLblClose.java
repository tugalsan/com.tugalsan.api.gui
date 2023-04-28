package com.tugalsan.api.gui.client.pop;

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

public class TGC_PopLblClose implements TGC_PopInterface {

    final private static TGC_Log d = TGC_Log.of(TGC_PopLblClose.class);

    public TGC_PopLblClose(TGC_Dimension dim,
            CharSequence lblHTML, CharSequence btnOkText,
            TGS_RunnableType1<TGC_PopLblClose> onExe,
            TGS_RunnableType1<TGC_PopLblClose> onEsc,
            TGS_Runnable onVisible_optional) {
        this(dim,
                lblHTML, btnOkText,
                onExe, onVisible_optional,
                null
        );
    }

    public TGC_PopLblClose(TGC_Dimension dim,
            CharSequence lblHTML, CharSequence btnOkText,
            TGS_RunnableType1<TGC_PopLblClose> onExe,
            TGS_Runnable onVisible_optional, CharSequence iconClassExe_optional) {
        this.dim = dim;
        this.lblHTML = lblHTML.toString();
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
    final private String lblHTML, btnOkText;
    final public TGS_RunnableType1<TGC_PopLblClose> onExe;
    final private TGS_Runnable onVisible;

    @Override
    public void createWidgets() {
        btnExe = TGC_ButtonUtils.createIcon(iconClassExe == null ? TGS_IconUtils.CLASS_CHECKMARK() : iconClassExe, btnOkText);
        label = new HTML(lblHTML);
    }
    public HTML label;
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
        panelPopup = new TGC_Pop(
                TGC_PanelLayoutUtils.createDockNorth(
                        2,
                        TGC_PanelLayoutUtils.createVertical(
                                btnExe,
                                label
                        ),
                        new HTML("")
                ),
                dim, onVisible
        );
    }
    private TGC_Pop panelPopup;

    @Override
    public TGC_Pop getPop() {
        return panelPopup;
    }
}
