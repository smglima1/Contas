package transferencia_contas.demo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import transferencia_contas.demo.dto.Conta;

public interface ContaRepository extends MongoRepository<Conta, String> {
}

