package it.bridgeconsulting.camel.cache.route;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class IntegrationRoute extends EndpointRouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from(direct("start"))
	        .to("caffeine-cache://cache?action=PUT&key=1")
	        .setBody(constant("new value"))
	        .log("Body: ${body}")
	        .to("caffeine-cache://cache?key=1&action=GET")
	        .log("${header.CamelCaffeineActionHasResult}")
	        .log("Body: ${body}");
	}
}
