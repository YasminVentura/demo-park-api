package com.yasmingv.demoparkapi.jwt;

import com.yasmingv.demoparkapi.entity.Usuario;
import com.yasmingv.demoparkapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.buscarPorUsername(username);
        return new JwtUserDetails(usuario);
    }

    public JwtToken getTokenAuthenticated(String username) {
        Usuario.Role role = usuarioService.buscarRoleporUsername(username);
        return JwtUtils.createToken(username, role.name().substring("ROLE_".length()));
    }

}