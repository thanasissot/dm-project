package sotiroglou.athanasios.microservices.mapper;

import org.mapstruct.Mapper;
import sotiroglou.athanasios.microservices.entity.CartItem;
import sotiroglou.athanasios.microservices.dto.CartItemDto;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItem dtoToEntity(CartItemDto dto);
    CartItemDto entityToDto(CartItem item);

    Set<CartItem> dtosToEntities(Set<CartItemDto> dtos);
    Set<CartItemDto> entitiesToDtos(Set<CartItem> items);
}
