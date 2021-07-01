package Controller;

import Model.Usuario;
import Services.UsuarioService;
import java.util.List;

/**
 *
 * @author Samsung
 */
public class UsuarioController {

    UsuarioService usuarioService = new UsuarioService();

    public Integer salvaUsuario(Usuario usuario) {
        return usuarioService.InserirUsuario(usuario);
    }

    public Boolean deletaUsuario(Usuario usuario) {
        return usuarioService.excluirUsuario(usuario);
    }

    public List<Usuario> get(Usuario usuario) {
        return usuarioService.buscarUsuario(usuario);
    }

    public Boolean validaUsuario(Usuario usuario) {
        return usuarioService.validarLogin(usuario);
    }

    public Usuario buscarUsuarioPorCodigo(Usuario usuario) {
        return usuarioService.buscarUsuarioPorCodigo(usuario);
    }

}
