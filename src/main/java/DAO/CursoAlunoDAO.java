package DAO;

import Model.Aluno;
import Model.Curso;
import Model.CursoAluno;
import dbConnection.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CursoAlunoDAO {

    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    public Integer inserir(CursoAluno cursoAluno) {
        int retorno = 0;
        StringBuilder query = new StringBuilder();

        query.append("INSERT INTO curso_aluno ");
        query.append("(codigo_aluno, codigo_curso) VALUES ");
        query.append("(" + cursoAluno.getAluno().getCodigo() + ", ");
        query.append(cursoAluno.getCurso().getCodigo() + ");");
        try {
            st = conn.prepareStatement(query.toString());
            retorno = st.executeUpdate();
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
        return retorno;
    }

    public Boolean existeCursoCadastrado(CursoAluno cursoAluno) {
        Boolean retorno = false;
        StringBuilder query = new StringBuilder();

        query.append("SELECT codigo FROM curso_aluno ");
        query.append("WHERE codigo_aluno =" + cursoAluno.getAluno().getCodigo());
        query.append(" AND codigo_curso =" + cursoAluno.getCurso().getCodigo());
        System.out.println(query.toString());

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(query.toString());
            rs = st.executeQuery();
            if(rs.next()){
              retorno = true;  
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: \n" + ex.getMessage());
        }
        return retorno;
    }

    public Integer atualizar(CursoAluno cursoAluno) {
        Integer retorno = 0;
        StringBuilder query = new StringBuilder();

        query.append("UPDATE curso_aluno SET ");
        query.append("codigo_curso = " + cursoAluno.getCurso().getCodigo() + " , ");
        query.append("codigo_aluno =" + cursoAluno.getAluno().getCodigo());
        query.append(" WHERE codigo =" + cursoAluno.getCodigo());

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(query.toString());
            retorno = st.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: \n" + ex.getMessage());
        } finally {
            connection.closeStatement(st);
            conn = null;
        }
        return retorno;
    }

    public Boolean delete(CursoAluno cursoAluno) {
        Boolean retorno = false;
        StringBuilder query = new StringBuilder();

        query.append("DELETE FROM curso_aluno WHERE codigo =" + cursoAluno.getCodigo());

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(query.toString());
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
            conn = null;
        }
        return retorno;
    }

    public List<CursoAluno> get(CursoAluno cursoAluno) {
        List<CursoAluno> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ca.*,a.nome,c.descricao FROM curso_aluno ca ");
        sql.append("INNER JOIN aluno a on a.codigo = ca.codigo_aluno ");
        sql.append("INNER JOIN curso c on c.codigo = ca.codigo_curso ");
        sql.append("WHERE 1 = 1");

        if (cursoAluno.getCodigo() > 0) {
            sql.append(" AND ca.codigo = " + cursoAluno.getCodigo());
        }

        if (cursoAluno.getAluno() != null) {
            if (cursoAluno.getAluno().getCodigo() > 0) {
                sql.append(" AND a.codigo = " + cursoAluno.getAluno().getCodigo());
            }
            if (cursoAluno.getAluno().getNome() != null) {
                sql.append(" AND a.nome ilike '%" + cursoAluno.getAluno().getNome() + "%'");
            }
        }

        if (cursoAluno.getCurso() != null) {
            if (cursoAluno.getCurso().getCodigo() > 0) {
                sql.append(" AND c.codigo = " + cursoAluno.getCurso().getCodigo());
            }
            if (cursoAluno.getCurso().getDescricao() != null) {
                sql.append(" AND c.descricao ilike '%" + cursoAluno.getCurso().getDescricao() + "%'");
            }
        }

        sql.append(" ORDER BY ca.codigo");

        try {

            conn = connection.getConnection();
            st = conn.prepareStatement(sql.toString());
            rs = st.executeQuery();

            while (rs.next()) {
                CursoAluno ca = new CursoAluno();
                Curso curso = new Curso();
                Aluno aluno = new Aluno();

                ca.setCodigo(rs.getInt("codigo"));

                curso.setCodigo(rs.getInt("codigo_curso"));
                curso.setDescricao(rs.getString("descricao"));

                aluno.setCodigo(rs.getInt("codigo_aluno"));
                aluno.setNome(rs.getString("nome"));

                ca.setAluno(aluno);
                ca.setCurso(curso);
                lista.add(ca);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            connection.closeResultset(rs);
            connection.closeStatement(st);
            conn = null;
        }
        return lista;
    }
    
    public CursoAluno buscaPorCodigo(CursoAluno cursoAluno){
        CursoAluno ca = new CursoAluno();
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ca.*,a.nome,c.descricao FROM curso_aluno ca ");
        sql.append("INNER JOIN aluno a on a.codigo = ca.codigo_aluno ");
        sql.append("INNER JOIN curso c on c.codigo = ca.codigo_curso ");
        sql.append("WHERE ca.codigo = " + cursoAluno.getCodigo());
        
        try {

            conn = connection.getConnection();
            st = conn.prepareStatement(sql.toString());
            rs = st.executeQuery();

           if(rs.next()) {
                Curso curso = new Curso();
                Aluno aluno = new Aluno();

                ca.setCodigo(rs.getInt("codigo"));

                curso.setCodigo(rs.getInt("codigo_curso"));
                curso.setDescricao(rs.getString("descricao"));

                aluno.setCodigo(rs.getInt("codigo_aluno"));
                aluno.setNome(rs.getString("nome"));

                ca.setAluno(aluno);
                ca.setCurso(curso);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            connection.closeResultset(rs);
            connection.closeStatement(st);
            conn = null;
        }
        return ca;
    }
    

}
