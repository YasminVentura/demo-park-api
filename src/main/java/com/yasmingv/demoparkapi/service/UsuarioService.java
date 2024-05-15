package com.yasmingv.demoparkapi.service;

import com.yasmingv.demoparkapi.entity.Usuario;
import com.yasmingv.demoparkapi.exception.EntityNotFoundException;
import com.yasmingv.demoparkapi.exception.UsernameUniqueViolationException;
import com.yasmingv.demoparkapi.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (org.springframework.dao.DataIntegrityViolationException ex){
            throw new UsernameUniqueViolationException(String.format("Username '%s' já cadastrado", usuario.getUsername()));
        }
    }

    @Transactional
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário id %s não encontrado.", id))
        );
    }

    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmeSenha) {

        if (!novaSenha.equals(confirmeSenha)) {
            throw new RuntimeException("Nova senha não confere com confirmação de senha.");
        }

        Usuario user = buscarPorId(id);
        if (!user.getPassword().equals(senhaAtual)){
            throw new RuntimeException("Sua senha não confere.");
        }

        user.setPassword(novaSenha);
        return user;
    }

    @Transactional
    public List<Usuario> buscarTodos() {
        return  usuarioRepository.findAll();
    }
}
