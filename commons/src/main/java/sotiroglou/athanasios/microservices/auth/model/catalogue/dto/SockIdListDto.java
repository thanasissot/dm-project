package sotiroglou.athanasios.microservices.model.catalogue.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SockIdListDto {
    private List<String> sockIds;
}
