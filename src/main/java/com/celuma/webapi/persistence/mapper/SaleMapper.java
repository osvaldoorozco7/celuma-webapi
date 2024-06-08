package com.celuma.webapi.persistence.mapper;

import com.celuma.webapi.domain.SaleDTO;
import com.celuma.webapi.persistence.entity.Sale;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    @Mappings({
            @Mapping(source = "idSale", target = "saleId"),
            @Mapping(source = "idOrder", target = "orderId"),
            @Mapping(source = "payed", target = "payed"),
            @Mapping(source = "total", target = "total"),
            @Mapping(source = "accountDestiny", target = "destinyAccount")
    })
    SaleDTO toSaleDTO(Sale sale);

    @InheritInverseConfiguration
    Sale toSale(SaleDTO saleDTO);

}
