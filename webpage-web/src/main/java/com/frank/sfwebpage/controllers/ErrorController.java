package com.frank.sfwebpage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/oups")
    public void showErrorPage() {
        throw new RuntimeException("Expected: controller used to showcase what "
                + "happens when an exception is thrown");
    }

}
