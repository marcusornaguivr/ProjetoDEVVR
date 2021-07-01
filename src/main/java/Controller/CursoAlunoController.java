package Controller;

import Model.CursoAluno;
import Services.CursoAlunoService;
import java.util.List;

public class CursoAlunoController {

    CursoAlunoService service = new CursoAlunoService();

    public Integer inserir(CursoAluno cursoAluno) {
        return service.Inserir(cursoAluno);
    }

    public List<CursoAluno> get(CursoAluno cursoAluno) {
        return service.buscarCursoAluno(cursoAluno);
    }

    public Boolean delete(CursoAluno cursoAluno) {
        return service.excluirCursoAluno(cursoAluno);
    }
    
    public CursoAluno buscaPorCodigo(CursoAluno cursoAluno){
        return service.buscaPorCodigo(cursoAluno);
    }
}
