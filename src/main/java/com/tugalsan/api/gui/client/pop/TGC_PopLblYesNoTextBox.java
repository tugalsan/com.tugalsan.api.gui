package com.tugalsan.api.gui.client.pop;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.tugalsan.api.function.client.TGS_Func;
import com.tugalsan.api.function.client.TGS_Func_In1;
import com.tugalsan.api.gui.client.click.TGC_ClickUtils;
import com.tugalsan.api.gui.client.focus.TGC_FocusUtils;
import com.tugalsan.api.gui.client.focus.TGS_FocusSides4;
import com.tugalsan.api.gui.client.key.TGC_KeyUtils;
import com.tugalsan.api.gui.client.dim.TGC_Dimension;
import com.tugalsan.api.gui.client.widget.TGC_ButtonUtils;
import com.tugalsan.api.gui.client.panel.TGC_PanelLayoutUtils;
import com.tugalsan.api.icon.client.TGS_IconUtils;



public class TGC_PopLblYesNoTextBox implements TGC_PopInterface {

//    final private static TGC_Log d = TGC_Log.of(TGC_PopLblYesNoTextBox.class);

    public TGC_PopLblYesNoTextBox(TGC_Dimension dim,
            CharSequence lblHTML, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_Func_In1<TGC_PopLblYesNoTextBox> onExe,
            TGS_Func_In1<TGC_PopLblYesNoTextBox> onEsc,
            TGS_Func onVisible_optional) {
        this(dim,
                lblHTML, btnOkText, btnCancelText,
                onExe, onEsc, onVisible_optional,
                null, null
        );
    }

    public TGC_PopLblYesNoTextBox(TGC_Dimension dim,
            CharSequence lblHTML, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_Func_In1<TGC_PopLblYesNoTextBox> onExe,
            TGS_Func_In1<TGC_PopLblYesNoTextBox> onEsc,
            TGS_Func onVisible_optional, CharSequence iconClassExe_optional, CharSequence iconClassEsc_optional) {
        this.dim = dim;
        this.lblHTML = lblHTML.toString();
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
        panelPopup.setVisible_focus = textBox;
    }
    private String iconClassExe, iconClassEsc;
    private TGC_Dimension dim;
    final private String lblHTML, btnOkText, btnCancelText;
    final public TGS_Func_In1<TGC_PopLblYesNoTextBox> onEsc, onExe;
    final public TGS_Func onVisible;

    @Override
    final public void createWidgets() {
        btnEsc = TGC_ButtonUtils.createIcon(iconClassEsc == null ? TGS_IconUtils.CLASS_CROSS() : iconClassEsc, btnCancelText);
        btnExe = TGC_ButtonUtils.createIcon(iconClassExe == null ? TGS_IconUtils.CLASS_CHECKMARK() : iconClassExe, btnOkText);
        label = new HTML(lblHTML);
        textBox = new TextBox();
    }
    public PushButton btnEsc, btnExe;
    public HTML label;
    public TextBox textBox;

    @Override
    final public void createPops() {
    }

    @Override
    final public void configInit() {
    }

    @Override
    final public void configActions() {
        TGC_ClickUtils.add(btnExe, () -> onExe.run(this));
        TGC_ClickUtils.add(btnEsc, () -> onEsc.run(this));
        TGC_KeyUtils.add(btnExe, () -> onExe.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(btnEsc, () -> onEsc.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(textBox, () -> onExe.run(this), () -> onEsc.run(this));
    }

    @Override
    final public void configFocus() {
        TGC_FocusUtils.addKeyDown(btnEsc, new TGS_FocusSides4(null, btnExe, null, textBox));
        TGC_FocusUtils.addKeyDown(btnExe, new TGS_FocusSides4(btnEsc, textBox, null, textBox));
        TGC_FocusUtils.addKeyDown(textBox, new TGS_FocusSides4(null, null, btnEsc, null));
    }

    @Override
    final public void configLayout() {
        var maxWidth = dim == null ? null : dim.getWidth();
        panelPopup = new TGC_Pop(
                TGC_PanelLayoutUtils.createDockNorth(
                        3,
                        TGC_PanelLayoutUtils.createVertical(
                                TGC_PanelLayoutUtils.createGridPair(maxWidth, 50, btnEsc, btnExe),
                                label,
                                textBox
                        ),
                        new HTML("")
                ),
                dim, onVisible
        );
    }
    private TGC_Pop panelPopup;

    @Override
    final public TGC_Pop getPop() {
        return panelPopup;
    }
}
