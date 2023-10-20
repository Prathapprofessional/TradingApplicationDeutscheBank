package com.db.test.TradingApplicationDeutscheBank.domain;

import java.util.HashMap;
import java.util.Map;

public class Action {
    private final ActionTypes actionType;
    private final Map<String,Object> params;

    public Action(ActionTypes actionType) {
        this.actionType = actionType;
        this.params=new HashMap<>();
    }
    public ActionTypes getActionType()
    {
        return actionType;
    }
    public Map<String, Object> getParams()
    {
        return params;
    }
}
