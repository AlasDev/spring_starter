package it.objectmethod.spring_starter.dto;

import jakarta.validation.constraints.NotNull;

public class VeicoloDTO {

    @NotNull
    private Integer id;

    @NotNull
    private String numTarga;

    @NotNull
    private String modello;

    @NotNull
    private String colore;

    public VeicoloDTO() {}

    public VeicoloDTO(String numTarga, String modello, String colore) {
        this.numTarga = numTarga;
        this.modello = modello;
        this.colore = colore;
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
