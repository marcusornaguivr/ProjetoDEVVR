package Model;

public class CursoAluno {
    private int codigo;
    private Aluno aluno;
    private Curso curso;
   
    

    public CursoAluno() {
    }

    public CursoAluno(int codigo, Aluno aluno, Curso curso) {
        this.codigo = codigo;
        this.aluno = aluno;
        this.curso = curso;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "CursoAluno{" + "codigo=" + codigo + ", aluno=" + aluno + ", curso=" + curso + '}';
    }
    
    
    
    
    
    
    
}
