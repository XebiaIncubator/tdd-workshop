package com.xebia.tdd.training.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface HotelService {
	
	@WebMethod
	public String getHotelName(Long id);

}