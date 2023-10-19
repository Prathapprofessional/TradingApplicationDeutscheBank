package com.TradingApplicationDeutscheBank.db.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trading")
public class TradingController {

    @GetMapping("/signal")
    public void process(int id)
    {

    }
}
