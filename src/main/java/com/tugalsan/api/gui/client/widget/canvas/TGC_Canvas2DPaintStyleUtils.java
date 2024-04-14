package com.tugalsan.api.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.user.client.ui.Image;
import com.tugalsan.api.gui.client.widget.TGC_ImageUtils;
import com.tugalsan.api.log.client.TGC_Log;
import com.tugalsan.api.shape.client.TGS_ShapeCircle;
import com.tugalsan.api.shape.client.TGS_ShapeLocation;
import java.util.List;

public class TGC_Canvas2DPaintStyleUtils {

    final private static TGC_Log d = TGC_Log.of(TGC_Canvas2DPaintStyleUtils.class);

    public static record GradientConfig(Float offset, CssColor color) {

    }

    public static CanvasGradient createGradiantLinear(Canvas canvas,
            TGS_ShapeLocation<Integer> locStart, TGS_ShapeLocation<Integer> locEnd,
            List<GradientConfig> offset_color) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        var grad = c2d.createLinearGradient(locStart.x, locStart.y, locEnd.x, locEnd.y);
        offset_color.stream().forEachOrdered(colorStop -> grad.addColorStop(colorStop.offset(), colorStop.color().value()));
        d.ci("createGradiantLinear", grad);
        return grad;
    }

    public static CanvasGradient createGradiantRadial(Canvas canvas,
            TGS_ShapeCircle<Integer, Integer> locStart, TGS_ShapeCircle<Integer, Integer> locEnd,
            List<GradientConfig> offset_color) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        var grad = c2d.createRadialGradient(locStart.x, locStart.y, locStart.radius, locEnd.x, locEnd.y, locEnd.radius);
        offset_color.stream().forEachOrdered(colorStop -> grad.addColorStop(colorStop.offset(), colorStop.color().value()));
        d.ci("createGradiantRadial", grad);
        return grad;
    }

    public static CanvasPattern createPatternRepeatNone(Canvas canvas, Image image) {
        d.ci("createPatternRepeatNone", "init");
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        return c2d.createPattern(TGC_ImageUtils.toImageElement(image), Context2d.Repetition.NO_REPEAT);
    }

    public static CanvasPattern createPatternRepeatXY(Canvas canvas, Image image) {
        d.ci("createPatternRepeatXY", "init");
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        return c2d.createPattern(TGC_ImageUtils.toImageElement(image), Context2d.Repetition.REPEAT);
    }

    public static CanvasPattern createPatternRepeatX(Canvas canvas, Image image) {
        d.ci("createPatternRepeatX", "init");
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        return c2d.createPattern(TGC_ImageUtils.toImageElement(image), Context2d.Repetition.REPEAT_X);
    }

    public static CanvasPattern createPatternRepeatY(Canvas canvas, Image image) {
        d.ci("createPatternRepeatY", "init");
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        return c2d.createPattern(TGC_ImageUtils.toImageElement(image), Context2d.Repetition.REPEAT_Y);
    }
}
