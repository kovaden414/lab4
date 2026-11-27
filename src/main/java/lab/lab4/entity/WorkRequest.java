package lab.lab4.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Table("work_requests")
public class WorkRequest {

    @Id
    private Long id;

    private Long tenantId;
    private Long workTypeId;
    private String scale;
    private LocalDateTime preferredTime;
    private String status;
}
