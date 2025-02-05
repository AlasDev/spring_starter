package it.objectmethod.spring_starter.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Column(name = "cliente_ID")
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "data_nascita")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNascita;

    @Column(name = "cod_fiscale")
    private String codFiscale;

    @Column(name = "data_iscrizione")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataIscrizione;

    @OneToMany(mappedBy = "cliente")
    private List<Corsa> corse;

    public Cliente() {}

    public Cliente(String nome, String cognome, Date dataNascita, String codFiscale, Date dataIscrizione, List<Corsa> corse) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.codFiscale = codFiscale;
        this.dataIscrizione = dataIscrizione;
        this.corse = corse;
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

    public Date getDataIscrizione() {
        return dataIscrizione;
    }

    public void setDataIscrizione(Date dataIscrizione) {
        this.dataIscrizione = dataIscrizione;
    }

    public List<Corsa> getCorse() {
        return corse;
    }

    public void setCorse(List<Corsa> corse) {
        this.corse = corse;
    }
}
