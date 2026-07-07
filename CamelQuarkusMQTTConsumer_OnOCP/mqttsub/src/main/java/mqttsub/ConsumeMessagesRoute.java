package mqttsub;

import org.apache.camel.builder.RouteBuilder;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConsumeMessagesRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
	from("paho-mqtt5:testqueue?brokerUrl=tcp://{{brokeraddress}}:{{brokerport}}" +
	     "&clientId=mqtt-subscriber-fixed-id" +
	     "&cleanStart=false" +                 
	     "&sessionExpiryInterval=4294967295" + 
	     "&automaticReconnect=true")           
	     .log("Received: ${body}");
//        from("paho-mqtt5:testqueue?qos=1&brokerUrl=tcp://{{brokeraddress}}:{{brokerport}}")
//		.threads(10)
//                .log("Received: ${body}");

    }
}
