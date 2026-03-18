package QuarkusLogger;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class QuarkusLogger extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:trigger?period=5000")
                .routeId("history-test-route")
                .to("log:com.mycompany.department.project.subproject.audit.logger.output")
                .process(exchange -> {
                    throw new RuntimeException("One wide table, coming up!");
                });
    }
}