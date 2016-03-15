package com.lushprojects.circuitjs1.client.api;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kraken
 * @since 3/15/16.
 */
public class ApiProvider {
    private Map<ActionType, ApiAction<?>> actions = new HashMap<>();

    public ApiProvider() {
        injectEventListener();
    }

    public void addAction(ActionType type, ApiAction action) {
        actions.put(type, action);
    }

    private Object invokeAction(String actionName, JavaScriptObject jsonData) {
        final ActionType actionType = ActionType.valueOf(actionName);
        final ApiAction<?> action = actions.get(actionType);
        if (action == null) {
            throw new RuntimeException("Unknown action");
        }

        return action.apply(new JSONObject(jsonData));
    }

    private native void injectEventListener() /*-{
        var that = this;
        $wnd.$circuitjs = function (action, data) {
            return that.@com.lushprojects.circuitjs1.client.api.ApiProvider::invokeAction(*)(action, data);
        };
    }-*/;
}
