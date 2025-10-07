.PHONY: build up down test clean
build:
	docker compose build

up:
	docker compose up -d

down:
	docker compose down

test: up
	./scripts/test_contract.sh

clean: down
	docker system prune -f
