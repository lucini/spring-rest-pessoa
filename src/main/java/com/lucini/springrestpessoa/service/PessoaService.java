package com.lucini.springrestpessoa.service;

import com.lucini.springrestpessoa.entity.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaService {

    List<Pessoa> findAll();

    Optional<Pessoa> findById(Long id);

    Pessoa save(Pessoa pessoa);

    void delete(Long id);

}
