package CNPM.G14.ems.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerResponse {
    int code;
    int deptId;
    LocalDate manageDate;
}
