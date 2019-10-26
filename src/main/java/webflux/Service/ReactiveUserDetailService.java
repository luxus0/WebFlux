package webflux.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.Domain.User;
import webflux.Repo.UserReactive_Repo;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public abstract class ReactiveUserDetailService implements UserReactive_Repo {


    private UserReactive_Repo repo;
    private UserDetails userDetails;
    private Logger log = LoggerFactory.getLogger(UserDetails.class);
    private Logger log2 = LoggerFactory.getLogger(User.class);
    private User user;

    @Override
    public Mono<UserDetails> findByUsername(String name) {

        Mono<UserDetails> user = repo.findByUsername(name);
        return user;
    }

    @Override
    public Mono<UserDetails> findUserFirstName(String firstName) {
        Mono<UserDetails>  first = repo.findUserFirstName(firstName).cache(Duration.ofSeconds(30));
        return first;
    }

    @Override
    public Mono<UserDetails> findUserLasttName(String LastName) {



        Mono<UserDetails> last = repo.findUserLasttName(LastName);
        last.flatMap(p -> {
                    if (Mono.just(LastName).equals(Mono.empty())) {
                        log.error("EMPTY LastName");
                    } else {
                        try {
                            HttpStatus status = ServerResponse.created(new URL("/api/client").toURI())
                                    .body(LastName).statusCode();
                        } catch (URISyntaxException | MalformedURLException e) {
                            e.printStackTrace();
                        }

                        if(ServerResponse.badRequest().equals("400"))
                        {
                                log.error("BAD REQUEST");
                        }
                        else
                        {
                            ServerResponse.ok().body(last);
                        }

                    }

            return Mono.empty();
                }
        );


        return last;
    }

    @Override
    public Mono<UserDetails> findPassword(String password) {
        Mono<UserDetails> passw = repo.findPassword(password);
        return passw;
    }

    @Override
    public Mono<UserDetails> findEmail(String email) {
        Mono<UserDetails> emails = repo.findEmail(email);
        return emails;
    }

    @Override
    public Mono<UserDetails> findRoles(List<String> roles) {
        List<String> role = new ArrayList<>();
        role.add("USER");
        roles.add("ADMIN");
        roles.add("ALL");
        roles.add("READ");
        roles.add("WRITE");


           role.stream().forEach(b -> System.out.println(role.get(0)));
        role.stream().forEach(b -> System.out.println(role.get(1)));
        role.stream().forEach(b -> System.out.println(role.get(2)));
        role.stream().forEach(b -> System.out.println(role.get(3)));
        role.stream().forEach(b -> System.out.println(role.get(4)));

        return repo.findRoles(role);
    }

    @Override
    public Mono<UserDetails> isEnabled(boolean enabled) {
        Mono<UserDetails> enable =  repo.isEnabled(true);

        if(ServerResponse.accepted().body(true).headers().containsKey(HttpHeaders.CONTENT_TYPE))
        {
            log.info("SERVER ACCEPTED--ENABLE PORT");
        }
        return enable;
    }

    @Override
    public Mono<UserDetails> dateLastConfirmedPassword(Date lastConfirmedPasswordDate) {
        Mono<UserDetails> confirmPassw = repo.dateLastConfirmedPassword(lastConfirmedPasswordDate);
        return confirmPassw;
    }

    @Override
    public Mono<UserDetails> dateLastSendEmail(Date lastSendEmailDate) {
        Mono<UserDetails> sendEmail = repo.dateLastSendEmail(lastSendEmailDate);
        return sendEmail;
    }

    @Override
    public Flux<User> FindByUsername(String name) {
        Flux<User> findUser = repo.FindByUsername(name);
                findUser.cache(23,Duration.ofMinutes(1))
                .checkpoint("Description_Checkpoint")
                .filterWhen(p ->{

                    if(ResponseEntity.accepted().body(repo.findByUsername(name)).equals(
                            ResponseEntity.accepted().body(repo.FindByUsername(name))))
                    {
                        Mono<Long> countName  = Flux.just(findByUsername(name)).count();
                        Mono<Long> countName2 = Flux.just(FindByUsername(name)).count();

                        countName.log("Count username1: ");
                        countName2.log("Count username2: ");

                    }
                    return null;
                });
        return findUser;
    }

    @Override
    public Flux<User> FindUserFirstName(String firstName) {
        return repo.FindUserFirstName(firstName);
    }

    @Override
    public Flux<User> FindUserLasttName(String LastName) {
        return repo.FindUserLasttName(LastName);
    }

    @Override
    public Flux<User> FindPassword(String password) {
        return repo.FindPassword(password).filter(p -> {
                if(p.getPassword().equals("viewsonic1986@#$"))
        {
            ServerResponse.accepted().body(password);
            ServerResponse.ok().body(password);
            ServerResponse.BodyBuilder server = ServerResponse.status(HttpStatus.OK);
            server.build();
        }
                else {
                    ServerResponse.notFound().build();
                }
            return false;
        });
    }
    @Override
    public Flux<User> FindEmail(String email) {
        return repo.FindEmail(email);
    }

    @Override
    public Flux<User> FindRoles(List<String> roles) {
        return repo.FindRoles(roles);
    }

    @Override
    public Flux<User> IsEnabled(boolean enabled) {
        log2.info("DISABLE REACTIVE MODE");
        return repo.IsEnabled(false);
    }

    @Override
    public Flux<User> DateLastConfirmedPassword(Date lastConfirmedPasswordDate) {
        return repo.DateLastConfirmedPassword(lastConfirmedPasswordDate);
    }

    @Override
    public Flux<User> DateLastSendEmail(Date lastSendEmailDate) {
        return repo.DateLastSendEmail(lastSendEmailDate);
    }

    @Override
    public Mono<UserDetails> addUsername(String name) {
        Mono<UserDetails> addUser = repo.addUsername(name).flatMap(n -> {
            if (name.isEmpty()) {
                ResponseEntity.BodyBuilder req = ResponseEntity.badRequest();
                req.build();
            }
            else {
                ResponseEntity.ok();
            }

            return null;
        });

        return addUser;
    }
    @Override
    public Mono<UserDetails> addUserFirstName(String firstName) {
       Mono<UserDetails> detail =  repo.addUserFirstName(firstName);
       detail.log("ADD First Name").flatMap(event ->
        {
            if(Mono.just(firstName).equals(detail))
            {
                log.error("Empty firstName");
            }
            else
            {
                ServerResponse.accepted().build();
            }
            return Mono.empty();
        });

        return detail;
    }

    @Override
    public Mono<UserDetails> addUserLasttName(String LastName) {
        return repo.addUserLasttName(LastName);
    }

    @Override
    public Mono<UserDetails> addPassword(String password) {
        Mono<UserDetails> passw = repo.addPassword(password).log("ADD PASSWORD").map(iter -> {

            if(password.isEmpty())
            {
                log.error(ResponseEntity.EMPTY.toString());
            }
            else {
                ResponseEntity.ok(password);
            }


            return iter;
        });

        return passw;
    }

    @Override
    public Mono<UserDetails> addEmail(String email) {
        return repo.addEmail(email);
    }

    @Override
    public Mono<UserDetails> addRoles(List<String> roles) {
        return repo.addRoles(roles);
    }

    @Override
    public Mono<UserDetails> isEnabled_(boolean enabled) {
        return repo.isEnabled_(true);
    }



    @Override
    public Flux<User> deleteUsername(String name) {
        return repo.deleteUsername(name);
    }

    @Override
    public Flux<User> deleteFirstName(String firstName) {
        return repo.deleteFirstName(firstName);
    }

    @Override
    public Flux<User> deleteUserLasttName(String LastName) {
        return repo.deleteUserLasttName(LastName);
    }

    @Override
    public Flux<User> deletePassword(String password) {
        return repo.deletePassword(password);
    }

    @Override
    public Flux<User> deleteEmail(String email) {
        return repo.deleteEmail(email);
    }

    @Override
    public Flux<User> deletedRoles(List<String> roles) {
        return repo.deletedRoles(roles);
    }

    @Override
    public Flux<User> disabled(boolean enabled) {
        return repo.disabled(true);
    }

    @Override
    public Flux<User> deleteDateLastConfirmedPassword(Date lastConfirmedPasswordDate) {
        return repo.deleteDateLastConfirmedPassword(lastConfirmedPasswordDate);
    }

    @Override
    public Flux<User> deleteDateLastSendEmail(Date lastSendEmailDate) {
        return repo.deleteDateLastSendEmail(lastSendEmailDate);
    }
}