package com.tugalsan.api.gui.client.pop;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.tugalsan.api.executable.client.TGS_Executable;
import com.tugalsan.api.gui.client.dim.TGC_Dimension;
import com.tugalsan.api.log.client.TGC_Log;

public class TGC_PopToast implements TGC_PopInterface {

    final private static TGC_Log d = TGC_Log.of(TGC_PopToast.class);

    private TGC_PopToast(TGC_Dimension dim, TGS_Executable onVisible_optional) {
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
        panelPopup.widget.setGlassEnabled(false);
    }
    private TGC_Pop panelPopup;

    @Override
    public TGC_Pop getPop() {
        return panelPopup;
    }

    public static void toast(TGC_Dimension dim, TGS_Executable onVisible_optional, int seconds, String htmlContent) {
        var toast = new TGC_PopToast(dim, onVisible_optional);
        toast.html.setHTML(htmlContent);
        RootPanel.get().add(toast.panelPopup.widget);
        toast.panelPopup.widget.setPopupPositionAndShow((int offsetWidth, int offsetHeight) -> {
            //center
//            int left = (Window.getClientWidth() - offsetWidth) / 2;
//            int top = (Window.getClientHeight() - offsetHeight) / 2;
            toast.panelPopup.widget.setPopupPosition(
                    10,
                    TGC_Dimension.FULLSCREEN.getHeight() - offsetHeight - 10
            );
        });
        new Timer() {
            @Override
            public void run() {
                RootPanel.get().remove(toast.panelPopup.widget);
                this.cancel();
            }
        }.schedule(seconds * 1000);
    }
}
