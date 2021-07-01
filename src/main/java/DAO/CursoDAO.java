package DAO;

import Model.Curso;
import dbConnection.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CursoDAO {

    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    public Integer inserir(Curso curso) {
        Integer linhasAfetadas = 0;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO curso(descricao, ementa) VALUES");
        query.append(" ('" + curso.getDescricao() + "',");
        query.append("'" + curso.getEmenta() + "');");

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(query.toString());
            linhasAfetadas = st.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
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
        return linhasAfetadas;
    }

    public Integer atualizarCurso(Curso curso) {
        Integer linhasAfetadas = 0;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE curso SET ");
        query.append("  descricao= '" + curso.getDescricao() + "'");
        query.append(" , ementa= '" + curso.getEmenta() + "' WHERE ");
        query.append("codigo =" + curso.getCodigo());

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(query.toString());
            linhasAfetadas = st.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
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
        return linhasAfetadas;
    }

    public Boolean delete(Curso curso) {
        Boolean retorno = false;

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement("DELETE FROM curso WHERE codigo = " + curso.getCodigo());
            st.executeUpdate();
            conn.commit();
            retorno = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro: \n" + e);
            }
        } finally {
            connection.closeStatement(st);
            connection.closeResultset(rs);
            conn = null;
        }
        return retorno;
    }

    public Boolean existeClasseCurso(Curso curso) {
        Boolean retorno = false;
        StringBuilder query = new StringBuilder();
        query.append("SELECT codigo FROM curso_aluno WHERE codigo_curso =" + curso.getCodigo());

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(query.toString());
            rs = st.executeQuery();
            if (rs.next()) {
                retorno = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            connection.closeResultset(rs);
            connection.closeStatement(st);
            conn = null;
        }
        return retorno;
    }

    public List<Curso> getCursos(Curso curso) {
        List<Curso> cursos = new ArrayList<>();
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM curso WHERE 1 = 1 ");
        if (curso.getCodigo() > 0) {
            sql.append("AND codigo = " + curso.getCodigo());
        }
        if (curso.getDescricao() != null) {
            sql.append(" AND descricao ilike '%" + curso.getDescricao() + "%'");
        }

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(sql.toString());
            rs = st.executeQuery();

            while (rs.next()) {
                Curso c = new Curso();
                c.setCodigo(rs.getInt("codigo"));
                c.setDescricao(rs.getString("descricao"));
                c.setEmenta(rs.getString("ementa"));
                cursos.add(c);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            connection.closeResultset(rs);
            connection.closeStatement(st);
            conn = null;
        }
        return cursos;
    }

    public Curso BuscaCursoPorCodigo(Curso curso) {
        Curso c = new Curso();
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM curso ");
        sql.append("WHERE codigo = " + curso.getCodigo());

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(sql.toString());
            rs = st.executeQuery();

            while (rs.next()) {
                c.setCodigo(rs.getInt("codigo"));
                c.setDescricao(rs.getString("descricao"));
                c.setEmenta(rs.getString("ementa"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            connection.closeResultset(rs);
            connection.closeStatement(st);
            conn = null;
        }
        return c;
    }
}
