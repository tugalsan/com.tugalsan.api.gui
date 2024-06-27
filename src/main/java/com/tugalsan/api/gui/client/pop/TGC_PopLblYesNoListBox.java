package com.tugalsan.api.gui.client.pop;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.tugalsan.api.function.client.TGS_Func;
import com.tugalsan.api.function.client.TGS_Func_In1;
import java.util.List;
import com.tugalsan.api.gui.client.click.TGC_ClickUtils;
import com.tugalsan.api.gui.client.focus.TGC_FocusUtils;
import com.tugalsan.api.gui.client.focus.TGS_FocusSides4;
import com.tugalsan.api.gui.client.key.TGC_KeyUtils;
import com.tugalsan.api.gui.client.panel.TGC_PanelLayoutUtils;
import com.tugalsan.api.gui.client.widget.TGC_ListBoxUtils;
import com.tugalsan.api.gui.client.browser.TGC_BrowserNavigatorUtils;
import com.tugalsan.api.gui.client.dim.TGC_Dimension;
import com.tugalsan.api.gui.client.widget.TGC_ButtonUtils;
import com.tugalsan.api.icon.client.TGS_IconUtils;



public class TGC_PopLblYesNoListBox implements TGC_PopInterface {

//    final private static TGC_Log d = TGC_Log.of(TGC_PopLblYesNoListBox.class);

    final private String lblHTML, btnOkText, btnCancelText;
    final public TGS_Func_In1<TGC_PopLblYesNoListBox> onEsc, onExe;
    final public List<String> listBoxContent;
    final private TGS_Func onVisible;

    public TGC_PopLblYesNoListBox(TGC_Dimension dim,
            List<String> listBoxContent_optional,
            CharSequence lblHTML, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_Func_In1<TGC_PopLblYesNoListBox> onExe,
            TGS_Func_In1<TGC_PopLblYesNoListBox> onEsc,
            TGS_Func onVisible_optional) {
        this(dim, listBoxContent_optional,
                lblHTML, btnOkText, btnCancelText,
                onExe, onEsc, onVisible_optional, null, null);
    }

    public TGC_PopLblYesNoListBox(TGC_Dimension dim,
            List<String> listBoxContent_optional,
            CharSequence lblHTML, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_Func_In1<TGC_PopLblYesNoListBox> onExe,
            TGS_Func_In1<TGC_PopLblYesNoListBox> onEsc,
            TGS_Func onVisible_optional, CharSequence iconClassExe_optional, CharSequence iconClassEsc_optional) {
        this.dim = dim;
        this.lblHTML = lblHTML.toString();
        this.btnOkText = btnOkText.toString();
        this.btnCancelText = btnCancelText.toString();
        this.onEsc = onEsc;
        this.onExe = onExe;
        this.listBoxContent = listBoxContent_optional;
        this.onVisible = onVisible_optional;
        this.iconClassExe = iconClassExe_optional == null ? null : iconClassExe_optional.toString();
        this.iconClassEsc = iconClassEsc_optional == null ? null : iconClassEsc_optional.toString();
        createWidgets();
        createPops();
        configInit();
        configActions();
        configFocus();
        configLayout();
        panelPopup.setVisible_focus = listBox;
    }
    private TGC_Dimension dim;
    private String iconClassExe, iconClassEsc;

    private TGC_Pop panelPopup;
    public HTML label;
    public PushButton btnEsc, btnExe;
    public ListBox listBox;

    final public void selectLastItem() {
        listBox.setSelectedIndex(listBox.getItemCount() - 1);
    }

    @Override
    final public void createWidgets() {
        btnEsc = TGC_ButtonUtils.createIcon(iconClassEsc == null ? TGS_IconUtils.CLASS_CROSS() : iconClassEsc, btnCancelText);
        btnExe = TGC_ButtonUtils.createIcon(iconClassExe == null ? TGS_IconUtils.CLASS_CHECKMARK() : iconClassExe, btnOkText);
        label = new HTML(lblHTML);
        listBox = TGC_ListBoxUtils.create(false);
    }

    @Override
    final public void createPops() {
    }

    @Override
    final public void configInit() {
        if (listBoxContent != null) {
            listBoxContent.stream().forEachOrdered(s -> listBox.addItem(s));
        }
    }

    @Override
    final public void configActions() {
        TGC_ClickUtils.add(btnEsc, () -> onEsc.run(this));
        TGC_ClickUtils.add(btnExe, () -> onExe.run(this));
        TGC_ClickUtils.add(listBox, null, () -> onExe.run(this));
        TGC_KeyUtils.add(btnExe, () -> onExe.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(btnEsc, () -> onEsc.run(this), () -> onEsc.run(this));
        TGC_KeyUtils.add(listBox, () -> onExe.run(this), () -> onEsc.run(this));
    }

    @Override
    final public void configFocus() {
        TGC_FocusUtils.addKeyDown(btnEsc, new TGS_FocusSides4(null, btnExe, null, listBox));
        TGC_FocusUtils.addKeyDown(btnExe, new TGS_FocusSides4(btnEsc, listBox, null, listBox));
        TGC_FocusUtils.addKeyDown(listBox, new TGS_FocusSides4(btnExe, null, null, null));
    }

    @Override
    final public void configLayout() {
        var maxWidth = dim == null ? null : dim.getWidth();
        var mobile = TGC_BrowserNavigatorUtils.mobile();
        Integer[] columnPercent = {50, 50};
        Widget[] widgets;
        if (mobile) {
            widgets = new Widget[]{
                btnEsc, btnExe,
                label, listBox
            };
        } else {
            widgets = new Widget[]{
                btnEsc, btnExe,
                label, null
            };
        }
        var grid = TGC_PanelLayoutUtils.createGrid(maxWidth, columnPercent, widgets, false);
        panelPopup = new TGC_Pop(
                TGC_PanelLayoutUtils.createDockNorth(2, grid, mobile ? new HTML() : listBox),
                dim, onVisible
        );
    }

    @Override
    final public TGC_Pop getPop() {
        return panelPopup;
    }
}
