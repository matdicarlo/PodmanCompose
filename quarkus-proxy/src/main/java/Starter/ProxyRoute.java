package Starter;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ProxyRoute extends RouteBuilder {

    @Inject
    CamelContext camelContext;
    private static final Logger log = LoggerFactory.getLogger(ProxyRoute.class);


    @Override
    public void configure() throws Exception {


// Solution 1, setting Global Options for proxy works, but setting nonProxyHost is not working
/*
            camelContext.getGlobalOptions().put("http.proxyHost", "127.0.0.1");
            camelContext.getGlobalOptions().put("http.proxyPort", "3128");
//        camelContext.getGlobalOptions().put("http.nonProxyHosts", "postman-echo.com|*.postman-echo.com");

        from("timer:nonProxyTest?repeatCount=1")
                .setHeader("CamelHttpMethod", constant("GET"))
                .to("http://postman-echo.com/get")
                .log("Response from localhost: ${body}");

        from("timer:proxyTest?repeatCount=1")
                .setHeader("CamelHttpMethod", constant("GET"))
                .to("http://httpbin.org")
                .log("Response from httpbin.org: ${body}");
*/

// Solution 2: use a custom configuration class

        from("timer:proxyTest?repeatCount=1")
                .setHeader("CamelHttpMethod", constant("GET"))
                //Use the non proxy configuration / bypass the proxy
                .to("httpNonProxy://httpbin.org")
                .log("Response from httpbin.org: ${body}");
// bypass the proxy:
        from("timer:proxyTest?repeatCount=1")
                .setHeader("CamelHttpMethod", constant("GET"))
                //Use the proxy configuration
                .to("http://httpbin.org")
                .log("Response from httpbin.org: ${body}");






    }
}