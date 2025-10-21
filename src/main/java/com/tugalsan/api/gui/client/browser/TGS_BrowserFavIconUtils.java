package com.tugalsan.api.gui.client.browser;

import com.tugalsan.api.string.client.*;

public class TGS_BrowserFavIconUtils {

    private TGS_BrowserFavIconUtils() {

    }

    public static String createLabel(CharSequence filePrefix) {
        return createLabel(filePrefix, 16, "png", true);
    }

    public static String createLabel(CharSequence filePrefix, int pixel, CharSequence type, boolean dark) {
        var filenamePrefix = filePrefix.toString().replace(" ", "_");
        var fileNameTheme = dark ? "light" : "dark";
        var fileNamePixel = String.valueOf(pixel);
        return TGS_StringUtils.cmn().concat(filenamePrefix, "-", fileNameTheme, "-", fileNamePixel, "x", fileNamePixel, ".", type);
    }
}
