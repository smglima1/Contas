package transferencia_contas.demo.service.impl;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import transferencia_contas.demo.dto.Conta;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class LogNotificacaoServiceTest {

    @Test
    void deveRegistrarLogAoNotificar() {

        Logger logger = (Logger) LoggerFactory.getLogger(LogNotificacaoService.class);
        ListAppender<ILoggingEvent> appender = new ListAppender<>();
        appender.start();
        logger.addAppender(appender);

        LogNotificacaoService service = new LogNotificacaoService();

        Conta origem = Conta.builder()
                .id("1")
                .nome("origem")
                .saldo(BigDecimal.valueOf(100))
                .build();

        Conta destino = Conta.builder()
                .id("2")
                .nome("destino")
                .saldo(BigDecimal.valueOf(50))
                .build();

        service.notificar(origem, destino, BigDecimal.valueOf(25));

        assertThat(appender.list).hasSize(1);

        ILoggingEvent event = appender.list.get(0);
        assertThat(event.getLevel()).isEqualTo(Level.INFO);
        assertThat(event.getFormattedMessage())
                .contains("Transferência realizada")
                .contains("1")
                .contains("2")
                .contains("25");
    }
}
