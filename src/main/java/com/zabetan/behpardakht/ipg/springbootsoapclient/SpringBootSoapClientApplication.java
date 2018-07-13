package com.zabetan.behpardakht.ipg.springbootsoapclient;

import com.zabetan.behpardakht.ipg.schemas.school.BpPayRequest;
import com.zabetan.behpardakht.ipg.schemas.school.BpPayRequestResponse;
import com.zabetan.behpardakht.ipg.schemas.school.ObjectFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.bind.JAXBElement;

@SpringBootApplication
public class SpringBootSoapClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSoapClientApplication.class, args);
	}
	
	@Bean
	CommandLineRunner lookup(SOAPConnector soapConnector) {
		return args -> {
			BpPayRequest request = new BpPayRequest();
			request.setTerminalId(00000); //terminal ID
			request.setUserName("Username");
			request.setUserPassword("Password");
			request.setOrderId(13);
			request.setAmount(1000);
			request.setLocalDate("20170808");
			request.setLocalTime("101010");
			request.setAdditionalData("");
			request.setCallBackUrl("");
			request.setPayerId(0);
			ObjectFactory objectFactory = new ObjectFactory();
			objectFactory.createBpPayRequest(request);

			JAXBElement<BpPayRequestResponse> object = (JAXBElement<BpPayRequestResponse>) soapConnector.callWebService("https://bpm.shaparak.ir/pgwchannel/services/pgw", objectFactory.createBpPayRequest(request));
			BpPayRequestResponse bp = (BpPayRequestResponse)object.getValue();
			System.out.println("Got Response As below ========= : ");
			System.out.println("Return : "+bp.getReturn());
		};
	}
}
