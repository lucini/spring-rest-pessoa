package com.lucini.springrestpessoa.repository;

import com.lucini.springrestpessoa.entity.Pessoa;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
    @Query("select * from pessoa order by nome")
    List<Pessoa> findAllOrderByNome();
}
