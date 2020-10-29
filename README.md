# tdc-service

Trabalho de conclusão de curso da disciplina - SERVICES ARCHITECTURE / API / MOBILE ARCHITECTURE


Passo a passo para executar o nosso programa:

Pré-requisitos:
- git
- IDE para compilar código java (ex: eclipse).
- Docker instalado.

1 - Baixe esse repositório.

2 - Importe os projetos "filme-service", "usuario-service" e "chamado-service" para a sua IDE.

3 - No diretório "tdc-service" utilize o comando docker-compose up. O Docker irá iniciar o postgres, Kafka e o Eureka.

4 - Pela IDE inicialize os microserviços após o Docker subir, pois eles possuem dependência do eureka-server.

5 - Acesse a URL dos microserviços pelo swagger e dos outros componentes:

Catalogo-Service: http://localhost:8083/catalogo-service/swagger-ui.html#/

Chamado-Service: http://localhost:8084/chamado-service/swagger-ui.html#/

Usuario-Service: http://localhost:8085/usuario-service/swagger-ui.html#/

Kafka: http://localhost:19000/

PostgresSQL: http://localhost:16543/
  Usuário: admin@admin.com
  Senha: admin123
  
Eureka-Server: http://localhost:8761/
