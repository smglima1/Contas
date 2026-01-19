package transferencia_contas.demo.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "contas")
public class Conta {

    @Id
    private String id;

    private String nome;
    private BigDecimal saldo;

    @Version
    private Long version;
}
