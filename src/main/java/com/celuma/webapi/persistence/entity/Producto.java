package com.celuma.webapi.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer idProducto;

    @Column(name = "name")
    private String nombre;

    @Column(name = "category_id")
    private Integer idCategoria;

    @Column(name = "content")
    private String contenido;

    @Column(name = "instructions")
    private String instrucciones;

    @Column(name = "cautions")
    private String precauciones;

    @Column( name = "is_deleted")
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "category_id", updatable = false, insertable = false)
    private Categoria categorias;

    @OneToMany(mappedBy = "producto")
    private List<ProductoOrden> ordenes;

    // Getters and Setters

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getPrecauciones() {
        return precauciones;
    }

    public void setPrecauciones(String precauciones) {
        this.precauciones = precauciones;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Categoria getCategorias() {
        return categorias;
    }

    public void setCategorias(Categoria categorias) {
        this.categorias = categorias;
    }

    public List<ProductoOrden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<ProductoOrden> ordenes) {
        this.ordenes = ordenes;
    }
}
