package com.celuma.webapi.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer IdOrden;

    @Column(name = "creation_date")
    private LocalDateTime fechaCreacion;

    @Column(name = "status")
    private boolean estado;

    @OneToMany(mappedBy = "orden")
    private List<ProductoOrden> productoOrden;

    @OneToOne(mappedBy = "orden")
    private Invoice invoice;

    @OneToOne(mappedBy = "orden")
    private Sale sale;

    // Getters and Setter

    public Integer getIdOrden() {
        return IdOrden;
    }

    public void setIdOrden(Integer idOrden) {
        IdOrden = idOrden;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<ProductoOrden> getProductoOrden() {
        return productoOrden;
    }

    public void setProductoOrden(List<ProductoOrden> productoOrden) {
        this.productoOrden = productoOrden;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
