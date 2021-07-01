/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbConnection;

import Controller.BancoController;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class connection {

    public static Connection conn = null;
    private static StringBuilder url;
    private static Properties props = new Properties();
    private static BancoController bancoController = new BancoController();
    private static String nomeBanco = "";
    private static String caminhoBanco = "";
    private static String usuario = "";
    private static String senha = "";
    private static String portaBanco = "";

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            url = new StringBuilder();
            url.append("jdbc:postgresql://" + caminhoBanco + ":" + portaBanco + "/" + nomeBanco);
            try {
                conn = DriverManager.getConnection(url.toString(), usuario, senha);
                conn.setAutoCommit(false);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "A tentativa de Conexao Falhou!");
                conn = null;
            }
        }
        return conn;
    }

    public static boolean loadProperties() {
        boolean retorno;

        try (FileInputStream fs = new FileInputStream("c:\\configuracao\\banco.properties")) {
            props.load(fs);

            nomeBanco = props.getProperty("database.nome");
            caminhoBanco = props.getProperty("database.ip");
            usuario = props.getProperty("database.user");
            senha = props.getProperty("database.senha");
            portaBanco = props.getProperty("database.porta");
            retorno = true;
        } catch (IOException e) {
            retorno = false;
        }
        return retorno;
    }

    //validacao de arquivo
    public static boolean validaArquivo() {
        boolean retorno = false;
        File f = new File("c:\\configuracao\\banco.properties");
        if (f.exists()) {
            retorno = true;
        }
        return retorno;
    }

    //criacao de arquivo
    public static boolean criaArquivo(String ip, String bancoUser, String bancoPswd, String bancoNome, String porta) {
        String arquivo = "c:\\configuracao\\banco.properties";
        File pasta = new File("c:\\configuracao\\");
        boolean retorno = false;

        if (!pasta.exists()) {
            pasta.mkdir();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            bw.write("useSSL = false");
            bw.newLine();
            bw.write("database.ip=" + ip);
            bw.newLine();
            bw.write("database.nome=" + bancoNome);
            bw.newLine();
            bw.write("database.porta=" + porta);
            bw.newLine();
            bw.write("database.senha=" + bancoPswd);
            bw.newLine();
            bw.write("database.user=" + bancoUser);
            retorno = true;
        } catch (IOException e) {
            retorno = false;
        }
        return retorno;
    }

    public static boolean deletarProperties() {
        File pasta = new File("c:\\configuracao\\");
        File arquivo = new File("c:\\configuracao\\banco.properties");
        boolean retorno = false;

        if (pasta.exists()) {
            pasta.delete();
            retorno = true;
        }
        return retorno;
    }

    public static Boolean validaBanco() {
        Boolean retorno = false;
        url = new StringBuilder();

        url.append("jdbc:postgresql://" + caminhoBanco + ":" + portaBanco + "/");
        try {
            conn = DriverManager.getConnection(url.toString(), usuario, senha);
            retorno = bancoController.validaBancoDados(nomeBanco);
            conn = null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "A tentativa de Conexao falhou ao validar banco!!\n" + ex);
        }
        return retorno;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                throw new dbException(e.getMessage());
            }
        }
    }

    public static Boolean criaBancoDados() {
        Boolean retorno = false;
        url = new StringBuilder();
        url.append("jdbc:postgresql://" + caminhoBanco + ":" + portaBanco + "/");

        try {
            conn = DriverManager.getConnection(url.toString(), usuario, senha);
            if (bancoController.criarBancoDados(nomeBanco)) {
                url.append(nomeBanco);
                conn.close();
                conn = null;
                conn = DriverManager.getConnection(url.toString(), usuario, senha);
                bancoController.criarTabela("aluno");
                bancoController.criarTabela("curso");
                bancoController.criarTabela("cursoaluno");
                bancoController.criarTabela("usuario");
                conn.setAutoCommit(false);
                retorno = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar banco \n " + ex.getMessage());
        }
        return retorno;
    }

    public static void closeResultset(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new dbException(e.getMessage());
        }
    }

    public static void closeStatement(Statement st) {
        try {
            if (st != null) {
                st.close();
            }

        } catch (SQLException e) {
            throw new dbException(e.getMessage());
        }
    }
}
