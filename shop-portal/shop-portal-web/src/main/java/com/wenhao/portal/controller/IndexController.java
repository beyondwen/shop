package com.wenhao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /**
     * index页面
     */
    private static String INDEX_FTL = "index";

    @GetMapping("/")
    public String index() {
        return INDEX_FTL;
    }
}
