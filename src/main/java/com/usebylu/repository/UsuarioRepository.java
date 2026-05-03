package com.usebylu.repository;

import com.usebylu.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String login);

    @Override
    boolean existsById(Long id);

    boolean existsByEmail(String email);

    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByEmail(String email);

}
