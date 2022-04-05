package it.devlecce.SpringRestAppGestioneProdotti.configuration;

import it.devlecce.SpringRestAppGestioneProdotti.model.Prodotto;
import it.devlecce.SpringRestAppGestioneProdotti.persistence.ProductDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.parameters.P;

@Configuration
public class PrimoInserimento {
    private static final Logger logger = LoggerFactory.getLogger(PrimoInserimento.class);

    @Bean
    CommandLineRunner initDatabase(ProductDAO repository){
        return args -> {
            repository.save(new Prodotto(500.0,"Televisore"));
            repository.save(new Prodotto(700.0,"Cellulare"));
        };
    }
}
