package com.example.springsecuritycustom.controller;

import com.example.springsecuritycustom.model.Template;
import com.example.springsecuritycustom.service.TemplatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class LoginFormController {

    private final TemplatesService templatesService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/templates")
    public String getTemplates(Model model) {
        List<Template> allTemplates = templatesService.getAllTemplates();
        model.addAttribute("templates", allTemplates);
        return "templates";
    }

    @PostMapping("/add-template")
    public String addTemplates() {
        templatesService.add(new Template(UUID.randomUUID().toString()));
        return "redirect:templates";
    }
}
