package br.upf.protegemed.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.Equipamento;
import br.upf.protegemed.beans.Eventos;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.beans.Modelo;
import br.upf.protegemed.beans.UsoSalaEquip;
import br.upf.protegemed.dao.ProtegemedDAO;

@Path("/query")
public class Query {

	@GET
	@Path("get/list-useroomequip")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UsoSalaEquip> getUseRoomEquip(){
		ProtegemedDAO dao = new ProtegemedDAO();
		List<UsoSalaEquip> list = new ArrayList<>();
		list = dao.queryUseRoomEquip();
		return list;
	}
	
	@GET
	@Path("get/list-events")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Eventos> getEvents(){
		ProtegemedDAO dao = new ProtegemedDAO();
		List<Eventos> list = new ArrayList<>();
		list = dao.queryEvents();
		return list;
	}
	
	@GET
	@Path("get/list-models")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Modelo> getModels(){
		ProtegemedDAO dao = new ProtegemedDAO();
		List<Modelo> list = new ArrayList<>();
		list = dao.queryModels();
		return list;
	}
	
	@GET
	@Path("get/list-harmonic-current")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HarmAtual> getHarmCurrent(){
		ProtegemedDAO dao = new ProtegemedDAO();
		List<HarmAtual> list = new ArrayList<>();
		list = dao.queryHarmCurrent();
		return list;
	}

	@GET
	@Path("get/list-capture-current")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CapturaAtual> getCaptureCurrent(){
		ProtegemedDAO dao = new ProtegemedDAO();
		List<CapturaAtual> list = new ArrayList<>();
		list = dao.queryCaptureCurrent();
		return list;
	}
	
	@GET
	@Path("get/list-equipaments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Equipamento> getEquipaments(){
		ProtegemedDAO dao = new ProtegemedDAO();
		List<Equipamento> list = new ArrayList<>();
		list = dao.queryEquipament();
		return list;
	}
}
