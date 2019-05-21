package ke.co.creswave.uzimahealth.auth;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import ke.co.creswave.uzimahealth.model.AccountCredentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserDetailsService userDetailsService;
	private AccountCredentials accountCredentials;

	public AccountCredentials getAccountCredentials() {
		return accountCredentials;
	}

	public void setAccountCredentials(AccountCredentials accountCredentials) {
		this.accountCredentials = accountCredentials;
	}

	public WebSecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;

	}

	private static String REALM = "ECARE_BASIC_AUTH";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().cacheControl().and().and()
        .csrf().ignoringAntMatchers("/socket/**").and()

        .authorizeRequests().and().httpBasic().disable();

		http.cors().and().csrf().disable().httpBasic().disable();
		http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/doc/**").permitAll()
				.antMatchers(HttpMethod.GET, "/v2/api-docs/**").permitAll().antMatchers(HttpMethod.GET, "/forgot/**")
				.permitAll().antMatchers(HttpMethod.POST, "/forgot/**").permitAll()
				.antMatchers(HttpMethod.POST, "/data/create/**").permitAll()
				.antMatchers(HttpMethod.POST, "/data/updatepasswordviacode**").permitAll()
				.antMatchers(HttpMethod.POST, "/search/apartment**").permitAll()
				.antMatchers(HttpMethod.POST, "/search/location**").permitAll()
				.antMatchers(HttpMethod.GET, "/search/propertytypes**").permitAll()
				.antMatchers("/socket**").permitAll().antMatchers("/socket/info**")
				.permitAll().antMatchers("/socket/info/**").permitAll()
				.antMatchers("/socket/**/**/**").permitAll()
				.antMatchers("/socket/**/**/**").permitAll()
				.antMatchers("/socket/**").permitAll()
				.antMatchers("/socket/**/**").permitAll()
				.antMatchers("/socket/**/**/**/**").permitAll()
				.antMatchers("/socket/**/**/**/**/**").permitAll()
				.antMatchers("/socket/**/**/**/**/**/**").permitAll()
				.antMatchers(HttpMethod.GET, "/search/apartmenttypes**").permitAll()
				.antMatchers(HttpMethod.GET, "/search/apartmenttypesbyproperty/**").permitAll()
				.antMatchers(HttpMethod.GET, "/search/latestapartments**").permitAll()
				.antMatchers(HttpMethod.POST, "/data/forgotpassword**").permitAll().anyRequest().authenticated().and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager(), accountCredentials))
				.addFilter(new JWTAuthorizationFilter(authenticationManager())).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().httpBasic().disable();


		http.csrf().disable().authorizeRequests().antMatchers("/appointments/update/patient/**").hasRole("ADMIN").and()
				.httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint()).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// We don't need sessions to
																							// be created.

		http.csrf().disable().authorizeRequests().antMatchers("/appointments/update/appointment**").hasRole("ADMIN")
				.and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint()).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// We don't need sessions to
																							// be created.
		http.csrf().disable().authorizeRequests().antMatchers("/appointments/get/users/**").hasRole("ADMIN").and()
				.httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint()).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// We don't need sessions to
																							// be created.
		http.csrf().disable().authorizeRequests().antMatchers("/appointments/get/medicalconditions/**").hasRole("ADMIN")
				.and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint()).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// We don't need sessions to
																							// be created.
		http.csrf().disable().authorizeRequests().antMatchers("/appointments/get/departments/**").hasRole("ADMIN").and()
				.httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint()).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// We don't need sessions to
																							// be created.
		http.csrf().disable().authorizeRequests().antMatchers("/appointments/upload-file**").hasRole("ADMIN").and()
				.httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint()).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// We don't need sessions to
																							// be created.
		http.csrf().disable().authorizeRequests().antMatchers("/appointments/upload-file-patient/**").hasRole("ADMIN")
				.and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint()).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// We don't need sessions to

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("ECARETXT").password("$5uNMkjiQWRTsqXc").roles("ADMIN");
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

	}

	@Bean
	public UzimaHealthBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
		return new UzimaHealthBasicAuthenticationEntryPoint();
	}

	/*
	 * @Override public void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { //auth.inMemoryAuthentication().withUser("ECARETXT").password(
	 * "$5uNMkjiQWRTsqXc").roles("ADMIN");
	 * //auth.userDetailsService(userDetailsService).passwordEncoder(
	 * bCryptPasswordEncoder);
	 * 
	 * }
	 */

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}