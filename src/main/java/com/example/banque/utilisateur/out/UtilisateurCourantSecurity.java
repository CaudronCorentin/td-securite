package com.example.banque.utilisateur.out;

import com.example.banque.utilisateur.Utilisateur;
import com.example.banque.utilisateur.UtilisateurRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UtilisateurCourantSecurity implements UtilisateurCourant {

    public UtilisateurRepository user;

    public UtilisateurCourantSecurity(UtilisateurRepository user) {
        this.user = user;
    }

    @Override
    public Optional<Utilisateur> get(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Utilisateur) {
            return Optional.of((Utilisateur) authentication.getPrincipal());
        }
        return user.findByLogin(authentication.getName());
    }
}
