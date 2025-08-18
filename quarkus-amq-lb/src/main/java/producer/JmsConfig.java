package producer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;

@ApplicationScoped
public class JmsConfig {

    @Produces
    public ConnectionFactory pooledConnectionFactory() {
        ActiveMQConnectionFactory amqFactory = new ActiveMQConnectionFactory(
                "failover:(tcp://localhost:61616,tcp://localhost:61617)?randomize=true"
        );

        PooledConnectionFactory pool = new PooledConnectionFactory();
        pool.setConnectionFactory(amqFactory);
        pool.setMaxConnections(10);
        return pool;
    }
}
