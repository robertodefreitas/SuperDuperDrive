package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.udacity.jwdnd.course1.cloudstorage.constants.Path;
import com.udacity.jwdnd.course1.cloudstorage.constants.TemplateHtml;

@Controller
@RequestMapping(Path.RESULT)
public class ResultController {

    @GetMapping()
    public String resultView() {
        return TemplateHtml.RESULT;
    }
}
