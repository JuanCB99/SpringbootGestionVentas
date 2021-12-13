/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.hemisferiod.entities;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Juan
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "producto")
public class Producto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    @ApiModelProperty(name = "nombreProducto", required = true, example = "Portatil Gamer TUF")
    private String nombreProducto;
    @ApiModelProperty(name = "cantidadStock", required = true, example = "55")
    private int cantidadStock;
    @ApiModelProperty(name = "tipoProducto", required = true, example = "Tecnologia")
    private String tipoProducto;
    @ApiModelProperty(name = "marcaProducto", required = true, example = "ASUS")
    private String marcaProducto;
    @ApiModelProperty(name = "precioProducto", required = true, example = "2780000")
    private double precioProducto;
    
}
