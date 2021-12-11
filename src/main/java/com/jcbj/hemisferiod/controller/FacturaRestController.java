/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.hemisferiod.controller;

import com.jcbj.hemisferiod.domain.GestionVentasDomain;
import com.jcbj.hemisferiod.dto.FacturaResponse;
import com.jcbj.hemisferiod.entities.Factura;
import com.jcbj.hemisferiod.entities.Producto;
import com.jcbj.hemisferiod.entities.Tiene;
import com.jcbj.hemisferiod.mappers.FacturaResponseMapper;
import com.jcbj.hemisferiod.repository.FacturaRepository;
import com.jcbj.hemisferiod.repository.ProductoRepository;
import com.jcbj.hemisferiod.repository.TieneRepository;
import io.swagger.annotations.Api;
import java.util.ArrayList;
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

    @GetMapping()
    public ResponseEntity<List<FacturaResponse>> list() {
        return gestionVentasDomain.listarFacturas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaResponse> get(@PathVariable Long id) {
        return gestionVentasDomain.buscarFacturaPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> put(@PathVariable Long id, @RequestBody Factura input) {
        return gestionVentasDomain.editarFactura(id, input);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Factura> post(@PathVariable Long id, @RequestBody Factura input) {
        return gestionVentasDomain.crearFactura(id, input);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Factura> delete(@PathVariable Long id) {
        return gestionVentasDomain.borrarFactura(id);
    }

}
