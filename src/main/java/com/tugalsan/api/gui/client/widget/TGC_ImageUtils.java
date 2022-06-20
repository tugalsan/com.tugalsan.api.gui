package com.tugalsan.api.gui.client.widget;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Image;
import com.tugalsan.api.shape.client.TGS_ShapeDimension;

public class TGC_ImageUtils {

    public static ImageElement toImageElement(Image image) {
        return ImageElement.as(image.getElement());
    }

    public static TGS_ShapeDimension<Integer> getDimension(Image image) {
        return new TGS_ShapeDimension(image.getWidth(), image.getHeight());
    }

    public static void setAltText(Image image, String text) {
        if (image.getElement().hasChildNodes()) {
            image.getElement().getFirstChildElement().setAttribute("alt", text);
        } else {
            DOM.setElementAttribute(image.getElement(), "alt", text);
        }
    }
}
