package com.thelivelock.elasticsearch_autocomplete.controller;

import com.thelivelock.elasticsearch_autocomplete.model.Formulation;
import com.thelivelock.elasticsearch_autocomplete.service.FormulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class SearchController {

    @Autowired
    private FormulationService formulationService;

    @GetMapping
    public List<Formulation> getAllUsers() {
        return this.formulationService.listAll();
    }

    @GetMapping(path = "/search")
    public List<Formulation> searchUsers(@RequestParam String keywords) {
        return this.formulationService.search(keywords);
    }

    @PostMapping("/insert")
    public Formulation insertProduct(@RequestBody Formulation user){
        return formulationService.save(user);
    }

}
