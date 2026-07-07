package mqttpub;

import org.apache.camel.builder.RouteBuilder;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GenerateMessagesRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .handled(true)
                .logStackTrace(false)
                .maximumRedeliveries(5)
                .redeliveryDelay(250);

        String payload300Bytes = "A".repeat(300);

        from("timer:startOnce?repeatCount=1")
                .loop(500000)
                    .setBody(constant(payload300Bytes))
                    .to("seda:publisher?blockWhenFull=true")
                .end()
                .log("Finished sending 500,000 messages");
        
        from("seda:publisher?concurrentConsumers=10")
                .to("paho-mqtt5:testqueue?qos=1&brokerUrl=tcp://{{brokeraddress}}:{{brokerport}}" +
                    "&cleanStart=false&sessionExpiryInterval=4294967295&automaticReconnect=true")
                .log("Published to MQTT");
    }
}




/*
package mqttpub;

import org.apache.camel.builder.RouteBuilder;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GenerateMessagesRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // 1. Define the Exception handler globally for this route builder
        onException(Exception.class)
                .handled(true)
                .logStackTrace(false)
                .maximumRedeliveries(5)
                .redeliveryDelay(250)
                .useExponentialBackOff()
                .backOffMultiplier(2.0);

        String largePayload = "A".repeat(1500);

        // 2. Timer to trigger message bursts
        from("timer:test?period=5&fixedRate=true")
                .setBody(constant("Test Message " + System.currentTimeMillis()))
                .to("seda:publisher?concurrentConsumers=20&blockWhenFull=true")
                .log("Sent message to SEDA queue");

        // 3. The actual MQTT publisher (Updated with cleanStart and sessionExpiryInterval)
        from("seda:publisher?concurrentConsumers=10")
                .to("paho-mqtt5:testqueue?qos=1&brokerUrl=tcp://{{brokeraddress}}:{{brokerport}}&cleanStart=false&sessionExpiryInterval=4294967295&automaticReconnect=true")
                .log("Published to MQTT");
    }
}
*/
