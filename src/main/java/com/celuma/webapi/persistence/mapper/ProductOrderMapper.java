package com.celuma.webapi.persistence.mapper;

import com.celuma.webapi.domain.ProductOrder;
import com.celuma.webapi.persistence.entity.ProductoOrden;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductOrderMapper {

    @Mappings({
            @Mapping(source = "id", target = "productOrderId"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "producto", target = "product"),
            @Mapping(source = "orden", target = "order")
    })
    ProductOrder toProductOrder (ProductoOrden productoOrden);




    @InheritInverseConfiguration
    ProductoOrden toProductoOrder(ProductOrder productOrder);

}
