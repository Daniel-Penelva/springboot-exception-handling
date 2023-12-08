# Exception Handling

## Classe `RestExceptionHandler`

```java
package com.api.springbootexceptionhandling.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ResponseEntity<Object> errorHandler(IllegalArgumentException ex, WebRequest req) {

        String responseString = "Illegal Argument";

        return handleExceptionInternal(ex, responseString, new HttpHeaders(), HttpStatus.BAD_REQUEST, req);
    }

}
```

Analisando linha por linha:

1. `@RestControllerAdvice`: Esta anotação indica que a classe é destinada a manipular exceções em controladores REST. Ela funciona como um interceptor para exceções lançadas pelos controladores.

2. `public class RestExceptionHandler extends ResponseEntityExceptionHandler`: Essa classe estende `ResponseEntityExceptionHandler`, uma classe fornecida pelo Spring que contém métodos comuns para lidar com exceções em controladores.

3. `@ExceptionHandler(value = { IllegalArgumentException.class })`: Essa anotação é usada para indicar que o método abaixo manipulará exceções do tipo `IllegalArgumentException`. Isso significa que, se um controlador lançar uma exceção do tipo `IllegalArgumentException`, este método será chamado para lidar com ela.

4. `@ResponseStatus(HttpStatus.FORBIDDEN)`: Esta anotação define o código de status HTTP que será retornado quando a exceção for manipulada. Neste caso, é `FORBIDDEN` (403).

5. `protected ResponseEntity<Object> errorHandler(IllegalArgumentException ex, WebRequest req)`: Este é o método que será invocado para lidar com exceções do tipo `IllegalArgumentException`. Ele recebe dois parâmetros: a exceção (`ex`) e uma instância de `WebRequest`. O método retorna um objeto `ResponseEntity<Object>`.

6. `String responseString = "Illegal Argument";`: Aqui, uma mensagem simples "Illegal Argument" é definida como o conteúdo da resposta que será enviada ao cliente.

7. `return handleExceptionInternal(ex, responseString, new HttpHeaders(), HttpStatus.BAD_REQUEST, req);`: Este comando retorna uma resposta HTTP com base nos parâmetros fornecidos. `handleExceptionInternal` é um método da classe `ResponseEntityExceptionHandler` que constrói uma resposta apropriada para uma exceção dada. Neste caso, ele cria uma resposta com um corpo contendo a string "Illegal Argument", cabeçalhos vazios, e um código de status HTTP `BAD_REQUEST` (400).

Resumindo, este script trata exceções do tipo `IllegalArgumentException` em controladores REST, respondendo com um código de status HTTP 403 (FORBIDDEN) e uma mensagem "Illegal Argument". Note que a anotação `@ResponseStatus` define um código de status diferente do código fornecido no método `handleExceptionInternal` (403 e 400, respectivamente). Isto pode ser ajustado conforme necessário para atender aos requisitos específicos do aplicativo.