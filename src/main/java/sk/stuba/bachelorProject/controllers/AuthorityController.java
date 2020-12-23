package sk.stuba.bachelorProject.controllers;


import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.stuba.bachelorProject.model.Authority;
import sk.stuba.bachelorProject.model.User;
import sk.stuba.bachelorProject.services.AuthorityService;

import java.util.List;

@RestController
@RequestMapping("authority/")
public class AuthorityController {
    @Autowired
    AuthorityService authorityService;

    @PostMapping("createAuthority")
    private Authority createAuthority(@RequestBody Authority authority) {
        return authorityService.createAuthority(authority);
    }

    @DeleteMapping("deleteAuthority/{id}")
    public void deleteAuthority(@PathVariable(name = "id") String id) {
        authorityService.deleteAuthority(id);
    }

    @GetMapping("getAuthorityById/{id}")
    public Authority getAuthorityById(@PathVariable(name = "id") String id) {
        return authorityService.getAuthorityById(id);
    }

    @GetMapping("getAllAuthorities")
    public List<Authority> getAllAuthorities() {
        return authorityService.getAllAuthorities();
    }

    @PutMapping("updateAuthority/{id}")
    public Authority updateAuthority(@PathVariable(name = "id") String id, @RequestBody Authority authority) {
        return authorityService.updateAuthority(id, authority);
    }

}
