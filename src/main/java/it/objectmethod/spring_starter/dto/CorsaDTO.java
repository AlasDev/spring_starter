package it.objectmethod.spring_starter.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CorsaDTO {

    @NotNull
    private Integer id;

    @NotNull
    private String statoCorsa;

    @NotNull
    private Double distanzaPercorsa;

    @NotNull
    private Double costoCorsa;

    @NotNull
    private String indirizzoInizio;

    @NotNull
    private String indirizzoFine;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date dataPrenotazione;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date dataInizio;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date dataFine;

    //Foreign Key
    @NotNull
    private Integer cliente_ID;

    //Foreign Key
    @NotNull
    private Integer autista_ID;

    public CorsaDTO() {}

    public CorsaDTO(String statoCorsa, Double distanzaPercorsa, Double costoCorsa, String indirizzoInizio, String indirizzoFine, Date dataPrenotazione, Date dataInizio, Date dataFine, Integer cliente_ID, Integer autista_ID) {
        this.statoCorsa = statoCorsa;
        this.distanzaPercorsa = distanzaPercorsa;
        this.costoCorsa = costoCorsa;
        this.indirizzoInizio = indirizzoInizio;
        this.indirizzoFine = indirizzoFine;
        this.dataPrenotazione = dataPrenotazione;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.cliente_ID = cliente_ID;
        this.autista_ID = autista_ID;
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

    public Integer getCliente_ID() {
        return cliente_ID;
    }

    public void setCliente_ID(Integer cliente_ID) {
        this.cliente_ID = cliente_ID;
    }

    public Integer getAutista_ID() {
        return autista_ID;
    }

    public void setAutista_ID(Integer autista_ID) {
        this.autista_ID = autista_ID;
    }
}
