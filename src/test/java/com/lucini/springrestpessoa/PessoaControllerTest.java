package com.lucini.springrestpessoa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucini.springrestpessoa.controller.PessoaController;
import com.lucini.springrestpessoa.entity.Pessoa;
import com.lucini.springrestpessoa.service.PessoaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(PessoaController.class)
public class PessoaControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PessoaService service;

    @Test
    public void findAll() throws Exception {
        Mockito.when(service.findAll()).thenReturn(Collections.emptyList());

        mvc.perform(MockMvcRequestBuilders.get("/pessoa/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findById() throws Exception {
        Mockito.when(service.findById(1L)).thenReturn(Optional.of(new Pessoa(1L)));

        mvc.perform(MockMvcRequestBuilders.get("/pessoa/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void save() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        var pessoa = new Pessoa(1L, "Rafael", "02210133343");
        var pessoaJson = mapper.writeValueAsString(pessoa);
        Mockito.when(service.save(pessoa)).thenReturn(pessoa);

        mvc.perform(
                MockMvcRequestBuilders
                        .post("/pessoa/")
                        .content(pessoaJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void update() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        var pessoa = new Pessoa(1L, "Rafael", "02210133343");
        var pessoaJson = mapper.writeValueAsString(pessoa);
        Mockito.when(service.save(pessoa)).thenReturn(pessoa);

        mvc.perform(
                MockMvcRequestBuilders
                        .put("/pessoa/")
                        .content(pessoaJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(service).delete(1L);

        mvc.perform(
                MockMvcRequestBuilders
                        .delete("/pessoa/1")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
