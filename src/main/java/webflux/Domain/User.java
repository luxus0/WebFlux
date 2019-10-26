package webflux.Domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User  implements UserDetails {


    @JsonSerialize(using = ToStringSerializer.class)
    private String id;
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
    private List<String> roles;
    private final Date lastConfirmedPasswordDate;
    private final Date lastSendEmailDate;


    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public User(String id, String userName, String firstName, String lastName, String password, String email, List<String> roles, boolean enabled, Date lastConfirmedPasswordDate, Date lastSendEmailDate) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.lastConfirmedPasswordDate = lastConfirmedPasswordDate;
        this.lastSendEmailDate = lastSendEmailDate;


    }


    public String getId() {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public List<String> gerRoles()
    {
        return roles;
    }

    public void setRoles(List<String> roles)
    {
        this.roles = roles;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Date getLastConfirmedPasswordDate() {
        return lastConfirmedPasswordDate;
    }

    public Date getLastSendEmailDate() {
        return lastSendEmailDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorites = new ArrayList<>(this.roles.size());
        for(String role : this.roles) {
            authorites.add(new SimpleGrantedAuthority(role));
        }

        return authorites;
    }



    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
