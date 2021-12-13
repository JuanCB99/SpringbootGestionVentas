/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.hemisferiod.controller;

import com.jcbj.hemisferiod.domain.GestionVentasDomain;
import com.jcbj.hemisferiod.dto.FacturaResponse;
import com.jcbj.hemisferiod.entities.Factura;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Juan
 */
@Api(tags = "API Gestion Facturas")
@RestController
@RequestMapping("/factura")
public class FacturaRestController {

    @Autowired
    GestionVentasDomain gestionVentasDomain;

    @ApiOperation(value = "Retorna una lista con todos los objetos tipo Factura almacenados en la BD",
            notes = "Esta lista de objetos a su ves tiene un Cliente y una lista de Productos asociados, para cada factura.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se encontraron Facturas en la BD."),
        @ApiResponse(code = 204, message = "No se encontraron Facturas en la BD."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @GetMapping("/listarFacturas")
    public ResponseEntity<List<FacturaResponse>> list() {
        return gestionVentasDomain.listarFacturas();
    }

    @ApiOperation(value = "Retorna un unico objeto de tipo Factura, el cual se busca por medio del Id enviado por PATH.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se encontro la Factura en la BD."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 404, message = "La Factura con ese ID no esta en la BD."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FacturaResponse> get(@PathVariable Long id) {
        return gestionVentasDomain.buscarFacturaPorId(id);
    }

    @ApiOperation(value = "Recibe un objeto de tipo Factura con Id ya existente y un Id por PATH, con datos para actualizar.",
            notes = "1) El Id que se recibe por PATH es el de el producto que se agrega a la factura."
                    + "2) Cada ves que se desee agregar un producto se llamara a este metodo, internamente se le suma la cantidad y se"
                    + "actualizan datos como el total de la factura.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se actualizaron los datos de la Factura con exito."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 404, message = "La Factura o el Producto no se encontraron en la BD."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Factura> put(@PathVariable Long id, @RequestBody Factura input) {
        return gestionVentasDomain.editarFactura(id, input);
    }

    @ApiOperation(value = "Recibe un objeto de tipo Factura sin incluir el Id y un Id por PATH, con datos para actualizar.",
            notes = "1) El Id que se recibe por PATH es el de el producto que se agrega a la factura."
                    + "2) La factura se crea apartir de escoger el primer producto y se le suma la cantidad de productos en forma automatica.)")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Se guardo la Factura en la BD."),
        @ApiResponse(code = 204, message = "Se proceso correctamente la petición, pero no se cumplio una condición."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 404, message = "Es probable que este agregando un Producto con un Id que no existe."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @PostMapping("/{id}")
    public ResponseEntity<Factura> post(@PathVariable Long id, @RequestBody Factura input) {
        return gestionVentasDomain.crearFactura(id, input);
    }

    @ApiOperation(value = "Elimina una Factura de la BD, tomando como parametro un Id enviado por el PATH")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Se elimino la Factura con exito."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 404, message = "La Factura no se encontro en la BD."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Factura> delete(@PathVariable Long id) {
        return gestionVentasDomain.borrarFactura(id);
    }

}
