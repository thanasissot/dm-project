package sotiroglou.athanasios.microservices.mapper;

import org.mapstruct.Mapper;
import sotiroglou.athanasios.microservices.entity.Cart;
import sotiroglou.athanasios.microservices.dto.CartDto;

@Mapper(componentModel = "spring", uses = CartItemMapper.class)
public interface CartMapper {

    Cart dtoToEntity(CartDto dto);

    CartDto entityToDto(Cart entity);
}
