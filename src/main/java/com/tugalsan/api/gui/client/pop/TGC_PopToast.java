package com.tugalsan.api.gui.client.pop;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.tugalsan.api.executable.client.TGS_Executable;
import com.tugalsan.api.gui.client.dim.TGC_Dimension;
import com.tugalsan.api.log.client.TGC_Log;

public class TGC_PopToast implements TGC_PopInterface {

    final private static TGC_Log d = TGC_Log.of(TGC_PopToast.class);

    public TGC_PopToast(TGC_Dimension dim, TGS_Executable onVisible_optional) {
        this.dim = dim;
        this.onVisible = onVisible_optional;
        createWidgets();
        createPops();
        configInit();
        configActions();
        configFocus();
        configLayout();
    }
    private TGC_Dimension dim;
    public final TGS_Executable onVisible;

    @Override
    public void createWidgets() {
        html = new HTML("");
    }
    private HTML html;

    @Override
    public void createPops() {
    }

    @Override
    public void configInit() {
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
                html,
                dim,
                onVisible
        );
        panelPopup.widget.setAutoHideEnabled(true);

    }
    private TGC_Pop panelPopup;

    @Override
    public TGC_Pop getPop() {
        return panelPopup;
    }

    public void toast(String htmlContent) {
//        var pop = new PopupPanel(true);
//        pop.setWidget(new Label(text));
//
//        RootPanel.get().add(pop);

        html.setHTML(htmlContent);

        panelPopup.widget.setPopupPositionAndShow((int offsetWidth, int offsetHeight) -> {
            int left = (Window.getClientWidth() - offsetWidth) / 2;
            int top = (Window.getClientHeight() - offsetHeight) / 2;
            panelPopup.widget.setPopupPosition(left, top);
        });

        new Timer() {
            @Override
            public void run() {
                System.out.println("timer repeated");
                RootPanel.get().remove(panelPopup.widget);
                this.cancel();
            }
        }.scheduleRepeating(2000);
    }
}