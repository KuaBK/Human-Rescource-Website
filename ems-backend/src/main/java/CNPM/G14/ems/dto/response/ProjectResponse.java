package CNPM.G14.ems.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectResponse {
    Integer projectId;
    String name;
    Integer participants;
    String description;
    Integer deptId;
}
