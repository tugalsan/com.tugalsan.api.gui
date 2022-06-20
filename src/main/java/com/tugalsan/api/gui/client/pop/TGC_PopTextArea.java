package com.tugalsan.api.gui.client.pop;

import com.google.gwt.user.client.ui.TextArea;
import com.tugalsan.api.log.client.TGC_Log;
import com.tugalsan.api.gui.client.editable.TGC_EditableUtils;
import com.tugalsan.api.gui.client.dim.TGC_Dimension;
import com.tugalsan.api.gui.client.panel.TGC_PanelLayoutUtils;
import com.tugalsan.api.executable.client.TGS_Executable;

public class TGC_PopTextArea implements TGC_PopInterface {

    final private static TGC_Log d = TGC_Log.of(TGC_PopTextArea.class.getSimpleName());

    final private String initText;
    final public TGS_Executable onVisible;

    public TGC_PopTextArea(TGC_Dimension dim, CharSequence initText, TGS_Executable onVisible_optional) {
        this.dim = dim;
        this.initText = initText.toString();
        this.onVisible = onVisible_optional;
        createWidgets();
        createPops();
        configInit();
        configActions();
        configFocus();
        configLayout();
        panelPopup.setVisible_focus = textArea;
    }

    private TGC_Pop panelPopup;
    public TextArea textArea;
    private TGC_Dimension dim;

    @Override
    public void createWidgets() {
        textArea = new TextArea();
    }

    @Override
    public void createPops() {
    }

    @Override
    public void configInit() {
        TGC_EditableUtils.set(textArea, false);
        textArea.setText(initText);
    }

    @Override
    public void configActions() {
    }

    @Override
    public void configFocus() {
    }

    @Override
    public void configLayout() {
        panelPopup = new TGC_Pop(
                TGC_PanelLayoutUtils.createDock(
                        textArea
                ),
                dim, onVisible
        );
    }

    @Override
    public TGC_Pop getPop() {
        return panelPopup;
    }
}
