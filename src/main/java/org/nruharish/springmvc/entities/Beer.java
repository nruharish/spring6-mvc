package org.nruharish.springmvc.entities;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;
import org.nruharish.springmvc.model.BeerStyle;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Beer {
    @NotNull
    @NotBlank
    @Column(length = 50)
    @Size(max = 50)
    private String beerName;
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(length=36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID id;
    @Version
    private Integer version;
    @NotNull
    private BeerStyle beerStyle;
    @NotNull
    @NotBlank
    private String upc;
    private Integer quantityOnHand;
    @DecimalMin(value = "0.0", inclusive = true)
    @Digits(integer=2, fraction=2)
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
