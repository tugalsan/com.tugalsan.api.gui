package com.tugalsan.api.gui.client.widget.suggest;

import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import java.util.Arrays;
import java.util.List;

public class TGC_SuggestBoxUtils {

    public static SuggestBox create(String... items) {
        return create(Arrays.asList(items));
    }

    public static SuggestBox create(List<String> items) {
        var oracle = new MultiWordSuggestOracle();
        items.forEach(item -> oracle.add(item));
        return new SuggestBox(new TGC_SuggestBoxOracleLazy(oracle));
    }
}
