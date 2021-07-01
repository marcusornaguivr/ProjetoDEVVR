package Aplicacao;

import GUI.DadosBanco;
import GUI.Login;
import dbConnection.connection;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String args[]) {
        //carrega tema
        try {
            System.out.println(System.getProperties());
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            JOptionPane.showConfirmDialog(null, "Erro", "Erro ao Carregar Tema",
                    JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
        }
        //Valida se existe o properties
        if (connection.validaArquivo()) {
            //se o arquivo existe carrega o properties e chama a tela de login
            if (connection.loadProperties()) {
                //se conseguir ler o arquivo valida o banco
                if (connection.validaBanco()) {
                    Login frmLogin = new Login();
                    frmLogin.setVisible(true);
                } else {
                    int escolha = JOptionPane.showConfirmDialog(null, "Banco de dados "
                            + "nao enconrado, deseja Criar?",
                            "Atencao!!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (escolha == 0) {
                        if (connection.criaBancoDados()) {
                            Login login = new Login();
                            login.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nao foi possivel conectar com o banco!");
                        System.exit(0);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Erro Ao Carregar Arquivo Properties!");
            }
            //se o arquivo não existir 
        } else {
            DadosBanco frmDadosBanco = new DadosBanco();
            frmDadosBanco.setVisible(true);
        }
    }
}
