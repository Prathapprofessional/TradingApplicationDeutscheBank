package com.db.test.TradingApplicationDeutscheBank.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trading")
public class TradingController {

    @GetMapping("/signal/{id}")
    public void processSignal(@PathVariable("id") int id)
    {
        System.out.println("Hello");
    }
}
