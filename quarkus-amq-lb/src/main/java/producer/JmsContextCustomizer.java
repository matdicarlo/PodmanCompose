package producer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.jms.ConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.quarkus.core.events.ComponentAddEvent;

@ApplicationScoped
public class JmsContextCustomizer {

    public void onComponentAdd(@Observes ComponentAddEvent event, CamelContext camelContext) {
        if (event.getComponent() instanceof org.apache.camel.component.jms.JmsComponent) {
            org.apache.camel.component.jms.JmsComponent jmsComponent = (org.apache.camel.component.jms.JmsComponent) event.getComponent();
            jmsComponent.setConnectionFactory(camelContext.getRegistry().lookupByNameAndType("pooledConnectionFactory", ConnectionFactory.class));
        }
    }
}
