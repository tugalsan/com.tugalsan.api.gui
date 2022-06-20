package com.tugalsan.api.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.CanvasElement;
import com.tugalsan.api.log.client.TGC_Log;
import com.tugalsan.api.shape.client.TGS_ShapeRectangle;

public class TGC_Canvas2DUtils {

    final private static TGC_Log d = TGC_Log.of(TGC_Canvas2DUtils.class.getSimpleName());

    public static CanvasElement toCanvasElement(Canvas canvas) {
        var c2d = toContext2d(canvas);
        return c2d.getCanvas();
    }

    public static Context2d toContext2d(Canvas canvas) {
        return canvas.getContext2d();
    }

    public static Canvas clear(Canvas canvas) {
        var res = TGC_CanvasUtils.getResolution(canvas);
        return clear(canvas, new TGS_ShapeRectangle(0, 0, res.width, res.height));
    }

    public static Canvas clear(Canvas canvas, TGS_ShapeRectangle<Integer> rect) {
        var c2d = toContext2d(canvas);
        c2d.clearRect(rect.x, rect.y, rect.width, rect.height);
        return canvas;
    }

    public static Canvas save(Canvas canvas, TGS_ShapeRectangle<Integer> rect) {
        var c2d = toContext2d(canvas);
        c2d.save();
        return canvas;
    }

    public static Canvas restore(Canvas canvas, TGS_ShapeRectangle<Integer> rect) {
        var c2d = toContext2d(canvas);
        c2d.restore();
        return canvas;
    }

}
