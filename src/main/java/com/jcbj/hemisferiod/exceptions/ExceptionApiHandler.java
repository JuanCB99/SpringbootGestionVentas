/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.hemisferiod.exceptions;

import java.sql.DataTruncation;
import java.sql.SQLException;
import org.hibernate.exception.DataException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author Juan
 */
@RestControllerAdvice
public class ExceptionApiHandler extends Exception{

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionApiResponse> manejadorExcepcionNoDisponible(Exception ex) {
        ExceptionApiResponse response = new ExceptionApiResponse(
                "Error algo salio mal al procesar la petición en el servidor",
                "error-500", 
                ex.getMessage(),
                "/error/error-generico/error-interno"
        );
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionApiResponse> manejadorExcepcionDeSQL(DataIntegrityViolationException ex) {
        ExceptionApiResponse response = new ExceptionApiResponse(
                "Error se genero un problema con la BD SQL",
                "error-550", 
                ex.getMessage(),
                "/error/error-sql/error-de-bd"
        );
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
