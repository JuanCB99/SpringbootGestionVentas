/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.hemisferiod.dto;

import com.jcbj.hemisferiod.entities.Cliente;
import com.jcbj.hemisferiod.entities.Producto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Juan
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Este modelo representa todos los datos que vera el usuario cuando una factura sea creada")
public class FacturaResponse {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura;
    @Temporal(TemporalType.DATE)
    private Date fechVentaFactura;
    @ApiModelProperty(name = "articulosVendidosFactura", required = false, hidden = false)
    private int articulosVendidosFactura;
    private double totalVentaFactura;
    @ManyToOne
    private Cliente clienteId;
    @ApiModelProperty(name = "productosList", required = false, hidden = false)
    private List<Producto> productosList;

}
