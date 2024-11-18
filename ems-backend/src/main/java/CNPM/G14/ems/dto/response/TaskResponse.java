package CNPM.G14.ems.dto.response;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    private String id;
    private String description;
    private LocalDate due;
    private String status;
    private int projectId;
    private int employeeId;

}
