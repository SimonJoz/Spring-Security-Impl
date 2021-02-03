package com.example.springsecurity_basic.controller;

import com.example.springsecurity_basic.model.Template;
import com.example.springsecurity_basic.service.TemplatesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
public class TemplateController {

    private final TemplatesService service;

    public TemplateController(TemplatesService service) {
        this.service = service;
    }

    @GetMapping
    public List<Template> getAllTemplates() {
        return service.getAllTemplates();
    }

    @GetMapping("{id}")
    public Template getTemplateById(@PathVariable Long id) {
        return service.getTemplateById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Template add(@RequestBody Template entity) {
        return service.add(entity);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(String.format("Template with id %s deleted successfully.", id), HttpStatus.NO_CONTENT);
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Template updateById(@RequestBody Template updatedTemplate, @PathVariable("id") Long id) {
        return service.update(id, updatedTemplate);
    }

    @GetMapping("count")
    public long getCount() {
        return service.getCount();
    }

    @GetMapping("exist")
    public boolean isTemplateNameExist(@RequestParam String name) {
        return service.isNameExist(name);
    }
}
