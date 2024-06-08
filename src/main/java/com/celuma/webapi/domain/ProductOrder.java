package com.celuma.webapi.domain;

public class ProductOrder {

    private int productOrderId;

    private int quantity;

    private ProductDTO productDTO;

    private OrderDetailDTO orderDetailDTO;


    // Getterd and Setters

    public int getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(int productOrderId) {
        this.productOrderId = productOrderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductDTO getProduct() {
        return productDTO;
    }

    public void setProduct(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public OrderDetailDTO getOrder() {
        return orderDetailDTO;
    }

    public void setOrder(OrderDetailDTO orderDetailDTO) {
        this.orderDetailDTO = orderDetailDTO;
    }
}
