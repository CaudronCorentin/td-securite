package com.example.banque.utilisateur;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurDetailsService implements UserDetailsService{

    UtilisateurRepository userRepository;
    public UtilisateurDetailsService(UtilisateurRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        Utilisateur utilisateur = userRepository.findByLogin(username)
                .orElseThrow(()  ->
                        new UsernameNotFoundException(username));
        return User.withUsername(utilisateur.getLogin())
                .password(utilisateur.getMotDePasse())
                .roles(utilisateur.getRole().toString())
                .build();

    }


}
