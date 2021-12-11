/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcbj.hemisferiod.mappers;

import com.jcbj.hemisferiod.dto.FacturaResponse;
import com.jcbj.hemisferiod.entities.Factura;
import java.util.List;
import org.mapstruct.Mapper;

/**
 *
 * @author Juan
 */
@Mapper(componentModel = "spring")
public interface FacturaResponseMapper {
    
    FacturaResponse recibeFacturaRetornaFacturaResponse(Factura factura);
    
    List<FacturaResponse> recibeListaFacturaRetornaListaFacturaResponse(List<Factura> factura);
}
