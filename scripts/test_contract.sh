#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
for s in services/catalogo services/pedidos services/notificacoes; do
  (cd "$s" && mvn -q -Pdocker -DskipTests package)
done
docker compose up -d --build
echo "Aguardando serviços subirem..."
sleep 5
mvn -q -f tests/contract/pom.xml -Dtest=ContratoAPITest test
