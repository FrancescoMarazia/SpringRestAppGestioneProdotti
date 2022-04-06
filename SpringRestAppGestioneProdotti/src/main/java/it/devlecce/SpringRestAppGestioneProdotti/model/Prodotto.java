package it.devlecce.SpringRestAppGestioneProdotti.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Prodotto {

    //ID, prezzo, data di acquisto, nome
    @Id
    @GeneratedValue
    private Long id;
    private float prezzo;
    private float qt;
    private Date dataDiAcquisto;
    private Date dataDiScadenza;
    private String nome;

    Prodotto(){

    }
    //cj
    public Prodotto(float prezzo, float qt, Date dataDiAcquisto, Date dataDiScadenza, String nome) {
        this.prezzo = prezzo;
        this.qt = qt;
        this.dataDiAcquisto = dataDiAcquisto;
        this.dataDiScadenza = dataDiScadenza;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public float getQt() {
        return qt;
    }

    public void setQt(float qt) {
        this.qt = qt;
    }

    public Date getDataDiAcquisto() {
        return dataDiAcquisto;
    }

    public void setDataDiAcquisto(Date dataDiAcquisto) {
        this.dataDiAcquisto = dataDiAcquisto;
    }

    public Date getDataDiScadenza() {
        return dataDiScadenza;
    }

    public void setDataDiScadenza(Date dataDiScadenza) {
        this.dataDiScadenza = dataDiScadenza;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
