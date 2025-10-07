# cbse-soa

Sistema exemplo orientado a serviços (SOA/REST).

## Como rodar
1. `docker compose up --build`
2. Endpoints:
   - Catálogo: `GET http://localhost:8081/api/produtos` e `GET /api/produtos/{id}`
   - Pedidos: `POST http://localhost:8082/api/pedidos` body: `{ "produtoId":"P001", "quantidade": 2 }`
   - Notificações: `GET http://localhost:8083/api/notificacoes`

## Conceitos
- Serviços stateless e interface publicada via HTTP/JSON.
- Composição: `pedidos` chama `catalogo` e publica evento para `notificacoes`.
