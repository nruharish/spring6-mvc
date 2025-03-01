package org.nruharish.springmvc.entities;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String customerName;
    @Id
    @GeneratedValue(generator = "UUID")
   // @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @UuidGenerator
    @Column(length=36, columnDefinition = "Varchar(36)", updatable = false, nullable = false)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;
    @Column(length = 255)
    private String email;
    @Version
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
