# Global variables
APP_NAME = neuro-credit-api

## [short-aliases] Most common commands (using docker by default) ----------------------------------------------------------
s:      docker.start                      ### Start application on docker (alias of docker.start)
t:      docker.server.test                ### Runs tests on docker (alias of docker.server.test) 
sh:  	docker.server.shell               ### Enter on shell of server container (alias of docker.server.shell)
h:      help                              ### Show help (alias of help)

## [aliases] Other common commands (using docker by default) ---------------------------------------------------------------
start:        docker.start                ### Start application on docker (alias of docker.start)
build:        docker.build                ### Build application on docker compose (alias of docker.build)
stop:         docker.stop                 ### Stop application on docker compose (alias of docker.stop)
down:         docker.down                 ### Down application on docker compose (alias of docker.down)
status:       docker.status               ### Show status of containers on docker compose (alias of docker.status)
test:         docker.server.test          ### Run tests on docker (alias of docker.server.test)
shell:        docker.server.shell         ### Enter on shell of server container (alias of docker.server.shell)

## [docker] Docker Commands ------------------------------------------------------------------------------------------------ 

### Start (and build) application on docker
docker.start:
	docker compose up --build --remove-orphans

### Stop application on docker
docker.stop:
	docker compose stop

### Down application on docker
docker.down:
	docker compose down

### Build application on docker
docker.build: 
	docker compose build

### Status of application on docker
docker.status:
	docker compose stats

## [docker-server] Docker Server Commands ---------------------------------------------------------------------------------- 

### Start just the server container (and dependencies)
docker.server.start:
	docker compose up --build --remove-orphans server  

### Stop just the server container 
docker.server.stop:
	docker compose stop server

### Restart just the server container 
docker.server.restart:
	docker compose restart server

### Enter on shell of server container 
docker.server.shell: 
	docker compose exec server bash

### Run tests on server container
docker.server.test:
	docker compose build server 
	docker compose run --rm server mvn clean package
	docker compose run --rm server mvn test

## [help] Commands to help -------------------------------------------------------------------------------------------------
### help: Exibe esta mensagem de ajuda
help:
	@echo "Uso: make [regra]"
	@echo "Regras disponíveis:"
	@awk -v section_format="\n\033[1m%s\033[0m\n" \
	     -v rule_format="  \033[36m%-30s\033[0m %s\n" \
	     ' \
	     /^## / { \
	         printf section_format, substr($$0, 4); \
	     } \
	     /^### / { \
	         comment = substr($$0, 5); \
	     } \
	     /^[a-zA-Z_.-]+:/ { \
	         rule = substr($$1, 1, length($$1) - 1); \
	         if (match($$0, /### /)) { \
	             comment = substr($$0, index($$0, "###") + 4); \
	         } \
	         if (comment) { \
	             printf rule_format, rule, comment; \
	             comment = ""; \
	         } \
	     }' $(MAKEFILE_LIST)

# PHONY TARGETS 
# Get a list of all targets in the Makefile
ALL_TARGETS := $(shell grep -E '^[a-zA-Z_-]+:' Makefile | cut -d: -f1)

# Declare all targets as phony
.PHONY: $(ALL_TARGETS)

