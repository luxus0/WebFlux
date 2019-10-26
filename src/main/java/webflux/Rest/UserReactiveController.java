package webflux.Rest;


import net.bytebuddy.implementation.bytecode.Throw;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.handler.ExceptionHandlingWebHandler;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import webflux.Domain.Adress;
import webflux.Domain.User;
import webflux.Repo.UserReactive_Repo;
import webflux.Service.ReactiveUserDetailService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;
import java.util.function.Consumer;

@Validated
public class UserReactiveController {

    @Autowired
    private User user;
    private UserReactive_Repo repo;
    private ServerRequest request;
    private ClientHttpResponse client;
    private HttpClientErrorException error;
    private Logger log = LoggerFactory.getLogger(UserReactiveController.class);
    private @Valid ResponseEntityExceptionHandler exc;
    private @Valid org.springframework.web.reactive.function.server.ServerResponse res;
    private @Valid org.springframework.web.reactive.function.server.ServerRequest req;
    private Adress adress;
    private ReactiveUserDetailService service;

    @GetMapping("/all")
    public Flux<User> getAllUser(@RequestBody User user, Model model) {


        Flux<User> get = repo.findAll();
        get.filter(event -> {
            List<String> funct = new ArrayList<>();
            Mono.just(event.gerRoles()).log("Roles User").map(ev -> {


                funct.add("User");
                funct.add("Admin");
                funct.add("Readable");
                funct.add("Writable");


                user.setId(ev.get(Integer.parseInt(user.getId())));
                user.setRoles(funct);

                if (request.headers().contentType().filter(p -> p.includes(MediaType.APPLICATION_JSON)).isPresent()) {


                    request.attributes().put("application-json", ServerResponse.ok());
                    request.attributes().put("Not found", ServerResponse.notFound());
                    request.attributes().put("Content don't exists", ServerResponse.noContent());
                    request.attributes().put("Utf-8", StandardCharsets.UTF_8);
                    request.attribute("400").filter(p -> Boolean.parseBoolean(ResponseEntityExceptionHandler.PAGE_NOT_FOUND_LOG_CATEGORY));

                }


                return model.addAttribute("Monika","Zelmer");
            });

            return true;
        });
        return get;
    }

    @GetMapping("/get/{id}")
    public void getUserDetailsById(@PathVariable("id") String id, @RequestBody User user) throws Exception {

        this.user = user;
        URI uri = new URI("localhost:8080/User");
        RequestEntity.BodyBuilder create = null;

        ResponseEntity resp;
        Flux.just(id).filter(p -> p.length() > 0 ).map( recurs ->
                {
                    Map<ResponseEntity<String>, RequestEntity.BodyBuilder>  client = new HashMap<>();
                    try {



                       RequestEntity.BodyBuilder builder1 = client.put(ResponseEntity.ok(id) , RequestEntity.put(uri));
                        RequestEntity.BodyBuilder builder2 = client.put(ResponseEntity.of(Optional.of(id)), RequestEntity.method(HttpMethod.OPTIONS,uri));
                        RequestEntity.BodyBuilder builder3 = client.put(ResponseEntity.notFound().build(), create.contentType(null).headers((Consumer<HttpHeaders>) null));
                        RequestEntity.BodyBuilder builder4 = client.put(ResponseEntity.ok(id),create.ifModifiedSince(Instant.now()));


                        Assert.notNull(builder1,"SERVER OK 200");
                        Assert.notNull(builder2,"REST OPTIONS URI");
                        Assert.notNull(builder3,"NOT FOUND PAGE");
                        Assert.notNull(builder4,"SERVER OK - IF MODIFIED NOW");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    return user;

                }).defaultIfEmpty(new User(user.getId(),user.getUsername(),user.getFirstName(),user.getLastName(),
                user.getPassword(), user.getEmail(), user.gerRoles(),user.isEnabled(), user.getLastConfirmedPasswordDate(),
                user.getLastSendEmailDate())).flatMap(p -> {
                    if(user.getId() == null)
                    {
                        return Mono.error( new InterruptedException("no Exist User"));
                    }
                    else
                    {
                        return Mono.just(p);
                    }

                }

        ).then().onErrorResume(p -> Mono.just("redirect:/list/error=no+exist+producto").then().log("REDIRECT ERROR"));
    }

    @GetMapping("/getUsername")
public Mono<User> getUserName(String username, Model model)
    {
        return repo.findById(username).log("Username finding..").doOnNext(search ->
        {
               model.addAttribute(username,"Vicanto");
               model.addAttribute(username,"Russwelt");
               model.addAttribute(username,"Imertiante");
               model.addAttribute(username,"Fadero");
               model.addAttribute(username,"Dacento");
        }).then()
                .flatMap(map ->
                        {
                              if(username.equals("Dacento"))
                                {
                                    Assert.notNull(username,"Dacento");
                                }
                              else
                              {
                                  log.error("You don't choose this name");

                              }


                            return Mono.justOrEmpty(user);

                        }).doOnNext(next ->
                        {
                            Flux.just(repo.findAll()).filter((ev -> next.getId() == "1" && (next.getUsername() == "Macho")))
                                                    .filter((p ->  next.getId() == "2" && (next.getUsername() == "Imertiante")))
                                                    .filter(p -> next.getId() == "3" && next.getUsername() == "Dater")
                                                    .filter(fill -> next.getId() == "4" && next.getUsername() == "Mnemos")
                                                     .filter(e -> next.getId() == "5" && next.getEmail() == "abcd@o2.pl")

                                                    .log("You filter id and username")
                                                    .filterWhen(v ->
                                                    {
                                                        if(user.getId().length() > 0 && (!user.getUsername().isEmpty()))
                                                        {
                                                            log.info("ID > 0 and user is not empty");
                                                        };

                                                        return Mono.empty();
                                                    })
                                            .then()
                                                     .flatMap( now ->
                                                         {
                                                            Mono<User> userId = repo.findById(next.getId() );

                                                    return Mono.just(user.getUsername());
                                            });
                                });
            };

    @PostMapping("/addAll")
    public void addUser(@RequestBody User user, @NotNull BindingResult result, ServerResponse response)
    {
        //repo.saveAll(user);
    }



















    private ServerResponse  getResponseAuth(@Valid ResponseEntityExceptionHandler exc,@Valid ServerResponse resp,@Valid org.springframework.web.reactive.function.server.ServerRequest req)
    {
        this.res = resp;
        this.exc = exc;
        this.req = req;

        return null;
    }

    public void ClientHttpResponseOauth2()
        {

            ResponseEntity entity = new ResponseEntity(HttpStatus.BAD_REQUEST);

            if (ResponseEntity.notFound().header("Error", "Fatality", "Bad").equals(RequestEntity.EMPTY.toString())) {
                error.getLocalizedMessage();
            }
            else {

                log.error("RESPONSE IS OK 200",new ResponseStatusException(HttpStatus.OK));
            }
        }


    }
