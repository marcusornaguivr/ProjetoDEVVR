package DAO;

import Model.Usuario;
import dbConnection.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    public Integer insereUsuario(Usuario usuario) {
        StringBuilder query = new StringBuilder();
        Integer retorno = 0;

        query.append("INSERT INTO usuario(nome, login, senha)VALUES ");
        query.append("('" + usuario.getNome() + "' , ");
        query.append("'" + usuario.getLogin() + "' , ");
        query.append("'" + usuario.getSenha() + "' ) RETURNING id");

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(query.toString());
            rs = st.executeQuery();
            if (rs.next()) {
                retorno = rs.getInt("id");
            }
            conn.commit();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: \n" + ex);
            }
        } finally {
            connection.closeResultset(rs);
            connection.closeStatement(st);
            conn = null;
        }
        return retorno;
    }

    public Integer atualizarUsuario(Usuario usuario) {
        Integer retorno = 0;
        StringBuilder query = new StringBuilder();

        query.append("UPDATE usuario set ");
        query.append("nome = '" + usuario.getNome() + "'");
        query.append(", login = '" + usuario.getLogin() + "'");
        query.append(",senha = '" + usuario.getSenha() + "'");
        query.append(" WHERE id = " + usuario.getCodigo() + " RETURNING id");

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(query.toString());
            rs = st.executeQuery();
            if (rs.next()) {
                retorno = rs.getInt("id");
            }
            conn.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: \n" + ex.getMessage());
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro: \n" + e);
            }
        } finally {
            connection.closeStatement(st);
            conn = null;
        }
        return retorno;
    }

    public Boolean deletaUsuario(Usuario usuario) {
        StringBuilder query = new StringBuilder();
        Boolean retorno = false;

        query.append("DELETE FROM usuario WHERE id = " + usuario.getCodigo());

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(query.toString());
            st.executeUpdate();
            conn.commit();
            retorno = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro: \n" + e);
            }
        } finally {
            connection.closeStatement(st);
            conn = null;
        }
        return retorno;
    }

    public List<Usuario> get(Usuario usuario) {
        List<Usuario> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM usuario WHERE 1 = 1 ");
        if (usuario.getCodigo() > 0) {
            sql.append(" AND id =" + usuario.getCodigo());
        }
        if (usuario.getNome() != null) {
            sql.append(" AND nome ilike '%" + usuario.getNome() + "%'");
        }
        System.out.println(sql.toString());

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(sql.toString());
            rs = st.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setCodigo(rs.getInt("id"));
                u.setLogin(rs.getString("login"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                if (u.getCodigo() > 0) {
                    lista.add(u);
                }
            }
            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            connection.closeResultset(rs);
            connection.closeStatement(st);
            conn = null;
        }
        return lista;
    }

    public Boolean validaUsuario(Usuario usuario) {
        Boolean retorno = false;
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM usuario WHERE ");
        query.append("login = '" + usuario.getLogin() + "'");
        query.append(" AND senha = '" + usuario.getSenha() + "'");

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(query.toString());
            rs = st.executeQuery();
            if (rs.next()) {
                retorno = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            connection.closeResultset(rs);
            connection.closeStatement(st);
            conn = null;
        }
        return retorno;
    }

    public Usuario buscaPorCodigo(Usuario usuario) {
        Usuario u = new Usuario();
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM usuario  ");
        if (usuario.getCodigo() > 0) {
            sql.append(" WHERE id =" + usuario.getCodigo());
        }

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(sql.toString());
            rs = st.executeQuery();

            if (rs.next()) {
                u.setCodigo(rs.getInt("id"));
                u.setLogin(rs.getString("login"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            connection.closeResultset(rs);
            connection.closeStatement(st);
            conn = null;
        }
        return u;
    }

}
