package webflux.webflux.Accept;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.h2.expression.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ResolvableType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.util.Assert;
import org.springframework.web.reactive.accept.FixedContentTypeResolver;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.accept.RequestedContentTypeResolverBuilder;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.MediaTypeNotSupportedStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.mail.internet.ContentType;
import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.charset.spi.CharsetProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class FixedContentTypeResolver_demo extends FixedContentTypeResolver implements RequestedContentTypeResolver {

    private List<MediaType> contentType;
    private MediaType mediaType;
    private RequestedContentTypeResolver requestResolver;
    private RequestedContentTypeResolverBuilder requestResolverBuilder;
   private Logger log = LoggerFactory.getLogger(FixedContentTypeResolver_demo.class);
    private ServerWebExchange exchange;
    private ServerResponse response;
    private ServerRequest request;
    private HttpMessageReader messageReader;
    private ResolvableType resolvType;

    public FixedContentTypeResolver_demo(MediaType mediaType) {
        super(mediaType);
    }

    public FixedContentTypeResolver_demo(List<MediaType> contentTypes) {
        super(contentTypes);
    }

    @JsonGetter
    public List<MediaType> getContentType() {


        MediaType type = new MediaType(MediaType.APPLICATION_JSON_VALUE,"application/json");
        MediaType type2 = new MediaType(MediaType.APPLICATION_ATOM_XML_VALUE, "xml");

        MediaType type3 = new MediaType(MediaType.APPLICATION_CBOR_VALUE,"cbor",
                Charset.forName(StandardCharsets.US_ASCII.name()));

        MediaType type4 = new MediaType(MediaType.APPLICATION_FORM_URLENCODED_VALUE,"urlencoded",
                Charset.forName(StandardCharsets.UTF_8.name()));

        MediaType type5 = new MediaType(MediaType.APPLICATION_PROBLEM_JSON_VALUE,"problem-Json");

        List<MediaType> contentTypeList = new ArrayList<>();
        contentTypeList.add(type);
        contentTypeList.add(type2);
        contentTypeList.add(type3);
        contentTypeList.add(type4);
        contentTypeList.add(type5);

        ListIterator iter = contentTypeList.listIterator();
        while(iter.hasNext())
        {
            for(int i = 0; i < contentTypeList.size(); i++) {
                Charset charset = contentTypeList.get(i).getCharset();
                System.out.println(charset);
            }
        }

        ContentType content = new ContentType();
        content.setParameter(type.getType(),type.getSubtype());
        content.setParameter(type2.getType(),type.getSubtype());
        content.setParameter(type3.getType(),type.getSubtype());
        content.setParameter(type4.getType(),type.getSubtype());
        content.setParameter(type5.getType(),type.getSubtype());

        if(content.match(type4.getSubtype()))
        {
            ServerResponse.ok().build();
        }
        else
        {

            log.error("CONTENTTYPE",new MediaTypeNotSupportedStatusException("NOT MATCH CONTENT TYPE"));
        }


        return contentType;
    }

    public void setContentType(List<MediaType> contentType) {
        this.contentType = contentType;
    }

    @JsonGetter
    public MediaType getMediaType() {

        MediaType type = new MediaType("JSON","JSON-VALUE");

        if(type.includes(MediaType.ALL))
        {
            throw new MediaTypeNotSupportedStatusException("MEDIA NOT SUPPORT");
        }
        else
        {
            log.info("MediaType is : " +type);
        }
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }


    public RequestedContentTypeResolver getRequestResolver() throws MalformedURLException {



        List<HttpMessageReader<?>> reader = new ArrayList<>();
        Assert.state(reader.add(messageReader),"MESSAGE FROM REQUEST");

        URL url = new URL("http","localhost",8080,"resolver");
        exchange.addUrlTransformer(p -> {
            try {

                url.openConnection().connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return p;


        });

        Mono.just(url).map(serv -> ServerRequest.create(exchange,reader));
        Mono.just(url).map(serv -> ServerResponse.ok());

        requestResolver.resolveMediaTypes(exchange);

        return requestResolver;
    }

    public void setRequestResolver(RequestedContentTypeResolver requestResolver) {
        this.requestResolver = requestResolver;
    }

    public RequestedContentTypeResolverBuilder getRequestResolverBuilder() {
        return requestResolverBuilder;
    }

    public void setRequestResolverBuilder(RequestedContentTypeResolverBuilder requestResolverBuilder) {
        this.requestResolverBuilder = requestResolverBuilder;
    }

    public ResolvableType resolvableType()
    {
        return resolvType;
    }
}
