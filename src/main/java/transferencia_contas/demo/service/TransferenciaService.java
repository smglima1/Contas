package transferencia_contas.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import transferencia_contas.demo.dto.Conta;
import transferencia_contas.demo.dto.Movimentacao;
import transferencia_contas.demo.enums.TipoMovimentacao;
import transferencia_contas.demo.repository.ContaRepository;
import transferencia_contas.demo.repository.MovimentacaoRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransferenciaService {

    private final ContaRepository contaRepository;
    private final MovimentacaoRepository movimentacaoRepository;
    private final NotificacaoService notificacaoService;

    @Transactional
    public void transferir(String origemId, String destinoId, BigDecimal valor) {

        Conta origem = contaRepository.findById(origemId)
                .orElseThrow(() -> new RuntimeException("Conta origem não encontrada"));

        Conta destino = contaRepository.findById(destinoId)
                .orElseThrow(() -> new RuntimeException("Conta destino não encontrada"));

        if (origem.getSaldo().compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        origem.setSaldo(origem.getSaldo().subtract(valor));
        destino.setSaldo(destino.getSaldo().add(valor));

        contaRepository.save(origem);
        contaRepository.save(destino);

        movimentacaoRepository.save(
                Movimentacao.builder()
                        .contaId(origem.getId())
                        .valor(valor.negate())
                        .tipo(TipoMovimentacao.DEBITO)
                        .data(LocalDateTime.now())
                        .build()
        );

        movimentacaoRepository.save(
                Movimentacao.builder()
                        .contaId(destino.getId())
                        .valor(valor)
                        .tipo(TipoMovimentacao.CREDITO)
                        .data(LocalDateTime.now())
                        .build()
        );

        notificacaoService.notificar(origem, destino, valor);
    }
}
