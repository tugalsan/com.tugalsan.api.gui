package com.tugalsan.api.gui.client.pop;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.datepicker.client.DateBox;
import com.tugalsan.api.log.client.TGC_Log;
import com.tugalsan.api.gui.client.click.TGC_ClickUtils;
import com.tugalsan.api.gui.client.focus.TGC_FocusUtils;
import com.tugalsan.api.gui.client.focus.TGS_FocusSides4;
import com.tugalsan.api.gui.client.key.TGC_KeyUtils;
import com.tugalsan.api.gui.client.dim.TGC_Dimension;
import com.tugalsan.api.gui.client.widget.TGC_ButtonUtils;
import com.tugalsan.api.gui.client.panel.TGC_PanelLayoutUtils;
import com.tugalsan.api.icon.client.TGS_IconUtils;
import com.tugalsan.api.executable.client.TGS_Executable;
import com.tugalsan.api.executable.client.TGS_ExecutableType1;
import com.tugalsan.api.gui.client.widget.TGC_DateBoxUtils;
import com.tugalsan.api.network.client.TGS_NetworkHTMLUtils;
import com.tugalsan.api.string.client.TGS_StringUtils;
import com.tugalsan.api.thread.client.TGC_ThreadUtils;

public class TGC_PopLblYesNoDateBoxExtraBtn2 implements TGC_PopInterface {

    final private static TGC_Log d = TGC_Log.of(TGC_PopLblYesNoDateBoxExtraBtn2.class.getSimpleName());

    public void setLabelHTML(String html) {
        lblHtml = TGS_NetworkHTMLUtils.HTML_SPACE() + html;
        label.setHTML(lblHtml);
    }
    private String lblHtml;

    public void setVisibleDatePicker(boolean visible) {
        TGC_ThreadUtils.execute_afterGUIUpdate(() -> {
            if (visible) {
                dateBox.showDatePicker();
            } else {
                dateBox.hideDatePicker();
            }
            d.ci("setVisibleDatePicker", "visible", visible);
        });
//        TGC_DOMUtils.setVisible(dateBox.getDatePicker().getElement(), visible);//TODO CAUSES A GLASS PANEL ON LEFT TOP
    }

    public TGC_PopLblYesNoDateBoxExtraBtn2(TGC_Dimension dim,
            CharSequence lblText, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_ExecutableType1<TGC_PopLblYesNoDateBoxExtraBtn2> onExe,
            TGS_ExecutableType1<TGC_PopLblYesNoDateBoxExtraBtn2> onEsc,
            TGS_Executable onVisible_optional) {
        this(dim,
                lblText, btnOkText, btnCancelText,
                onExe, onEsc, onVisible_optional,
                null, null
        );
    }

    public TGC_PopLblYesNoDateBoxExtraBtn2(TGC_Dimension dim,
            CharSequence lblHtml, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_ExecutableType1<TGC_PopLblYesNoDateBoxExtraBtn2> onExe,
            TGS_ExecutableType1<TGC_PopLblYesNoDateBoxExtraBtn2> onEsc,
            TGS_Executable onVisible_optional, CharSequence iconClassExe_optional, CharSequence iconClassEsc_optional) {
        this.dim = dim;
        this.lblHtml = lblHtml.toString();
        this.btnOkText = btnOkText.toString();
        this.btnCancelText = btnCancelText.toString();
        this.onEsc = onEsc;
        this.onExe = onExe;
        this.onVisible = () -> {
            setVisibleDatePicker(false);
            if (onVisible_optional != null) {
                onVisible_optional.execute();
            }
        };
        this.iconClassExe = iconClassExe_optional == null ? null : iconClassExe_optional.toString();
        this.iconClassEsc = iconClassEsc_optional == null ? null : iconClassEsc_optional.toString();
        createWidgets();
        createPops();
        configInit();
        configActions();
        configFocus();
        configLayout();
        panelPopup.setVisible_focus = dateBox.getTextBox();
    }
    private TGC_Dimension dim;
    private String iconClassExe, iconClassEsc;
    final private String btnOkText, btnCancelText;
    final public TGS_ExecutableType1<TGC_PopLblYesNoDateBoxExtraBtn2> onEsc, onExe;
    final public TGS_Executable onVisible;

    @Override
    public void createWidgets() {
        btnEsc = TGC_ButtonUtils.createIcon(iconClassEsc == null ? TGS_IconUtils.CLASS_CROSS() : iconClassEsc, btnCancelText);
        btnExe = TGC_ButtonUtils.createIcon(iconClassExe == null ? TGS_IconUtils.CLASS_CHECKMARK() : iconClassExe, btnOkText);
        label = new HTML(lblHtml);
        dateBox = TGC_DateBoxUtils.create();
        btnAdd1 = TGC_ButtonUtils.createIcon(TGS_IconUtils.CLASS_HOUR_GLASS(), "btnAdd1");
        btnAdd2 = TGC_ButtonUtils.createIcon(TGS_IconUtils.CLASS_HOUR_GLASS(), "btnAdd2");
    }
    public PushButton btnEsc, btnExe;
    public HTML label;
    public DateBox dateBox;
    private PushButton btnAdd1, btnAdd2;

    @Override
    public void createPops() {
    }

