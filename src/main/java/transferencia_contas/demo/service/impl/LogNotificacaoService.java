package transferencia_contas.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import transferencia_contas.demo.dto.Conta;
import transferencia_contas.demo.service.NotificacaoService;

import java.math.BigDecimal;

@Slf4j
@Service
public class LogNotificacaoService implements NotificacaoService {

    @Override
    public void notificar(Conta origem, Conta destino, BigDecimal valor) {
        log.info("Transferência realizada de {} para {} no valor {}",
                origem.getId(), destino.getId(), valor);
    }
}
