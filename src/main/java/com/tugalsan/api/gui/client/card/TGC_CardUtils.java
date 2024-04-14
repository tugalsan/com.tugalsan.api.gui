package com.tugalsan.api.gui.client.card;

import com.google.gwt.user.client.ui.*;
import com.tugalsan.api.charset.client.*;
import com.tugalsan.api.color.client.*;
import com.tugalsan.api.runnable.client.*;
import com.tugalsan.api.gui.client.widget.canvas.*;
import com.tugalsan.api.list.client.*;
import com.tugalsan.api.log.client.*;
import com.tugalsan.api.shape.client.*;
import com.tugalsan.api.string.client.*;
import java.util.*;
import java.util.stream.*;

public class TGC_CardUtils {

    final private static TGC_Log d = TGC_Log.of(TGC_CardUtils.class);

    public static String CLASSNAME_DIV_CARDS() {
        return TGC_Card.class.getSimpleName() + "_cards";
    }

    public static String CLASSNAME_CARD_A() {
        return TGC_Card.class.getSimpleName() + "_card_a";
    }

    public static String CLASSNAME_CARD_SPAN() {
        return TGC_Card.class.getSimpleName() + "_card_span";
    }

    public static String CLASSNAME_CARD_SPAN_NOCOLOR() {
        return TGC_Card.class.getSimpleName() + "_card_span_nocolor";
    }

    public static String CLASSNAME_CARD_CONTENT() {
        return TGC_Card.class.getSimpleName() + "_card_content";
    }

    public static String CLASSNAME_CARD_IMAGE() {
        return TGC_Card.class.getSimpleName() + "_card_image";
    }

    public static String IDNAME_INPUT() {
        return TGC_Card.class.getSimpleName() + "_input";
    }

    public static Ball create(ScrollPanel optionalScroll, CharSequence optionalTitleHtml, CharSequence urlSearchIcon, TGS_RunnableType1<List<TGC_Card>> cards) {
        List<TGC_Card> cardsInner = TGS_ListUtils.of();
        cards.run(cardsInner);

        var tbInput = new TextBox();
        tbInput.getElement().setId(IDNAME_INPUT());
        tbInput.addStyleName(IDNAME_INPUT());
        if (urlSearchIcon != null) {
            tbInput.getElement().getStyle().setBackgroundImage("url('" + urlSearchIcon.toString() + "')");
            d.ci("create", "setBackgroundImage", urlSearchIcon);
        }
        tbInput.getElement().setAttribute("placeholder", "Ara");

        var divCards = new FlowPanel();
        divCards.getElement().setId(CLASSNAME_DIV_CARDS());
        divCards.addStyleName(CLASSNAME_DIV_CARDS());

        tbInput.addKeyUpHandler(e -> {
            if (optionalScroll != null) {
                optionalScroll.scrollToTop();
            }
            var searchTagTrim = TGS_CharSetCast.toLocaleLowerCase(tbInput.getText().trim());
            var searchTagIsEmpty = TGS_StringUtils.isNullOrEmpty(searchTagTrim);
            IntStream.range(0, cardsInner.size()).forEachOrdered(i -> {
                var widget = divCards.getWidget(i);
                if (searchTagIsEmpty) {
                    if (!widget.isVisible()) {
                        widget.setVisible(true);
                    }
                    return;
                }
                var card = cardsInner.get(i);
                if (card.optionalAction == null) {
                    if (widget.isVisible()) {
                        widget.setVisible(false);
                    }
                    return;
                }

                var contentTrim = TGS_CharSetCast.toLocaleLowerCase(card.htmlContent.toString().trim());
                var contains = TGS_CharSetCast.containsLocaleIgnoreCase(contentTrim, searchTagTrim);

                if (widget.isVisible() && !contains) {
                    widget.setVisible(false);
                    return;
                }
                if (!widget.isVisible() && contains) {
                    widget.setVisible(true);
                    return;
                }
            });
        });

        cardsInner.forEach(card -> {
            //PRE-FIX CARD-IMG2
            if (!card.span_nocolor && card.optional_urlImgBackground == null) {
                card.optional_urlImgBackground = TGC_Canvas2DImageUtils.createImageUrl(
                        new TGS_ShapeDimension(1, 1),
                        TGC_ColorUtils.createRandom(75)
                );
            }

            //CARDS BODY IMG
            var img = new HTML();
            img.addStyleName(TGC_CardUtils.CLASSNAME_CARD_IMAGE());
            if (!card.span_nocolor) {
                var imgFg = card.optional_urlImgForeground;
                var imgBg = card.optional_urlImgBackground;
                if (TGS_StringUtils.isNullOrEmpty(card.optional_urlImgForeground)) {
                    img.getElement().getStyle().setBackgroundImage("url(" + imgBg + ")");
                    img.addStyleName("backgroundCenterRepeat");
                } else {
                    img.getElement().getStyle().setBackgroundImage("url(" + imgFg + "), url(" + imgBg + ")");
                    img.addStyleName("backgroundCenterCenterNoRepeatRepeat");
                }
            }

            //CARDS BODY CONTENT
            var content = new HTML(card.span_nocolor ? "" : card.htmlContent.toString());
            content.addStyleName(TGC_CardUtils.CLASSNAME_CARD_CONTENT());

            //CARDS cardX
            var cardX = new FlowPanel();
            cardX.add(img);
            cardX.add(content);

            var cardFocus = new FocusPanel(cardX);
            if (card.optionalAction == null) {
                if (card.span_nocolor) {
                    cardFocus.addStyleName(TGC_CardUtils.CLASSNAME_CARD_SPAN_NOCOLOR());
                } else {
                    cardFocus.addStyleName(TGC_CardUtils.CLASSNAME_CARD_SPAN());
                }
            } else {
                cardFocus.addStyleName(TGC_CardUtils.CLASSNAME_CARD_A());
                cardFocus.addClickHandler(e -> {
                    card.optionalAction.run();
                });
            }

            divCards.add(cardFocus);
        });
        return new Ball(tbInput, divCards, cardsInner);
    }

    public static record Ball(TextBox tb, FlowPanel fp, List<TGC_Card> cards) {

    }

}
