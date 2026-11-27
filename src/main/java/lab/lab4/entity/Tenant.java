package lab.lab4.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("tenants")
public class Tenant {

    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private String address;
    private String phone;
}
