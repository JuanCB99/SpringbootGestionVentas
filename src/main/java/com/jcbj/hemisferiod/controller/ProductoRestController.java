/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.hemisferiod.controller;

import com.jcbj.hemisferiod.domain.GestionVentasDomain;
import com.jcbj.hemisferiod.entities.Producto;
import com.jcbj.hemisferiod.repository.ProductoRepository;
import io.swagger.annotations.Api;
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
    
    @GetMapping()
    public ResponseEntity<List<Producto>> list() {

        return gestionVentasDomain.listarProductos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Producto> get(@PathVariable Long id) {
        return gestionVentasDomain.buscarProductoPorId(id);
    }
    
    @PutMapping()
    public ResponseEntity<Producto> put(@RequestBody Producto input) {
        return gestionVentasDomain.editarProducto(input);
    }
    
    @PostMapping
    public ResponseEntity<Producto> post(@RequestBody Producto input) {
      
        return gestionVentasDomain.crearProducto(input);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> delete(@PathVariable Long id) {
        return gestionVentasDomain.borrarProducto(id);
    }
    
}
