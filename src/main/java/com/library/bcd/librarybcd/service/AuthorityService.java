package com.library.bcd.librarybcd.service;

import com.library.bcd.librarybcd.entity.Authority;
import com.library.bcd.librarybcd.exception.UserNotFoundException;
import com.library.bcd.librarybcd.repository.AuthorityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorityService {
    private String ROLE_USER = "ROLE_USER";

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public void grantRoleToUser(String login) {
        Authority authority = new Authority();
        authority.setId(0);
        authority.setAuthority(ROLE_USER);
        authority.setLogin(login);
        authorityRepository.save(authority);
    }

    public void revokeRoleFromUser(String login) throws UserNotFoundException {
        Authority authority = authorityRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException(login));
        authorityRepository.delete(authority);
    }


}
