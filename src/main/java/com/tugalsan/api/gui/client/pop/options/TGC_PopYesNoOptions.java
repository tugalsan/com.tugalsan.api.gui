package com.tugalsan.api.gui.client.pop.options;

import com.google.gwt.user.client.ui.*;
import com.tugalsan.api.callable.client.TGS_CallableType0_Run;
import com.tugalsan.api.callable.client.TGS_CallableType1_Run;
import com.tugalsan.api.gui.client.pop.*;
import com.tugalsan.api.gui.client.click.TGC_ClickUtils;
import com.tugalsan.api.gui.client.focus.TGC_FocusUtils;
import com.tugalsan.api.gui.client.focus.TGS_FocusSides4;
import com.tugalsan.api.gui.client.key.TGC_KeyUtils;
import com.tugalsan.api.gui.client.panel.TGC_PanelLayoutUtils;
import com.tugalsan.api.gui.client.dim.TGC_Dimension;
import com.tugalsan.api.gui.client.widget.TGC_ButtonUtils;
import com.tugalsan.api.icon.client.TGS_IconUtils;



public class TGC_PopYesNoOptions implements TGC_PopInterface {

//    final private static TGC_Log d = TGC_Log.of(TGC_PopYesNoOptions.class);

    final private String btnOkText, btnCancelText;
    final public TGS_CallableType1_Run<TGC_PopYesNoOptions> onEsc, onExe;
    final private TGS_CallableType0_Run onVisible;

    public TGC_PopYesNoOptions(TGC_Dimension dim,
            CharSequence btnOkText, CharSequence btnCancelText,
            TGS_CallableType1_Run<TGC_PopYesNoOptions> onExe,
            TGS_CallableType1_Run<TGC_PopYesNoOptions> onEsc,
            TGS_CallableType0_Run onVisible_optional) {
        this(dim, btnOkText, btnCancelText,
                onExe, onEsc, onVisible_optional, null, null);
    }

    public TGC_PopYesNoOptions(TGC_Dimension dim,
            CharSequence btnOkText, CharSequence btnCancelText,
            TGS_CallableType1_Run<TGC_PopYesNoOptions> onExe,
            TGS_CallableType1_Run<TGC_PopYesNoOptions> onEsc,
            TGS_CallableType0_Run onVisible_optional, CharSequence iconClassExe_optional, CharSequence iconClassEsc_optional) {
        this.dim = dim;
        this.btnOkText = btnOkText.toString();
        this.btnCancelText = btnCancelText.toString();
        this.onEsc = onEsc;
        this.onExe = onExe;
        this.onVisible = onVisible_optional;
        this.iconClassExe = iconClassExe_optional == null ? null : iconClassExe_optional.toString();
        this.iconClassEsc = iconClassEsc_optional == null ? null : iconClassEsc_optional.toString();
        createWidgets();
        createPops();
        configInit();
        configActions();
        configFocus();
        configLayout();
        panelPopup.setVisible_focus = btnEsc;
    }
    private TGC_Dimension dim;
    private String iconClassExe, iconClassEsc;

    public PushButton btnEsc, btnExe;

    @Override
    final public void createWidgets() {
        btnEsc = TGC_ButtonUtils.createIcon(iconClassEsc == null ? TGS_IconUtils.CLASS_CROSS() : iconClassEsc, btnCancelText);
        btnExe = TGC_ButtonUtils.createIcon(iconClassExe == null ? TGS_IconUtils.CLASS_CHECKMARK() : iconClassExe, btnOkText);
    }

    @Override
    final public void createPops() {
    }

    @Override
    final public void configInit() {
    }

    @Override
    final public void configActions() {
        TGC_ClickUtils.add(btnEsc, () -> onEsc.run(this));
        TGC_ClickUtils.add(btnExe, () -> onExe.run(this));
        TGC_KeyUtils.add(btnExe, () -> onExe.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(btnEsc, () -> onEsc.run(this), () -> onEsc.run(this));
    }

    @Override
    final public void configFocus() {
        FocusWidget widgetDown = null;
        TGC_FocusUtils.addKeyDown(btnEsc, new TGS_FocusSides4(null, btnExe, null, widgetDown));
        TGC_FocusUtils.addKeyDown(btnExe, new TGS_FocusSides4(btnEsc, widgetDown, null, widgetDown));
    }

    @Override
    final public void configLayout() {
        var maxWidth = dim == null ? null : dim.getWidth();
        Integer[] columnPercent = {50, 50};
        var widgets = new Widget[]{
            btnEsc, btnExe
        };
        var grid = TGC_PanelLayoutUtils.createGrid(maxWidth, columnPercent, widgets, false);
        panelPopup = new TGC_Pop(grid, dim, onVisible);
    }
    private TGC_Pop panelPopup;

    @Override
    final public TGC_Pop getPop() {
        return panelPopup;
    }
}
