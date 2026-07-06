package mqttpub;

import org.apache.camel.builder.RouteBuilder;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GenerateMessagesRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:test?period=1&fixedRate=true")
                .setBody(constant("Test Message " + System.currentTimeMillis()))
                .to("seda:publisher?concurrentConsumers=10&blockWhenFull=true")
                .log("Sent message to SEDA queue");

        from("seda:publisher?concurrentConsumers=10")
                .to("paho-mqtt5:testqueue?qos=1&brokerUrl=tcp://{{brokeraddress}}:{{brokerport}}")
                .log("Published to MQTT");
    }
}