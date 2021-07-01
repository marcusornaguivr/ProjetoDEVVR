
package Controller;

import Services.BancoService;

/**
 *
 * @author gabri
 */
public class BancoController {
    BancoService service = new BancoService();
    
    public Boolean criarTabela(String nomeTabela){
        return service.criarTabela(nomeTabela);
    }
    
    public Boolean validaBancoDados(String nomeBanco){
        return service.validaBancoDados(nomeBanco);
    }
    
    public Boolean criarBancoDados(String nomeBanco){
        return service.criarBancoDados(nomeBanco);
    }
}
