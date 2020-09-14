package com.lucini.springrestpessoa;

import com.lucini.springrestpessoa.entity.Pessoa;
import com.lucini.springrestpessoa.repository.PessoaRepository;
import com.lucini.springrestpessoa.service.PessoaService;
import com.lucini.springrestpessoa.service.PessoaServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
public class PessoaServiceTest {

    @TestConfiguration
    static class PessoaServiceImplTestContextConfiguration {

        @Bean
        public PessoaService pessoaService() {
            return new PessoaServiceImpl();
        }
    }

    @Autowired
    private PessoaService service;

    @MockBean
    private PessoaRepository repository;

    @Before
    public void setUp() {
        Pessoa rafael = new Pessoa(1L, "Rafael", "68689196030");
        Pessoa caius = new Pessoa(2L, "Caius", "70713907460");

        Mockito.when(repository.findAllOrderByNome()).thenReturn(List.of(rafael, caius));
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(rafael));
        Mockito.when(repository.save(rafael)).thenReturn(rafael);
        Mockito.doNothing().when(repository).deleteById(1L);
    }

    @Test
    public void salvarPessoa() {
        var pessoa = new Pessoa();
        pessoa.setNome("Rafael");
        pessoa.setCpf("68689196030");
        var pessoaSalva = service.save(pessoa);
        assertEquals(pessoa.getCpf(), pessoa.getCpf());
    }

    @Test
    public void trazerPessoaPorId() {
        var pessoa = service.findById(1L);

        pessoa.ifPresent(value -> assertEquals(value.getId(), 1L));
    }

    @Test
    public void trazerListaDePessoas() {
        List<Pessoa> lista = service.findAll();

        assertEquals(lista.get(1).getNome() , "Caius");
    }

    @Test
    public void removerPessoa() {
        service.delete(1L);
        Mockito.verify(repository, Mockito.times(1)).deleteById(1L);
    }
}
