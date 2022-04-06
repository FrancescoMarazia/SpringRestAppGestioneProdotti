package it.devlecce.SpringRestAppGestioneProdotti.controller;

import it.devlecce.SpringRestAppGestioneProdotti.model.Prodotto;
import it.devlecce.SpringRestAppGestioneProdotti.persistence.ProductDAO;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class ProdottoRestController {

    private final ProductDAO repository;

    ProdottoRestController(ProductDAO repository) {
        this.repository = repository;
    }

    @GetMapping("/prodotti")
    List<Prodotto> tutti() {
        return repository.findAll();
    }

    @GetMapping("/prodotto/{id}")
    Optional<Prodotto> prodottoPerId(@PathVariable Long id) {
        return repository.findById(id);
    }

    @GetMapping("/prodotto/{nome}")
    List<Prodotto> prodottoPerNome(@PathVariable String nome) {
        return repository.findByNome(nome);
    }

    @GetMapping("/prodotto/{prezzo}")
    List<Prodotto> prodottoPerPrezzo(@PathVariable float prezzo) {
        return repository.findByPrezzo(prezzo);
    }

    @GetMapping("/prodotti/ricerca/dataScadenza")
    public List<Prodotto> ricercaPerDataScadenzaProdottoTraDate(
            @RequestParam(name = "dataDa") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date datada,
            @RequestParam(name = "dataA") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date dataa
    ) {
        return repository.findByDataDiScadenzaBetween(datada, dataa);
    }

    @GetMapping("/prodotti/ricerca/dataAcquisto")
    public List<Prodotto> ricercaPerDataAcquistoProdottoTraDate(
            @RequestParam(name = "dataDa") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date datada,
            @RequestParam(name = "dataA") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date dataa
    ) {
        return repository.findByDataDiAcquistoBetween(datada, dataa);
    }

    @PostMapping("/prodotto")
    Prodotto nuovoProdotto(@RequestBody Prodotto nuovoProdotto) {
        return repository.save(nuovoProdotto);
    }

    private final @PostMapping("/caricaCSV")
    ResponseEntity<String> caricaCSV(@RequestParam("file") MultipartFile file) {
        Reader in = null;
        Logger logger = LoggerFactory.getLogger(ProdottoRestController.class);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        List<Prodotto> prodotti = new ArrayList();

        try {
            in = new InputStreamReader(file.getInputStream());
// Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder().build().parse(in);
            for (CSVRecord record : records) {
                //Long id = Long.parseLong(record.get(0));
                float prezzo = Float.parseFloat(record.get(0));
                //logger.info("Autore: " + autore);
                float qt = Float.parseFloat(record.get(1));
                //logger.warn("Titolo: " + titolo);

                String dAcquisto = record.get(2);
                Date dataAcquisto = dateFormat.parse(dAcquisto);
                // logger.warn("Titolo: " + titolo);
                String dScadenza = record.get(3);
                Date dataScadenza = dateFormat.parse(dAcquisto);
                //logger.warn("Titolo: " + titolo);
                String nome = record.get(4);
                Prodotto p = new Prodotto(prezzo, qt, dataAcquisto, dataScadenza, nome);
                prodotti.add(p);
            }


        } catch (IOException | ParseException e) {
            logger.error("Si è verificato un errore", e);
        }
        repository.saveAll(prodotti);
        return ResponseEntity.ok("CSV");
    }
}