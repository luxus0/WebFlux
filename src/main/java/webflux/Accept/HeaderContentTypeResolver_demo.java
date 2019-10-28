package webflux.webflux.Accept;


import io.netty.channel.ChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.accept.HeaderContentTypeResolver;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.Disposable;
import reactor.netty.Connection;
import reactor.netty.DisposableChannel;

import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class HeaderContentTypeResolver_demo extends HeaderContentTypeResolver {

    private HeaderContentTypeResolver headerType;
    private ServerWebExchange exchange;
    private HttpMessageReader messageReader;
    private Connection connection;
    private Disposable dispose;

    public HeaderContentTypeResolver_demo() throws ServerNotActiveException {

        List<HttpMessageReader<?>> reader = new ArrayList<>();
        Assert.state(reader.add(messageReader),"MESSAGE FROM REQUEST");


        headerType = new HeaderContentTypeResolver();

        exchange.addUrlTransformer(p -> StringUtils.applyRelativePath("http://localhost:8080/",""));


        ServerRequest.create(exchange,reader);
        ServerResponse.ok().body(exchange,HeaderContentTypeResolver_demo.class);

        headerType.resolveMediaTypes(exchange);

        if(exchange.getRequest() == null && exchange.getResponse() == null)
        {
           throw new ServerNotActiveException("SERVER NOT FOUND");
        }
        else
        {

            Logger log = LoggerFactory.getLogger(HeaderContentTypeResolver_demo.class);
            log.info("SERVER IS CONNECTED");
        }
    }
}
