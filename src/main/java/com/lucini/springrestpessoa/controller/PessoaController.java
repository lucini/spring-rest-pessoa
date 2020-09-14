package com.lucini.springrestpessoa.controller;

import com.lucini.springrestpessoa.entity.Pessoa;
import com.lucini.springrestpessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    PessoaService service;

    @GetMapping("/")
    public Iterable<Pessoa> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Pessoa findById(@PathVariable Long id) {
        return service.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public Pessoa save(@RequestBody Pessoa pessoa) {
        return service.save(pessoa);
    }

    @PutMapping("/")
    public Pessoa update(@RequestBody Pessoa pessoa) {
        return service.save(pessoa);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
