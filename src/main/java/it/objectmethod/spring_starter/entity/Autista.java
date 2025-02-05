package it.objectmethod.spring_starter.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
public class Autista {

    @Column(name = "autista_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    //Foreign Keys
    @ManyToOne
    @JoinColumn(name = "veicolo_ID", nullable = false)
    private Veicolo veicolo;

    @OneToMany(mappedBy = "autista")
    private List<Corsa> corse;

    public Autista() {}

    public Autista(String nome, String cognome, Date dataNascita, String codFiscale, Veicolo veicolo, List<Corsa> corse) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.codFiscale = codFiscale;
        this.veicolo = veicolo;
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

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(Veicolo veicolo) {
        this.veicolo = veicolo;
    }

    public List<Corsa> getCorse() {
        return corse;
    }

    public void setCorse(List<Corsa> corse) {
        this.corse = corse;
    }
}
