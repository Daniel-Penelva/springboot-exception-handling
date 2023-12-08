# Exception Handling

## Classe `ErrorResponse`

A classe `ErrorResponse` é usada para representar informações sobre erros na aplicação Spring Boot. 

Analisando cada parte do código:

1. **Campos da Classe:**
   ```java
   private String message;
   private String errorCode;
   private Date time;
   ```
   - `message`: Uma String que representa a mensagem de erro.
   - `errorCode`: Uma String que pode ser usada para identificar o código de erro associado ao erro.
   - `time`: Um objeto `Date` que representa o momento em que o erro ocorreu.

2. **Métodos de Acesso (Getters e Setters):**
   ```java
   public String getMessage() {
       return message;
   }
   
   public void setMessage(String message) {
       this.message = message;
   }
   
   public String getErrorCode() {
       return errorCode;
   }
   
   public void setErrorCode(String errorCode) {
       this.errorCode = errorCode;
   }
   
   public Date getTime() {
       return time;
   }
   
   public void setTime(Date time) {
       this.time = time;
   }
   ```
   - Esses são métodos padrão de acesso (getters e setters) para os campos da classe. Eles permitem que outros objetos obtenham e definam os valores dos campos `message`, `errorCode`, e `time`.

3. **Uso Esperado:**
   - Esta classe parece destinada a ser usada para encapsular informações sobre erros em uma resposta da API. Pode ser útil para padronizar o formato das mensagens de erro, incluindo uma mensagem descritiva, um código de erro e um timestamp.

4. **Exemplo de Uso:**
   - Pode ser utilizada em conjunto com a classe `RestExceptionHandler` mencionada anteriormente. Se ocorrer uma exceção, o manipulador de exceções pode criar um objeto `ErrorResponse`, preenchendo seus campos com informações relevantes sobre o erro antes de enviá-lo de volta como parte da resposta da API.

Em resumo, essa classe `ErrorResponse` serve como um recipiente para informações sobre erros em uma aplicação Spring Boot, proporcionando uma maneira estruturada de representar e comunicar erros.


## Classe `RestExceptionHandler`

A classe `RestExceptionHandler` é um controlador de exceções para a aplicação. Ele trata diferentes tipos de exceções e retorna respostas HTTP personalizadas com base nos tipos de exceções lançadas. 

```java
package com.api.springbootexceptionhandling.exception;

import java.io.IOException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.api.springbootexceptionhandling.controller.CustomException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ErrorResponse errorHandler(IllegalArgumentException ex, WebRequest req) {

        String responseString = "Illegal Argument";

        ErrorResponse err = new ErrorResponse();
        err.setMessage(ex.getMessage());
        err.setErrorCode("errorCodeIA");
        err.setTime(new Date());
        return err;
    }

    @ExceptionHandler(value = { IOException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse errorHandlerIO(IOException ex, WebRequest req) {

        ErrorResponse err = new ErrorResponse();
        err.setMessage(ex.getMessage());
        err.setErrorCode("errorCodeIO");
        err.setTime(new Date());
        return err;
    }

    @ExceptionHandler(value = { CustomException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse errorHandlerCustom(CustomException ex, WebRequest req) {

        ErrorResponse err = new ErrorResponse();
        err.setMessage(ex.getMessage());
        err.setErrorCode("errorCodeCustom");
        err.setTime(new Date());
        return err;
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse errorHandlerExc(CustomException ex, WebRequest req) {

        ErrorResponse err = new ErrorResponse();
        err.setMessage(ex.getMessage());
        err.setErrorCode("errorCodeException");
        err.setTime(new Date());
        return err;
    }

}
```

Analisando cada parte do código:


### 1. **Declaração da Classe:**
   ```java
   @RestControllerAdvice
   public class RestExceptionHandler extends ResponseEntityExceptionHandler {
   ```
   - A anotação `@RestControllerAdvice` indica que esta classe é um controlador de exceções global para controladores REST na aplicação.

### 3. **Manipuladores de Exceção:**
   ```java
   @ExceptionHandler(value = { IllegalArgumentException.class })
   @ResponseStatus(HttpStatus.FORBIDDEN)
   protected ErrorResponse errorHandler(IllegalArgumentException ex, WebRequest req) {
       // ...
   }
   
   @ExceptionHandler(value = { IOException.class })
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   protected ErrorResponse errorHandlerIO(IOException ex, WebRequest req) {
       // ...
   }

   @ExceptionHandler(value = { CustomException.class })
   @ResponseStatus(HttpStatus.NOT_FOUND)
   protected ErrorResponse errorHandlerCustom(CustomException ex, WebRequest req) {
       // ...
   }

   @ExceptionHandler(value = { Exception.class })
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   protected ErrorResponse errorHandlerExc(CustomException ex, WebRequest req) {
       // ...
   }
   ```
   - Esses são métodos anotados com `@ExceptionHandler`, cada um tratando um tipo específico de exceção. Cada método retorna um objeto `ErrorResponse` personalizado, com mensagens de erro específicas e códigos de status HTTP correspondentes.

