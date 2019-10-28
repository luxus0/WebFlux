package webflux.webflux.Server;

import org.h2.jdbc.JdbcSQLDataException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.netty.http.server.HttpServerRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Function;

@Configuration
@EnableWebSecurity
public class SecurityServer extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("username").password(passwordEncoder().encode("passw")).roles("username")
                .and()
                .withUser("admin").password(passwordEncoder().encode("passwd123")).roles("admin");

        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username,password,enabled FROM user WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username,password,enabled FROM user WHERE username = ?")
                .passwordEncoder(passwordEncoder());

    }



    public void UserDetails(UserDetailsService service)
    {
        service.loadUserByUsername("user");
    }

    @Bean
public PasswordEncoder passwordEncoder()
{
    return new BCryptPasswordEncoder();
}



}
