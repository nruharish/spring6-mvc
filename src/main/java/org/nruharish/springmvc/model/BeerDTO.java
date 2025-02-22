package org.nruharish.springmvc.model;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class BeerDTO {
    @NotNull
    @NotBlank
    private String beerName;
    private UUID id;
    private Integer version;
    @NotNull
    private BeerStyle beerStyle;
    @DecimalMin(value = "0.0", inclusive = true)
    @Digits(integer=2, fraction=2)
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
