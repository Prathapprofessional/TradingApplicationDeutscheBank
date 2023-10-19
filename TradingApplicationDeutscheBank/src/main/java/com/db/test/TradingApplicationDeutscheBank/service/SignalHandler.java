package com.db.test.TradingApplicationDeutscheBank.service;

/**
 * This is an upcall from our trading system, and we cannot change it.
 */
public interface SignalHandler {
    void handleSignal(int signal);
}