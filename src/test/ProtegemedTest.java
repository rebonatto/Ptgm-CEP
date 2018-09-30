package test;

import static java.lang.System.out;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import br.upf.protegemed.beans.CapturaAtual;
import br.upf.protegemed.beans.Equipamento;
import br.upf.protegemed.beans.Eventos;
import br.upf.protegemed.beans.HarmAtual;
import br.upf.protegemed.beans.ParamRequest;
import br.upf.protegemed.beans.SalaCirurgia;
import br.upf.protegemed.beans.Tomada;
import br.upf.protegemed.rest.LoadConfiguration;
import br.upf.protegemed.utils.Utils;


class ProtegemedTest {

	private static KieServices ks;
	private static KieContainer kContainer;
	private static KieSession kSession;
	private static List<String> eventos;
	private static List<CapturaAtual> list;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		LoadConfiguration.loadConnection();
		LoadConfiguration.loadVersao();
		LoadConfiguration.loadPericulosidadeFuga();
		LoadConfiguration.loadEscalaSimilaridade();
		LoadConfiguration.loadEscalaFrequencias();
		LoadConfiguration.loadEscalaCorrente();
		LoadConfiguration.initInstanceDrools();

		ks = KieServices.Factory.get();
		kContainer = ks.getKieClasspathContainer();
		kSession = kContainer.newKieSession("protegemed");
		eventos = Arrays.asList("TYPE=01&OUTLET=01&RFID=FFFF0002&OFFSET=2093&GAIN=444E6B09&RMS=3FA277C5&MV=00000000&MV2=00000000&UNDER=0000&OVER=0000&DURATION=0000&SIN=44A74464%3BC232764E%3BC02F3D9A%3BC12780FC%3B42618EF4%3BC1CC261E%3BC21668A5%3BC0E1178A%3BC164BAB6%3BC0A07318%3BC10AEC48%3BC095ACE4&COS=C41D1918%3B418A1530%3B41B06ECC%3B40F0FE2A%3B42321A4A%3BC0C2D6BE%3BBE3880E4%3BC01D72A3%3BC12024DE%3B3FF28A7C%3BC05C0DE6%3B4014D9FD",
				"TYPE=01&OUTLET=02&RFID=FFFF0002&OFFSET=2077&GAIN=430003F8&RMS=3E999B0E&MV=00000000&MV2=00000000&UNDER=0000&OVER=0000&DURATION=0000&SIN=4254A050%3BBFE0F68A%3BBE464CAA%3BBF62A529%3BBFA9CDB2%3BBDCA9270%3B3E8FB022%3BBE8987A2%3B3C6A3700%3BBEFD04EB%3BBEFF5D15%3BBE9BB644&COS=412A13F6%3BBCFBBE6C%3BBEBA6265%3BBDD75965%3BBFB807F3%3B3DFC5F62%3B3A7BB900%3BBDBB55DF%3B3E72D46A%3B3DA7D24B%3BBDBA04F4%3B3E25F431",
				"TYPE=06&OUTLET=02&RFID=FFFF0002&OFFSET=2077&GAIN=430003F8&RMS=3E999B0E&MV=00000000&MV2=00000000&UNDER=0000&OVER=0000&DURATION=0000&SIN=4254A050%3BBFE0F68A%3BBE464CAA%3BBF62A529%3BBFA9CDB2%3BBDCA9270%3B3E8FB022%3BBE8987A2%3B3C6A3700%3BBEFD04EB%3BBEFF5D15%3BBE9BB644&COS=412A13F6%3BBCFBBE6C%3BBEBA6265%3BBDD75965%3BBFB807F3%3B3DFC5F62%3B3A7BB900%3BBDBB55DF%3B3E72D46A%3B3DA7D24B%3BBDBA04F4%3B3E25F431",
				"TYPE=06&OUTLET=01&RFID=FFFF0002&OFFSET=2093&GAIN=444E6B09&RMS=3FA277C5&MV=00000000&MV2=00000000&UNDER=0000&OVER=0000&DURATION=0000&SIN=44A74464%3BC232764E%3BC02F3D9A%3BC12780FC%3B42618EF4%3BC1CC261E%3BC21668A5%3BC0E1178A%3BC164BAB6%3BC0A07318%3BC10AEC48%3BC095ACE4&COS=C41D1918%3B418A1530%3B41B06ECC%3B40F0FE2A%3B42321A4A%3BC0C2D6BE%3BBE3880E4%3BC01D72A3%3BC12024DE%3B3FF28A7C%3BC05C0DE6%3B4014D9FD");
		list = new ArrayList<>();
		for(int i = 0; i < eventos.size(); i++) 
			list.add(objCaptura(eventos.get(i)));
	}

	@BeforeEach
	void setUp() throws Exception {
	
	}

	@Test
	void testGetSession() {
		
		if (kSession == null) {
			assertTrue(false);
		} else {
			assertTrue(true);
		}
	}

	@Test
	void testPostReceiveEvent() {
		for (CapturaAtual captura: list) {
			kSession.insert(captura);
		}
		kSession.fireAllRules();
		
		for (CapturaAtual captura: list) 
			assertEquals("Igual", 0, captura.getPericulosidadeCorrente().intValue());
		
		for (CapturaAtual captura: list) 
			assertEquals("Igual", 0, captura.getPericulosidadeSimilaridade().intValue());	
	}

	public static CapturaAtual objCaptura(String payload) {
		List<HarmAtual> listHarmAtual = new ArrayList<>();
		Equipamento equipamento;
		Eventos eventos = new Eventos();
		Tomada tomada = new Tomada();
		SalaCirurgia salaCirurgia;
		ParamRequest paramRequest;
		CapturaAtual capturaAtual = new CapturaAtual();
		
		String[] temp = payload.split("&");
		String[] arrayCos;
		String[] arraySen;

		paramRequest = Protegemed.splitRequest(temp);
		
		capturaAtual.setCodCaptura(1);
		eventos.setCodEvento(Integer.parseInt(paramRequest.getTYPE()));
		tomada.setCodTomada(Integer.parseInt(paramRequest.getOUTLET()));
		salaCirurgia = new SalaCirurgia(1, "TESTE");
		equipamento = new Equipamento();
		equipamento.setCodEquip(1);
		equipamento.setRfid(paramRequest.getRFID());
		capturaAtual.setOffset(Float.parseFloat(paramRequest.getOFFSET()));
		capturaAtual.setGain(Utils.convertHexToFloat(paramRequest.getGAIN()));
		capturaAtual.setEficaz(Utils.convertHexToFloat(paramRequest.getRMS()));
		capturaAtual.setMv(Utils.convertHexToFloat(paramRequest.getMV()));
		capturaAtual.setMv2(Utils.convertHexToFloat(paramRequest.getMV2()));
		capturaAtual.setUnder(Integer.parseInt(paramRequest.getUNDER()));
		capturaAtual.setOver(Integer.parseInt(paramRequest.getOVER()));
		capturaAtual.setDuracao(Integer.parseInt(paramRequest.getDURATION()));
		
		capturaAtual.setEventos(eventos);
		capturaAtual.setEquipamento(equipamento);
		capturaAtual.setTomada(tomada);
		capturaAtual.setSalaCirurgia(salaCirurgia);
		
		arraySen = paramRequest.getSIN().split("%");
		arrayCos = paramRequest.getCOS().split("%");

		for (int i = 0; i < arrayCos.length; i++) {
			HarmAtual harmAtual = new HarmAtual();
			harmAtual.setCodHarmonica(i);
			harmAtual.setSen(Utils.convertHexToFloat(arraySen[i]));
			harmAtual.setCos(Utils.convertHexToFloat(arrayCos[i]));
			listHarmAtual.add(harmAtual);
		}

		capturaAtual.setListHarmAtual(listHarmAtual);
		capturaAtual.setData(Calendar.getInstance());
		capturaAtual.setPericulosidadeCorrente(0);
		capturaAtual.setPericulosidadeFrequencia(0);
		capturaAtual.setPericulosidadeSimilaridade(0);
		
		return capturaAtual;
	}
}
