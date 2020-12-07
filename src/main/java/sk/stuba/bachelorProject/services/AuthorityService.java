package sk.stuba.bachelorProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.bachelorProject.model.Authority;
import sk.stuba.bachelorProject.repositories.AuthorityRepository;

@Service
public class AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    public Authority createAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }
}
