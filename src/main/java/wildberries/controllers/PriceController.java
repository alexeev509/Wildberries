package wildberries.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import wildberries.Services.GoogleSheetsService;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Controller
public class PriceController {

    @Autowired
    private GoogleSheetsService googleSheetsService;

    @GetMapping("/refreshPrices")
    public String homePage(Model model) throws GeneralSecurityException, IOException {
        googleSheetsService.getPrices();
        model.addAttribute("appName", "any");
        return "home";
    }
}