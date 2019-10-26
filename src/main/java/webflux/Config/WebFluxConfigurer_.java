package webflux.webflux.Config;

import com.nimbusds.oauth2.sdk.ResponseMode;
import org.apache.commons.logging.Log;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.ResourceHandlerRegistrationCustomizer;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxRegistrations;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.boot.web.codec.CodecCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.core.codec.Decoder;
import org.springframework.core.codec.Encoder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ResourceLoader;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.reactive.config.*;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.handler.WebFluxResponseStatusExceptionHandler;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.metadata.MethodType;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.rmi.ServerError;
import java.text.ParseException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Logger;


@Configuration
public class WebFluxConfigurer_  implements WebFluxConfigurer, WebFluxRegistrations {

    private WebFluxAutoConfiguration.WebFluxConfig config;
    private WebFluxAutoConfiguration autoConfiguration;
    private WebFluxConfigurerComposite composite;
    private WebFluxRegistrations webFluxRegistrations;
    private ListableBeanFactory beanFactory;
    private ObjectProvider<HandlerMethodArgumentResolver> resolvers;
    private ObjectProvider<CodecCustomizer> codecCustomizers;
    private ObjectProvider<ResourceHandlerRegistrationCustomizer> resourceHandlerRegistrationCustomizer;
    private ObjectProvider<ViewResolver> viewResolvers;
    private WebFluxProperties properites;
    private WebFluxResponseStatusExceptionHandler handler;
    private HttpMessageConverter<ResponseEntity> messageConverterHandler;
    private Parser<Mono> monoParser;
    private RestTemplate restTemplate;
    private Printer<RestTemplate> printerRest;
    private Mono<ServerRequest> request;
    private Mono<ServerResponse> response;
    private HttpServletRequest servRequest;
    private WebFluxConfigurer configur;
    private MethodParameter methodParameter;
    private BindingContext context;



    public WebFluxAutoConfiguration.WebFluxConfig getConfig(@Qualifier @NotNull FormatterRegistry formatterRegistry, Formatter<ResponseEntity> formatRes, Formatter<ServerResponse> formatServ) throws ParseException, IllegalAccessException, InstantiationException, ServerError {


        ResponseEntity format = formatRes.parse("Format ENGLISH", Locale.ENGLISH);
       String printformat = formatServ.print(ServerResponse.accepted().build(),Locale.CANADA_FRENCH);


        formatRes = Formatter.class.cast("New FORMAT");
        formatServ = Formatter.class.newInstance();

        formatterRegistry.addFormatter(formatRes);
        formatterRegistry.addFormatter(formatServ);

        Mono mono = monoParser.parse(Mono.just(getProperites()).metrics().map(p -> p.getDateFormat() == DateTimeFormatter.BASIC_ISO_DATE.toString()).toString(),Locale.CANADA_FRENCH);


        formatterRegistry.addParser((Parser<?>) mono);
        formatterRegistry.addPrinter(getPrinterRest());

        formatterRegistry.addFormatterForFieldType(WebFluxConfigurer_.class,formatRes);
        this.config.addFormatters(formatterRegistry);


        Map<String, HttpStatus> mapRest = new HashMap<>();

        mapRest.put("STATUS ACCEPT",HttpStatus.ACCEPTED);
        mapRest.put("BAD GATEWAY",HttpStatus.BAD_GATEWAY);
        mapRest.put("CONFLICT SERVER",HttpStatus.CONFLICT);
        mapRest.put("STATUS FORBIDDEN",HttpStatus.FORBIDDEN);


        String server = printerRest.print(restTemplate.getForObject("/status",RestTemplate.class,mapRest),Locale.ENGLISH);
        if(server.isEmpty()) {

            Error error = new Error();
            error.fillInStackTrace();
            throw new ServerError("SERVER NOT FOUND",error);
        }
        else if(request.just(server).equals(HttpStatus.OK)) {


            List<HttpMessageConverter<?>> convert = new ArrayList<>();
            convert.add(messageConverterHandler);

            ServerRequest.create(servRequest,convert);
        }

        else if(response.just(server).equals(HttpStatus.OK)) {
            ServerResponse.ok().contentType(MediaType.ALL).allow(HttpMethod.GET).build();
        }

        return config;
    }

