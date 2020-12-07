package sk.stuba.bachelorProject.services;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sk.stuba.bachelorProject.model.Authority;
import sk.stuba.bachelorProject.model.User;
import sk.stuba.bachelorProject.repositories.AuthorityRepository;
import sk.stuba.bachelorProject.repositories.UserRepository;

import java.util.List;

@Service
public class AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserRepository userRepository;

    public Authority createAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    public void deleteAuthority(String id) {
        Authority auth = authorityRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("id", id));
        for (User user : auth.getUsers()) {
            user.getAuthorities().remove(auth);
            userRepository.save(user);
        }
        authorityRepository.deleteById(id);
    }

    public Authority getAuthorityById(String id){
        return  authorityRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("id", id));
    }

    public List<Authority> getAllAuthorities(){
        return authorityRepository.findAll();
    }

    public Authority updateAuthority(String id,Authority authority){
        Authority authorityToUpdate = authorityRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("id", id));
        authorityToUpdate.setUsers(authority.getUsers());
        authorityToUpdate.setAuthority(authority.getAuthority());
        return authorityRepository.save(authorityToUpdate);
    }
}
