/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.AlunoDAO;
import Model.Aluno;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class AlunoService {

    AlunoDAO alunoDAO = new AlunoDAO();

    public Integer inserirAluno(Aluno aluno) {
        Integer retorno = 0;
        if (aluno.getCodigo() > 0) {
            retorno = alunoDAO.atualizarAluno(aluno);
        } else {
            retorno = alunoDAO.inserir(aluno);
        }
        return retorno;
    }

    public Boolean excluirAluno(Aluno aluno) {
        Boolean retorno = false;
        if (alunoDAO.existeClasseAluno(aluno)) {
            JOptionPane.showMessageDialog(null, "Existe uma classe vinculada ao aluno e não pode ser excluído!");
        } else {
            retorno = alunoDAO.delete(aluno);
        }
        return retorno;
    }

    public List<Aluno> buscarAluno(Aluno aluno) {
        return alunoDAO.getAlunos(aluno);
    }
    
    public Aluno buscaPorCodigo(Aluno aluno){
        Aluno a = new Aluno();
        if(aluno.getCodigo() > 0){
            a = alunoDAO.buscaPorCodigo(aluno);
        }
        return a;
    }

}
