package transferencia_contas.demo.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaRequest {

    @NotBlank
    private String origemId;

    @NotBlank
    private String destinoId;

    @NotNull
    @Positive
    private BigDecimal valor;
}

