package com.tugalsan.api.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.tugalsan.api.shape.client.TGS_ShapeLocation;

public class TGC_Canvas2DPaintTextUtils {

    public static Canvas paint(Canvas canvas, TGS_ShapeLocation<Integer> loc, CharSequence text) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.fillText(text.toString(), loc.x, loc.y);
        return canvas;
    }
}
