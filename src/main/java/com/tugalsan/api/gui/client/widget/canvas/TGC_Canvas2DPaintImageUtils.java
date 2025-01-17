package com.tugalsan.api.gui.client.widget.canvas;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.ImageData;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.Image;
import com.tugalsan.api.gui.client.widget.TGC_ImageUtils;
import com.tugalsan.api.shape.client.TGS_ShapeLocation;

public class TGC_Canvas2DPaintImageUtils {

    public static Canvas paint(Canvas canvas, Canvas imageData, TGS_ShapeLocation<Integer> loc) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.drawImage(TGC_Canvas2DUtils.toCanvasElement(imageData), loc.x, loc.y);
        return canvas;
    }

    public static Canvas paint(Canvas canvas, ImageData imageData, TGS_ShapeLocation<Integer> loc) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        c2d.putImageData(imageData, loc.x, loc.y);
        return canvas;
    }

    public static Canvas paint(Canvas canvas, ImageElement imageElement, TGS_ShapeLocation<Integer> loc) {
        var c2d = TGC_Canvas2DUtils.toContext2d(canvas);
        imageElement.setAttribute("crossOrigin", "Anonymous");//Anonymous or ''
        c2d.drawImage(imageElement, loc.x, loc.y);
        return canvas;
    }

    public static Canvas paint(Canvas canvas, Image image, TGS_ShapeLocation<Integer> loc) {
        paint(canvas, TGC_ImageUtils.toImageElement(image), loc);
        return canvas;
    }
}
