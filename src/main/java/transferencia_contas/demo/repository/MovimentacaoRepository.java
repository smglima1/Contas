package transferencia_contas.demo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import transferencia_contas.demo.dto.Movimentacao;

public interface MovimentacaoRepository extends MongoRepository<Movimentacao, String> {
}

