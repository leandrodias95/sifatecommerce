# SIFAT E-COMMERCE

## Descrição

## Instrução de uso
1. Clone o produto através do git
2. Vá para o arquivo "application.properties" e altere 
```bash
spring.profiles.active=hml
```
Para: 
```bash
spring.profiles.active=dev
```
3. Agora para a execução vá no arquivo "application-dev.properties" na linha de código: 
```bash
spring.datasource.url=jdbc:h2:mem:test
```
Copie:
```bash
 jdbc:h2:mem:test
```
Cole:
Com o projeto em execução abra o navegador e acesse link "http://localhost:8080/h2-console" e no h2-console cole enfrente ao JDBC URL

