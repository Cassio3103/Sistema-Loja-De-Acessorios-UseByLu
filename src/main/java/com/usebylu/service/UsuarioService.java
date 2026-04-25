package com.usebylu.service;

import com.usebylu.dto.UsuarioRequestDTO;
import com.usebylu.dto.UsuarioResponseDTO;
import com.usebylu.exception.UsuarioDuplicadoException;
import com.usebylu.model.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.usebylu.repository.UsuarioRepository;

import java.time.LocalDate;

@Getter
@Setter
@Service
public class UsuarioService extends UsuarioLogadoService implements UserDetailsService {

    // INJEÇÃO DE DEPENDÊNCIAS -------------------------------------------------------------/

    private Usuario usuario;
    private UsuarioRepository usuarioRepository;

    public UsuarioService(Usuario usuario, UsuarioRepository usuarioRepository){
        super(usuarioRepository);
        this.usuario = usuario;
    }

    // CREATE, READ, UPDATE, DELETE -------------------------------------------------------------/

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO dto){

        verificarSeExisteUsuarioPorEmail(dto);
        verificarDataDeIngresso(dto);

        Usuario usuario = new Usuario();

        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setSenha(dto.getSenha());
        usuario.setCpfUsuario(dto.getCpfUsuario());
        usuario.setTelefoneUsuario(dto.getTelefoneUsuario());
        usuario.setEmailUsuario(dto.getEmailUsuario());
        usuario.setEnderecoUsuario(dto.getEnderecoUsuario());
        usuario.setDataIngresso(dto.getDataDeIngresso());

        usuario = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(
                usuario.getUsuario_id(),
                usuario.getNomeUsuario(),
                usuario.getEmailUsuario(),
                usuario.getDataIngresso()
        );
    }

    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO dto){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontradp!"));
        Usuario usuarioLogado = getUsuarioLogado();

        //return
    }

    // MÉTODOS AUXILIARES -------------------------------------------------------------/

    public void verificarSeExisteUsuarioPorEmail(UsuarioRequestDTO dto){
        if(usuarioRepository.existsByemailUsuario(dto.getEmailUsuario())) {
            throw new UsuarioDuplicadoException("Usuário já cadastrado no sistema!");
        }
    }

    public void verificarDataDeIngresso(UsuarioRequestDTO dto){
        if(dto.getDataDeIngresso() == null){
            throw new IllegalArgumentException("Usuário com data de ingresso obrigatória!");
        } else if(dto.getDataDeIngresso().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Usuário com data de ingresso inválida!");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
