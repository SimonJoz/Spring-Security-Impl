package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.exception.InvalidUpdateException;
import com.example.springsecurityjwt.exception.ResourceNotFoundException;
import com.example.springsecurityjwt.model.Template;
import com.example.springsecurityjwt.repo.TemplateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplatesService {
    private final TemplateRepo templateRepo;

    @Autowired
    public TemplatesService(TemplateRepo templatesRepo) {
        this.templateRepo = templatesRepo;
    }

    public Template add(Template template) {
        template = new Template(template.getName());
        return templateRepo.save(template);
    }

    public List<Template> getAllTemplates() {
        return templateRepo.findAll();
    }

    public Template getTemplateById(Long id) {
        return templateRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Template with id %d does not exist", id)));
    }

    public long getCount() {
        return templateRepo.count();
    }

    public boolean isNameExist(String name) {
        return templateRepo.countByName(name) > 0;
    }

    public void deleteById(Long id) {
        templateRepo.deleteById(id);
    }

    public Template update(Long id, Template updatedTemplate) {
        long updatedTemplateId = updatedTemplate.getId();
        if (id.equals(updatedTemplateId)) {
            return templateRepo.save(updatedTemplate);
        }
        throw new InvalidUpdateException(
                String.format("Id '%d' updated template id '%d' does not match", id, updatedTemplateId));
    }
}