    public void setConfig(WebFluxAutoConfiguration.WebFluxConfig config) {

        this.config = config;
    }

    public WebFluxAutoConfiguration getAutoConfiguration() {

        ServerWebExchange exchange = null;
        Function<String,String> function = Function.identity();
        function.apply("Exchange");
        function.apply("Exchange2");
        function.apply("Exchange3");
        function.apply("Exchange4");

        exchange.addUrlTransformer(function.andThen(p ->{
            try {
                URL url = new URL("http","localhost",8080,"exchange");
                URLConnection connection = url.openConnection();
                connection.addRequestProperty(HttpHeaders.ACCEPT_RANGES, StandardCharsets.UTF_8.displayName(Locale.CANADA_FRENCH));
                connection.addRequestProperty("accept-language",Locale.getISOCountries().toString());
                connection.setAllowUserInteraction(true);

                Map<String,String> map = new HashMap<>();
                map.put("Connected","true");
                map.put("Disconnected","true");
                map.put("Server error", new ServerErrorException("CRITICAL ERROR").getLocalizedMessage());
                for(String key : map.keySet()) {
                    for (String value : map.values()) {
                        Log log = (Log) Logger.getLogger(key);
                        log.debug("KEY CONNECTION: " + key);
                        log.debug("VALUE CONNECTION: " +value);

                        connection.setRequestProperty(key,value);
                    }
                }

                connection.addRequestProperty(RequestEntity.get(url.toURI()).toString(), exchange.getLocaleContext().getLocale()
                .getCountry());

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();

            }
            return p;
        }));
        WebFluxAutoConfiguration conf = new WebFluxAutoConfiguration();

        conf.hiddenHttpMethodFilter().setMethodParamName(MediaType.APPLICATION_CBOR_VALUE);
        conf.hiddenHttpMethodFilter().setMethodParamName(ApplicationContext.CLASSPATH_ALL_URL_PREFIX);
        conf.hiddenHttpMethodFilter().setMethodParamName(ResponseEntity.ok().lastModified(Instant.now()).toString());
        conf.hiddenHttpMethodFilter().setMethodParamName(ResponseCookie.from("COKIEEZ", OAuth2AccessToken.TokenType.BEARER.getValue()).toString());
        //conf.hiddenHttpMethodFilter().filter();

        return autoConfiguration;
    }

    public void setAutoConfiguration(WebFluxAutoConfiguration autoConfiguration) {
        this.autoConfiguration = autoConfiguration;
    }

    public WebFluxConfigurerComposite getComposite(CorsRegistry corsRegistry, ResourceLoader loader, ArgumentResolverConfigurer argumentResolver,ServerWebExchange exchange) {
        WebFluxConfigurerComposite composite = new WebFluxConfigurerComposite();
        corsRegistry = new CorsRegistry();
        corsRegistry.addMapping("/registry");
        corsRegistry.addMapping("/allowed")
                .allowCredentials(true)
                .allowedHeaders(HttpHeaders.ACCEPT_CHARSET)
                .allowedMethods(HttpMethod.GET.name(),HttpMethod.POST.name(),HttpMethod.PUT.name(),HttpMethod.DELETE.name())
                .allowedOrigins("Oriigins").maxAge(70);

        ResourceHandlerRegistry registry = new ResourceHandlerRegistry(loader);
        composite.addCorsMappings(corsRegistry);
        composite.addResourceHandlers(registry.setOrder(2));

        List<WebFluxConfigurer> config = new ArrayList<>();
        configur.addFormatters((FormatterRegistry) corsRegistry);
        HandlerMethodArgumentResolver resolver = null;

        Mono.just(methodParameter).filter(p -> {
            return p.getMethod().equals(MethodType.GETTER);
        });
        resolver.resolveArgument(methodParameter,context,exchange);
        argumentResolver.addCustomResolver(resolver);
        configur.configureArgumentResolvers(argumentResolver);
        configur.configureHttpMessageCodecs(getServerCodecConfig());
        config.add(configur);



        return composite;
    }

