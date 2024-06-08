package com.celuma.webapi.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Integer Idinvoice;

    @Column(name = "order_id")
    private Integer idOrden;

    @Column(name = "name")
    private String nombre;

    private String rfc;

    private String zip;

    @Column(name = "fiscal_regime")
    private String regimenFiscal;

    @OneToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Orden orden;

    // Getters and Setters


    public Integer getIdinvoice() {
        return Idinvoice;
    }

    public void setIdinvoice(Integer idinvoice) {
        Idinvoice = idinvoice;
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getRegimenFiscal() {
        return regimenFiscal;
    }

    public void setRegimenFiscal(String regimenFiscal) {
        this.regimenFiscal = regimenFiscal;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }
}
