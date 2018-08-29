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

	private Utils() {
	}

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
	public static final String QUERY_EQUIPAMENTO = "select codEquip,codMarca,codModelo,codTipo,codTomada,rfid,codPatrimonio,`desc`,dataUltimaFalha,dataUltimaManutencao,tempoUso from protegemed.equipamento";
	public static final String QUERY_ONDA_PADRAO = "select codOndaPadrao,codTipoOnda,codTomada,codEquip,valorMedio,offset,gain,eficaz,dataPadrao,codTipoPadrao from protegemed.ondapadrao";
	public static final String QUERY_COD_SALA = "select s.codSala, s.desc from protegemed.salacirurgia s, tomada t where t.codSala = s.codSala and t.codTomada = ?";
	public static final String QUERY_COD_EQUIPAMENTO = "select e.codEquip, e.codMarca, e.codModelo, e.codTipo, e.codPatrimonio, e.desc from protegemed.equipamento e where e.rfid = ?";
	public static final String QUERY_FREQ_NORMAL = "select f.valor from protegemed.frequencia_normal f inner join versao_frequencia v on f.id_versao = v.id and v.id = ?";
	public static final String QUERY_FREQ_ATENCAO = "select f.valor from protegemed.frequencia_atencao f inner join versao_frequencia v on f.id_versao = v.id and v.id = ?";
	public static final String QUERY_FREQ_PERIGO = "select f.valor from protegemed.frequencia_perigo f inner join versao_frequencia v on f.id_versao = v.id and v.id = ?";
	public static final String QUERY_ESCALA_FREQUENCIA = "SELECT f.valor, f.frequencia FROM protegemed.escala_frequencia f, protegemed.versao v, protegemed.periculosidade_fuga pf WHERE f.id_versao = v.id AND f.id_tipo = pf.id AND f.id_tipo = ? AND f.id_versao = ?";
	public static final String QUERY_ESCALA_SIMILARIDADE = "SELECT es.valor_min, es.valor_max FROM protegemed.escala_similaridade es, protegemed.versao v, protegemed.periculosidade_fuga pf WHERE es.id_versao = v.id AND es.id_tipo = pf.id AND es.id_tipo = ? AND es.id_versao = ?";
	public static final String QUERY_ESCALA_CORRENTE = "SELECT ec.valor FROM protegemed.escala_corrente ec, protegemed.versao v, protegemed.periculosidade_fuga pf WHERE ec.id_versao = v.id AND ec.id_tipo = pf.id AND ec.id_tipo = ? AND ec.id_versao = ?";
	public static final String QUERY_VERSAO = "select id, data, id_versao, descricao from versao where id_versao = ?";
	public static final String QUERY_PERICULOSIDADE_FUGA = "select id,tipo,descricao from protegemed.periculosidade_fuga";
	//public static final String INSERT_VL_CORRENTE = "insert into protegemed.vl_corrente(resultado, id_captura) values(?, ?)";
	public static final String INSERT_SPEARMAN = "insert into protegemed.spearman(valor) values(?)";
	
	public static final String PASSWORD = "senha.123";
	public static final String USER = "protegemed";
	public static final String BD = "protegemed";
	public static final String JDBC = "mysql";
	public static final String HOST = "localhost";
	public static final String PORT = "3306";
	public static final String MASK_DATA = "YYYY-MM-dd HH:mm:ss.SSS";
	public static final String MASK_YYYY_MM_DD = "yyyy-MM-dd";
	public static final float NORMAL = 0.06F;
	public static final float ATENTION = 0.1F;
	public static final float INTERVENTION = 0.5F;
	public static final Integer VERSAO_FREQUENCIA = 1;

	private static String LOCALE_LOG = System.getProperty("user.home") + "/Downloads/teste.txt";
	public static final int MEMORIA = 256 * 1024 * 1024;
	public static final int MAXSALAS = 16;
	public static final int MAXTOMADAS = 12;
	public static final int HARMONICAS = 12;
	public static final int PONTOSONDA = 256;
	public static final int FREQBASE = 60;
	public static final int TEMPOATUALIZARELOGIOS = 1000;
	public static final int TEMPOATUALIZATABELA = 3000;
	public static final int DIGITOSSIGNIFICATIVOS = 3;
	public static final double NIVELDESIGNIFICANCIA = 0.05;

	public static void logger(String msg) {

		if (WSProtegemed.getAtivarlog() == 1) {
			Date dataTemp;
			String dataString = null;

			try {
				SimpleDateFormat f = new SimpleDateFormat(MASK_DATA);
				dataTemp = Calendar.getInstance().getTime();
				dataString = "[LOG] ".concat(f.format(dataTemp)).concat(" - ");

				Files.write(Paths.get(LOCALE_LOG), dataString.getBytes(), StandardOpenOption.APPEND);
				Files.write(Paths.get(LOCALE_LOG), msg.getBytes(), StandardOpenOption.APPEND);
				Files.write(Paths.get(LOCALE_LOG), "\n".getBytes(), StandardOpenOption.APPEND);
			} catch (IOException e) {
				logger(e.getMessage());
			}
		}
	}

	public static Float convertHexToFloat(String string) {
		return Float.intBitsToFloat(new Long(Long.parseLong(string, 16)).intValue());
	}
}
