package it.devlecce.SpringRestAppGestioneProdotti.controller;

import it.devlecce.SpringRestAppGestioneProdotti.model.Prodotto;
import it.devlecce.SpringRestAppGestioneProdotti.persistence.ProductDAO;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("/prodotti/{id}")
    Optional<Prodotto> prodottoPerId(@PathVariable Long id) {
        return repository.findById(id);
    }

    @GetMapping("/prodotti/ricerca/nome")
    List<Prodotto> prodottoPerNome(@RequestParam("nome") String nome) {
        return repository.findByNome(nome);
    }

    @GetMapping("/prodotti/ricerca/qt")
    List<Prodotto> prodottoPerQt(@RequestParam("qtMinima") float qt1 , @RequestParam("qtMassima") float qt2) {
        return repository.findByQtBetween(qt1,qt2);
    }

    @GetMapping("/prodotti/ricerca/qt/massimo")
    List<Prodotto> prodottoPerQtMassima(@RequestParam("qt") float qt) {
        return repository.findByQtLessThan(qt);
    }
    @GetMapping("/prodotti/ricerca/qt/massimoIncluso")
    List<Prodotto> prodottoPerQtMassimaInclusa(@RequestParam("qt") float qt) {
        return repository.findByQtLessThanEqual(qt);
    }

    @GetMapping("/prodotti/ricerca/qt/minimo")
    List<Prodotto> prodottoPerQtMinima(@RequestParam("qt") float qt) {
        return repository.findByQtGreaterThan(qt);
    }
    @GetMapping("/prodotti/ricerca/qt/minimoIncluso")
    List<Prodotto> prodottoPerQtMinimaInclusa(@RequestParam("qt") float qt) {
        return repository.findByQtGreaterThanEqual(qt);
    }

    @GetMapping("/prodotti/ricerca/prezzo/massimo")
    List<Prodotto> prodottoPerPrezzoMassimo(@RequestParam("prezzoMassimo")  float prezzo) {
        return repository.findByPrezzoLessThan(prezzo);
    }

    @GetMapping("/prodotti/ricerca/prezzo/massimoIncluso")
    List<Prodotto> prodottoPerPrezzoMassimoIncluso(@RequestParam("prezzoMassimo")  float prezzo) {
        return repository.findByPrezzoLessThanEqual(prezzo);
    }

    @GetMapping("/prodotti/ricerca/prezzo/minimo")
    List<Prodotto> prodottoPerPrezzoMinimo(@RequestParam("prezzoMinimo")  float prezzo) {
        return repository.findByPrezzoGreaterThan(prezzo);
    }

    @GetMapping("/prodotti/ricerca/prezzo/minimoIncluso")
    List<Prodotto> prodottoPerPrezzoMinimoIncluso(@RequestParam("prezzoMinimo")  float prezzo) {
        return repository.findByPrezzoGreaterThanEqual(prezzo);
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

    @GetMapping("/prodotti/ricerca/dataScadenza/prima")
    public List<Prodotto> ricercaPrimaDataScadenzaProdotto(
            @RequestParam(name = "data") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date data

    ) {
        return repository.findByDataDiScadenzaBefore(data);
    }

    @GetMapping("/prodotti/ricerca/dataScadenza/dopo")
    public List<Prodotto> ricercaDopoDataScadenzaProdotto(
            @RequestParam(name = "data") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date data

    ) {
        return repository.findByDataDiScadenzaAfter(data);
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

    @GetMapping("/prodotti/ricerca/dataAcquisto/prima")
    public List<Prodotto> ricercaPrimaDataAcquistoProdotto(
            @RequestParam(name = "data") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date data

    ) {
        return repository.findByDataDiAcquistoBefore(data);
    }

    @GetMapping("/prodotti/ricerca/dataAcquisto/dopo")
    public List<Prodotto> ricercaDopoDataAcquistoProdotto(
            @RequestParam(name = "data") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date data

    ) {
        return repository.findByDataDiAcquistoAfter(data);
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

                float prezzo = Float.parseFloat(record.get(0));

                float qt = Float.parseFloat(record.get(1));

                String dAcquisto = record.get(2);
                Date dataAcquisto = dateFormat.parse(dAcquisto);

                String dScadenza = record.get(3);
                Date dataScadenza = dateFormat.parse(dScadenza);
                String nome = record.get(4);
                Prodotto p = new Prodotto(prezzo, qt, dataAcquisto, dataScadenza, nome);
                prodotti.add(p);
            }
        } catch (IOException | ParseException e) {
            logger.error("Si è verificato un errore", e);
        }

        repository.saveAll(prodotti);
        logger.info("Contenuto del file aggiunto al database");
        return ResponseEntity.ok("Contenuto del CSV aggiunto al database");
    }

    @PutMapping("/prodotti/{id}")
    Prodotto aggiornaProdotto(@RequestBody Prodotto prodotto, @PathVariable Long id) {
        return repository.findById(id)
                .map(product -> {
                    product.setNome(prodotto.getNome());
                    product.setPrezzo(prodotto.getPrezzo());
                    product.setQt(prodotto.getQt());
                    product.setDataDiAcquisto(prodotto.getDataDiAcquisto());
                    product.setDataDiScadenza(prodotto.getDataDiScadenza());
                    return repository.save(product);
                })
                .orElseGet(() -> {
                    prodotto.setId(id);
                    return repository.save(prodotto);
                });
    }


    @DeleteMapping("/prodotto/{id}")
    void eliminaProdotto(@PathVariable Long id){
        repository.deleteById(id);
    }
}