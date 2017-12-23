package pt.iscte.es1.resultcompiler;

import java.io.File;
import java.io.IOException;

/**
 * Classe referente a compilar de ficheiros de extensão .R, a partir dos quais gera ficheiro .eps contendo
 * BoxPlot referente a dados gerados pelo algoritmo de configuração automática.
 * @author ES1-2017-IC2-82
 *
 */

public class RToEpsCompiler {
	/** Diretório onde é gravado ficheiro .eps gerado por Rscript */
	private static final String R = "/R";
	/** Ficheiro analisado para gerar Boxplot */
	private static final String NSGAII_R_FILE = "HV.Boxplot.R";
	/** Ficheiro com boxplot */
	private static final String NSGAII_EPS_FILE = "/HV.Boxplot.eps";
	/** Diretório onde são mantidos os ficheiros relativos ao algoritmo anti-spam */
	private static final String EXPERIMENT_BASE_DIRECTORY = "experimentBaseDirectory";
	/** Diretório onde são mantidos os ficheiros relativos ao algoritmo */
	private static final String ANTI_SPAM_STUDY = EXPERIMENT_BASE_DIRECTORY + "/AntiSpamStudy";

	/**
	 * Compila o ficheiro .R, gerado pelo algoritmo NSGAII Algorithm, criando o ficheiro EPS.
	 * @return File retorna um ficheiro .R
	 * @throws IOException - lança excepção no caso de não efetuar a compilação do ficheiro.
	 * @throws InterruptedException - lança excepção no caso de ser interrompida
	 */
	public File compile() throws IOException, InterruptedException {
		final Runtime rt = Runtime.getRuntime();
		rt.exec("/usr/local/bin/Rscript " + "/Users/mariojose/eclipse-workspace/ES1-2017-IC2-82/experimentBaseDirectory/AntiSpamStudy/R/" + NSGAII_R_FILE
			); 
		return new File(ANTI_SPAM_STUDY + R + NSGAII_EPS_FILE);
	}
}
