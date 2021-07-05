package DAO;

import Model.Aluno;
import dbConnection.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AlunoDAO {

    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    public Integer inserir(Aluno aluno) {
        Integer linhasAfetadas = 0;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO aluno(nome) VALUES ('" + aluno.getNome() + "' ) RETURNING codigo");

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(query.toString());
            rs = st.executeQuery();
            if(rs.next()){
                linhasAfetadas = rs.getInt("codigo");
            }
            conn.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro: \n" + e.getMessage());
            }
        } finally {
            connection.closeResultset(rs);
            connection.closeStatement(st);
            conn = null;
        }
        return linhasAfetadas;
    }

    public Integer atualizarAluno(Aluno aluno) {
        Integer linhasAfetadas = 0;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE aluno SET nome ='" + aluno.getNome() + "' ");
        query.append("WHERE codigo =" + aluno.getCodigo());

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(query.toString());
            linhasAfetadas = st.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro: \n" + e.getMessage());
            }
        } finally {
            connection.closeStatement(st);
            conn = null;
        }
        return linhasAfetadas;
    }

    public Boolean delete(Aluno aluno) {
        Boolean situacao = false;

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement("DELETE FROM aluno WHERE codigo = " + aluno.getCodigo());
            st.executeUpdate();
            conn.commit();
            situacao = true;
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
            connection.closeResultset(rs);
            connection.closeStatement(st);
            conn = null;
        }
        return situacao;
    }

    public Boolean existeClasseAluno(Aluno aluno) {
        Boolean retorno = false;
        StringBuilder query = new StringBuilder();
        query.append("SELECT codigo FROM curso_aluno WHERE codigo_aluno = " + aluno.getCodigo());
        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(query.toString());
            rs = st.executeQuery();
            if (rs.next()) {
                retorno = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            connection.closeResultset(rs);
            connection.closeStatement(st);
            conn = null;
        }
        return retorno;
    }

    public List<Aluno> getAlunos(Aluno aluno) {
        List<Aluno> alunos = new ArrayList<>();
        StringBuilder query = new StringBuilder();

        query.append("SELECT * FROM aluno WHERE 1=1");

        if (aluno.getCodigo() > 0) {
            query.append(" AND codigo = " + aluno.getCodigo());
        }
        if (aluno.getNome() != null) {
            query.append(" AND nome ilike '%" + aluno.getNome() + "%'");
        }

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(query.toString());
            rs = st.executeQuery();

            while (rs.next()) {
                Aluno a = new Aluno();
                a.setCodigo(rs.getInt("codigo"));
                a.setNome(rs.getString("nome"));
                alunos.add(a);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            connection.closeResultset(rs);
            connection.closeStatement(st);
            conn = null;
        }
        return alunos;
    }

    public Aluno buscaPorCodigo(Aluno aluno) {
        Aluno a = new Aluno();
        StringBuilder query = new StringBuilder();

        query.append("SELECT * FROM aluno ");
        query.append("WHERE codigo = " + aluno.getCodigo());

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(query.toString());
            rs = st.executeQuery();

            if (rs.next()) {
                a.setCodigo(rs.getInt("codigo"));
                a.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            connection.closeResultset(rs);
            connection.closeStatement(st);
            conn = null;
        }
        return a;
    }

}
