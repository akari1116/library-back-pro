
package com.sapporo.library.config;
import static com.sapporo.library.constans.SecurityConstans.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.sapporo.library.auth.JWTAuthenticationFilter;
import com.sapporo.library.auth.JWTAuthorzationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserDetailsService userDetailsService;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/login").permitAll()
		.anyRequest().authenticated()
		.and()
        .cors()
        .configurationSource(this.corsConfigurationSource())
    .and()
	.formLogin()//3
	.loginPage("https://library-front-61849.web.app/login")
	.permitAll()
		.defaultSuccessUrl("https://library-front-61849.web.app/")
		 .and().csrf().disable()
		 .addFilter(new JWTAuthenticationFilter(authenticationManager(), bCryptPasswordEncoder()))
       .addFilter(new JWTAuthorzationFilter(authenticationManager()))
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
    }
	
	@Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder());
//		  auth.authenticationProvider(new BasicAuthenticationProvider());
                
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        corsConfiguration.addExposedHeader(HEADER_STRING);
        corsConfiguration.addAllowedOrigin("https://library-front-61849.web.app");

        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", corsConfiguration);

        return corsSource;
    }
    
    
    public class BasicAuthenticationProvider implements AuthenticationProvider {

        // (9) 認証チェック
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {

            String name = authentication.getName();
            String password = authentication.getCredentials().toString();

            //  入力された name / password をチェックする
            if( name.equals("suzuki.akari@acrovision.jp") && password.equals("sapporolibrary") ){
               return new UsernamePasswordAuthenticationToken(name,password,authentication.getAuthorities());
            }

            throw new AuthenticationCredentialsNotFoundException("basic auth error");
        }

        // (10) 処理すべきAuthenticationクラスのチェック
        @Override
        public boolean supports(Class<?> authentication) {
            return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
        }
    }

}
