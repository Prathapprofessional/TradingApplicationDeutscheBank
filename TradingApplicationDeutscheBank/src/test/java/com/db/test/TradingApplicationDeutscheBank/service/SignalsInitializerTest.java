package com.db.test.TradingApplicationDeutscheBank.service;

import com.db.test.TradingApplicationDeutscheBank.domain.ActionConstants;
import com.db.test.TradingApplicationDeutscheBank.domain.ActionTypes;
import com.db.test.TradingApplicationDeutscheBank.domain.Signal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

class SignalsInitializerTest {



    @AfterEach
    void tearDown() {
        Field field = null;
        try {
            field = SignalsInitializer.class.getDeclaredField("isInitialized");
            field.setAccessible(true);
            field.set(null, false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    // add test cases here
    @Test
    public void testSignalJsonIsValid() {
        try(MockedStatic<Files> mockedPaths = mockStatic(Files.class)) {
            mockedPaths.when(() -> Files.walk(Mockito.any()))
                    .thenReturn(List.of(Path.of("src/test/resources/signals source path/valid.json")).stream());
            SignalsInitializer signalsInitializer = new SignalsInitializer();
            signalsInitializer.init();
            assertTrue("Signal with Id 1 should be present", signalsInitializer.hasSignal(1));
        }
    }

    @Test
    public void testSignalJsonIsNotValid() {
        try(MockedStatic<Files> mockedPaths = mockStatic(Files.class)) {
            mockedPaths.when(() -> Files.walk(Mockito.any()))
                    .thenReturn(List.of(Path.of("src/test/resources/signals source path/invalid.json")).stream());
            SignalsInitializer signalsInitializer = new SignalsInitializer();
            assertThrows(RuntimeException.class, () -> signalsInitializer.init());
            assertFalse("Signal with Id 1 should not be present", signalsInitializer.hasSignal(1));
        }
    }

    @Test
    public void testSignalJsonValues() {
        try(MockedStatic<Files> mockedPaths = mockStatic(Files.class)) {
            mockedPaths.when(() -> Files.walk(Mockito.any()))
                    .thenReturn(List.of(Path.of("src/test/resources/signals source path/valid.json")).stream());
            SignalsInitializer signalsInitializer = new SignalsInitializer();
            signalsInitializer.init();
            assertTrue("Signal with Id 1 should be present", signalsInitializer.hasSignal(1));
            Signal signal= signalsInitializer.getSignal(1);
            assertEquals(1, signal.getId());
            assertEquals(4, signal.getActions().size());
            assertEquals(ActionTypes.SETUP, signal.getActions().get(0).getActionType());
            assertEquals(ActionTypes.SET_ALGO_PARAM, signal.getActions().get(1).getActionType());
            assertEquals(1,signal.getActions().get(1).getParams().get(ActionConstants.ACTION_TYPE_SET_ALGO_PARAM_PARAM_1_NAME));
            assertEquals(60,
                    signal.getActions().get(1).getParams().get(ActionConstants.ACTION_TYPE_SET_ALGO_PARAM_PARAM_2_NAME));
            assertEquals(ActionTypes.PERFORM_CALC, signal.getActions().get(2).getActionType());
            assertEquals(ActionTypes.SUBMIT_TO_MARKET, signal.getActions().get(3).getActionType());


        }
    }



}