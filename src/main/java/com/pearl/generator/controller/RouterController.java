package com.pearl.generator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author TangDan
 * @version 1.0
 * @since 2022/6/29
 */
@Controller
@RequestMapping
public class RouterController {

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }
}
