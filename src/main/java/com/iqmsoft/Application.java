package com.iqmsoft;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Profile("!test")
    @Bean
    public CommandLineRunner demo(UserRepo repo) {
        return args -> {
    
            Iterable<User> users = repo.findAll();
            if (users.iterator().hasNext()) {
                
                for (User user : users) {
                    System.out.println(user.toString());
                }
                
                return;
            }
    
            User u1 = repo.save(new User("test1", "test@demo.com", "123456"));
            u1.setRole(User.Role.ROLE_ADMIN);
            repo.save(u1);
            
            repo.save(new User("test2", "test@demo.com", "123456"));
            
            User u2 = repo.save(new User("test3", "test@demo.com", "123456"));
            u2.setEnabled(false);
            u2.setRole(User.Role.ROLE_ADMIN);
            repo.save(u2);

            repo.save(new User("test4", "test@demo.com", "123456"));
            repo.save(new User("test5", "test@demo.com", "123456"));
            repo.save(new User("test6", "test@demo.com", "123456"));
            repo.save(new User("test7", "test@demo.com", "123456"));
    
            repo.save(new User("test8", "test@demo.com", "123456"));
            repo.save(new User("test9", "test@demo.com", "123456"));
            repo.save(new User("test10", "test@demo.com", "123456"));
            repo.save(new User("test11", "test@demo.com", "123456"));
            repo.save(new User("test12", "test@demo.com", "123456"));
            repo.save(new User("test13", "test@demo.com", "123456"));
            repo.save(new User("test14", "test@demo.com", "123456"));
            repo.save(new User("test15", "test@demo.com", "123456"));
        };
    }
}
