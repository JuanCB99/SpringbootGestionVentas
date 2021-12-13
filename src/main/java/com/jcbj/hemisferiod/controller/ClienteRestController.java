/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.hemisferiod.controller;

import com.jcbj.hemisferiod.domain.GestionVentasDomain;
import com.jcbj.hemisferiod.entities.Cliente;
import com.jcbj.hemisferiod.exceptions.ExceptionApiHandler;
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
@Api(tags = "API Gestion Clientes")
@RestController
@RequestMapping("/cliente")
public class ClienteRestController {

    @Autowired
    GestionVentasDomain gestionVentasDomain;

    @ApiOperation(value = "Retorna una lista con todos los objetos tipo Cliente almacenados en la BD")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se encontraron Clientes en la BD."),
        @ApiResponse(code = 204, message = "No se encontraron Clientes en la BD."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @GetMapping("/listarClientes")
    public ResponseEntity<List<Cliente>> list() {
        return gestionVentasDomain.listarClientes();
    }

    @ApiOperation(value = "Retorna un unico objeto de tipo Cliente, el cual se busca por medio del Id enviado por PATH.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se encontro el Cliente en la BD."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 404, message = "El Cliente con ese ID no esta en la BD."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> get(@PathVariable Long id) {
        return gestionVentasDomain.buscarClientePorId(id);
    }

    @ApiOperation(value = "Recibe un objeto de tipo Cliente con Id ya existente, con datos para actualizar.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se actualizaron los datos del Cliente con exito."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 404, message = "El Cliente no se encontro en la BD."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @PutMapping()
    public ResponseEntity<Cliente> put(@RequestBody Cliente input) throws ExceptionApiHandler {
        return gestionVentasDomain.editarCliente(input);
    }

    @ApiOperation(value = "Recibe un objeto de tipo Cliente sin incluir un Id.",
            notes = "Si se incluye un Id existente, no se guardara la info.")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Se guardo el Cliente en la BD."),
        @ApiResponse(code = 204, message = "Se proceso correctamente la petición, pero no se cumplio una condición."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @PostMapping
    public ResponseEntity<Cliente> post(@RequestBody Cliente input) throws ExceptionApiHandler {
        return gestionVentasDomain.crearCliente(input);
    }

    @ApiOperation(value = "Elimina un Cliente de la BD, tomando como parametro un Id enviado por el PATH",
            notes = "Si elimina un cliente, se eliminaran todas las facturas asociadas al mismo.")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Se elimino el Cliente con exito."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 404, message = "El Empleado no se encontro en la BD."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable Long id) {
        return gestionVentasDomain.borrarCliente(id);
    }

}
