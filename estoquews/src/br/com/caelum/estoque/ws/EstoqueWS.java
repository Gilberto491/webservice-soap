package br.com.caelum.estoque.ws;

import br.com.caelum.estoque.modelo.item.*;
import br.com.caelum.estoque.modelo.usuario.AutorizacaoException;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

@WebService
public class EstoqueWS {

    private ItemDao dao = new ItemDao();

    @WebMethod(operationName = "TodosOsItens")
    @WebResult(name = "itens")
    @ResponseWrapper(localName = "itens")
    public ListaItens getItens(@WebParam(name = "filtros") Filtros filtros) {
        System.out.println("Chamando getItens()");

        List<Filtro> listaFiltros = filtros.getLista();
        List<Item> lista = dao.todosItens(listaFiltros);

        return new ListaItens(lista);
    }

    @WebMethod(operationName = "CadastrarItens")
    @WebResult(name = "item")
    public Item cadastrarItem(@WebParam(name = "tokenUsuario", header = true)
                                      TokenUsuario token, @WebParam(name = "item") Item item)
            throws AutorizacaoException {

        System.out.println("cadastrando item " + item + ", Token: " + token);

        boolean valido = new TokenDao().ehValido(token);
        if (!valido) {
            throw new AutorizacaoException("Token inválido");
        }

        //novo
        new ItemValidador(item).validate();

        this.dao.cadastrar(item);
        return item;
    }

}
