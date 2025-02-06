package it.objectmethod.spring_starter.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "corsa")
public class Corsa {
    @Column(name = "corsa_ID")
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "stato_corsa")
    private String statoCorsa;

    @Column(name = "distanza_percorsa")
    private Double distanzaPercorsa;

    @Column(name = "costo_corsa")
    private Double costoCorsa;

    @Column(name = "indirizzo_inizio")
    private String indirizzoInizio;

    @Column(name = "indirizzo_fine")
    private String indirizzoFine;

    @Column(name = "data_prenotazione")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date dataPrenotazione;

    @Column(name = "data_inizio")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date dataInizio;

    @Column(name = "data_fine")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date dataFine;

    @ManyToOne
    @JoinColumn(name = "autista_ID", nullable = false)
    private Autista autista;

    //Foreign Keys
    @ManyToOne
    @JoinColumn(name = "cliente_ID", nullable = false)
    private Cliente cliente;

    public Corsa() {}

    public Corsa(String statoCorsa, Double distanzaPercorsa, Double costoCorsa, String indirizzoInizio, String indirizzoFine, Date dataPrenotazione, Date dataInizio, Date dataFine, Autista autista, Cliente cliente) {
        this.statoCorsa = statoCorsa;
        this.distanzaPercorsa = distanzaPercorsa;
        this.costoCorsa = costoCorsa;
        this.indirizzoInizio = indirizzoInizio;
        this.indirizzoFine = indirizzoFine;
        this.dataPrenotazione = dataPrenotazione;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.autista = autista;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatoCorsa() {
        return statoCorsa;
    }

    public void setStatoCorsa(String statoCorsa) {
        this.statoCorsa = statoCorsa;
    }

    public Double getDistanzaPercorsa() {
        return distanzaPercorsa;
    }

    public void setDistanzaPercorsa(Double distanzaPercorsa) {
        this.distanzaPercorsa = distanzaPercorsa;
    }

    public Double getCostoCorsa() {
        return costoCorsa;
    }

    public void setCostoCorsa(Double costoCorsa) {
        this.costoCorsa = costoCorsa;
    }

    public String getIndirizzoInizio() {
        return indirizzoInizio;
    }

    public void setIndirizzoInizio(String indirizzoInizio) {
        this.indirizzoInizio = indirizzoInizio;
    }

    public String getIndirizzoFine() {
        return indirizzoFine;
    }

    public void setIndirizzoFine(String indirizzoFine) {
        this.indirizzoFine = indirizzoFine;
    }

    public Date getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(Date dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public Autista getAutista() {
        return autista;
    }

    public void setAutista(Autista autista) {
        this.autista = autista;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}