/*
 * Created on 06/12/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.anuncia.controle;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.anuncia.negocio.AnuncianteFachada;
import br.com.anuncia.negocio.modelo.Anunciante;
import br.com.anuncia.negocio.modelo.Evento;

public class AcompanharPagamentoBean {
	// Objetos utilizados pela página
	private List<Evento> eventos;
	ManterAnuncianteBean manterAnuncianteBean;
	
	// Objetos para acesso ao negócio
	private AnuncianteFachada anuncianteFachada;
	
	public AcompanharPagamentoBean() {
		// TODO Auto-generated constructor stub
		anuncianteFachada = new AnuncianteFachada();
	}
	
	@PostConstruct
	public void init() {
		
		if (anuncianteFachada == null) {
			anuncianteFachada = new AnuncianteFachada();
		}
	}
			
	/**
	 * @return Returns the eventos.
	 */
	public List<Evento> getEventos() {
		return eventos;
	}

	/**
	 * @param secoes The secoes to set.
	 */
	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	
	public void pegarAnuncianteSecao() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		manterAnuncianteBean = (ManterAnuncianteBean) session.getAttribute("manterAnuncianteBean");
	}
	
	public String acompanharPagamentos() {
		//pegarAnuncianteSecao();
		eventos = anuncianteFachada.listarTodosEventos();
		return "acompanhar_pagamentos";
	}
	
}
