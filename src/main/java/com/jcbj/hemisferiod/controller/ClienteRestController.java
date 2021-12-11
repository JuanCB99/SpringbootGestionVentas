/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.hemisferiod.controller;

import com.jcbj.hemisferiod.domain.GestionVentasDomain;
import com.jcbj.hemisferiod.entities.Cliente;
import com.jcbj.hemisferiod.repository.ClienteRepository;
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
@Api(tags = "API Gestion Clientes")
@RestController
@RequestMapping("/cliente")
public class ClienteRestController {
    
    @Autowired
    GestionVentasDomain gestionVentasDomain;
    
    @GetMapping()
    public ResponseEntity<List<Cliente>> list() {
        return gestionVentasDomain.listarClientes();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> get(@PathVariable Long id) {
        return gestionVentasDomain.buscarClientePorId(id);
    }
    
    @PutMapping()
    public ResponseEntity<Cliente> put(@RequestBody Cliente input) {
        return gestionVentasDomain.editarCliente(input);
    }
    
    @PostMapping
    public ResponseEntity<Cliente> post(@RequestBody Cliente input) {
        
        return gestionVentasDomain.crearCliente(input);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable Long id) {
        return gestionVentasDomain.borrarCliente(id);
    }
    
}
