package com.tugalsan.api.gui.client.widget.abs;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.tugalsan.api.gui.client.dom.TGC_DOMUtils;
import com.tugalsan.api.gui.client.dim.TGC_Dimension;
import com.tugalsan.api.gui.client.panel.TGC_PanelAbsoluteUtils;
import com.tugalsan.api.shape.client.TGS_ShapeRectangle;
import com.tugalsan.api.union.client.TGS_UnionExcuseVoid;

public final class TGC_ScrollPanel extends ScrollPanel {

    public TGC_ScrollPanel(TGC_Dimension dim) {
        content = TGC_PanelAbsoluteUtils.create(null);
        setWidget(content);
        setSize(dim);
    }

    public void setSize(TGC_Dimension dim) {
        this.dim = dim;
        TGC_DOMUtils.setSize(getWidget().getElement(), dim.getWidth(), dim.getHeight());
    }

    public TGC_Dimension getSize() {
        return dim;
    }
    private TGC_Dimension dim;

    public AbsolutePanel getContent() {
        return content;
    }
    private final AbsolutePanel content;

    public TGS_UnionExcuseVoid addWidget_enlargeContentSize(Widget widget, int x, int y, int w, int h) {
        TGC_PanelAbsoluteUtils.setWidget(content, widget, new TGS_ShapeRectangle(x, y, w, h));
        return enlargeContentSize();
    }

    private TGS_UnionExcuseVoid enlargeContentSize() {
        for (var i = 0; i < content.getWidgetCount(); i++) {
            var grandChildWidget = content.getWidget(i);
            var u_gcx = TGC_DOMUtils.getLeft(grandChildWidget.getElement());
            if (u_gcx.isExcuse()) {
                return u_gcx.toExcuseVoid();
            }
            var gcx = u_gcx.value();
            var u_gcy = TGC_DOMUtils.getTop(grandChildWidget.getElement());
            if (u_gcy.isExcuse()) {
                return u_gcy.toExcuseVoid();
            }
            var gcy = u_gcy.value();
            var u_gcw = TGC_DOMUtils.getWidth(grandChildWidget.getElement());
            if (u_gcw.isExcuse()) {
                return u_gcw.toExcuseVoid();
            }
            var gcw = u_gcw.value();
            var u_gch = TGC_DOMUtils.getHeight(grandChildWidget.getElement());
            if (u_gch.isExcuse()) {
                return u_gch.toExcuseVoid();
            }
            var gch = u_gch.value();
            var u_w = TGC_DOMUtils.getWidth(content.getElement());
            if (u_w.isExcuse()) {
                return u_w.toExcuseVoid();
            }
            var w = u_w.value();
            var u_h = TGC_DOMUtils.getHeight(content.getElement());
            if (u_h.isExcuse()) {
                return u_h.toExcuseVoid();
            }
            var h = u_h.value();
            if (gcx + gcw > w) {
                w = gcx + gcw;
            }
            if (gcy + gch > h) {
                h = gcy + gch;
            }
            TGC_DOMUtils.setSize(content.getElement(), w, h);
        }
        return TGS_UnionExcuseVoid.ofVoid();
    }
}
