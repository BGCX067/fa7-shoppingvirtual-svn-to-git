/*
 * Created on 26/08/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.seteshop.negocio;

import br.com.seteshop.negocio.modelo.Cliente;
import br.com.seteshop.negocio.modelo.Lojista;
import br.com.seteshop.negocio.persistencia.LojistaDAO;

public class LojistaFachada {

	private LojistaDAO lojistaDAO;	

	public LojistaFachada() {
		// TODO Auto-generated constructor stub
		lojistaDAO = new LojistaDAO();		
	}
	
	public Lojista recuperarLojista(Long id) {
		return lojistaDAO.findById(id);
	}

	public void salvar(Lojista lojista) {
		if (lojista.getId() != null) {
			lojistaDAO.update(lojista);
		} else {
			lojistaDAO.save(lojista);
		}
	}
	
	public Lojista recuperarLojistaPorLogin(String login) {
		return lojistaDAO.recuperaPorLogin(login);
	}
	
	public Lojista recuperarLojistaPorEmailESenha(String email, String senha) {
		return lojistaDAO.recuperaPorLoginESenha(email, senha);
	}
}
