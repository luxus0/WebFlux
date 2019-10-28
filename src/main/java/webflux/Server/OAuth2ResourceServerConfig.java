package webflux.webflux.Server;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity security) throws Exception {
        security
                .requestMatchers()
                .antMatchers("/log")
                .antMatchers("/log2")
                .antMatchers("/log3").and()
                .logout().logoutUrl("/logout").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated();



    }
}
