package Services;

import DAO.BancoDAO;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class BancoService {

    BancoDAO bancoDAO = new BancoDAO();

    public Boolean criarTabela(String nomeTabela) {
        Boolean retorno = false;
        String nome = nomeTabela.toUpperCase();

        if (!bancoDAO.existeTabelaBanco(nome)) {
            switch (nome) {
                case "ALUNO":
                    retorno = bancoDAO.criaTabelaAluno();
                    break;

                case "CURSO":
                    retorno = bancoDAO.criaTabelaCurso();
                    break;

                case "USUARIO":
                    retorno = bancoDAO.criaTabelaUsuario();
                    break;
                    
                case "CURSOALUNO":
                    retorno = bancoDAO.criaTabelaCursoAluno();
                break;
                
                default:
                    JOptionPane.showMessageDialog(null, "Nome de tabela desconhecido!!");
            }
        }
        return retorno;
    }
    
    public Boolean validaBancoDados(String nome){
        return bancoDAO.existeBancoDados(nome);
    }
    
    public Boolean criarBancoDados(String nomeBanco){
        return bancoDAO.criarBancoDados(nomeBanco);
    }
}
