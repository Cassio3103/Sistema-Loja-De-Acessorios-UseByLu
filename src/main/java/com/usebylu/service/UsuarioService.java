package com.usebylu.service;

import com.usebylu.dto.UsuarioPatchDTO;
import com.usebylu.dto.UsuarioRequestDTO;
import com.usebylu.dto.UsuarioResponseDTO;
import com.usebylu.exception.NoChangeException;
import com.usebylu.exception.UsuarioDuplicadoException;
import com.usebylu.exception.UsuarioNaoEncontradoException;
import com.usebylu.model.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.usebylu.repository.UsuarioRepository;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Service
public class UsuarioService extends UsuarioLogadoService implements UserDetailsService {

    // INJEÇÃO DE DEPENDÊNCIAS -------------------------------------------------------------/

    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        super(usuarioRepository);
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // CREATE, READ, UPDATE, DELETE -------------------------------------------------------------/

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO dto){

        verificarSeExistePorEmail(dto);
        //verificarDataDeIngresso(dto);

        Usuario usuario = new Usuario();



        usuario.setNome(dto.getNome());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setCpf(dto.getCpf());
        usuario.setTelefone(dto.getTelefone());
        usuario.setEmail(dto.getEmail());
        usuario.setEndereco(dto.getEndereco());
        usuario.setDataIngresso(LocalDate.now());

        usuario = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataIngresso()
        );
    }

    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioPatchDTO dto) throws AccessDeniedException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontradp!"));
        Usuario usuarioLogado = getUsuarioLogado();
        if(!Objects.equals(usuarioLogado.getId(), usuario.getId()))
            throw new AccessDeniedException("Acesso não autorizado!");

        usuarioMudou(usuario, dto);

        if (dto.getEmail() != null &&
                !dto.getEmail().equals(usuario.getEmail())) {

            if (usuarioRepository.existsByEmail(dto.getEmail())) {
                throw new RuntimeException("Email já usado!");
            }
        }

        if (dto.getNome() != null) {
            usuario.setNome(dto.getNome());
        }

        if (dto.getSenha() != null) {
            usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        if (dto.getEmail() != null) {
            usuario.setEmail(dto.getEmail());
        }

        if (dto.getTelefone() != null) {
            usuario.setTelefone(dto.getTelefone());
        }

        if (dto.getEndereco() != null) {
            usuario.setEndereco(dto.getEndereco());
        }


        usuario = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataIngresso()
        );
    }

    public UsuarioResponseDTO buscarUsuario(Long usuario_Id) throws AccessDeniedException {

        Usuario usuario = usuarioRepository.findById(usuario_Id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado!"));

        Usuario usuarioLogado = getUsuarioLogado();
        if(!Objects.equals(usuarioLogado.getId(), usuario.getId())){
            throw new AccessDeniedException("Acesso não autorizado! O ID digitado não é o seu!");
        }

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataIngresso()
        );
    }

    public void deletarUsuario(Long usuario_id) throws AccessDeniedException {
        Usuario usuario = usuarioRepository.findById(usuario_id)
                .orElseThrow(()-> new UsuarioNaoEncontradoException("Usuário não encontrado!"));

        Usuario usuarioLogado = getUsuarioLogado();
        if(!(usuarioLogado.getId().equals(usuario.getId()))){
            throw new AccessDeniedException("Acesso não autorizado! A conta que você está tentando deletar não é sua!");
        }

        if(usuario.isDeleted()){
            throw new IllegalStateException("Usuário já inativado!");
        }

        usuario.setDeleted(true);
        usuarioRepository.save(usuario);
    }

    public void ativarUsuario(Long usuario_id) throws AccessDeniedException {
        Usuario usuario = usuarioRepository.findById(usuario_id)
                .orElseThrow(()-> new UsuarioNaoEncontradoException("Usuário não encontrado!"));

        reativarUsuarioPorId(usuario_id);
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuário Não encontrado!"));

        return usuario;
    }

    // MÉTODOS AUXILIARES -------------------------------------------------------------/

    public void usuarioMudou(Usuario usuario, UsuarioPatchDTO dto) {

        boolean houveMudanca = false;

        if (dto.getNome() != null &&
                !dto.getNome().equals(usuario.getNome())) {
            houveMudanca = true;
        }

        if (dto.getEmail() != null &&
                !dto.getEmail().equals(usuario.getEmail())) {
            houveMudanca = true;
        }

        if (dto.getTelefone() != null &&
                !dto.getTelefone().equals(usuario.getTelefone())) {
            houveMudanca = true;
        }

        if (dto.getEndereco() != null &&
                !dto.getEndereco().equals(usuario.getEndereco())) {
            houveMudanca = true;
        }

        if (dto.getSenha() != null &&
                !passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
            houveMudanca = true;
        }

        if (!houveMudanca) {
            throw new NoChangeException("Não há mudanças para se atualizar!");
        }
    }

    public void verificarSeExistePorEmail(@NonNull UsuarioRequestDTO dto){
        if(usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new UsuarioDuplicadoException("Usuário já cadastrado no sistema!");
        }
    }

//    public void verificarDataDeIngresso(@NonNull UsuarioRequestDTO dto){
//        if(dto.getDataDeIngresso() == null){
//            throw new IllegalArgumentException("Usuário com data de ingresso obrigatória!");
//        } else if(dto.getDataDeIngresso().isAfter(LocalDate.now())){
//            throw new IllegalArgumentException("Usuário com data de ingresso inválida!");
//        }
//    }

    public void reativarUsuarioPorId(Long usuario_id) throws AccessDeniedException {
        Usuario usuarioLogado = getUsuarioLogado();

        Usuario usuario = new Usuario();

        if(!(usuarioLogado.getId().equals(usuario.getId()))){
            throw new AccessDeniedException("Acesso não autorizado! A conta que você está tentando deletar não é sua!");
        }

        if(!(usuario.isDeleted())){
            throw new IllegalStateException("O usuário já está ativo!");
        }

        usuario.setDeleted(false);
        usuarioRepository.save(usuario);
    }


}
