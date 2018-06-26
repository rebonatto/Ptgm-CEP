package br.upf.protegemed.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.Equipamento;
import br.upf.protegemed.beans.Eventos;
import br.upf.protegemed.beans.TipoOnda;
import br.upf.protegemed.beans.Tomada;
import br.upf.protegemed.exceptions.ProtegeClassException;
import br.upf.protegemed.exceptions.ProtegeDAOException;
import br.upf.protegemed.exceptions.ProtegeIllegalAccessException;
import br.upf.protegemed.exceptions.ProtegeInstanciaException;
import br.upf.protegemed.jdbc.ConnectionFactory;
import br.upf.protegemed.utils.Utils;

public class CapturaAtualDAO {

	public List<CapturaAtual> queryCaptureCurrent() throws ProtegeDAOException, ProtegeInstanciaException, ProtegeIllegalAccessException, ProtegeClassException{
		CapturaAtual capturaAtual;
		Tomada tomada;
		TipoOnda tipoOnda;
		Equipamento equipamento;
		Eventos eventos;
		
		List<CapturaAtual> listCapturaAtual = new ArrayList<>();
		
		PreparedStatement stmt;
		ResultSet resultSet;
		
		try {
			stmt = new ConnectionFactory().getConnection().prepareStatement(Utils.QUERY_CAPTURA_ATUAL);
			resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				
				capturaAtual = new CapturaAtual();
				tomada = new Tomada();
				tipoOnda = new TipoOnda();
				equipamento = new Equipamento();
				eventos = new Eventos();
				
				capturaAtual.setCodCaptura(resultSet.getInt(1));
				tomada.setCodTomada(resultSet.getInt(2));
				tipoOnda.setCodTipoOnda(resultSet.getInt(3));
				equipamento.setCodEquip(resultSet.getInt(4));
				eventos.setCodEvento(resultSet.getInt(5));
				
				capturaAtual.setTomada(tomada);
				capturaAtual.setTipoOnda(tipoOnda);
				capturaAtual.setEquipamento(equipamento);
				capturaAtual.setEventos(eventos);
				capturaAtual.setMv(resultSet.getFloat(6));
				capturaAtual.setOffset(resultSet.getFloat(7));
				capturaAtual.setGain(resultSet.getFloat(8));
				capturaAtual.setEficaz(resultSet.getFloat(9));
				capturaAtual.setMv2(resultSet.getFloat(11));
				capturaAtual.setUnder(resultSet.getInt(12));
				capturaAtual.setOver(resultSet.getInt(13));
				capturaAtual.setDuracao(resultSet.getInt(14));
				listCapturaAtual.add(capturaAtual);
			}
			stmt.close();
			return listCapturaAtual;
			
		} catch (SQLException pr) {
			throw new ProtegeDAOException(pr.getMessage());
		}
	}
}
