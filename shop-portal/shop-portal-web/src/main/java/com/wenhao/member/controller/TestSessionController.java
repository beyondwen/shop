package com.wenhao.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class TestSessionController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/createSession")
    public String createSession(HttpServletRequest request, String nameValue) {
        HttpSession session = request.getSession();
        session.setAttribute("name", nameValue);
        return "sucess" + "端口号" + serverPort;
    }

    @GetMapping("/getSession")
    public String getSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object value = session.getAttribute("name");
        return value + "端口号" + serverPort;
    }
}
