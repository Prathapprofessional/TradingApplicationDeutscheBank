package com.db.test.TradingApplicationDeutscheBank.service;

import com.db.test.TradingApplicationDeutscheBank.domain.Action;
import com.db.test.TradingApplicationDeutscheBank.domain.ActionConstants;
import com.db.test.TradingApplicationDeutscheBank.domain.Signal;
import com.trading.thirdparty.Algo;

import java.util.List;

/**
 * This is your team’s code and should be changed as you see fit.
 */
public class Application implements SignalHandler {

    /*Ideally database table can be used with sample format like,
    * Table Name : Signal_information
    * id -primary key auto generated
    * signal_id
    * param
    * value*/

    private final SignalsInitializer signalsInitializer;

    public Application()
    {
        signalsInitializer = new SignalsInitializer();
        signalsInitializer.init();
    }
    public void handleSignal(int signalId) {
        Algo algo = new Algo();
        if(signalsInitializer.hasSignal(signalId))
        {
            Signal signal = signalsInitializer.getSignal(signalId);
            List<Action> actions  = signal.getActions();
            for (Action action : actions)
            {
                switch(action.getActionType()){
                    case SETUP:
                        algo.setUp();
                        break;
                    case REVERSE:
                        algo.reverse();
                        break;
                    case PERFORM_CALC:
                        algo.performCalc();
                        break;
                    case SET_ALGO_PARAM:
                        int Integer;
                        algo.setAlgoParam((Integer)action.getParams().get(ActionConstants.ACTION_TYPE_SET_ALGO_PARAM_PARAM_1_NAME),(Integer)action.getParams().get(ActionConstants.ACTION_TYPE_SET_ALGO_PARAM_PARAM_2_NAME));
                        break;
                    case SUBMIT_TO_MARKET:
                        algo.submitToMarket();
                }
            }
        }
        else{ algo.cancelTrades();}
        algo.doAlgo();

                }
            }

