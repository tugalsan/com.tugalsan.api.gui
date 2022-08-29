package com.tugalsan.api.gui.client.pop;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;
import java.util.stream.IntStream;
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
import com.tugalsan.api.gui.client.widget.TGC_CheckBoxUtils;
import com.tugalsan.api.icon.client.TGS_IconUtils;
import com.tugalsan.api.executable.client.TGS_Executable;
import com.tugalsan.api.executable.client.TGS_ExecutableType1;
import com.tugalsan.api.list.client.*;
import com.tugalsan.api.pack.client.TGS_Pack2;
import com.tugalsan.api.stream.client.*;
import java.util.*;
import java.util.stream.*;

public class TGC_PopLblYesNoCheckListBox implements TGC_PopInterface {

    final private static TGC_Log d = TGC_Log.of(TGC_PopLblYesNoCheckListBox.class);

    final private String lblListBoxHTML, btnOkText, btnCancelText, lblCheckBoxHTML;
    final public TGS_ExecutableType1<TGC_PopLblYesNoCheckListBox> onEsc, onExe;
    final public List<String> listBoxContent;
    final public List<TGS_Pack2<String, String>> checkBoxIconAndLabels;
    final private TGS_Executable onVisible;

    public TGC_PopLblYesNoCheckListBox(TGC_Dimension dim,
            List<String> listBoxContent_optional, List<TGS_Pack2<String, String>> checkBoxIconAndLabels,
            CharSequence lblListBoxHTML, CharSequence lblCheckBoxHTML, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_ExecutableType1<TGC_PopLblYesNoCheckListBox> onExe,
            TGS_ExecutableType1<TGC_PopLblYesNoCheckListBox> onEsc,
            TGS_Executable onVisible_optional) {
        this(dim, listBoxContent_optional, checkBoxIconAndLabels,
                lblListBoxHTML, lblCheckBoxHTML, btnOkText, btnCancelText,
                onExe, onEsc, onVisible_optional, null, null);
    }

    public TGC_PopLblYesNoCheckListBox(TGC_Dimension dim,
            List<String> listBoxContent_optional, List<TGS_Pack2<String, String>> checkBoxIconAndLabels,
            CharSequence lblListBoxHTML, CharSequence lblCheckBoxHTML, CharSequence btnOkText, CharSequence btnCancelText,
            TGS_ExecutableType1<TGC_PopLblYesNoCheckListBox> onExe,
            TGS_ExecutableType1<TGC_PopLblYesNoCheckListBox> onEsc,
            TGS_Executable onVisible_optional, CharSequence iconClassExe_optional, CharSequence iconClassEsc_optional) {
        this.dim = dim;
        this.lblListBoxHTML = lblListBoxHTML.toString();
        this.btnOkText = btnOkText.toString();
        this.btnCancelText = btnCancelText.toString();
        this.lblCheckBoxHTML = lblCheckBoxHTML.toString();
        this.onEsc = onEsc;
        this.onExe = onExe;
        this.listBoxContent = listBoxContent_optional;
        this.checkBoxIconAndLabels = checkBoxIconAndLabels;
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

    public void selectLastItem() {
        listBox.setSelectedIndex(listBox.getItemCount() - 1);
    }

    @Override
    public void createWidgets() {
        btnEsc = TGC_ButtonUtils.createIcon(iconClassEsc == null ? TGS_IconUtils.CLASS_CROSS() : iconClassEsc, btnCancelText);
        btnExe = TGC_ButtonUtils.createIcon(iconClassExe == null ? TGS_IconUtils.CLASS_CHECKMARK() : iconClassExe, btnOkText);
        lblListBox = new HTML(lblListBoxHTML);
        lblCheckBox = new HTML(lblCheckBoxHTML);
        listBox = TGC_ListBoxUtils.create(false);
        checkBoxes = TGS_StreamUtils.toList(
                IntStream.range(0, checkBoxIconAndLabels.size())
                        .mapToObj(i -> TGC_CheckBoxUtils.createIcon(checkBoxIconAndLabels.get(i).value0, checkBoxIconAndLabels.get(i).value1))
        );
    }
    public HTML lblListBox;
    public HTML lblCheckBox;
    public PushButton btnEsc, btnExe;
    public ListBox listBox;
    public List<CheckBox> checkBoxes;

    @Override
    public void createPops() {
    }
    private TGC_Pop panelPopup;

    @Override
    public void configInit() {
        if (listBoxContent != null) {
            listBoxContent.stream().forEachOrdered(s -> listBox.addItem(s));
            listBox.setSelectedIndex(0);
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
        checkBoxes.stream().forEachOrdered(cb -> TGC_KeyUtils.add(cb, null, () -> onEsc.execute(this)));
    }

    @Override
    public void configFocus() {
        TGC_FocusUtils.addKeyDown(btnEsc, new TGS_FocusSides4(null, btnExe, null, checkBoxes.get(0)));
        TGC_FocusUtils.addKeyDown(btnExe, new TGS_FocusSides4(btnEsc, checkBoxes.get(0), null, checkBoxes.get(0)));
        TGC_FocusUtils.addKeyDown(listBox, new TGS_FocusSides4(checkBoxes.get(checkBoxes.size() - 1), null, checkBoxes.get(checkBoxes.size() - 1), null));
        IntStream.range(0, checkBoxes.size()).forEachOrdered(i -> {
            TGC_FocusUtils.addKeyDown(checkBoxes.get(i), nativeKeyCode -> {
                if (null != nativeKeyCode) {
                    switch (nativeKeyCode) {
                        case KeyCodes.KEY_UP:
                            if (i == 0) {
                                TGC_FocusUtils.setFocusAfterGUIUpdate(btnEsc);
                            } else {
                                TGC_FocusUtils.setFocusAfterGUIUpdate(checkBoxes.get(i - 1));
                            }
                            break;
                        case KeyCodes.KEY_DOWN:
                            if (i == checkBoxes.size() - 1) {
                                TGC_FocusUtils.setFocusAfterGUIUpdate(listBox);
                            } else {
                                TGC_FocusUtils.setFocusAfterGUIUpdate(checkBoxes.get(i + 1));
                            }
                            break;
                        default:
                            break;
                    }
                }
            });
        });
    }

    @Override
    public void configLayout() {
        var maxWidth = dim == null ? null : dim.getWidth();
        boolean mobile = TGC_BrowserNavigatorUtils.mobile();
        Integer[] columnPercent = {50, 50};
        List<Widget> widgetsArr = TGS_ListUtils.of(
                btnEsc, btnExe,
                lblCheckBox
        );
        checkBoxes.stream().forEachOrdered(cb -> {
            widgetsArr.add(cb);
        });
        if (checkBoxes.size() % 2 == 0) {
            widgetsArr.add(new HTML());
        }
        widgetsArr.add(lblListBox);
        widgetsArr.add(mobile ? listBox : new HTML());
        var widgets = new Widget[widgetsArr.size()];
        IntStream.range(0, widgetsArr.size()).forEachOrdered(i -> widgets[i] = widgetsArr.get(i));
        var grid = TGC_PanelLayoutUtils.createGrid(maxWidth, columnPercent, widgets, false);
        panelPopup = new TGC_Pop(
                TGC_PanelLayoutUtils.createDockNorth(4 /*+ checkBoxes.size() / 2*/, grid, mobile ? new HTML() : listBox),
                dim, onVisible
        );

    }

    @Override
    public TGC_Pop getPop() {
        return panelPopup;
    }
}
