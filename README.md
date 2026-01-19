# Transferência de Contas – API REST

API REST simples para simular transferências de valores entre contas de um banco digital, com foco em consistência, concorrência e clareza de código.

---

## 🚀 Como rodar o projeto

### Pré-requisitos
- Java 17
- Maven 3.8+
- MongoDB em execução (local)

### Subir o MongoDB (opcional com Docker)
```bash
docker run -d \
  --name mongo-transferencia \
  -p 27017:27017 \
  mongo:7
