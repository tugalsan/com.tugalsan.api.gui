package com.tugalsan.api.gui.client.pop;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PushButton;
import com.tugalsan.api.log.client.TGC_Log;
import com.tugalsan.api.gui.client.click.TGC_ClickUtils;
import com.tugalsan.api.gui.client.focus.TGC_FocusUtils;
import com.tugalsan.api.gui.client.focus.TGS_FocusSides4;
import com.tugalsan.api.gui.client.key.TGC_KeyUtils;
import com.tugalsan.api.gui.client.dim.TGC_Dimension;
import com.tugalsan.api.gui.client.panel.TGC_PanelLayoutUtils;
import com.tugalsan.api.gui.client.widget.TGC_ButtonUtils;
import com.tugalsan.api.icon.client.TGS_IconUtils;
import com.tugalsan.api.executable.client.TGS_Executable;
import com.tugalsan.api.executable.client.TGS_ExecutableType1;

public class TGC_PopLblYesNo implements TGC_PopInterface {

    final private static TGC_Log d = TGC_Log.of(TGC_PopLblYesNo.class.getSimpleName());

    public TGC_PopLblYesNo(TGC_Dimension dim,
            CharSequence lblHTML, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_ExecutableType1<TGC_PopLblYesNo> onExe,
            TGS_ExecutableType1<TGC_PopLblYesNo> onEsc,
            TGS_Executable onVisible_optional) {
        this(dim,
                lblHTML, btnOkText, btnCancelText,
                onExe, onEsc, onVisible_optional,
                null, null
        );
    }

    public TGC_PopLblYesNo(TGC_Dimension dim,
            CharSequence lblHTML, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_ExecutableType1<TGC_PopLblYesNo> onExe,
            TGS_ExecutableType1<TGC_PopLblYesNo> onEsc,
            TGS_Executable onVisible_optional, CharSequence iconClassExe_optional, CharSequence iconClassEsc_optional) {
        this.dim = dim;
        this.lblHTML = lblHTML.toString();
        this.btnOkText = btnOkText.toString();
        this.btnCancelText = btnCancelText.toString();
        this.onExe = onExe;
        this.onEsc = onEsc;
        this.onVisible = onVisible_optional;
        this.iconClassExe = iconClassExe_optional == null ? null : iconClassExe_optional.toString();
        this.iconClassEsc = iconClassEsc_optional == null ? null : iconClassEsc_optional.toString();
        createWidgets();
        createPops();
        configInit();
        configActions();
        configFocus();
        configLayout();
        panelPopup.setVisible_focus = btnExe;
    }
    private String iconClassExe, iconClassEsc;
    private TGC_Dimension dim;
    final private String lblHTML, btnOkText, btnCancelText;
    final public TGS_ExecutableType1<TGC_PopLblYesNo> onEsc, onExe;
    final private TGS_Executable onVisible;

    @Override
    public void createWidgets() {
        btnEsc = TGC_ButtonUtils.createIcon(iconClassEsc == null ? TGS_IconUtils.CLASS_CROSS() : iconClassEsc, btnCancelText);
        btnExe = TGC_ButtonUtils.createIcon(iconClassExe == null ? TGS_IconUtils.CLASS_CHECKMARK() : iconClassExe, btnOkText);
        label = new HTML(lblHTML);
    }
    public HTML label;
    public PushButton btnEsc, btnExe;

    @Override
    public void createPops() {
    }

    @Override
    public void configInit() {
    }

    @Override
    public void configActions() {
        TGC_ClickUtils.add(btnEsc, () -> onEsc.execute(this));
        TGC_ClickUtils.add(btnExe, () -> onExe.execute(this));
        TGC_KeyUtils.add(btnExe, () -> onExe.execute(this), () -> onEsc.execute(this));
        TGC_KeyUtils.add(btnEsc, () -> onEsc.execute(this), () -> onEsc.execute(this));
    }

    @Override
    public void configFocus() {
        TGC_FocusUtils.addKeyDown(btnEsc, new TGS_FocusSides4(null, btnExe, null, null));
        TGC_FocusUtils.addKeyDown(btnExe, new TGS_FocusSides4(btnEsc, null, null, null));
    }

    @Override
    public void configLayout() {
        var maxWidth = dim == null ? null : dim.getWidth();
        panelPopup = new TGC_Pop(
                TGC_PanelLayoutUtils.createDockNorth(
                        2,
                        TGC_PanelLayoutUtils.createVertical(
                                TGC_PanelLayoutUtils.createGridPair(maxWidth, null, btnEsc, btnExe),
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
