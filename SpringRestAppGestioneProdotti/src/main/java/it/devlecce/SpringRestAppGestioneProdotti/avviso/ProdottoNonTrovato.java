package it.devlecce.SpringRestAppGestioneProdotti.avviso;

public class ProdottoNonTrovato extends RuntimeException{
    public ProdottoNonTrovato(Long id){
        super("Prodotto con id n° "+id+" non trovato");
    }

}
