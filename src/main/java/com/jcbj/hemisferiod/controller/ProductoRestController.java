/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.hemisferiod.controller;

import com.jcbj.hemisferiod.domain.GestionVentasDomain;
import com.jcbj.hemisferiod.entities.Producto;
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
@Api(tags = "API Gestion Productos")
@RestController
@RequestMapping("/producto")
public class ProductoRestController {

    @Autowired
    GestionVentasDomain gestionVentasDomain;

    @ApiOperation(value = "Retorna una lista con todos los objetos tipo Producto almacenados en la BD")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se encontraron Productos en la BD."),
        @ApiResponse(code = 204, message = "No se encontraron Productos en la BD."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @GetMapping("/listarProductos")
    public ResponseEntity<List<Producto>> list() {
        return gestionVentasDomain.listarProductos();
    }

    @ApiOperation(value = "Retorna un unico objeto de tipo Producto, el cual se busca por medio del Id enviado por PATH.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se encontro el Producto en la BD."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 404, message = "El Producto con ese ID no esta en la BD."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Producto> get(@PathVariable Long id) {
        return gestionVentasDomain.buscarProductoPorId(id);
    }

    @ApiOperation(value = "Recibe un objeto de tipo Producto con Id ya existente, con datos para actualizar.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se actualizaron los datos del Producto con exito."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 404, message = "El Producto no se encontro en la BD."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @PutMapping()
    public ResponseEntity<Producto> put(@RequestBody Producto input) {
        return gestionVentasDomain.editarProducto(input);
    }

    @ApiOperation(value = "Recibe un objeto de tipo Producto sin incluir un Id.",
            notes = "Si se incluye un Id existente, no se guardara la info.")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Se guardo el Producto en la BD."),
        @ApiResponse(code = 204, message = "Se proceso correctamente la petición, pero no se cumplio una condición."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @PostMapping
    public ResponseEntity<Producto> post(@RequestBody Producto input) {
        return gestionVentasDomain.crearProducto(input);
    }

    @ApiOperation(value = "Elimina un Producto de la BD, tomando como parametro un Id enviado por el PATH",
            notes = "Si elimina un Producto, este se eliminara de las facturas que contengan ese producto.")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Se elimino el Producto con exito."),
        @ApiResponse(code = 400, message = "La petición es invalida, asegurese de enviar los datos con el formato correcto."),
        @ApiResponse(code = 404, message = "El Empleado no se encontro en la BD."),
        @ApiResponse(code = 500, message = "Error interno del servidor, contacte con el soporte de la API.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> delete(@PathVariable Long id) {
        return gestionVentasDomain.borrarProducto(id);
    }

}
