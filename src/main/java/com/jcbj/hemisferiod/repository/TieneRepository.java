/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.hemisferiod.repository;

import com.jcbj.hemisferiod.entities.Factura;
import com.jcbj.hemisferiod.entities.Producto;
import com.jcbj.hemisferiod.entities.Tiene;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Juan
 */
public interface TieneRepository extends JpaRepository<Tiene, Long> {
    
    public List<Tiene> findAllByfacturaId(Factura facturaId);
    
    public Tiene findOneByfacturaId(Factura facturaId);
    
    public List<Tiene> findAllByproductoId(Producto productoId);
    
}
