package Controller;

import Model.Curso;
import Services.CursoService;
import java.util.List;

/**
 *
 * @author Samsung
 */
public class CursoController {
    private CursoService service = new CursoService();
    private Curso aluno = new Curso();
    
    public Integer inserir(Curso curso){
        return service.inserirCurso(curso);
    }
    
    public Boolean delete(Curso curso){
        return service.excluirCurso(curso);
    }
    public List<Curso>getCursos(Curso curso){
       return service.buscarCurso(curso);
    }
    
    public Curso buscarPorCodigo(Curso curso){
        return service.buscaPorCodigo(curso);
    }
}
