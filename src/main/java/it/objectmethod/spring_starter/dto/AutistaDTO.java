package it.objectmethod.spring_starter.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AutistaDTO {

    @NotNull
    private Integer id;

    @NotNull
    private String nome;

    @NotNull
    private String cognome;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNascita;

    @NotNull
    private String codFiscale;

    //Foreign Key
    @NotNull
    private Integer veicolo_ID;

    public AutistaDTO() {}

    public AutistaDTO(String nome, String cognome, Date dataNascita, String codFiscale, Integer veicolo_ID) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.codFiscale = codFiscale;
        this.veicolo_ID = veicolo_ID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getCodFiscale() {
        return codFiscale;
    }

    public void setCodFiscale(String codFiscale) {
        this.codFiscale = codFiscale;
    }

    public Integer getVeicolo_ID() {
        return veicolo_ID;
    }

    public void setVeicolo_ID(Integer veicolo_ID) {
        this.veicolo_ID = veicolo_ID;
    }
}
