package it.devlecce.SpringRestAppGestioneProdotti.avviso;

import it.devlecce.SpringRestAppGestioneProdotti.model.Prodotto;
import it.devlecce.SpringRestAppGestioneProdotti.persistence.ProductDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProdottoRestController {
    private final ProductDAO repository;
    ProdottoRestController(ProductDAO repository){
        this.repository=repository;
    }

    @GetMapping("/prodotti")
    List<Prodotto> tutti(){
        return repository.findAll();
    }
    @GetMapping("/prodotto/{id}")
    Prodotto prodottoPerId(@PathVariable Long id){
        return repository.findById(id).orElseThrow(
                ()->new ProdottoNonTrovato(id));
    }
    @GetMapping("/prodotti/{nome}")
    List<Prodotto> prodottoPerNome(@PathVariable String nome){
        return repository.findByNome(nome);
    }
    @PostMapping("/prodotto")
    Prodotto addProdotto(@RequestBody Prodotto prodotto){
        return repository.save(prodotto);
    }

    /*@PutMapping("/prodotto/{id}")
    Prodotto aggiornaProdotto(@PathVariable Long id,@RequestBody Prodotto nuovoProdotto){
        Optional<Prodotto> prodotto = repository.findById(id);
        if(prodotto.isPresent()){
            prodotto.get().setNome(nuovoProdotto.getNome());
            prodotto.get().setDataAcquisto(nuovoProdotto.getDataAcquisto());
            prodotto.get().setPrezzo(nuovoProdotto.getPrezzo());
            return repository.save(prodotto.get());
        }
    }*/
}
