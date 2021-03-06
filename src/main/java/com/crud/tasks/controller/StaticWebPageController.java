package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@CrossOrigin("*")
@Controller
public class StaticWebPageController {

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("variable", "My thymeleaf variable");
        model.put("one", 1);
        model.put("two", 2);
        model.put("multiply", "*");
        model.put("add", "+");
        model.put("subtract", "-");
        model.put("equals", "=");
        return "index";
    }
}
