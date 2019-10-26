package webflux.Mono;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.netty.handler.codec.http.cookie.ServerCookieEncoder;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.netty.http.server.HttpServer;
import reactor.netty.tcp.TcpServer;
import webflux.Domain.User;
import webflux.Service.ReactiveUserDetailService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

public class Mono_Example {

    private ServerResponse response;
    private ServerRequest request;
    private HttpServletRequest servletRequest;
    private HttpServletResponse servletResponse;
    private ServerResponse.Context context;
    private HttpResponse.ResponseInfo info;
    private BindingResult result;
    private ReactiveUserDetailService service;
    private Logger log = LoggerFactory.getLogger(Mono_Example.class);
    private Publisher<HttpClient> client;
    private Subscriber<HttpClient> subs;
    private Subscription subscr;

    public void MonoExample(ResponseErrorHandler handle, ServerRequest request, ServerResponse response, String name) {
        Mono<ServerResponse> mono = Mono.just(service.findByUsername(name)).ofType(Mono_Example.class).map(p ->
        {
            if (service.findByUsername(name).equals("Jarek")) {
                log.info("username: " + name);
            } else {
                List<String> role = new ArrayList<String>();
                role.add("User");
                role.add("Admin");
                role.add("Freelancer");
                role.add("Begginer");

                String[] resp = result.resolveMessageCodes(service.addRoles(role).blockOptional().get().getUsername());
                for (int i = 0; i < resp.length; i++) {

                    try {
                        HttpServerErrorException req = new HttpServerErrorException(HttpStatus.BAD_REQUEST);
                        HttpServerErrorException gate = new HttpServerErrorException(HttpStatus.BAD_GATEWAY);
                        HttpServerErrorException accept = new HttpServerErrorException(HttpStatus.ACCEPTED);
                        HttpServerErrorException forbid = new HttpServerErrorException(HttpStatus.FORBIDDEN);
                        HttpServerErrorException time = new HttpServerErrorException(HttpStatus.GATEWAY_TIMEOUT);

                        ModelAndView view = new ModelAndView();
                        Map<String, HttpServerErrorException> ex = new HashMap<>();
                        ex.put("SERVER BAD REQUEST", req);
                        ex.put("SERVER ACCEPTED", accept);
                        ex.put("SERVER BAD GATEWAY", gate);
                        ex.put("SERVER FORBIDDEN", forbid);
                        ex.put("SERVER GATEWAY TIMEOUT", time);

                        view.addAllObjects(ex);
                        ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(role).writeTo(servletRequest, servletResponse, context);


                    } catch (ServletException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return response;
        });


    }

    public void Flux_Example(User user, HttpResponse.ResponseInfo response,HttpServer httpServer) {

        List<String> headersName = response.headers().allValues("Name");
        headersName.add(HttpHeaders.CONTENT_TYPE);
        headersName.add(HttpHeaders.COOKIE);
        headersName.add(HttpHeaders.ACCEPT_LANGUAGE);

        headersName.forEach(System.out::println);

        Map<String, MediaType> map = new HashMap<>();
        map.put(HttpHeaders.ACCEPT_LANGUAGE, MediaType.APPLICATION_JSON);
        map.put(HttpHeaders.ACCEPT_ENCODING, MediaType.APPLICATION_FORM_URLENCODED);
        map.put(HttpHeaders.ACCEPT_CHARSET, MediaType.TEXT_PLAIN);
        map.put(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, MediaType.MULTIPART_MIXED);

        for (String key : map.keySet()) {
            for (MediaType values : map.values()) {
                if (map.isEmpty()) {
                    log.error(Marker.ANY_MARKER, HttpHeaders.EMPTY, "EMPTY HEADERS");
                    log.error(Marker.ANY_MARKER, HttpHeaders.EMPTY, "EMPTY MEDIATYPE ");

                    for (int i = 0; i < map.size(); i++) {
                        Assert.isNull(map.get(i));
                    }
                } else {
                    log.info(key);
                    log.info(String.valueOf(values));

                    for (int i = 0; i < map.size(); i++) {

                        Assert.notNull((map.get(i)));
                    }
                }
            }


            Function<HttpResponse.ResponseInfo, Publisher> func1 = null;
            func1.apply(response);

            int code = response.statusCode();
            log.error("STATUS CODE:", code);

            Flux
                    .create(FluxSink<HttpResponse.ResponseInfo>::complete)
                    .filter(p -> user.getUsername().equals("Mona List"))
                    .then()
                    .map(ultra -> {
                        Object[] obj = {"2", "3", "4"};
                        Function<Object[], HttpServer> func2 = null;
                        HttpServer http = func2.apply(obj);

                        Assert.notNull(http);

                        subs.onSubscribe(getSubscr());
                        client.subscribe(getSubs());

                        Flux<HttpServer> tcp = Flux.combineLatest(func2, client).concatWithValues(HttpServer.from(TcpServer.create()));
                        return tcp;
                    })
                    .flatMap(fluxmode ->

                    {
                        final HttpServer server = HttpServer.create();
                        server.host("localhost");
                        server.port(8080);


                    Flux<HttpServer> ser = Flux.just(httpServer).log("Server is active is activate").map(p -> p.cookieCodec(ServerCookieEncoder.LAX)).cache(Duration.ofHours(2));
                        Flux.range(0, 10).log("Range 0 to 10").cache().map(cle ->
                        {

                            return "server";
                        });
                        return ser.then();
                    });
        }
    }
    @JsonGetter
    public Publisher<HttpClient> getClient() {
        return client;
    }

    @JsonSetter
    public void setClient(Publisher<HttpClient> client) {
        this.client = client;
    }

    @JsonGetter
    public Subscriber<HttpClient> getSubs() {
        return subs;
    }

    @JsonSetter
    public void setSubs(Subscriber<HttpClient> subs) {
        this.subs = subs;
    }

    @JsonGetter
    public Subscription getSubscr() {
        return subscr;
    }

    @JsonSetter
    public void setSubscr(Subscription subscr) {
        this.subscr = subscr;
    }
}