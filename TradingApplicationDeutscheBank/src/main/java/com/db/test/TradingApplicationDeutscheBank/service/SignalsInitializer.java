package com.db.test.TradingApplicationDeutscheBank.service;

import com.db.test.TradingApplicationDeutscheBank.domain.Action;
import com.db.test.TradingApplicationDeutscheBank.domain.ActionConstants;
import com.db.test.TradingApplicationDeutscheBank.domain.ActionTypes;
import com.db.test.TradingApplicationDeutscheBank.domain.Signal;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.db.test.TradingApplicationDeutscheBank.domain.ActionConstants.*;
public class SignalsInitializer {

    private static final Map<Integer, Signal> signalsMap= new HashMap<>();
    private static boolean isInitialized = false;
    public void init()  {
        if(isInitialized)
        {return;
        }
        signalsMap.clear();
        List<Path> signalFiles;
        try {
        signalFiles=getSignalFilesUnderResourcesFolder();
        final ObjectMapper mapper = new ObjectMapper();
        for(Path signalFile: signalFiles){
            TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() { };
            Map<String, Object> signalMap = mapper.readValue(signalFile.toFile(), typeRef);
            checkIsFieldIsPresent(signalMap.get(SIGNAL_ID),SIGNAL_ID,Integer.class, signalFile.getFileName());
            int signalId=(int) signalMap.get(SIGNAL_ID);

            checkIsFieldIsPresent(signalMap.get(ACTIONS),ACTIONS, ArrayList.class, signalFile.getFileName());

            List<Map<String, Object>> actions = (List<Map<String,Object>>) signalMap.get(ACTIONS);
            List<Action> actionList= new ArrayList<>();
            for(Map<String, Object> actionData : actions){
                Action action;
                checkIsFieldIsPresent(actionData.get(ACTION_TYPE), ACTION_TYPE, String.class,signalFile.getFileName());
                if(!ActionTypes.isValuePresent((String)actionData.get(ACTION_TYPE))){
                   throw new RuntimeException("Invalid action type " + actionData.get(ACTION_TYPE) + "for " + signalFile.getFileName() + "signal . please check the signal file");
                }

                ActionTypes actionType = ActionTypes.valueOf((String) actionData.get(ACTION_TYPE));
                action = switch (actionType) {
                    case SET_ALGO_PARAM -> handleActionSetAlgoParam(actionData, signalFile.getFileName());
                    default -> new Action(actionType);
                };
               actionList.add(action);
                }
            signalsMap.put(signalId, new Signal(signalId,actionList));
                }
            }
        catch(IOException | URISyntaxException ex){throw new RuntimeException(ex);}
           isInitialized = true;
        }
    private void checkIsFieldIsPresent(Object field, String fieldName, Class<?> classType, Path fileName){
          if(field==null)
          {
              throw new RuntimeException(fieldName + "is mandatory for the signal. Please check the signal file again " + fileName);
          }
          if(field.getClass()!= classType){
              throw new RuntimeException(fieldName + "value must be of type "+ classType + "for signal. Please check the signal file " + fileName);
          }
    }
    private Action handleActionSetAlgoParam(Map<String, Object>actionData, Path fileName){
      if(!actionData.containsKey(ActionConstants.ACTION_TYPE_SET_ALGO_PARAM_PARAM_1_NAME)|| !actionData.containsKey(ActionConstants.ACTION_TYPE_SET_ALGO_PARAM_PARAM_2_NAME))
        {throw new RuntimeException(ActionConstants.ACTION_TYPE_SET_ALGO_PARAM_PARAM_1_NAME + " and " + ActionConstants.ACTION_TYPE_SET_ALGO_PARAM_PARAM_2_NAME +
                "are mandatory for SET_ALGO_PARAM action. Please check the signal file " + fileName);}

      if(!(actionData.get(ActionConstants.ACTION_TYPE_SET_ALGO_PARAM_PARAM_1_NAME) instanceof Integer) || !(actionData.get(ActionConstants.ACTION_TYPE_SET_ALGO_PARAM_PARAM_2_NAME)instanceof Integer))
        {throw new RuntimeException(ActionConstants.ACTION_TYPE_SET_ALGO_PARAM_PARAM_1_NAME + " and " + ActionConstants.ACTION_TYPE_SET_ALGO_PARAM_PARAM_2_NAME +
                " must be of type Integer for SET_ALGO_PARAM action. Please check the signal file " + fileName);}

      Action action = new Action(ActionTypes.SET_ALGO_PARAM);
      action.getParams().put(ActionConstants.ACTION_TYPE_SET_ALGO_PARAM_PARAM_1_NAME,actionData.get(ActionConstants.ACTION_TYPE_SET_ALGO_PARAM_PARAM_1_NAME));
        action.getParams().put(ActionConstants.ACTION_TYPE_SET_ALGO_PARAM_PARAM_2_NAME,actionData.get(ActionConstants.ACTION_TYPE_SET_ALGO_PARAM_PARAM_2_NAME));
        return action;
    }

    List<Path> getSignalFilesUnderResourcesFolder() throws IOException, URISyntaxException {

    //returns the Path objests as a list that represents the files in resources/signals source path
        return Files.walk(Paths.get(getClass().getResource("/signals source path").toURI())).filter(Files::isRegularFile).filter(file->file.getFileName().toString().endsWith(".json")).collect(Collectors.toList());
    }

   public Signal getSignal(int id){ return signalsMap.get(id);}
   public boolean hasSignal(int id) {return signalsMap.containsKey(id);}

}

