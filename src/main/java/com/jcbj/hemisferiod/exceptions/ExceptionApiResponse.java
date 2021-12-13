/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.hemisferiod.exceptions;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author Juan
 */
@Data
public class ExceptionApiResponse  extends Exception{

    @ApiModelProperty(notes = "Identificador de uri único que categoriza el error", name = "tipo",
            required = true, example = "/error/peticion-erronea/formato-datos-incorrecto")
    private String type = "/error/error-servidor";
    
    @ApiModelProperty(notes = "Descripción corta del error y facilmente entendible para una persona.", name = "titulo",
            required = true, example = "Error interno del servidor")
    private String title;
    
    @ApiModelProperty(notes = "Codigo unico para identificar el error", name = "codigo",
            required = false, example = "500")
    private String code;
    
    @ApiModelProperty(notes = "Explicación entendible para el usuario, sobre el error", name = "detalles",
            required = true, example = "Al parecer hubo un error interno del servidor al tratar de procesar la petición,"
                    + "por favor comuniquese con el soporte de la API para más información.")
    private String detail;
    
    @ApiModelProperty(notes = "Se especifica la URI donde ocurre el error especifico", name = "instancia",
            required = true, example = "/error/peticion-erronea/formato-datos-incorrecto/fecha-invalida")
    private String instance = "/error/error-generico/error-interno";

    public ExceptionApiResponse(String title, String code, String detail, String instance) {
        super();
        this.title = title;
        this.code = code;
        this.detail = detail;
        this.instance = instance;
    }

}
