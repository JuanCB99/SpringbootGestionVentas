/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.hemisferiod.controller;

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
    FacturaRepository facturaRepository;

    @Autowired
    TieneRepository tieneRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    FacturaResponseMapper facturaMapper;

    @GetMapping()
    public ResponseEntity<List<FacturaResponse>> list() {

        List<Tiene> listTiene = new ArrayList<>();

        List<Factura> facturaList = facturaRepository.findAll();

        List<Producto> listProductos = new ArrayList<>();

        List<FacturaResponse> facturaResponse = facturaMapper.recibeListaFacturaRetornaListaFacturaResponse(facturaList);

        int i = 0;

        for (Factura factura : facturaList) {

            listTiene = tieneRepository.findAllByfacturaId(factura);
            Tiene tieneId = null;

            for (Tiene prodIdTiene : listTiene) {

                tieneId = prodIdTiene;

                if (prodIdTiene.getFacturaId().getIdFactura().equals(factura.getIdFactura())) {

                    listProductos.add(productoRepository.findById(prodIdTiene.getProductoId().getIdProducto()).get());

                }

            }

            if (tieneId != null) {
                
                System.out.println("TIENE ID = " + tieneId.getFacturaId().getIdFactura());
                System.out.println("FACTURA ID = " + factura.getIdFactura());

                if (tieneId.getFacturaId().getIdFactura() == factura.getIdFactura()) {

                    facturaResponse.get(i).setProductosList(listProductos);
                    listProductos = new ArrayList<>();
                }
            }
            i++;

        }

        return ResponseEntity.ok(facturaResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> get(@PathVariable String id) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> put(@PathVariable Long id, @RequestBody Factura input) {

        Factura facturaFind = facturaRepository.findById(input.getIdFactura()).get();

        int cantidadArticulos = facturaFind.getArticulosVendidosFactura();

        input.setArticulosVendidosFactura(cantidadArticulos + 1);

        Producto productoFind = productoRepository.findById(id).get();

        Tiene tieneSave = new Tiene(0l, facturaFind, productoFind);

        tieneRepository.save(tieneSave);

        Factura facturaUpdate = facturaRepository.save(input);

        return ResponseEntity.ok(facturaUpdate);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Factura> post(@PathVariable Long id, @RequestBody Factura input) {

        input.setArticulosVendidosFactura(1);
        Factura facturaSave = facturaRepository.save(input);

        Producto productoFind = productoRepository.findById(id).get();

        Tiene tieneSave = new Tiene(0l, facturaSave, productoFind);

        tieneRepository.save(tieneSave);

        return ResponseEntity.ok(facturaSave);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Factura> delete(@PathVariable String id) {
        return null;
    }

}
