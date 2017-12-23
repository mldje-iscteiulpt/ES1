package pt.iscte.es1.resultcompiler;

import java.io.File;
import java.io.IOException;

/**
 * Classe responsável pela compilação do ficheiro AntiSpamStudy.tex para o formato .pdf
 * @author ES1-2017-IC2-82
 *
 */

public class TexToPDFCompiler {

	/** Diretório latex */
	private static final String LATEX_DIR = "/latex";
	/** Designação do ficheiro utilizado na geração do pdf (latex) */
	private static final String NSGAII_TEX_FILE = "AntiSpamStudy.tex";
	/** Designação do ficheiro gerado em pdf */
	private static final String NSGAII_PDF_FILE = "/AntiSpamStudy.pdf";
	/** Define diretoria onde serão guardados os ficheiros resultantes do algoritmo */
	private static final String EXPERIMENT_BASE_DIRECTORY = "experimentBaseDirectory";
	/** Define diretoria onde serão guardados os ficheiros resultantes do algoritmo */
	private static final String ANTI_SPAM_STUDY = EXPERIMENT_BASE_DIRECTORY + "/AntiSpamStudy";

	/**
	 * Compila o ficheiro Tex, gerado pelo algoritmo NSGAII, criando o ficheiro PDF.
	 * @return File - retorna ficheiro .pdf
	 * @throws IOException - lança excepção caso não consiga compilar PDF
	 * @throws InterruptedException - lança excepção caso seja interrompido
	 */
	public File compile() throws IOException, InterruptedException {
		final Runtime rt = Runtime.getRuntime();
		rt.exec("/usr/local/bin/pdflatex " + NSGAII_TEX_FILE, null,
			new File(ANTI_SPAM_STUDY + LATEX_DIR)).waitFor();
		return new File(ANTI_SPAM_STUDY + LATEX_DIR + NSGAII_PDF_FILE);
	}
}