/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.hemisferiod.domain;

import com.jcbj.hemisferiod.dto.FacturaResponse;
import com.jcbj.hemisferiod.entities.Cliente;
import com.jcbj.hemisferiod.entities.Factura;
import com.jcbj.hemisferiod.entities.Producto;
import com.jcbj.hemisferiod.entities.Tiene;
import com.jcbj.hemisferiod.exceptions.ExceptionApiHandler;
import com.jcbj.hemisferiod.mappers.FacturaResponseMapper;
import com.jcbj.hemisferiod.repository.ClienteRepository;
import com.jcbj.hemisferiod.repository.FacturaRepository;
import com.jcbj.hemisferiod.repository.ProductoRepository;
import com.jcbj.hemisferiod.repository.TieneRepository;
import java.util.ArrayList;
import java.util.Date;
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

    //######################### Logica de las operaciones sobre el cliente #########################
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

    public ResponseEntity<Cliente> editarCliente(Cliente clienteEdit) throws ExceptionApiHandler {

        if (clienteRepository.existsById(clienteEdit.getIdCliente())) {

                clienteEdit = clienteRepository.save(clienteEdit);
                return ResponseEntity.ok(clienteEdit);


        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Cliente> crearCliente(Cliente clienteSave) throws ExceptionApiHandler {

        if (clienteRepository.existsById(clienteSave.getIdCliente())) {

            return ResponseEntity.noContent().build();

        } else {

            clienteSave = clienteRepository.save(clienteSave);
            return ResponseEntity.ok(clienteSave);

        }
    }

    public ResponseEntity<Cliente> borrarCliente(Long id) {

        if (clienteRepository.existsById(id)) {

            try {

                List<Factura> facturasList = facturaRepository.findAllByClienteId(clienteRepository.findById(id).get());

                facturasList.forEach(factura -> {
                    borrarFactura(factura.getIdFactura());
                });

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

    //######################### Logica de las operaciones sobre el producto #########################
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

    public ResponseEntity<Producto> borrarProducto(Long id) {

        if (productoRepository.existsById(id)) {
            try {

                Producto productoBorrar = productoRepository.findById(id).get();

                List<Tiene> tieneList = tieneRepository.findAllByproductoId(productoBorrar);

                tieneList.forEach(tiene -> {
                    //Por cada relacioen entre Factura y producto encontrada, se resta uno a la cantidad de la factura
                    //y se resta el valor del producto a eliminar.
                    Factura facturaActualizar = facturaRepository.findById(tiene.getFacturaId().getIdFactura()).get();
                    facturaActualizar.setArticulosVendidosFactura(facturaActualizar.getArticulosVendidosFactura() - 1);
                    facturaActualizar.setTotalVentaFactura(facturaActualizar.getTotalVentaFactura() - productoBorrar.getPrecioProducto());
                    facturaRepository.save(facturaActualizar);

                    tieneRepository.delete(tiene);
                });

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

    //######################### Logica de las operaciones sobre la factura #########################
    public ResponseEntity<List<FacturaResponse>> listarFacturas() {

        List<FacturaResponse> facturaList = listFacturasConProductos(0L);

        if (!facturaList.isEmpty()) {
            return ResponseEntity.ok(facturaList);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(facturaList);
        }
    }

    public ResponseEntity<FacturaResponse> buscarFacturaPorId(Long id) {

        if (facturaRepository.existsById(id)) {

            FacturaResponse facturaResponseEncontrada = null;
            List<FacturaResponse> facturaResponseList = listFacturasConProductos(id);

            for (FacturaResponse facturaResponse : facturaResponseList) {
                facturaResponseEncontrada = facturaResponse;
            }

            return ResponseEntity.ok(facturaResponseEncontrada);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Factura> editarFactura(Long id, Factura facturaEdit) {

        if (facturaRepository.existsById(facturaEdit.getIdFactura()) && productoRepository.existsById(id)) {
            try {

                //Cuando se añade un producto a una factura, se busca el producto, se resta 1 a el stock y se actualiza.
                Producto productoFind = productoRepository.findById(id).get();
                productoFind.setCantidadStock(productoFind.getCantidadStock() - 1);
                productoRepository.save(productoFind);

                //despues se suma 1 a la cantidad de productos de la factura, se le suma el procio del producto al total y se guarda la fecha.
                Factura facturaFind = facturaRepository.findById(facturaEdit.getIdFactura()).get();
                int cantidadArticulos = facturaFind.getArticulosVendidosFactura();
                facturaEdit.setArticulosVendidosFactura(cantidadArticulos + 1);
                facturaEdit.setFechVentaFactura(new Date());
                facturaEdit.setTotalVentaFactura(facturaFind.getTotalVentaFactura() + productoFind.getPrecioProducto());
                Factura facturaUpdate = facturaRepository.save(facturaEdit);

                //por ultimo se crea la relacion entre la factura y el producto que se agrego.
                Tiene tieneSave = new Tiene(0l, facturaFind, productoFind);
                tieneRepository.save(tieneSave);

                return ResponseEntity.ok(facturaUpdate);

            } catch (Exception e) {
                e.printStackTrace(System.err);
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Factura> crearFactura(Long id, Factura facturaSave) {

        if (facturaRepository.existsById(facturaSave.getIdFactura())) {
            return ResponseEntity.noContent().build();
        } else {
            if (productoRepository.existsById(id)) {

                try {

                    //Cuando se crea una factura, se busca el producto y se resta 1 a el stock y se actualiza.
                    Producto productoFind = productoRepository.findById(id).get();
                    productoFind.setCantidadStock(productoFind.getCantidadStock() - 1);
                    productoRepository.save(productoFind);

                    //despues se suma 1 a la cantidad de productos de la factura, se le suma el procio del producto y se guarda la fecha.
                    facturaSave.setArticulosVendidosFactura(1);
                    facturaSave.setFechVentaFactura(new Date());
                    facturaSave.setTotalVentaFactura(productoFind.getPrecioProducto());
                    facturaSave = facturaRepository.save(facturaSave);

                    //por ultimo se crea la relacion entre la factura y el producto que contiene.
                    Tiene tieneSave = new Tiene(0l, facturaSave, productoFind);
                    tieneRepository.save(tieneSave);

                    return ResponseEntity.ok(facturaSave);

                } catch (Exception e) {
                    e.printStackTrace(System.err);
                    return ResponseEntity.badRequest().build();
                }

            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    public ResponseEntity<Factura> borrarFactura(Long id) {

        if (facturaRepository.existsById(id)) {
            try {

                List<Tiene> tieneListBorrar = tieneRepository.findAllByfacturaId(facturaRepository.findById(id).get());
                tieneListBorrar.forEach(tiene -> {
                    tieneRepository.delete(tiene);
                });

                facturaRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

            } catch (Exception e) {
                e.printStackTrace(System.err);
                return ResponseEntity.badRequest().build();
            }

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<FacturaResponse> listFacturasConProductos(Long id) {

        List<Factura> facturaList = new ArrayList<>();

        //Si el parametro de entrada es = 0 entonces lista todas las facturas guardadas en la BD, de lo contrario, quiere decir que se 
        //esta buscando una factura en especifico, asi que solo se agrega una factura a la lista.
        if (id == 0) {
            facturaList = facturaRepository.findAll();
        } else {
            facturaList.add(0, facturaRepository.findById(id).get());
        }

        List<Tiene> listTiene = new ArrayList<>();
        List<Producto> listProductos = new ArrayList<>();
        List<FacturaResponse> facturaResponse = facturaMapper.recibeListaFacturaRetornaListaFacturaResponse(facturaList);

        int i = 0;

        //Para cada factura guardada se buscan los productos que contiene y se agrega la lista de productos a cada una.
        for (Factura factura : facturaList) {

            listTiene = tieneRepository.findAllByfacturaId(factura);
            Tiene tieneId = null;

            for (Tiene prodIdTiene : listTiene) {

                tieneId = prodIdTiene;

                if (prodIdTiene.getFacturaId().getIdFactura().equals(factura.getIdFactura())) {
                    listProductos.add(productoRepository.findById(prodIdTiene.getProductoId().getIdProducto()).get());
                }
            }
            
            //Si la PK de factura concuerda con la FK de tiene entonces se agrega la lista de productos a la posición i
            //de la lista de facturas.
            if (tieneId != null) {
                if (tieneId.getFacturaId().getIdFactura() == factura.getIdFactura()) {

                    facturaResponse.get(i).setProductosList(listProductos);
                    listProductos = new ArrayList<>();
                }
            }
            i++;
        }
        return facturaResponse;
    }

}
