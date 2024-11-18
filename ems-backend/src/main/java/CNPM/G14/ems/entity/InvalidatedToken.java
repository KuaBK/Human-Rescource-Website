package CNPM.G14.ems.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "invalidated_token")
public class InvalidatedToken {

    @Id
    @Column(name = "tokenID")
    String tokenID;

    @Column(name = "expired")
    Date expiryTime;
}
