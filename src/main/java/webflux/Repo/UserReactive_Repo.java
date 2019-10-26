package webflux.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.Domain.Role;
import webflux.Domain.User;

import java.util.Date;
import java.util.List;

@EnableReactiveMongoRepositories
public interface UserReactive_Repo extends ReactiveCrudRepository<User,String> {

    Mono<UserDetails> findByUsername(String name);
    Mono<UserDetails> findUserFirstName(String firstName);
    Mono<UserDetails> findUserLasttName(String LastName);
    Mono<UserDetails> findPassword(String password);
    Mono<UserDetails> findEmail(String email);
    Mono<UserDetails> findRoles(List<String> roles);
    Mono<UserDetails> isEnabled(boolean enabled);
    Mono<UserDetails> dateLastConfirmedPassword(Date lastConfirmedPasswordDate);
    Mono<UserDetails> dateLastSendEmail(Date lastSendEmailDate);



    Flux<User> FindByUsername(String name);
    Flux<User> FindUserFirstName(String firstName);
    Flux<User> FindUserLasttName(String LastName);
    Flux<User> FindPassword(String password);
    Flux<User> FindEmail(String email);
    Flux<User> FindRoles(List<String> roles);
    Flux<User> IsEnabled(boolean enabled);
    Flux<User> DateLastConfirmedPassword(Date lastConfirmedPasswordDate);
    Flux<User> DateLastSendEmail(Date lastSendEmailDate);


    Mono<UserDetails> addUsername(String name);
    Mono<UserDetails> addUserFirstName(String firstName);
    Mono<UserDetails> addUserLasttName(String LastName);
    Mono<UserDetails> addPassword(String password);
    Mono<UserDetails> addEmail(String email);
    Mono<UserDetails> addRoles(List<String> roles);
    Mono<UserDetails> isEnabled_(boolean enabled);
    Mono<UserDetails> adddateLastConfirmedPassword(Date lastConfirmedPasswordDate);
    Mono<UserDetails> adddateLastSendEmail(Date lastSendEmailDate);

    Flux<User> deleteUsername(String name);
    Flux<User> deleteFirstName(String firstName);
    Flux<User> deleteUserLasttName(String LastName);
    Flux<User> deletePassword(String password);
    Flux<User> deleteEmail(String email);
    Flux<User> deletedRoles(List<String> roles);
    Flux<User> disabled(boolean enabled);
    Flux<User> deleteDateLastConfirmedPassword(Date lastConfirmedPasswordDate);
    Flux<User> deleteDateLastSendEmail(Date lastSendEmailDate);


}
