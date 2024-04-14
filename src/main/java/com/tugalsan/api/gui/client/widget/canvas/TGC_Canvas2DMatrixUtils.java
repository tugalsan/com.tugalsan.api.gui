package com.tugalsan.api.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.tugalsan.api.math.client.TGS_MathMatrix2x2;
import com.tugalsan.api.math.client.TGS_MathRange;
import com.tugalsan.api.shape.client.TGS_ShapeLocation;

public class TGC_Canvas2DMatrixUtils {

//    final private static TGC_Log d = TGC_Log.of(TGC_Canvas2DMatrixUtils.class);

    public static Canvas reset(Canvas canvas) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.setTransform(1, 0, 0, 1, 0, 0);
        return canvas;
    }

    public static Canvas flipHorizontal(Canvas canvas) {
        return scale(canvas, -1, 1);
    }

    public static Canvas flipVertical(Canvas canvas) {
        return scale(canvas, 1, -1);
    }

    public static Canvas scale(Canvas canvas, float x, float y) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.scale(x, y);
        return canvas;
    }

    public static Canvas translate(Canvas canvas, float x, float y) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.translate(x, y);
        return canvas;
    }

    public static Canvas transform(Canvas canvas, TGS_MathMatrix2x2<Float> matrix_m11_m22, TGS_ShapeLocation<Float> d) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.transform(matrix_m11_m22.x00, matrix_m11_m22.x01, matrix_m11_m22.x10, matrix_m11_m22.x11, d.x, d.y);
        return canvas;
    }
}
