/*
package Starter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.apache.camel.component.http.HttpClientConfigurer;
import org.apache.camel.component.http.HttpComponent;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpHost;

@ApplicationScoped
public class HttpProxyConfig {
    @Named("proxyConfigurer")
    public HttpClientConfigurer createProxyConfigurer() {
        return new HttpClientConfigurer() {
            @Override
            public void configureHttpClient(HttpClientBuilder builder) {
                HttpHost proxy = new HttpHost("http", "127.0.0.1", 3128);
                builder.setProxy(proxy);
            }
        };
    }

    @Named("http")
    public HttpComponent proxyComponent(@Named("proxyConfigurer") HttpClientConfigurer configurer) {
        HttpComponent component = new HttpComponent();
        component.setHttpClientConfigurer(configurer);
        return component;
    }

    @Named("httpNonProxy")
    public HttpComponent nonProxyComponent() {
        return new HttpComponent();
    }


}
*/
