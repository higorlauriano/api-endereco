package com.higor.qualificacao.service;

import com.higor.qualificacao.exception.CustomRuntimeException;
import com.higor.qualificacao.mapper.EnderecoMapper;
import com.higor.qualificacao.model.dto.external.response.LocalidadeGeometryLocation;
import com.higor.qualificacao.model.dto.internal.request.EnderecoCreateRequest;
import com.higor.qualificacao.model.entity.Endereco;
import com.higor.qualificacao.repository.EnderecoRepository;
import com.higor.qualificacao.validador.ValidadorEndereco;
import org.apache.tomcat.jni.Local;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    private final LocalidadeService localidadeService;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository, LocalidadeService localidadeService) {
        this.enderecoRepository = enderecoRepository;
        this.localidadeService = localidadeService;
    }

    @Transactional
    public Endereco criar(EnderecoCreateRequest enderecoCreateRequest) {
        final Endereco endereco = Mappers.getMapper(EnderecoMapper.class).enderecoCreateRequestToEndereco(enderecoCreateRequest);

        ValidadorEndereco.validarDadosObrigatorios(endereco);

        if (endereco.getLatitude() == null
                || endereco.getLongitude() == null) {
            final LocalidadeGeometryLocation loc = localidadeService.getCoordenadasGeograficas(endereco);

            endereco.setLongitude(loc.getLongitude());
            endereco.setLatitude(loc.getLatitude());
        }

        return enderecoRepository.saveAndFlush(endereco);
    }

    public Endereco encontrar(final Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new CustomRuntimeException("Address not found with ID " + id));
    }

    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    public void remover(final Long id) {
        final Endereco endereco = encontrar(id);

        enderecoRepository.delete(endereco);
    }

    @Transactional
    public Endereco atualizar(final Long id, final EnderecoCreateRequest enderecoCreateRequest) {
        final Endereco endereco = encontrar(id);

        if (enderecoCreateRequest.getStreetName() != null
                && !enderecoCreateRequest.getStreetName().equals(endereco.getStreetName())) {
            endereco.setStreetName(enderecoCreateRequest.getStreetName());
        }

        if (enderecoCreateRequest.getNumber() != null
                && !enderecoCreateRequest.getNumber().equals(endereco.getNumber())) {
            endereco.setNumber(enderecoCreateRequest.getNumber());
        }

        if (enderecoCreateRequest.getComplement() != null
                && !enderecoCreateRequest.getComplement().equals(endereco.getComplement())) {
            endereco.setComplement(enderecoCreateRequest.getComplement());
        }

        if (enderecoCreateRequest.getNeighbourhood() != null
                && !enderecoCreateRequest.getNeighbourhood().equals(endereco.getNeighbourhood())) {
            endereco.setNeighbourhood(enderecoCreateRequest.getNeighbourhood());
        }

        if (enderecoCreateRequest.getCity() != null
                && !enderecoCreateRequest.getCity().equals(endereco.getCity())) {
            endereco.setCity(enderecoCreateRequest.getCity());
        }

        if (enderecoCreateRequest.getState() != null
                && !enderecoCreateRequest.getState().equals(endereco.getState())) {
            endereco.setState(enderecoCreateRequest.getState());
        }

        if (enderecoCreateRequest.getCountry() != null
                && !enderecoCreateRequest.getCountry().equals(endereco.getCountry())) {
            endereco.setCountry(enderecoCreateRequest.getCountry());
        }

        if (enderecoCreateRequest.getZipCode() != null
                && !enderecoCreateRequest.getZipCode().equals(endereco.getZipCode())) {
            endereco.setZipCode(enderecoCreateRequest.getZipCode());
        }

        if (enderecoCreateRequest.getLatitude() != null
                && !enderecoCreateRequest.getLatitude().equals(endereco.getLatitude())) {
            endereco.setLatitude(enderecoCreateRequest.getLatitude());
        }

        if (enderecoCreateRequest.getLongitude() != null
                && !enderecoCreateRequest.getLongitude().equals(endereco.getLongitude())) {
            endereco.setLongitude(enderecoCreateRequest.getLongitude());
        }

        ValidadorEndereco.validarDadosObrigatorios(endereco);


        final LocalidadeGeometryLocation loc = localidadeService.getCoordenadasGeograficas(endereco);

        endereco.setLongitude(loc.getLongitude());
        endereco.setLatitude(loc.getLatitude());

        return enderecoRepository.saveAndFlush(endereco);
    }


}
