package Services;

import DAO.CursoAlunoDAO;
import Model.CursoAluno;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class CursoAlunoService {

    CursoAlunoDAO cursoAlunoDAO = new CursoAlunoDAO();

    public Integer Inserir(CursoAluno cursoAluno) {
        Integer retorno = 0;
        if (cursoAluno.getCodigo() > 0) {
            if (!cursoAlunoDAO.existeCursoCadastrado(cursoAluno)) {
                retorno = cursoAlunoDAO.atualizar(cursoAluno);
            }else{
                JOptionPane.showMessageDialog(null, "Curso ja cadastrado "
                        + "para o aluno " + cursoAluno.getAluno().getCodigo() + " - " + cursoAluno.getAluno().getNome());
            }
        } else {
            if (!cursoAlunoDAO.existeCursoCadastrado(cursoAluno)) {
                retorno = cursoAlunoDAO.inserir(cursoAluno);
            } else {
                JOptionPane.showMessageDialog(null, "Curso ja cadastrado "
                        + "para o aluno " + cursoAluno.getAluno().getCodigo() + " - " + cursoAluno.getAluno().getNome());
            }
        }
        return retorno;
    }

    public Boolean excluirCursoAluno(CursoAluno cursoAluno) {
        return cursoAlunoDAO.delete(cursoAluno);
    }

    public List<CursoAluno> buscarCursoAluno(CursoAluno cursoAluno) {
        return cursoAlunoDAO.get(cursoAluno);
    }

    public CursoAluno buscaPorCodigo(CursoAluno cursoAluno) {
        CursoAluno ca = new CursoAluno();
        if (cursoAluno.getCodigo() > 0) {
            ca = cursoAlunoDAO.buscaPorCodigo(cursoAluno);
        }
        return ca;
    }
}
