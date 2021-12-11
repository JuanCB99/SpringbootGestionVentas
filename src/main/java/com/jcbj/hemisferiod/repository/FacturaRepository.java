/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.hemisferiod.repository;

import com.jcbj.hemisferiod.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Juan
 */
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    
}
