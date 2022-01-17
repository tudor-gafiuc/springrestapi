package com.tudor.springrestapi;


import com.tudor.springrestapi.entities.Greeting;
import com.tudor.springrestapi.entities.User;
import com.tudor.springrestapi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name){
        return new Greeting(counter.incrementAndGet(),String.format(template,name));
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepo.findAll();
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody User user){
        userRepo.save(user);
        return("Saved :P");
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user){
        User updatedUser = userRepo.findById(id).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setAge(user.getAge());
        userRepo.save(updatedUser);
        return("Updated :)");
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id){
        userRepo.deleteById(id);
        return("Bye bye");
    }

    @GetMapping("/error")
    public String errorPage(){
        return "Hello, I'm up but can't render anything right now.";
    }


}
