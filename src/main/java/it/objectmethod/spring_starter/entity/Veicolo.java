package it.objectmethod.spring_starter.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "veicolo")
public class Veicolo {
    @Column(name = "veicolo_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_targa")
    private String numTarga;

    @Column(name = "modello")
    private String modello;

    @Column(name = "colore")
    private String colore;

    //Foreign Key
    @OneToMany(mappedBy = "veicolo")
    private List<Autista> autisti;

    public Veicolo() {}

    public Veicolo(String colore, String modello, String numTarga) {
        this.colore = colore;
        this.modello = modello;
        this.numTarga = numTarga;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumTarga() {
        return numTarga;
    }

    public void setNumTarga(String numTarga) {
        this.numTarga = numTarga;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }
}
