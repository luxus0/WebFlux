package webflux.webflux.Database;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import webflux.webflux.Entity.User;
import webflux.webflux.Repo.UserRepo;

@Service
public class ConfigDB {

    @Autowired
    private UserRepo repo;

    @EventListener(ApplicationReadyEvent.class)
    public void UserDB()
    {
        User user = new User(1,"aa","bb",34,78,34);
        User user2 = new User(1,"aa","bb",34,78,34);
        User user3 = new User(1,"aa","bb",34,78,34);
        User user4 = new User(1,"aa","bb",34,78,34);

        repo.save(user);
        repo.save(user2);
        repo.save(user3);
        repo.save(user4);

    }
}
