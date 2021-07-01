/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DAO.CursoDAO;
import Model.Curso;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class CursoService {
    CursoDAO cursoDAO = new CursoDAO();
    
    public Integer inserirCurso(Curso curso){
        Integer retorno = 0;
        if(curso.getCodigo()> 0){
            retorno =cursoDAO.atualizarCurso(curso);
        }else{
            retorno =cursoDAO.inserir(curso);
        }
        return retorno;
    }
    
    public Boolean excluirCurso(Curso curso){
        Boolean retorno = false;
        if(!cursoDAO.existeClasseCurso(curso)){
           retorno =  cursoDAO.delete(curso);
        }else{
            JOptionPane.showMessageDialog(null, "Existe uma classe vinculada ao curso e nao pode ser excluido!");
        }
        return retorno;
    }
    
    public List<Curso> buscarCurso(Curso curso){
        return cursoDAO.getCursos(curso);
    }
    
    public Curso buscaPorCodigo(Curso curso){
        Curso c = new Curso();
        if(curso.getCodigo() > 0){
            c = cursoDAO.BuscaCursoPorCodigo(curso);
        }
        return c;
    }
}
