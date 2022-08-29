package com.tugalsan.api.gui.client.dim;

import com.tugalsan.api.gui.client.browser.TGC_BrowserDimensionUtils;
import com.tugalsan.api.log.client.TGC_Log;
import com.tugalsan.api.shape.client.TGS_ShapeMargin;
import com.tugalsan.api.shape.client.TGS_ShapeRectangle;

public class TGC_DimensionUtils {

    final private static TGC_Log d = TGC_Log.of(TGC_DimensionUtils.class);

    public static TGS_ShapeRectangle<Integer> toRect(TGS_ShapeMargin<Integer> margin) {
        var rect = new TGS_ShapeRectangle(
                margin.left,
                margin.up,
                TGC_BrowserDimensionUtils.width() - margin.left - margin.right,
                TGC_BrowserDimensionUtils.height() - margin.up - margin.down
        );
        d.ci("toRect", rect);
        return rect;
    }

}
