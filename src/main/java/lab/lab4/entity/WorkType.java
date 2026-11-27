package lab.lab4.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("work_types")
public class WorkType {

    @Id
    private Long id;

    private String name;
    private String description;
}
