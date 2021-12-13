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
@Table(name = "cliente")

public class Cliente implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    @ApiModelProperty(name = "identificaciónCliente", required = true, example = "10953045893")
    private String identificaciónCliente;
    @ApiModelProperty(name = "nombreCliente", required = true, example = "Diego Alexander")
    private String nombreCliente;
    @ApiModelProperty(name = "telefono", required = true, example = "3135568345")
    private String telefono;
    
    
}
