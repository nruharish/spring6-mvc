package org.nruharish.springmvc.entities;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String customerName;
    @Id
    private UUID id;
    @Version
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
