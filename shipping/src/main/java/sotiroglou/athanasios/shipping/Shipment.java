package sotiroglou.athanasios.shipping;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RegisterForReflection
@ToString
public class Shipment {
    private String name;
    private String id;
}
