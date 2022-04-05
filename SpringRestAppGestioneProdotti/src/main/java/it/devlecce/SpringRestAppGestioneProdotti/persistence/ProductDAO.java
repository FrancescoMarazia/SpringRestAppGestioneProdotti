package it.devlecce.SpringRestAppGestioneProdotti.persistence;

import it.devlecce.SpringRestAppGestioneProdotti.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDAO extends JpaRepository<Prodotto,Long> {
    List<Prodotto> findByNome(String nome);
}
