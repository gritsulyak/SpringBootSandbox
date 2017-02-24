package fr.guddy.test.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {
    @Override
    @RequestMapping("/error")
    public String getErrorPath() {
        return "Unknown error occurred";
    }
}
