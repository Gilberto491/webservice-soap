package br.com.caelum.estoque.ws;

import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ListaItens;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.ResponseWrapper;

@WebService
public class EstoqueWS {

    private ItemDao dao = new ItemDao();

    @WebMethod(operationName = "todosOsItens")
    @WebResult(name = "itens")
    @ResponseWrapper(localName = "itens")
    public ListaItens getItens() {
        System.out.println("Chamando getItens()");
        return new ListaItens(dao.todosItens());
    }

}
