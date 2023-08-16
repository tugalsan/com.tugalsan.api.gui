package com.tugalsan.api.gui.client.widget.suggest;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Callback;
import com.google.gwt.user.client.ui.SuggestOracle.Request;
import com.google.gwt.user.client.ui.SuggestOracle.Response;

public class TGC_SuggestBoxOracleLazy extends SuggestOracle {

    private final SuggestOracle oracle;
    private Query query;
    private String last;
    private Timer requestRetentionTimer;
    private boolean cancelOutstandingRequest;
    private boolean serveSuggestions;

    public TGC_SuggestBoxOracleLazy(SuggestOracle src) {
        oracle = src;
    }

    public String getLast() {
        return last;
    }

    @Override
    public void requestSuggestions(Request req, Callback cb) {
        if (!serveSuggestions) {
            return;
        }
        // Use a timer for key stroke retention, such that we don't query the
        // backend for each and every keystroke we receive.
        if (requestRetentionTimer != null) {
            requestRetentionTimer.cancel();
        }
        requestRetentionTimer
                = new Timer() {
            @Override
            public void run() {
                Query q = new Query(req, cb);
                if (query == null) {
                    query = q;
                    q.start();
                } else {
                    query = q;
                }
            }
        };
        requestRetentionTimer.schedule(200);
    }

    @Override
    public void requestDefaultSuggestions(Request req, Callback cb) {
        requestSuggestions(req, cb);
    }

    @Override
    public boolean isDisplayStringHTML() {
        return oracle.isDisplayStringHTML();
    }

    public void cancelOutstandingRequest() {
        if (requestRetentionTimer != null) {
            requestRetentionTimer.cancel();
        }
        if (query != null) {
            cancelOutstandingRequest = true;
        }
    }

    public void setServeSuggestions(boolean serveSuggestions) {
        this.serveSuggestions = serveSuggestions;
    }

    private class Query implements Callback {

        final Request request;
        final Callback callback;

        Query(Request req, Callback cb) {
            request = req;
            callback = cb;
        }

        void start() {
            oracle.requestSuggestions(request, this);
        }

        @Override
        public void onSuggestionsReady(Request req, Response res) {
            if (cancelOutstandingRequest || !serveSuggestions) {
                // If cancelOutstandingRequest() was called, we ignore this response
                cancelOutstandingRequest = false;
                query = null;
            } else if (query == this) {
                // No new request was started while this query was running.
                // Propose this request's response as the suggestions.
                query = null;
                last = request.getQuery();
                callback.onSuggestionsReady(req, res);
            } else {
                // Another query came in while this one was running. Skip
                // this response and start the most recent query.
                query.start();
            }
        }
    }
}
