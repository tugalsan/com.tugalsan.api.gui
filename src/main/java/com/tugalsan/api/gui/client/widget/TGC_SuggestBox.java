package com.tugalsan.api.gui.client.widget;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.TextBox;

public class TGC_SuggestBox extends SuggestBox {

    public TGC_SuggestBox() {
        super(
                new SuggestOracle() {
            @Override
            public void requestSuggestions(Request request, Callback callback) {
                ArrayList<Suggestion> suggestions = new ArrayList<Suggestion>();
                suggestions.add(new MySuggestion("aaa"));
                suggestions.add(new MySuggestion("bbb"));
                suggestions.add(new MySuggestion("ccc"));
                suggestions.add(new MySuggestion("ddd"));

                Response response = new Response();
                response.setSuggestions(suggestions);
                callback.onSuggestionsReady(request, response);
            }
        },
                new TextBox(),
                new MySuggestionDisplay());
    }

    public static class MySuggestionDisplay extends DefaultSuggestionDisplay {

        @Override
        protected void showSuggestions(SuggestBox suggestBox, Collection<? extends Suggestion> suggestions, boolean isDisplayStringHTML, boolean isAutoSelectEnabled, SuggestionCallback callback) {
            if (suggestBox.getText().length() > 1) {
                super.showSuggestions(suggestBox, suggestions, isDisplayStringHTML, isAutoSelectEnabled, callback);
            }
        }
    }

    public static class MySuggestion implements Suggestion {

        private String text;

        public MySuggestion(String text) {
            this.text = text;
        }

        @Override
        public String getDisplayString() {
            return text;
        }

        @Override
        public String getReplacementString() {
            return text;
        }
    }
}
