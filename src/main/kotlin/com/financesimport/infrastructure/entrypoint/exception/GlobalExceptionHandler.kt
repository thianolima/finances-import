package com.financesimport.infrastructure.entrypoint.exception

import com.financesimport.core.exception.DomainException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(DomainException::class)
    fun domain(ex: DomainException) =
        ResponseEntity(Response(ex.message!!), HttpStatus.UNAUTHORIZED)

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> =
        handleExceptionInternal(
            ex,
            ex.bindingResult.fieldErrors.map { Response("${it.field}: ${it.defaultMessage}") }.first(),
            headers,
            status,
            request
        )

    data class Response(val message: String)
}