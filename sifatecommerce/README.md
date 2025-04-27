
# SIFAT E-COMMERCE

## Descrição
O SIFAT E-COMMERCE é um projeto de API RESTful para gerenciamento de produtos e categorias, permitindo operações como cadastro, atualização, listagem e remoção de dados de produtos e categorias.

### O que faz o app?
- Cadastro de Produtos e Categorias.
- Listagem de Produtos e Categorias com paginação.
- Atualização e exclusão de Produtos e Categorias.
- Proteção de exclusão de categorias vinculadas a produtos.

### Com o que foi construído?
- **Back-end:** Spring Boot 3+
- **Banco de Dados:** H2 (em memória) e suporte para PostgreSQL.
- **Documentação:** Swagger (SpringDoc OpenAPI).
- **Validação:** Bean Validation (Jakarta Validation).
- **Conversão de objetos:** ModelMapper.
- **Testes:** JUnit 5 e Mockito.

### Por que foi construído?
Foi construído para fins de demonstração de habilidades técnicas em desenvolvimento de APIs RESTful, seguindo boas práticas de mercado, usando o ecossistema Spring.

---

## Instrução de instalação

### Pré-requisitos
- Java 17+
- Maven 3.8+
- Git 2.4+

### Etapas
Clone o projeto:
```bash
git clone https://github.com/leandrodias95/sifatecommerce.git
```

Entre na pasta do projeto:
```bash
cd sifat-ecommerce
```

Instale as dependências e compile:
```bash
./mvnw clean install
```

---

## Instrução de uso

1. Vá até o arquivo `application.properties`.
2. Altere:
```bash
spring.profiles.active=hml
```
Para:
```bash
spring.profiles.active=dev
```
3. Agora vá no arquivo `application-dev.properties`, localize a linha:
```bash
spring.datasource.url=jdbc:h2:mem:test
```
4. Copie o valor `jdbc:h2:mem:test`.
5. Com o projeto em execução, abra o navegador e acesse:
```
http://localhost:8080/h2-console
```
6. Cole o valor copiado no campo **JDBC URL** do console H2.
7. Clique em **Connect** para acessar o banco de dados.

---

## Documentação da API

Após rodar o projeto, acesse:
```
http://localhost:8080/swagger-ui.html
```

A documentação Swagger descreve todos os endpoints disponíveis, incluindo:

- Cadastro de Produtos
- Atualização de Produtos
- Listagem com paginação e filtro por Categoria
- Cadastro de Categorias
- Atualização de Categorias
- Exclusão controlada de Categorias

Cada endpoint foi documentado com `@Operation(summary, description)` para maior clareza.

---

## Licença

Este projeto está disponível para:
- Uso educacional e de aprendizado.
- Inspiração para outros projetos.
- Não é autorizado o uso comercial sem permissão.

---

## Contribuição

Contribuições não estão habilitadas para este projeto de teste técnico.
Este projeto é de caráter demonstrativo e individual.