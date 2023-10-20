package com.db.test.TradingApplicationDeutscheBank.Controllers;

import com.db.test.TradingApplicationDeutscheBank.service.Application;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trading")
public class TradingController {
    private Application application = new Application();

    @GetMapping("/signal/{id}")
    public void processSignal(@PathVariable("id") int id)
    {
       application.handleSignal(id);
    }
}
