package DAO;

import dbConnection.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class BancoDAO {

    Connection conn = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    public Boolean criaTabelaCurso() {
        Boolean retorno = false;
        StringBuilder sql = new StringBuilder();

        sql.append(" CREATE TABLE IF NOT EXISTS public.curso(");
        sql.append(" codigo SERIAL PRIMARY KEY,");
        sql.append("descricao VARCHAR(50) NOT NULL,");
        sql.append("ementa TEXT);");
        System.out.println(sql.toString());

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(sql.toString());
            st.executeUpdate();
            retorno = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar tabela: /n" + ex);
        } finally {
            connection.closeStatement(st);
            conn = null;
        }
        return retorno;
    }

    public Boolean criaTabelaUsuario() {
        Boolean retorno = false;
        StringBuilder sql = new StringBuilder();

        sql.append(" CREATE TABLE IF NOT EXISTS public.usuario(");
        sql.append(" id SERIAL PRIMARY KEY, ");
        sql.append("nome VARCHAR(50) NOT NULL, ");
        sql.append("login VARCHAR(50) NOT NULL,");
        sql.append("senha VARCHAR(50) NOT NULL);");

        sql.append("INSERT INTO public.usuario(id,nome,login,senha)");
        sql.append("VALUES(");
        sql.append("0,'administrador','admin','admin');");
        System.out.println(sql.toString());

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(sql.toString());
            st.executeUpdate();
            retorno = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar tabela: /n" + ex);
        } finally {
            connection.closeStatement(st);
            conn = null;
        }
        return retorno;
    }

    public Boolean criaTabelaAluno() {
        Boolean retorno = false;
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS public.aluno(");
        sql.append("codigo SERIAL PRIMARY KEY ,");
        sql.append("nome VARCHAR(50) NOT NULL );");
        System.out.println(sql.toString());

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(sql.toString());
            st.executeUpdate();
            retorno = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar tabela: /n" + ex);
        } finally {
            connection.closeStatement(st);
            conn = null;
        }
        return retorno;
    }

    public Boolean criaTabelaCursoAluno() {
        Boolean retorno = false;
        StringBuilder sql = new StringBuilder();

        sql.append(" CREATE TABLE IF NOT EXISTS public.curso_aluno(");
        sql.append(" codigo SERIAL PRIMARY KEY,");
        sql.append("codigo_aluno INTEGER NOT NULL,");
        sql.append("codigo_curso INTEGER NOT NULL);");

        sql.append(" ALTER TABLE public.curso_aluno ");
        sql.append("ADD FOREIGN KEY(codigo_curso) REFERENCES public.curso(codigo);");

        sql.append(" ALTER TABLE public.curso_aluno ");
        sql.append("ADD FOREIGN KEY(codigo_aluno) REFERENCES public.aluno(codigo);");
        System.out.println(sql.toString());

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(sql.toString());
            st.executeUpdate();
            retorno = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar tabela: /n" + ex);
        } finally {
            connection.closeStatement(st);
            conn = null;
        }
        return retorno;
    }

    public Boolean existeTabelaBanco(String nomeTabela) {
        StringBuilder sql = new StringBuilder();
        Boolean retorno = false;
        sql.append("SELECT tablename FROM PG_TABLES WHERE SCHEMANAME = 'public'");
        sql.append("AND TABLENAME = '" + nomeTabela + "'");

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(sql.toString());
            rs = st.executeQuery();
            if (rs.next()) {
                retorno = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro:\n" + ex.getMessage());
        }
        return retorno;
    }

    public Boolean existeBancoDados(String nomeBanco) {
        String banco = nomeBanco.toLowerCase();
        Boolean retorno = false;
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT * FROM pg_database WHERE datname = '" + banco + "'");
        System.out.println("Validacao de banco : " + sql.toString());
        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(sql.toString());
            rs = st.executeQuery();
            if (rs.next()) {
                retorno = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao validar banco " + ex.getMessage());
        }
        return retorno;
    }

    public Boolean criarBancoDados(String nomeBanco) {
        Boolean retorno = false;
        System.out.println("mandou criar banco");
        String banco = nomeBanco.toLowerCase();
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE DATABASE " + banco);
        System.out.println(sql.toString());

        try {
            conn = connection.getConnection();
            st = conn.prepareStatement(sql.toString());
            st.executeUpdate();
            retorno = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar banco" + ex.getMessage());
        }
        return retorno;
    }
}
