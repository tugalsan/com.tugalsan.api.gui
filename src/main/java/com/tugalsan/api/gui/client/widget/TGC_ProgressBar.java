package com.tugalsan.api.gui.client.widget;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;
import com.tugalsan.api.gui.client.dom.TGC_DOMUtils;
import com.tugalsan.api.math.client.TGS_MathRange;
import com.tugalsan.api.math.client.TGS_MathUtils;
import com.tugalsan.api.union.client.TGS_UnionExcuse;
import com.tugalsan.api.union.client.TGS_UnionExcuseVoid;

public class TGC_ProgressBar extends Widget {

    public Element barElement;
    public Element textElement;

    private TGC_ProgressBar() {

    }

    public static TGS_UnionExcuse<TGC_ProgressBar> of() {
        var _this = new TGC_ProgressBar();
        _this.textElement = TGC_DOMUtils.createDiv();
        _this.textElement.setClassName("gwt-ProgressBar-textElement");

        _this.barElement = TGC_DOMUtils.createDiv();
        _this.barElement.setClassName("gwt-ProgressBar-barElement");
        DOM.appendChild(_this.barElement, _this.textElement);

        _this.setElement(TGC_DOMUtils.createDiv());
        _this.getElement().setClassName("gwt-ProgressBar");
        DOM.appendChild(_this.getElement(), _this.barElement);

        _this.range = new TGS_MathRange(0, 100);
        var u_update = _this.update(100);
        if (u_update.isExcuse()) {
            return u_update.toExcuse();
        }
        return TGS_UnionExcuse.of(_this);
    }

    public TGS_MathRange<Integer> getRange() {
        return range;
    }
    public TGS_MathRange<Integer> range;

    final public TGS_UnionExcuseVoid update(int cur) {
        var u_percent = TGS_MathUtils.convertWeightedInt(cur, range, toMinMax100);
        if (u_percent.isExcuse()) {
            return u_percent.toExcuseVoid();
        }
        barElement.getStyle().setWidth(u_percent.value(), Style.Unit.PCT);
        textElement.setPropertyString("innerHTML", u_percent.value() + "%");
        return TGS_UnionExcuseVoid.ofVoid();
    }
    private final TGS_MathRange<Integer> toMinMax100 = new TGS_MathRange(0, 100);
//    private final TGS_Tuple2<Integer, Integer> toMinMax255 = new TGS_Tuple2(20, 50);

    public void setTextVisible(boolean textVisible) {
        textElement.getStyle().setVisibility(textVisible ? Style.Visibility.VISIBLE : Style.Visibility.HIDDEN);
    }
}
