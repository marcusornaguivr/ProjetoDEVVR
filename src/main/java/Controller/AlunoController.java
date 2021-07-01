package Controller;

import Model.Aluno;
import Services.AlunoService;
import java.util.List;

/**
 *
 * @author Samsung
 */
public class AlunoController {
    private final AlunoService service = new AlunoService();
    
    public Integer inserir(Aluno aluno){
        return service.inserirAluno(aluno);
    }
    
    public boolean delete(Aluno aluno){
         return service.excluirAluno(aluno);
    }
    public List<Aluno>getAlunos(Aluno aluno){
       return service.buscarAluno(aluno);
    } 
    
    public Aluno buscarPorCodigo(Aluno aluno){
        return service.buscaPorCodigo(aluno);
    }
    
}
