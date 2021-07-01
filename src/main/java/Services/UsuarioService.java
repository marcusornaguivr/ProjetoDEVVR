/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.UsuarioDAO;
import Model.Usuario;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class UsuarioService {

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Integer InserirUsuario(Usuario usuario) {
        Integer retorno = 0;

        if (usuario.getCodigo() > 0) {
            retorno = usuarioDAO.atualizarUsuario(usuario);
        } else {
            if (usuarioDAO.validaUsuario(usuario)) {
                JOptionPane.showMessageDialog(null, "Credenciais de login ja cadastrados para outro usuario!");
            } else {
                retorno = usuarioDAO.insereUsuario(usuario);
            }
        }
        return retorno;
    }

    public Boolean excluirUsuario(Usuario usuario) {
        Boolean retorno = false;
        if (usuario.getCodigo() > 0) {
            retorno = usuarioDAO.deletaUsuario(usuario);
        }
        return retorno;
    }

    public List<Usuario> buscarUsuario(Usuario usuario) {
        return usuarioDAO.get(usuario);
    }

    public Usuario buscarUsuarioPorCodigo(Usuario usuario) {
        Usuario u = new Usuario();

        if (usuario.getCodigo() > 0) {
            u = usuarioDAO.buscaPorCodigo(usuario);
        }
        return u;
    }

    public Boolean validarLogin(Usuario usuario) {
        Boolean retorno = false;
        if (usuario.getLogin() != null && usuario.getSenha() != null) {
            retorno = usuarioDAO.validaUsuario(usuario);
        }
        return retorno;
    }
}
