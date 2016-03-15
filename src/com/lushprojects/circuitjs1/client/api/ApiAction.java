package com.lushprojects.circuitjs1.client.api;

import com.google.gwt.json.client.JSONObject;

/**
 * @author kraken
 * @since 3/15/16.
 */
public interface ApiAction<T> {
    T apply(JSONObject args);
}
