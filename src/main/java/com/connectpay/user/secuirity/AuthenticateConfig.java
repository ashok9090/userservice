package com.connectpay.user.secuirity;

import static springfox.documentation.builders.PathSelectors.regex;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class AuthenticateConfig {
	
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("API-DOCS")
				.apiInfo(apiInfo()).select().paths(PathSelectors.any()).build();
	}

	private com.google.common.base.Predicate<String> postPaths() {
		return (regex("/users.*"));
	}

	

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("CONENCTPAY")
				.description("USER SERVICE API reference for developers")
				.termsOfServiceUrl("https://agent.connectpay.in/portal/")
				.contact(new Contact("Ashok Kumar Das", "https://agent.connectpay.in/portal/", "ashok.das@connectpay.in")).license("ConnectPay")
				.licenseUrl("https://agent.connectpay.in/portal/").version("1.0").build();
	}
	
	/*
	 * public Docket postApi() { return new
	 * Docket(DocumentationType.SWAGGER_2).groupName("ConnectPay API").apiInfo(
	 * apiInfo()) .select().paths(regex("/users.*")).build(); }
	 * 
	 * private ApiInfo apiInfo() { // TODO Auto-generated method stub return new
	 * ApiInfoBuilder().title("USER SERVICE")
	 * .description("API").termsOfServiceUrl("https://agent.connectpay.in/portal/").
	 * license("ConnectPay").version("1.0").build(); }
	 */

}
