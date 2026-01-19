package transferencia_contas.demo.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import transferencia_contas.demo.dto.TransferenciaRequest;
import transferencia_contas.demo.service.TransferenciaService;

import java.math.BigDecimal;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransferenciaController.class)
class TransferenciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransferenciaService transferenciaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveTransferirComSucesso() throws Exception {

        TransferenciaRequest request = new TransferenciaRequest();
        request.setOrigemId("conta-origem");
        request.setDestinoId("conta-destino");
        request.setValor(BigDecimal.valueOf(100));

        doNothing().when(transferenciaService)
                .transferir("conta-origem", "conta-destino", BigDecimal.valueOf(100));

        mockMvc.perform(post("/transferencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent());
    }
}