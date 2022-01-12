package com.higor.qualificacao.controller;

import com.higor.qualificacao.mapper.EnderecoMapper;
import com.higor.qualificacao.model.dto.internal.request.EnderecoCreateRequest;
import com.higor.qualificacao.model.dto.internal.response.EnderecoCreateResponse;
import com.higor.qualificacao.service.EnderecoService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = EnderecoController.PATH)
public class EnderecoController {

    public static final String PATH = "endereco";

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity criar(@RequestBody EnderecoCreateRequest enderecoCreateRequest) {
        final EnderecoCreateResponse response = Mappers.getMapper(EnderecoMapper.class).enderecoToEnderecoCreateResponse(
                enderecoService.criar(enderecoCreateRequest)
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity consultar(@PathVariable(name = "id") Long id) {
        final EnderecoCreateResponse response = Mappers.getMapper(EnderecoMapper.class).enderecoToEnderecoCreateResponse(
                enderecoService.encontrar(id)
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity remover(@PathVariable(name = "id") Long id) {
        enderecoService.remover(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity atualizar(@PathVariable(name = "id") Long id, @RequestBody EnderecoCreateRequest enderecoCreateRequest) {
        final EnderecoCreateResponse response = Mappers.getMapper(EnderecoMapper.class).enderecoToEnderecoCreateResponse(
                enderecoService.atualizar(id, enderecoCreateRequest)
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listar() {
        return new ResponseEntity(enderecoService.listarTodos(), HttpStatus.OK);
    }

}
