package pt.iscte.es1.resultcompiler;

import java.io.File;
import java.io.IOException;

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
	 * Compila o ficheiro R, gerado pelo algoritmo NSGAII Algorithm, criando o ficheiro EPS.
	 * @return File
	 * @throws IOException
	 */
	public File compile() throws IOException, InterruptedException {
		final Runtime rt = Runtime.getRuntime();
		rt.exec("Rscript " + NSGAII_R_FILE, null,
				new File(System.getProperty("user.dir") + "/" + ANTI_SPAM_STUDY + R)).waitFor();
		return new File(System.getProperty("user.dir") + "/" + ANTI_SPAM_STUDY + R + NSGAII_EPS_FILE);
	}
}
