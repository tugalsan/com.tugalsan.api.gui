package com.tugalsan.api.gui.client.widget;

import com.google.gwt.user.client.ui.*;
import com.tugalsan.api.gui.client.browser.*;
import com.tugalsan.api.gui.client.click.*;
import com.tugalsan.api.gui.client.panel.*;
import com.tugalsan.api.icon.client.*;
import com.tugalsan.api.shape.client.*;

public class TGC_ScrollUtils {

    public static void addScrollToTop(ScrollPanel scroll) {
        var btnTop = TGC_ButtonUtils.createIcon(TGS_IconUtils.CLASS_ARROW_UP(), "Yukarı");
        btnTop.addStyleName(TGC_ScrollUtils.class.getSimpleName() + "_btn");
        btnTop.setVisible(false);
        TGC_ClickUtils.add(btnTop, () -> scroll.scrollToTop());
        scroll.addScrollHandler(e -> {
            if (scroll.getVerticalScrollPosition() > 20) {
                if (!btnTop.isVisible()) {
                    btnTop.setVisible(true);
                }
            } else {
                if (btnTop.isVisible()) {
                    btnTop.setVisible(false);
                }
            }
        });
        addScrollToTop_setPos(btnTop);
        TGC_BrowserWindowUtils.exe_resizeHandlers.add(dim -> {
            addScrollToTop_setPos(btnTop);
        });
    }

    private static void addScrollToTop_setPos(PushButton btnTop) {
        var dimGapX = 200 - 110;
        var dimGapY = 200 - 80;
        var dimScr = TGC_BrowserDimensionUtils.getDimension();
        TGC_PanelAbsoluteUtils.setWidget(RootPanel.get(), btnTop, new TGS_ShapeRectangle(dimScr.width - dimGapX, dimScr.height - dimGapY, 75, 30));
    }
}
