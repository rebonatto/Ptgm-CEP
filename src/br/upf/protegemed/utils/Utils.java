package br.upf.protegemed.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.upf.protegemed.rest.WSProtegemed;

public class Utils {

	public static final String QUERY_EVENTOS = "select codEvento,`desc`,formaOnda from protegemed.eventos";
	public static final String QUERY_HARMONICA_ATUAL = "select codCaptura,codHarmonica,sen,cos from protegemed.harmatual";
	public static final String QUERY_HARMONICA_PADRAO = "select codHarmonica,codOndaPadrao,sen,cos from protegemed.harmpadrao";
	public static final String QUERY_MARCA = "select codMarca, `desc` from protegemed.marca";
	public static final String QUERY_MODELO = "select codModelo, `desc` from protegemed.modelo";
	public static final String QUERY_PROCEDIMENTO = "select codProced,`desc` from protegemed.procedimento";
	public static final String QUERY_RESPONSAVEL = "select codResp,`desc` from protegemed.responsavel";
	public static final String QUERY_SALA_CIRURGIA = "select codSala,`desc` from protegemed.salacirurgia";
	public static final String QUERY_TIPO = "select codTipo,`desc` from protegemed.tipo";
	public static final String QUERY_TIPO_ONDA = "select codTipoOnda,`desc` from protegemed.tipoonda";
	public static final String QUERY_TIPO_PADRAO = "select codTipoPadrao,`desc` from protegemed.tipopadrao";
	public static final String QUERY_TOMADA = "select codTomada,codSala,indice,modulo,`desc` from protegemed.tomada";
	public static final String QUERY_USO_SALA = "select codUsoSala,codSala,codProced,codResp,horaInicio,horaFinal,ativa from protegemed.usosala";
	public static final String QUERY_USO_SALA_CAPTURA = "select codCaptura,codUsoSala from protegemed.usosalacaptura";
	public static final String QUERY_USO_SALA_EQUIP = "select codEquip,codUsoSala from protegemed.usosalaequip";
	public static final String QUERY_CAPTURA_ATUAL = "select codCaptura,codTomada,codTipoOnda,codEquip,codEvento, valorMedio, offset, gain, eficaz, dataAtual, vm2, under, over, duration from protegemed.capturaatual";
	public static final String QUERY_EQUIPAMENTO = "select codEquip,codMarca,codModelo,codTipo,rfid,codPatrimonio,`desc`,dataUltimaFalha,dataUltimaManutencao,tempoUso from protegemed.equipamento";
	public static final String QUERY_ONDA_PADRAO = "select codOndaPadrao,codTipoOnda,codTomada,codEquip,valorMedio,offset,gain,eficaz,dataPadrao,codTipoPadrao from protegemed.ondapadrao";
	public static final String QUERY_COD_SALA = "select s.codSala, s.desc from protegemed.salacirurgia s, tomada t where t.codSala = s.codSala and t.codTomada = ?";
	public static final String QUERY_INSERT = "insert into protegemed.teste(i) values(?)";
	
	public static final String PASSWORD = "senha.123";
	public static final String USER = "protegemed";
	public static final String BD = "protegemed";
	public static final String JDBC = "mysql";
	public static final String HOST = "localhost";
	public static final String PORT = "3306";
	public static final String MASK_DATA = "YYYY-MM-dd HH:mm:ss.SSS";
	private static String LOCALE_LOG = System.getProperty("user.home") + "/Downloads/teste.txt";

	public static int MEMORIA = 256 * 1024 * 1024;

	public static int MAXSALAS = 16;
	public static int MAXTOMADAS = 12;

	public static int HARMONICAS = 12;
	public static int PONTOSONDA = 256;

	public static int FREQBASE = 60;

	public static int TEMPOATUALIZARELOGIOS = 1000;
	public static int TEMPOATUALIZATABELA = 3000;

	public static Calendar DATALIMITEVM;
	public static int DIGITOSSIGNIFICATIVOS = 3;

	public static void logger(String msg) {
		
		if (WSProtegemed.ativarLog == 1) {
			Date dataTemp;
			String dataString = null;
			
			try {
				SimpleDateFormat f = new SimpleDateFormat(MASK_DATA);
				dataTemp = Calendar.getInstance().getTime();
				dataString = "[LOG] ".concat(f.format(dataTemp)).concat(" - ");
				
				Files.write(Paths.get(LOCALE_LOG), new String(dataString).getBytes(), StandardOpenOption.APPEND);
				Files.write(Paths.get(LOCALE_LOG), new String(msg).getBytes(), StandardOpenOption.APPEND);
				Files.write(Paths.get(LOCALE_LOG), new String("\n").getBytes(), StandardOpenOption.APPEND);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Float convertHexToFloat(String string) {
		//Long bits = Long.parseLong(string, 16);
		return Float.intBitsToFloat(new Long(Long.parseLong(string, 16)).intValue());
	}
}
