package webflux.webflux.Server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class OAuth2Server extends AuthorizationServerConfigurerAdapter {

@Autowired
    private DataSource dataSource;
    private AuthenticationManager manager;



    @Override
    public void configure(AuthorizationServerSecurityConfigurer auth)
    {
        auth
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer client) throws Exception {
        client
                .inMemory()
                .withClient("clientID")
                .scopes("Scope")
                .authorizedGrantTypes("password","auth_grant","token")
                .authorities("Authority")
                .secret("ClientSecret")
                .accessTokenValiditySeconds(20*40)
                .refreshTokenValiditySeconds(450)
                .additionalInformation("All Client info");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer auth)
    {
        auth
                .tokenStore(tokenStore())
                .authenticationManager(manager);
    }


    @Bean
    public TokenStore tokenStore() {

        return new JdbcTokenStore(dataSource);
    }

}
