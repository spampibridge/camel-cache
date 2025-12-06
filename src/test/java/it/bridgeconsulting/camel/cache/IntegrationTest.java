package it.bridgeconsulting.camel.cache;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@CamelSpringBootTest
@MockEndpoints
class IntegrationTest {

	@EndpointInject("mock:caffeine-cache:cache")
    private MockEndpoint cache;

    @Autowired
    private ProducerTemplate producer;
    
    @Test
    void testCache() throws InterruptedException {
    	cache.expectedMessageCount(2);
    	cache.expectedBodiesReceived("value!!!", "new value");
    	cache.allMessages().body().isInstanceOf(String.class);
    	producer.sendBody("direct:start", "value!!!");
    	cache.assertIsSatisfied();
    }
}
