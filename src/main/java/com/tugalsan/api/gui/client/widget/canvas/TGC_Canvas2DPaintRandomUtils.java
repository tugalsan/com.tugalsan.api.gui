package com.tugalsan.api.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.user.client.ui.*;
import com.tugalsan.api.color.client.TGC_ColorUtils;
import com.tugalsan.api.log.client.TGC_Log;
import com.tugalsan.api.random.client.*;
import com.tugalsan.api.shape.client.TGS_ShapeArc;
import com.tugalsan.api.shape.client.TGS_ShapeDimension;
import com.tugalsan.api.shape.client.TGS_ShapeDimensionUtils;
import com.tugalsan.api.shape.client.TGS_ShapeLocation;
import com.tugalsan.api.shape.client.TGS_ShapeMargin;
import com.tugalsan.api.stream.client.*; 
import java.util.stream.*;

public class TGC_Canvas2DPaintRandomUtils {

    final private static TGC_Log d = TGC_Log.of(TGC_Canvas2DPaintFillStyleUtils.class.getSimpleName());

    public static Canvas styleDrawRandom(Canvas canvas) {
        switch (TGS_RandomUtils.nextInt(0, 2)) {
            case 0:
                TGC_Canvas2DPaintDrawStyleUtils.styleCap_BUTT(canvas);
                break;
            case 1:
                TGC_Canvas2DPaintDrawStyleUtils.styleCap_ROUND(canvas);
                break;
            case 2:
                TGC_Canvas2DPaintDrawStyleUtils.styleCap_SQUARE(canvas);
                break;
            default:
                break;
        }
        switch (TGS_RandomUtils.nextInt(0, 2)) {
            case 0:
                TGC_Canvas2DPaintDrawStyleUtils.styleJoin_ROUND(canvas);
                break;
            case 1:
                TGC_Canvas2DPaintDrawStyleUtils.styleJoin_BEVEL(canvas, null);
                break;
            case 2:
                var reso = TGC_CanvasUtils.getResolution(canvas);
                var min = Math.min(reso.width, reso.height) / 5;
                var mitterLimit = TGS_RandomUtils.nextFloat(1, min);
                TGC_Canvas2DPaintDrawStyleUtils.styleJoin_BEVEL(canvas, mitterLimit);
                break;
            default:
                break;
        }
        return canvas;
    }

    public static Canvas styleFillRandom(Canvas canvas, TGS_ShapeDimension<Integer> boundary, Image image) {
        switch (TGS_RandomUtils.nextInt(0, 7)) {
//            case 0:
//                d.ci("createGradiantLinear", "init");
//                var locLinearStart = TGS_RandomUtils.nextLoc(boundary);
//                var locLinearEnd = TGS_RandomUtils.nextLoc(boundary);
//                var offsetLinearColor = TGS_ListUtils.of(
//                        new TGS_Pack2(0f, TGC_ColorUtils.createRandom(false)),
//                        new TGS_Pack2(0.5f, TGC_ColorUtils.createRandom(false)),
//                        new TGS_Pack2(1f, TGC_ColorUtils.createRandom(false))
//                );
//                d.ci("locLinearStart", locLinearStart);
//                d.ci("locLinearEnd", locLinearEnd);
//                d.ci("offsetLinearColor", offsetLinearColor);
//                var gradLinear = TGC_Canvas2DPaintStyleUtils.createGradiantLinear(canvas, locLinearStart, locLinearEnd, offsetLinearColor);
//                TGC_Canvas2DPaintFillStyleUtils.style(canvas, gradLinear);
//                break;
//            case 1:
//                d.ci("createGradiantRadial", "init");
//                var locRadialStart = new TGS_ShapeCircle(
//                        TGS_RandomUtils.nextLoc(boundary),
//                        TGS_RandomUtils.nextInt(0, Math.min(boundary.width, boundary.height) / 2)
//                );
//                var locRadialEnd = new TGS_ShapeCircle(
//                        TGS_RandomUtils.nextLoc(boundary),
//                        TGS_RandomUtils.nextInt(0, Math.min(boundary.width, boundary.height) / 2)
//                );
//                var offsetRadialColor = TGS_ListUtils.of(
//                        new TGS_Pack2(0f, TGC_ColorUtils.createRandom(false)),
//                        new TGS_Pack2(0.5f, TGC_ColorUtils.createRandom(false)),
//                        new TGS_Pack2(1f, TGC_ColorUtils.createRandom(false))
//                );
//                d.ci("locRadialStart", locRadialStart);
//                d.ci("locRadialEnd", locRadialEnd);
//                d.ci("offsetRadialColor", offsetRadialColor);
//                var gradRadial = TGC_Canvas2DPaintStyleUtils.createGradiantRadial(canvas, locRadialStart, locRadialEnd, offsetRadialColor);
//                TGC_Canvas2DPaintFillStyleUtils.style(canvas, gradRadial);
//                break;
            case 2:
                var pattNone = TGC_Canvas2DPaintStyleUtils.createPatternRepeatNone(canvas, image);
                TGC_Canvas2DPaintFillStyleUtils.style(canvas, pattNone);
                break;
            case 3:
                var pattX = TGC_Canvas2DPaintStyleUtils.createPatternRepeatX(canvas, image);
                TGC_Canvas2DPaintFillStyleUtils.style(canvas, pattX);
                break;
            case 4:
                var pattY = TGC_Canvas2DPaintStyleUtils.createPatternRepeatY(canvas, image);
                TGC_Canvas2DPaintFillStyleUtils.style(canvas, pattY);
                break;
            case 5:
                var pattXY = TGC_Canvas2DPaintStyleUtils.createPatternRepeatXY(canvas, image);
                TGC_Canvas2DPaintFillStyleUtils.style(canvas, pattXY);
                break;
            case 6:
                var randomColor = TGC_ColorUtils.createRandom(false);
                TGC_Canvas2DPaintFillStyleUtils.style(canvas, randomColor);
                break;
            default:
                break;
        }
        return canvas;
    }

