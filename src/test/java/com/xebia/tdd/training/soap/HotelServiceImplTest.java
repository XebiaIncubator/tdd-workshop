package com.xebia.tdd.training.soap;

import static org.junit.Assert.assertEquals;

import java.net.ServerSocket;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HotelServiceImplTest {

    private static Endpoint endpoint;
    private static int port;

    @InjectMocks
    private static HotelServiceImpl hotelServiceImpl = new HotelServiceImpl();

    @BeforeClass
    public static void beforeClass() throws Exception {
        ServerSocket serverSocket = new ServerSocket(0);
        port = serverSocket.getLocalPort();
        serverSocket.close();
        endpoint = Endpoint.publish("http://localhost:" + port + "/", hotelServiceImpl);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        endpoint.stop();
    }

    @Test
    public void testGetHotelName() throws Exception {
        URL wsdlUrl = new URL("http://localhost:" + port + "/soap?wsdl");
        QName serviceName = new QName("http://soap.training.tdd.xebia.com/", "HotelServiceImplService");
        Service service = Service.create(wsdlUrl, serviceName);
        HotelService port = service.getPort(HotelService.class);
        String hotelName = port.getHotelName((Long) 1001L);
        assertEquals("Lakewood HOTELS", hotelName);
    }

}