    public ServerCodecConfigurer getServerCodecConfig()
    {
        Encoder<OAuth2AccessToken> ecodeToken = null;
        Decoder<ResponseMode> decodeResponse = null;

        ServerCodecConfigurer codec = ServerCodecConfigurer.create();
        ServerCodecConfigurer.ServerDefaultCodecs code =  codec.defaultCodecs();


        return codec;

    }

    public void setComposite(WebFluxConfigurerComposite composite) {
        this.composite = composite;
    }

    public WebFluxRegistrations getWebFluxRegistrations() {
        return webFluxRegistrations;
    }

    public void setWebFluxRegistrations(WebFluxRegistrations webFluxRegistrations) {
        this.webFluxRegistrations = webFluxRegistrations;
    }

    public ListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(ListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public ObjectProvider<HandlerMethodArgumentResolver> getResolvers() {
        return resolvers;
    }

    public void setResolvers(ObjectProvider<HandlerMethodArgumentResolver> resolvers) {
        this.resolvers = resolvers;
    }

    public ObjectProvider<CodecCustomizer> getCodecCustomizers() {
        return codecCustomizers;
    }

    public void setCodecCustomizers(ObjectProvider<CodecCustomizer> codecCustomizers) {
        this.codecCustomizers = codecCustomizers;
    }

    public ObjectProvider<ResourceHandlerRegistrationCustomizer> getResourceHandlerRegistrationCustomizer() {
        return resourceHandlerRegistrationCustomizer;
    }

    public void setResourceHandlerRegistrationCustomizer(ObjectProvider<ResourceHandlerRegistrationCustomizer> resourceHandlerRegistrationCustomizer) {
        this.resourceHandlerRegistrationCustomizer = resourceHandlerRegistrationCustomizer;
    }

    public ObjectProvider<ViewResolver> getViewResolvers() {
        return viewResolvers;
    }

    public void setViewResolvers(ObjectProvider<ViewResolver> viewResolvers) {
        this.viewResolvers = viewResolvers;
    }


    public WebFluxProperties getProperites() {
        properites.setStaticPathPattern("/path");
        return properites;
    }

    public void setProperites(WebFluxProperties properites) {
        this.properites = properites;
    }

    public WebFluxResponseStatusExceptionHandler getHandler() {
        return handler;
    }

    public void setHandler(WebFluxResponseStatusExceptionHandler handler) {
        this.handler = handler;
    }

    public WebFluxAutoConfiguration.WebFluxConfig getConfig() {
        return config;
    }

    public HttpMessageConverter<ResponseEntity> getMessageConverterHandler() {
        return messageConverterHandler;
    }

    public void setMessageConverterHandler(HttpMessageConverter<ResponseEntity> messageConverterHandler) {
        this.messageConverterHandler = messageConverterHandler;
    }

    public Parser<Mono> getMonoParser() {
        return monoParser;
    }

    public void setMonoParser(Parser<Mono> monoParser) {
        this.monoParser = monoParser;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Printer<RestTemplate> getPrinterRest() {
        String printus = printerRest.print(restTemplate.getForObject("/status",RestTemplate.class),Locale.ENGLISH);
        Log log = (Log) LoggerFactory.getLogger(printus);
        log.debug(printus);
        return printerRest;
    }

    public void setPrinterRest(Printer<RestTemplate> printerRest) {
        this.printerRest = printerRest;
    }

    public Mono<ServerRequest> getRequest() {
        return request;
    }

    public void setRequest(Mono<ServerRequest> request) {
        this.request = request;
    }

    public Mono<ServerResponse> getResponse() {
        return response;
    }

    public void setResponse(Mono<ServerResponse> response) {
        this.response = response;
    }

    public HttpServletRequest getServRequest() {
        return servRequest;
    }

    public void setServRequest(HttpServletRequest servRequest) {
        this.servRequest = servRequest;
    }
}
