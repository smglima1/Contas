package transferencia_contas.demo.service;

import transferencia_contas.demo.dto.Conta;

import java.math.BigDecimal;

public interface NotificacaoService {
    void notificar(Conta origem, Conta destino, BigDecimal valor);
}
