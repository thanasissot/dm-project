package sotiroglou.athanasios.microservices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sock {
    public String id;
    public String name;
    public String description;
    public float price;
    public int count;
    public List<String> imageUrls;
}