### 4. **Corpo dos Manipuladores de Exceção:**
   - Cada método cria uma instância de `ErrorResponse`, preenchendo seus campos (`message`, `errorCode`, `time`) com informações específicas da exceção que foi capturada.

### 5. **Tipo Genérico de Exceção:**
   ```java
   @ExceptionHandler(value = { Exception.class })
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   protected ErrorResponse errorHandlerExc(CustomException ex, WebRequest req) {
       // ...
   }
   ```
   - Há um manipulador genérico para exceções do tipo `Exception`, que captura qualquer exceção que não seja tratada pelos manipuladores específicos anteriores. Ele retorna um código de status HTTP `INTERNAL_SERVER_ERROR` (500).

Este controlador de exceções é valioso para personalizar respostas de erro para diferentes tipos de exceções em uma aplicação Spring Boot RESTful. Ele fornece uma maneira centralizada de lidar com exceções e enviar respostas formatadas de maneira consistente para o cliente.

## Classe `HelloController`

Este script é um controlador Spring Boot que manipula solicitações HTTP do tipo GET para a raiz ("/"). Ele possui um método chamado `getMessage` que recebe um parâmetro `message` da solicitação e pode lançar diferentes tipos de exceções com base no valor desse parâmetro. 

```java
package com.api.springbootexceptionhandling.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // http://localhost:8080/?message=Daniel
    // http://localhost:8080/?message=IllegalArgument
    // http://localhost:8080/?message=IOException
    // http://localhost:8080/?message=Custom
    @GetMapping("/")
    public String getMessage(String message) throws IOException, CustomException{

        if (message.equalsIgnoreCase("IllegalArgument")) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        if (message.equalsIgnoreCase("IOException")) {
            throw new IOException("IO Exception");
        }
        if (message.equalsIgnoreCase("Custom")) {
            throw new CustomException("My custom error message");
        }

        return "Hello " + message;
    }

}
```

Analisando o código linha por linha:

### 1. **Declaração do Método:**
   ```java
   @GetMapping("/")
   public String getMessage(String message) throws IOException, CustomException {
   ```
   - Este é um método de manipulação de solicitações GET para o caminho ("/"). O método recebe um parâmetro `message` e declara que pode lançar duas exceções diferentes: `IOException` e `CustomException`.

### 2. **Condições e Lançamento de Exceções:**
   ```java
   if (message.equalsIgnoreCase("IllegalArgument")) {
       throw new IllegalArgumentException("Illegal Argument");
   }
   if (message.equalsIgnoreCase("IOException")) {
       throw new IOException("IO Exception");
   }
   if (message.equalsIgnoreCase("Custom")) {
       throw new CustomException("My custom error message");
   }
   ```
   - Este trecho de código verifica o valor do parâmetro `message` usando instruções `if`. Se `message` for igual a "IllegalArgument", então uma exceção `IllegalArgumentException` é lançada com a mensagem "Illegal Argument". Se for igual a "IOException", então uma exceção `IOException` é lançada com a mensagem "IO Exception". Se for igual a "Custom", então uma exceção `CustomException` é lançada com a mensagem "My custom error message".

### 3. **Retorno da Mensagem:**
   ```java
   return "Hello " + message;
   ```
   - Se nenhum dos casos anteriores for correspondido, o método retorna uma mensagem de saudação "Hello" concatenada com o valor do parâmetro `message`.

### 4. **Exceções Lançadas:**
   - Este método é uma maneira de demonstrar como exceções diferentes podem ser lançadas com base em diferentes condições. A anotação `throws` na assinatura do método indica que ele pode lançar exceções do tipo `IOException` e `CustomException`. Isso é feito apenas para fins demonstrativos, e, na prática, as exceções devem ser tratadas e gerenciadas adequadamente no código real.

Em resumo, este método simula o lançamento de exceções diferentes com base no valor do parâmetro `message` e retorna uma mensagem de saudação se nenhuma exceção for lançada. Este exemplo é útil para entender como manipular exceções em controladores Spring Boot.

# Autor
## Feito por: `Daniel Penelva de Andrade`