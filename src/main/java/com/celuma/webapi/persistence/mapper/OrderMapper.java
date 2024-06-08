package com.celuma.webapi.persistence.mapper;
import com.celuma.webapi.domain.OrderDTO;
import com.celuma.webapi.domain.OrderDetailDTO;
import com.celuma.webapi.persistence.entity.Orden;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, InvoiceMapper.class, SaleMapper.class})
public interface OrderMapper {

    @Mappings({
            @Mapping(source = "idOrden", target = "orderId"),
            @Mapping(source = "fechaCreacion", target = "creationDate"),
            @Mapping(source = "estado", target = "status"),
    })
    OrderDTO toOrderDetail(Orden orden);

    @Mappings({
            @Mapping(source = "idOrden", target = "orderId"),
            @Mapping(source = "fechaCreacion", target = "creationDate"),
            @Mapping(source = "estado", target = "status"),
            @Mapping(source = "productoOrden", target = "productOrder"),
    })
    OrderDetailDTO toOrder(Orden orden);


    List<OrderDTO> toOrders(List<Orden> ordenes);

    @InheritInverseConfiguration
    Orden toOrden(OrderDetailDTO orderDetailDTO);

}