    public static Canvas paintImage(Canvas canvas, Image image) {
        var resolution = TGC_CanvasUtils.getResolution(canvas);
        var loc = TGS_RandomUtils.nextLoc(resolution);
        TGC_Canvas2DPaintImageUtils.paint(canvas, image, loc);
        return canvas;
    }

    public static Canvas paintFillList(Canvas canvas, Image image) {
        var resolution = TGC_CanvasUtils.getResolution(canvas);
        var locs = TGS_StreamUtils.toList(
                IntStream.range(0, 4).mapToObj(i -> TGS_RandomUtils.nextLoc(resolution))
        );
        styleFillRandom(canvas, TGS_ShapeDimensionUtils.getDimension(locs), image);
        TGC_Canvas2DPaintFillUtils.paintList(canvas, locs);
        return canvas;
    }

    public static Canvas paintFillArc(Canvas canvas, Image image) {
        var resolution = TGC_CanvasUtils.getResolution(canvas);
        var loc = TGS_RandomUtils.nextLoc(resolution);
        TGS_ShapeMargin<Integer> margins = new TGS_ShapeMargin(
                loc.x,
                resolution.width - loc.x,
                loc.y,
                resolution.height - loc.y
        );
        var radiusMax = Math.min(Math.min(margins.left, margins.right), Math.min(margins.up, margins.down));
        var radius = TGS_RandomUtils.nextInt(1, radiusMax);
        TGS_ShapeLocation<Float> radiusDegrees_start_and_end = new TGS_ShapeLocation(
                TGS_RandomUtils.nextFloat(0, 360),
                TGS_RandomUtils.nextFloat(0, 360)
        );
        TGS_ShapeArc<Integer, Integer, Float> arc = new TGS_ShapeArc(
                loc.x, loc.y,
                radius,
                radiusDegrees_start_and_end.x,
                radiusDegrees_start_and_end.y
        );
        styleFillRandom(canvas, TGS_ShapeDimensionUtils.getDimension(radius), image);
        TGC_Canvas2DPaintFillUtils.paintArc(canvas, arc);
        return canvas;
    }

    public static Canvas paintFillRect(Canvas canvas, Image image) {
        var resolution = TGC_CanvasUtils.getResolution(canvas);
        var randRect = TGS_RandomUtils.nextRect(resolution);
        styleFillRandom(canvas, TGS_ShapeDimensionUtils.getDimension(randRect), image);
        TGC_Canvas2DPaintFillUtils.paintRect(canvas, randRect);
        return canvas;
    }

    public static Canvas paintFillRectRadius(Canvas canvas, Image image) {
        var resolution = TGC_CanvasUtils.getResolution(canvas);
        var randRect = TGS_RandomUtils.nextRect(resolution);
        var radius = Math.min(randRect.width, randRect.height) / 5;
        styleFillRandom(canvas, TGS_ShapeDimensionUtils.getDimension(randRect), image);
        TGC_Canvas2DPaintFillUtils.paintRectRadius(canvas, randRect, radius);
        return canvas;
    }

}
