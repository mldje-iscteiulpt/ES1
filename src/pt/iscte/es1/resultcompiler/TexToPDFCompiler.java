package pt.iscte.es1.resultcompiler;

import java.io.File;
import java.io.IOException;

public class TexToPDFCompiler {
	
	private static final String LATEX_DIR = "/latex";
	private static final String NSGAII_TEX_FILE = "AntiSpamStudy.tex";
	private static final String NSGAII_PDF_FILE = "/AntiSpamStudy.pdf";
	private static final String EXPERIMENT_BASE_DIRECTORY = "experimentBaseDirectory";
	private static final String ANTI_SPAM_STUDY = EXPERIMENT_BASE_DIRECTORY + "/AntiSpamStudy";
	
	/**
	 * Compila o ficheiro Tex, gerado pelo algoritmo NSGAII, criando o ficheiro PDF.
	 * @return File
	 * @throws IOException
	 */
	public File compile() throws IOException, InterruptedException {
		final Runtime rt = Runtime.getRuntime();
		rt.exec("pdflatex " + NSGAII_TEX_FILE, null,
			new File(System.getProperty("user.dir") + "/" + ANTI_SPAM_STUDY + LATEX_DIR)).waitFor();
		return new File(System.getProperty("user.dir") + "/" + ANTI_SPAM_STUDY + LATEX_DIR + NSGAII_PDF_FILE);
	}
}

//../