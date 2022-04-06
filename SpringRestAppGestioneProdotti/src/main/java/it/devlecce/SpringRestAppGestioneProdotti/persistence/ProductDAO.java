package it.devlecce.SpringRestAppGestioneProdotti.persistence;

import it.devlecce.SpringRestAppGestioneProdotti.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProductDAO extends JpaRepository<Prodotto,Long> {
    List<Prodotto> findByNome(String nome);
    List<Prodotto> findByPrezzoLessThan(float prezzo);
    List<Prodotto> findByPrezzoLessThanEqual(float prezzo);
    List<Prodotto> findByPrezzoGreaterThan(float prezzo);
    List<Prodotto> findByPrezzoGreaterThanEqual(float prezzo);
    List<Prodotto> findByDataDiAcquistoBetween(Date dataAcquistoDa,Date dataAcquistoA);
    List<Prodotto> findByDataDiScadenzaBetween(Date dataScadenzaDa,Date dataScadenzaA);

}
