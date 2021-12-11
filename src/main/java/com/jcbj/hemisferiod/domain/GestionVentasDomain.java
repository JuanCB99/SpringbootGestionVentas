/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.hemisferiod.domain;

import com.jcbj.hemisferiod.entities.Cliente;
import com.jcbj.hemisferiod.entities.Producto;
import com.jcbj.hemisferiod.mappers.FacturaResponseMapper;
import com.jcbj.hemisferiod.repository.ClienteRepository;
import com.jcbj.hemisferiod.repository.FacturaRepository;
import com.jcbj.hemisferiod.repository.ProductoRepository;
import com.jcbj.hemisferiod.repository.TieneRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan
 */
@Service
public class GestionVentasDomain {

    @Autowired
    FacturaRepository facturaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    TieneRepository tieneRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    FacturaResponseMapper facturaMapper;

    //Logica de las operaciones sobre el cliente
    public ResponseEntity<List<Cliente>> listarClientes() {

        List<Cliente> clientesList = clienteRepository.findAll();

        if (!clientesList.isEmpty()) {
            return ResponseEntity.ok(clientesList);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(clientesList);
        }
    }

    public ResponseEntity<Cliente> buscarClientePorId(Long id) {

        if (clienteRepository.existsById(id)) {

            Cliente clienteEncontrado = clienteRepository.findById(id).get();
            return ResponseEntity.ok(clienteEncontrado);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Cliente> editarCliente(Cliente clienteEdit) {

        if (clienteRepository.existsById(clienteEdit.getIdCliente())) {

            try {

                clienteEdit = clienteRepository.save(clienteEdit);
                return ResponseEntity.ok(clienteEdit);

            } catch (Exception e) {
                e.printStackTrace(System.err);
                return ResponseEntity.badRequest().build();
            }

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Cliente> crearCliente(Cliente clienteSave) {

        if (clienteRepository.existsById(clienteSave.getIdCliente())) {

            return ResponseEntity.noContent().build();

        } else {

            try {

                clienteSave = clienteRepository.save(clienteSave);
                return ResponseEntity.ok(clienteSave);

            } catch (Exception e) {
                e.printStackTrace(System.err);
                return ResponseEntity.badRequest().build();
            }

        }
    }

    public ResponseEntity<Cliente> borrarCliente(Long id) {

        if (clienteRepository.existsById(id)) {

            try {

                clienteRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

            } catch (Exception e) {
                e.printStackTrace(System.err);
                return ResponseEntity.badRequest().build();
            }

        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    //Logica de las operaciones sobre el producto
        public ResponseEntity<List<Producto>> listarProductos() {

        List<Producto> productoList = productoRepository.findAll();

        if (!productoList.isEmpty()) {
            return ResponseEntity.ok(productoList);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(productoList);
        }
    }

    public ResponseEntity<Producto> buscarProductoPorId(Long id) {

        if (productoRepository.existsById(id)) {

            Producto productoEncontrado = productoRepository.findById(id).get();
            return ResponseEntity.ok(productoEncontrado);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Producto> editarProducto(Producto productoEdit) {

        if (productoRepository.existsById(productoEdit.getIdProducto())) {

            try {

                productoEdit = productoRepository.save(productoEdit);
                return ResponseEntity.ok(productoEdit);

            } catch (Exception e) {
                e.printStackTrace(System.err);
                return ResponseEntity.badRequest().build();
            }

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Producto> crearProducto(Producto productoSave) {

        if (productoRepository.existsById(productoSave.getIdProducto())) {
            return ResponseEntity.noContent().build();
        } else {
            try {

                productoSave = productoRepository.save(productoSave);
                return ResponseEntity.ok(productoSave);

            } catch (Exception e) {
                e.printStackTrace(System.err);
                return ResponseEntity.badRequest().build();
            }

        }
    }

    public ResponseEntity<Producto> borrarproducto(Long id) {

        if (productoRepository.existsById(id)) {
            try {

                productoRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

            } catch (Exception e) {
                e.printStackTrace(System.err);
                return ResponseEntity.badRequest().build();
            }

        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
