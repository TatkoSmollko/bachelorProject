package sk.stuba.bachelorProject.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.stuba.bachelorProject.model.Authority;
import sk.stuba.bachelorProject.services.AuthorityService;

@RestController
@RequestMapping("/authority")
public class AuthorityController {
    @Autowired
    AuthorityService authorityService;

    @PostMapping("/createAuthority")
    private Authority createAuthority(@RequestBody  Authority authority){
        return authorityService.createAuthority(authority);
    }

}
