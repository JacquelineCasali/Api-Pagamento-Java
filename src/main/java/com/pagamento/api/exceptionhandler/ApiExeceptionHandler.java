package com.pagamento.api.exceptionhandler;

import com.pagamento.domain.exception.NegocioException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.stream.Collectors;

//exceções de todas a aplicação

// trata as exceções ResponseEntityExceptionHandler
@AllArgsConstructor
@RestControllerAdvice
public class ApiExeceptionHandler  extends ResponseEntityExceptionHandler {
private  final MessageSource messageSource;
//metodo que trata as exceções
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                 HttpStatusCode status, WebRequest request) {
        ProblemDetail problemDetail=ProblemDetail.forStatus(status);
//        descrevendo o problema
        problemDetail.setTitle("Um ou mais campos estão inválidos");
        // so sé a url existir
        problemDetail.setType(URI.create("https://pagamento/erros/campos-invalidos"));
      // trazer qual o error
        var fields=ex.getBindingResult().getAllErrors().stream()
                .collect(Collectors.toMap(error->((FieldError) error).getField(),
// pegando a messagem da propriedade com a linguagem local
                        error->messageSource.getMessage(error, LocaleContextHolder.getLocale())));
        problemDetail.setProperty("fields",fields);
        return super.handleExceptionInternal(ex, problemDetail, headers,  status, request);
    }

    //capiturando exceção
    @ExceptionHandler(NegocioException.class)
    public ProblemDetail handleNegocio(NegocioException e ){
//		 pegando a messagem interna
        ProblemDetail problemDetail=ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
//        descrevendo o problema
        problemDetail.setTitle(e.getMessage());
        // so sé a url existir
        problemDetail.setType(URI.create("https://pagamento/erros/regra-de-negocio"));
        // trazer qual o error
return problemDetail;
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
public ProblemDetail handlData(DataIntegrityViolationException e){
        ProblemDetail problemDetail=ProblemDetail.forStatus(HttpStatus.CONFLICT);
problemDetail.setTitle("Esse Cliente não pode ser cancelado pois tem parcelamanto cadastrado");
problemDetail.setType(URI.create("https://pagamento/erros/recurso-em-uso"));

        return problemDetail;
}

}
