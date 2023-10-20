package com.db.test.TradingApplicationDeutscheBank.domain;
//Enum of Actions to be performed
public enum ActionTypes {
    SETUP,
    SET_ALGO_PARAM,
    PERFORM_CALC,
    SUBMIT_TO_MARKET,
    REVERSE;

    public static boolean isValuePresent(String Value){
        for(ActionTypes actionTypes : ActionTypes.values()){
            if (actionTypes.name().equals(Value)) {
                return true;
            }
        }
        return false;
    }
}

