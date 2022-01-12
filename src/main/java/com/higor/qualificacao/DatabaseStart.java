package com.higor.qualificacao;

import com.higor.qualificacao.model.entity.Endereco;
import com.higor.qualificacao.repository.EnderecoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseStart {

    @Bean
    public CommandLineRunner startDatabase(EnderecoRepository enderecoRepository) {
        return args -> {
            enderecoRepository.save(
                    new Endereco(null, "Avenida Toledo", 432, null, "Centro", "Cascavel", "PR", "BR", "85810-230", -24.950498, -53.477254)
            );
        };
    }

}
