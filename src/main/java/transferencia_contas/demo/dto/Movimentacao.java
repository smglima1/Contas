package transferencia_contas.demo.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import transferencia_contas.demo.enums.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "movimentacoes")
public class Movimentacao {

    @Id
    private String id;

    private String contaId;
    private BigDecimal valor;
    private TipoMovimentacao tipo;
    private LocalDateTime data;
}
