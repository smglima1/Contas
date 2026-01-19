package transferencia_contas.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import transferencia_contas.demo.dto.Conta;
import transferencia_contas.demo.repository.ContaRepository;
import transferencia_contas.demo.repository.MovimentacaoRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransferenciaServiceTest {

    @InjectMocks
    private TransferenciaService transferenciaService;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private MovimentacaoRepository movimentacaoRepository;

    @Mock
    private NotificacaoService notificacaoService;

    @Test
    void deveTransferirComSucesso() {

        Conta origem = new Conta();
        origem.setId("1");
        origem.setSaldo(BigDecimal.valueOf(200));

        Conta destino = new Conta();
        destino.setId("2");
        destino.setSaldo(BigDecimal.valueOf(100));

        when(contaRepository.findById("1")).thenReturn(Optional.of(origem));
        when(contaRepository.findById("2")).thenReturn(Optional.of(destino));

        transferenciaService.transferir("1", "2", BigDecimal.valueOf(50));

        verify(contaRepository).save(origem);
        verify(contaRepository).save(destino);
        verify(movimentacaoRepository, times(2)).save(any());
        verify(notificacaoService).notificar(origem, destino, BigDecimal.valueOf(50));
    }

    @Test
    void deveFalharQuandoSaldoInsuficiente() {

        Conta origem = new Conta();
        origem.setId("1");
        origem.setSaldo(BigDecimal.valueOf(10));

        Conta destino = new Conta();
        destino.setId("2");
        destino.setSaldo(BigDecimal.valueOf(100));

        when(contaRepository.findById("1")).thenReturn(Optional.of(origem));
        when(contaRepository.findById("2")).thenReturn(Optional.of(destino));

        assertThrows(RuntimeException.class, () ->
                transferenciaService.transferir("1", "2", BigDecimal.valueOf(50))
        );

        verify(contaRepository, never()).save(any());
        verify(movimentacaoRepository, never()).save(any());
        verify(notificacaoService, never()).notificar(any(), any(), any());
    }
}
