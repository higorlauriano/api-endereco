package com.higor.qualificacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higor.qualificacao.model.dto.internal.request.EnderecoCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
public class EnderecoControllerTests {

    private EnderecoController enderecoController;

    private MockMvc mockMvc;

    @Autowired
    public EnderecoControllerTests(EnderecoController enderecoController, MockMvc mockMvc) {
        this.enderecoController = enderecoController;
        this.mockMvc = mockMvc;
    }

    @BeforeEach
    public void setup() {
        MockMvcBuilders.standaloneSetup(this.enderecoController);
    }

    @Test
    public void listar() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/endereco"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void consultar() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/endereco/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void criar() throws Exception {

        final EnderecoCreateRequest enderecoCreateRequest = new EnderecoCreateRequest(
                "Amphitheatre Parkway",
                1600,
                null,
                "Santa Clara County",
                "Mountain View",
                "CA",
                "USA",
                "94043",
                37.422388,
                -122.0841883
        );

        final String stringPayload = new ObjectMapper().writeValueAsString(enderecoCreateRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/endereco")
                        .content(stringPayload.getBytes(StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void atualizar() throws Exception {

        final EnderecoCreateRequest enderecoCreateRequest = new EnderecoCreateRequest(
                "Avenida Toledo",
                434,
                null,
                "Centro",
                "Cascavel",
                "PR",
                "BR",
                "85810-230",
                null,
                null
        );

        final String stringPayload = new ObjectMapper().writeValueAsString(enderecoCreateRequest);

        mockMvc.perform(MockMvcRequestBuilders.patch("/endereco/{id}", 1L)
                        .content(stringPayload.getBytes(StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void remover() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/endereco/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
