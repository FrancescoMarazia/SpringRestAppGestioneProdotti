package it.devlecce.SpringRestAppGestioneProdotti.configuration;

import it.devlecce.SpringRestAppGestioneProdotti.model.Prodotto;
import it.devlecce.SpringRestAppGestioneProdotti.persistence.ProductDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.parameters.P;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class PrimoInserimento {
    private static final Logger logger = LoggerFactory.getLogger(PrimoInserimento.class);

    @Bean
    CommandLineRunner initDatabase(ProductDAO repository){
        return args -> {
            List<Prodotto> prodotti = new ArrayList();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-DD-yyyy");
            Date dataAcquisto = dateFormat.parse("01-01-2022");
            Date dataScadenza = dateFormat.parse("01-04-2022");
            Prodotto p1 = new Prodotto(5.49f,1f,dataAcquisto,dataScadenza,"Insalata di mare");
            dataAcquisto = dateFormat.parse("01-06-2022");
            dataScadenza = dateFormat.parse("01-07-2022");
            Prodotto p2 = new Prodotto(0.55f,3f,dataAcquisto,dataScadenza,"Passata di pomodoro");
            dataAcquisto = dateFormat.parse("01-02-2022");
            dataScadenza = dateFormat.parse("01-05-2022");
            Prodotto p3 = new Prodotto(1.09f,1f,dataAcquisto,dataScadenza,"Olive");

            prodotti.add(p1);
            prodotti.add(p2);
            prodotti.add(p3);
            repository.saveAll(prodotti);

        };
    }
}
