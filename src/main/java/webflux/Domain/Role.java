package webflux.Domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Document
public class Role  implements GrantedAuthority, AuthorityGranter {


    @Id
    private String id;


    @Override
    public String getAuthority() {
        return id;
    }

    @Override
    public Set<String> grant(Principal principal) {
        Set<String> set = new HashSet<>();
        set.add(principal.getName().replace("Principal1","get()"));
        set.add(principal.getName().replace("Principal3","set(principal)"));
        set.add(principal.getName().replace("Principal4","add(principal)"));
        set.add(principal.getName().replace("Principal1","remove(principal)"));

        set.stream().forEach(System.out::println);

        return set;
    }

    public void setAuthority(String id)
    {
        this.id = id;
    }
}
