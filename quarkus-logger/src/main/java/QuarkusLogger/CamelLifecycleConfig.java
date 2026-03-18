package QuarkusLogger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.apache.camel.impl.event.CamelContextStartingEvent;
import org.apache.camel.spi.MessageHistoryFactory;

@ApplicationScoped
public class CamelLifecycleConfig {
    public void configure(@Observes CamelContextStartingEvent event) {
        event.getContext().setMessageHistory(true);
        MessageHistoryFactory factory = event.getContext().getMessageHistoryFactory();
        event.getContext().getGlobalOptions().put("CamelMessageHistoryMaxChars", "500");
    }
}