package webflux.webflux.Accept;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.reactive.accept.ParameterContentTypeResolver;
import org.springframework.web.server.ServerWebExchange;

import java.time.Instant;
import java.util.Map;


public class ParameterContentTypeResolverDemo extends ParameterContentTypeResolver {

    private ParameterContentTypeResolver paramTypeResolver;
    private ServerWebExchange exchange;

    public ParameterContentTypeResolverDemo(Map<String, MediaType> mediaTypes) {
        super(mediaTypes);
    }

    public ParameterContentTypeResolver parameterContentTypeResolver()
    {
        Assert.notNull(exchange);

        if(exchange.isNotModified()) {
            exchange.checkNotModified("NOT MODIFIED PARAMETER CHECKED:", Instant.now());
        }
        else
        {
            Logger log = LoggerFactory.getLogger("MODIFY");
            log.info("YOU MODIFIED PARAMETER");
        }
        paramTypeResolver.setParameterName("ID");
        paramTypeResolver.setParameterName("Name");
        paramTypeResolver.setParameterName("Surname");
        paramTypeResolver.resolveMediaTypes(exchange);

        return paramTypeResolver;
    }
}
