package com.lucini.springrestpessoa.service;

import com.lucini.springrestpessoa.entity.Pessoa;
import com.lucini.springrestpessoa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    PessoaRepository repository;

    @Override
    public List<Pessoa> findAll() {
        return repository.findAllOrderByNome();
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
