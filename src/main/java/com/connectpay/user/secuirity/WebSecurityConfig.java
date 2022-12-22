package com.connectpay.user.secuirity;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.connectpay.user.util.ManualPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
    private LoginDetailsServiceImpl userService;
	
	@Autowired
	private JWTTokenHelper jWTTokenHelper;
	
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Bean
	public ManualPasswordEncoder passwordEncoder() {
		return new ManualPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
		.authenticationEntryPoint(authenticationEntryPoint).and().authorizeRequests()
		.antMatchers("/users/authenticate").permitAll()
		.antMatchers("/users/verifyUser").permitAll()
		.antMatchers("/users/exposeApi").permitAll()
		.antMatchers(AUTH_WHITELIST).permitAll()
		.antMatchers(HttpMethod.OPTIONS,"/users/**").permitAll()
		.anyRequest().authenticated();
		http.addFilterBefore(new JWTAuthenticationFilter(userService, jWTTokenHelper),
				UsernamePasswordAuthenticationFilter.class);
		
		
		
		http.httpBasic();
		http.csrf().disable();
		http.cors(c->{
			    CorsConfigurationSource cs = r -> {
                CorsConfiguration cc = new CorsConfiguration();
                cc.setAllowedOrigins(Arrays.asList("*"));
                cc.setAllowedMethods(Arrays.asList("GET","POST"));
                return cc;
            };

            c.configurationSource(cs);
		});
	}
	
	
	
	
}
