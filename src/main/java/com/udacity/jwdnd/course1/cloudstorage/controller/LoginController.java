package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.udacity.jwdnd.course1.cloudstorage.constants.Path;
import com.udacity.jwdnd.course1.cloudstorage.constants.TemplateHtml;

// https://huongdanjava.com/custom-login-page-using-bootstrap-and-thymeleaf-in-spring-security.html

@Controller
@RequestMapping(Path.LOGIN)
public class LoginController {

    @GetMapping()
    public String loginView() {
        return TemplateHtml.LOGIN;
    }
}