    @Override
    public void configInit() {
        TGC_ClickUtils.add(dateBox.getTextBox(), () -> setVisibleDatePicker(true));
    }

    @Override
    public void configActions() {
        TGC_ClickUtils.add(btnExe, () -> onExe.execute(this));
        TGC_ClickUtils.add(btnEsc, () -> onEsc.execute(this));
        TGC_ClickUtils.add(btnAdd1, () -> {
            if (onAdd1 == null) {
                d.ce("configActions", "Did you forget to set Action for onAdd1?");
            } else {
                onAdd1.execute(this);
            }
        });
        TGC_ClickUtils.add(btnAdd2, () -> {
            if (onAdd2 == null) {
                d.ce("configActions", "Did you forget to set Action for onAdd2?");
            } else {
                onAdd2.execute(this);
            }
        });
        TGC_KeyUtils.add(btnExe, () -> onExe.execute(this), () -> onEsc.execute(this));
        TGC_KeyUtils.add(btnEsc, () -> onEsc.execute(this), () -> onEsc.execute(this));
        TGC_KeyUtils.add(dateBox.getTextBox(), () -> onExe.execute(this), () -> onEsc.execute(this));
        TGC_KeyUtils.add(btnAdd1, () -> {
            if (onAdd1 == null) {
                d.ce("configActions", "Did you forget to set Action for onAdd1?");
            } else {
                onAdd1.execute(this);
            }
        }, () -> onEsc.execute(this));
        TGC_KeyUtils.add(btnAdd1, () -> {
            if (onAdd2 == null) {
                d.ce("configActions", "Did you forget to set Action for onAdd2?");
            } else {
                onAdd2.execute(this);
            }
        }, () -> onEsc.execute(this));
    }

    @Override
    public void configFocus() {
        TGC_FocusUtils.addKeyDown(btnEsc, new TGS_FocusSides4(null, btnExe, null, dateBox.getTextBox()));
        TGC_FocusUtils.addKeyDown(btnExe, new TGS_FocusSides4(btnEsc, dateBox.getTextBox(), null, dateBox.getTextBox()));
        TGC_FocusUtils.addKeyDown(dateBox.getTextBox(), new TGS_FocusSides4(null, null, btnEsc, btnAdd1));
        TGC_FocusUtils.addKeyDown(btnAdd1, new TGS_FocusSides4(null, btnAdd2, dateBox.getTextBox(), null));
        TGC_FocusUtils.addKeyDown(btnAdd2, new TGS_FocusSides4(btnAdd1, null, dateBox.getTextBox(), null));
    }

    @Override
    public void configLayout() {
        btnAdd1.setVisible(false);
        btnAdd2.setVisible(false);
        var maxWidth = dim == null ? null : dim.getWidth();
        panelPopup = new TGC_Pop(
                TGC_PanelLayoutUtils.createDockNorth(4,
                        TGC_PanelLayoutUtils.createVertical(TGC_PanelLayoutUtils.createGridPair(maxWidth, 50, btnEsc, btnExe),
                                label,
                                dateBox,
                                TGC_PanelLayoutUtils.createGridPair(maxWidth, 50, btnAdd1, btnAdd2)
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

    //EXTRA BUTTON
    public void btnAddHide(boolean hide1, boolean hide2) {
        d.ci("btnAddHide", hide1, hide2);
        btnAdd1.setVisible(!hide1);
        btnAdd2.setVisible(!hide2);
    }

    public void btnAddShowAs(String optional_iconClass1Name, String optional_AddBtn1Text, String optional_iconClass2Name, String optional_AddBtn2Text) {
        d.ci("btnAddShowAs", "#0", optional_iconClass1Name, optional_AddBtn1Text, optional_iconClass2Name, optional_AddBtn2Text);
        optional_iconClass1Name = TGS_StringUtils.toEmptyIfNull(optional_iconClass1Name);
        optional_AddBtn1Text = TGS_StringUtils.toEmptyIfNull(optional_AddBtn1Text);
        optional_iconClass2Name = TGS_StringUtils.toEmptyIfNull(optional_iconClass2Name);
        optional_AddBtn2Text = TGS_StringUtils.toEmptyIfNull(optional_AddBtn2Text);
        d.ci("btnAddShowAs", "#1", optional_iconClass1Name, optional_AddBtn1Text, optional_iconClass2Name, optional_AddBtn2Text);
        btnAddHide(optional_iconClass1Name.isEmpty() && optional_AddBtn1Text.isEmpty(), optional_iconClass2Name.isEmpty() && optional_AddBtn2Text.isEmpty());
        TGC_ButtonUtils.setIcon(btnAdd1, optional_iconClass1Name, optional_AddBtn1Text);
        TGC_ButtonUtils.setIcon(btnAdd2, optional_iconClass2Name, optional_AddBtn2Text);
    }

    public void btnAddSet(TGS_ExecutableType1<TGC_PopLblYesNoDateBoxExtraBtn2> onAdd1, TGS_ExecutableType1<TGC_PopLblYesNoDateBoxExtraBtn2> onAdd2) {
        this.onAdd1 = onAdd1;
        this.onAdd2 = onAdd2;
    }
    private TGS_ExecutableType1<TGC_PopLblYesNoDateBoxExtraBtn2> onAdd1, onAdd2;
}
