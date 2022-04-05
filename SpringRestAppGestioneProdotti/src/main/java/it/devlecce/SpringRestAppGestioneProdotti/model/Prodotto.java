package it.devlecce.SpringRestAppGestioneProdotti.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Prodotto {
    @Id
    @GeneratedValue
    private Long id;
    private double prezzo;
    private Date dataAcquisto;
    private String nome;

    Prodotto(){

    }

    public Prodotto(double prezzo, String nome) {
        this.id = id;
        this.prezzo = prezzo;
        this.nome = nome;
    }

    public Prodotto(Long id, double prezzo, Date dataAcquisto, String nome) {
        this.id = id;
        this.prezzo = prezzo;
        this.dataAcquisto = dataAcquisto;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public Date getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(Date dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
