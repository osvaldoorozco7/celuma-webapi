package com.celuma.webapi.persistence.mapper;

import com.celuma.webapi.domain.InvoiceDTO;
import com.celuma.webapi.persistence.entity.Invoice;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {


    @Mappings({
            @Mapping(source = "idinvoice", target = "invoiceId"),
            @Mapping(source = "idOrden", target = "orderId"),
            @Mapping(source = "nombre", target = "name"),
    })
    InvoiceDTO toInvoice(Invoice invoice);


    @InheritInverseConfiguration
    Invoice toInvoiceDTO(InvoiceDTO invoiceDTO);

}
