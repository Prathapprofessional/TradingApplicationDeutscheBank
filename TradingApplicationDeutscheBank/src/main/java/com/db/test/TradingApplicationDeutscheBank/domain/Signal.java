package com.db.test.TradingApplicationDeutscheBank.domain;

import java.util.List;

public class Signal
{
    private final int id;
    private final List<Action> actions;
    public Signal(int id, List<Action> actions)
    {
        this.id = id;
        this.actions =actions;
    }
    public int getId(){
        return id;
    }
    public List<Action> getActions(){ return actions; }
}
