package com.tugalsan.api.gui.client.pop;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;
import com.tugalsan.api.log.client.TGC_Log;
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
import com.tugalsan.api.executable.client.TGS_Executable;
import com.tugalsan.api.executable.client.TGS_ExecutableType1;

public class TGC_PopLblYesNoListBox implements TGC_PopInterface {

    final private static TGC_Log d = TGC_Log.of(TGC_PopLblYesNoListBox.class);

    final private String lblHTML, btnOkText, btnCancelText;
    final public TGS_ExecutableType1<TGC_PopLblYesNoListBox> onEsc, onExe;
    final public List<String> listBoxContent;
    final private TGS_Executable onVisible;

    public TGC_PopLblYesNoListBox(TGC_Dimension dim,
            List<String> listBoxContent_optional,
            CharSequence lblHTML, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_ExecutableType1<TGC_PopLblYesNoListBox> onExe,
            TGS_ExecutableType1<TGC_PopLblYesNoListBox> onEsc,
            TGS_Executable onVisible_optional) {
        this(dim, listBoxContent_optional,
                lblHTML, btnOkText, btnCancelText,
                onExe, onEsc, onVisible_optional, null, null);
    }

    public TGC_PopLblYesNoListBox(TGC_Dimension dim,
            List<String> listBoxContent_optional,
            CharSequence lblHTML, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_ExecutableType1<TGC_PopLblYesNoListBox> onExe,
            TGS_ExecutableType1<TGC_PopLblYesNoListBox> onEsc,
            TGS_Executable onVisible_optional, CharSequence iconClassExe_optional, CharSequence iconClassEsc_optional) {
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

    public void selectLastItem() {
        listBox.setSelectedIndex(listBox.getItemCount() - 1);
    }

    @Override
    public void createWidgets() {
        btnEsc = TGC_ButtonUtils.createIcon(iconClassEsc == null ? TGS_IconUtils.CLASS_CROSS() : iconClassEsc, btnCancelText);
        btnExe = TGC_ButtonUtils.createIcon(iconClassExe == null ? TGS_IconUtils.CLASS_CHECKMARK() : iconClassExe, btnOkText);
        label = new HTML(lblHTML);
        listBox = TGC_ListBoxUtils.create(false);
    }

    @Override
    public void createPops() {
    }

    @Override
    public void configInit() {
        if (listBoxContent != null) {
            listBoxContent.stream().forEachOrdered(s -> listBox.addItem(s));
        }
    }

    @Override
    public void configActions() {
        TGC_ClickUtils.add(btnEsc, () -> onEsc.execute(this));
        TGC_ClickUtils.add(btnExe, () -> onExe.execute(this));
        TGC_ClickUtils.add(listBox, null, () -> onExe.execute(this));
        TGC_KeyUtils.add(btnExe, () -> onExe.execute(this), () -> onEsc.execute(this));
        TGC_KeyUtils.add(btnEsc, () -> onEsc.execute(this), () -> onEsc.execute(this));
        TGC_KeyUtils.add(listBox, () -> onExe.execute(this), () -> onEsc.execute(this));
    }

    @Override
    public void configFocus() {
        TGC_FocusUtils.addKeyDown(btnEsc, new TGS_FocusSides4(null, btnExe, null, listBox));
        TGC_FocusUtils.addKeyDown(btnExe, new TGS_FocusSides4(btnEsc, listBox, null, listBox));
        TGC_FocusUtils.addKeyDown(listBox, new TGS_FocusSides4(btnExe, null, null, null));
    }

    @Override
    public void configLayout() {
        var maxWidth = dim == null ? null : dim.getWidth();
        boolean mobile = TGC_BrowserNavigatorUtils.mobile();
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
    public TGC_Pop getPop() {
        return panelPopup;
    }
}
