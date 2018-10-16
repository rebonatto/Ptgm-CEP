package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import br.upf.protegemed.beans.ParamRequest;

class WSProtegemedTest {

	private static List<String> list;
	private static WSProtegemed wsProtegemed;
	private static List<ParamRequest> params = new ArrayList<ParamRequest>();
	private Float result;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		wsProtegemed = new WSProtegemed();
		
	}

	@BeforeEach
	void setUp() throws Exception {
		List<String> eventos = Arrays.asList("TYPE=01&OUTLET=01&RFID=FFFF0002&OFFSET=2093&GAIN=444E6B09&RMS=3FA277C5&MV=00000000&MV2=00000000&UNDER=0000&OVER=0000&DURATION=0000&SIN=44A74464%3BC232764E%3BC02F3D9A%3BC12780FC%3B42618EF4%3BC1CC261E%3BC21668A5%3BC0E1178A%3BC164BAB6%3BC0A07318%3BC10AEC48%3BC095ACE4&COS=C41D1918%3B418A1530%3B41B06ECC%3B40F0FE2A%3B42321A4A%3BC0C2D6BE%3BBE3880E4%3BC01D72A3%3BC12024DE%3B3FF28A7C%3BC05C0DE6%3B4014D9FD",
				"TYPE=01&OUTLET=02&RFID=FFFF0002&OFFSET=2077&GAIN=430003F8&RMS=3E999B0E&MV=00000000&MV2=00000000&UNDER=0000&OVER=0000&DURATION=0000&SIN=4254A050%3BBFE0F68A%3BBE464CAA%3BBF62A529%3BBFA9CDB2%3BBDCA9270%3B3E8FB022%3BBE8987A2%3B3C6A3700%3BBEFD04EB%3BBEFF5D15%3BBE9BB644&COS=412A13F6%3BBCFBBE6C%3BBEBA6265%3BBDD75965%3BBFB807F3%3B3DFC5F62%3B3A7BB900%3BBDBB55DF%3B3E72D46A%3B3DA7D24B%3BBDBA04F4%3B3E25F431",
				"TYPE=06&OUTLET=02&RFID=FFFF0002&OFFSET=2077&GAIN=430003F8&RMS=3E999B0E&MV=00000000&MV2=00000000&UNDER=0000&OVER=0000&DURATION=0000&SIN=4254A050%3BBFE0F68A%3BBE464CAA%3BBF62A529%3BBFA9CDB2%3BBDCA9270%3B3E8FB022%3BBE8987A2%3B3C6A3700%3BBEFD04EB%3BBEFF5D15%3BBE9BB644&COS=412A13F6%3BBCFBBE6C%3BBEBA6265%3BBDD75965%3BBFB807F3%3B3DFC5F62%3B3A7BB900%3BBDBB55DF%3B3E72D46A%3B3DA7D24B%3BBDBA04F4%3B3E25F431",
				"TYPE=06&OUTLET=01&RFID=FFFF0002&OFFSET=2093&GAIN=444E6B09&RMS=3FA277C5&MV=00000000&MV2=00000000&UNDER=0000&OVER=0000&DURATION=0000&SIN=44A74464%3BC232764E%3BC02F3D9A%3BC12780FC%3B42618EF4%3BC1CC261E%3BC21668A5%3BC0E1178A%3BC164BAB6%3BC0A07318%3BC10AEC48%3BC095ACE4&COS=C41D1918%3B418A1530%3B41B06ECC%3B40F0FE2A%3B42321A4A%3BC0C2D6BE%3BBE3880E4%3BC01D72A3%3BC12024DE%3B3FF28A7C%3BC05C0DE6%3B4014D9FD");
		list = new ArrayList<>();
		for(int i = 0; i < eventos.size(); i++) { 
			list.add(eventos.get(i));
		}
		
		for (int i = 0; i < list.size(); i++) {
			String[] param = list.get(i).split("&");
			params.add(wsProtegemed.splitRequest(param));
		}
	}

	@Test
	void testInitInstanceDrools() {
		KieSession kSession = WSProtegemed.initInstanceDrools();
		assertNotNull(kSession);
	}

	@Test
	void testPostReceiveEvent() {
		List<CapturaAtual> lista = new ArrayList<CapturaAtual>();
		KieSession kSession = WSProtegemed.initInstanceDrools();
		for (int i = 0; i < list.size(); i++) {
			lista.add(wsProtegemed.postReceiveEvent(list.get(i)));		
		}
		
		Collection<FactHandle> collect = kSession.getFactHandles();
		
		assertEquals(list.size(), collect.size());
		assertNotNull(lista);
	}

	@Test
	void testSplitRequest() {
		
		for (int i = 0; i < list.size(); i++) {
			ParamRequest splitRequest = params.get(i);
			assertNotNull(splitRequest.getCOS());
			assertNotNull(splitRequest.getDURATION());
			assertNotNull(splitRequest.getGAIN());
			assertNotNull(splitRequest.getMV());
			assertNotNull(splitRequest.getMV2());
			assertNotNull(splitRequest.getOFFSET());
			assertNotNull(splitRequest.getOUTLET());
			assertNotNull(splitRequest.getOVER());
			assertNotNull(splitRequest.getRFID());
			assertNotNull(splitRequest.getRMS());
			assertNotNull(splitRequest.getSIN());
			assertNotNull(splitRequest.getTYPE());
			assertNotNull(splitRequest.getUNDER());
		}
	}

	@Test
	void testConvertHexToFloat() {
		for (int i = 0; i < list.size(); i++) {
			result = WSProtegemed.convertHexToFloat(params.get(i).getGAIN());
			assertEquals(Float.class, result.getClass());
			result = WSProtegemed.convertHexToFloat(params.get(i).getRMS());
			assertEquals(Float.class, result.getClass());
			result = WSProtegemed.convertHexToFloat(params.get(i).getMV());
			assertEquals(Float.class, result.getClass());
			result = WSProtegemed.convertHexToFloat(params.get(i).getMV2());
			assertEquals(Float.class, result.getClass());
		}
	}
}
