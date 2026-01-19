package transferencia_contas.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import transferencia_contas.demo.dto.TransferenciaRequest;
import transferencia_contas.demo.service.TransferenciaService;

@RestController
@RequestMapping("/transferencias")
@RequiredArgsConstructor
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transferir(@RequestBody TransferenciaRequest request) {
        transferenciaService.transferir(
                request.getOrigemId(),
                request.getDestinoId(),
                request.getValor()
        );
    }
}

