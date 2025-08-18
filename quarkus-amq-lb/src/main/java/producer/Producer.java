package producer;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class Producer extends RouteBuilder {

    @Override
    public void configure() {
        from("timer:testProducer?period=10000") // every 10 seconds
                .setBody(simple("Hello AMQ at ${date:now:yyyy-MM-dd HH:mm:ss}"))
                .log("Producing message: ${body}")
                .to("jms:queue:testqueue");

    }
}
