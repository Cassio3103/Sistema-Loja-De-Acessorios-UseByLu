package com.usebylu.repository;

import com.usebylu.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Override
    boolean existsById(Long id);

    boolean existsByemailUsuario(String emailUsuario);

    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByemailUsuario(String emailUsuario);

}
