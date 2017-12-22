package pt.iscte.es1.antiSpamFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.ComputeQualityIndicators;
import org.uma.jmetal.util.experiment.component.ExecuteAlgorithms;
import org.uma.jmetal.util.experiment.component.GenerateBoxplotsWithR;
import org.uma.jmetal.util.experiment.component.GenerateLatexTablesWithStatistics;
import org.uma.jmetal.util.experiment.component.GenerateReferenceParetoSetAndFrontFromDoubleSolutions;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

/** Classe respons�vel pela configura��o autom�tica do filtro anti-spam.
 * @author Mario
 *
 */

public class AntiSpamFilterAutomaticConfiguration {
	/** N�mero de vezes que o algoritmo � executado*/
	private static final int INDEPENDENT_RUNS = 5 ;

	/** Defini��o das caracter�sticas do problema a analisar (exemplo, n�mero de vari�veis */
	private AntiSpamFilterProblem problem;

	/** Instanciaca��o da configura��o autom�tica do filtro anti-spam */
	public AntiSpamFilterAutomaticConfiguration(AntiSpamFilterProblem problem) {
		this.problem = problem;
	}

	/** M�todo respons�vel por gerar a configura��o autom�tica 
	 * 
	 * @throws IOException
	 */
	public void generateAutomaticConfig() throws IOException{

		String experimentBaseDirectory = "experimentBaseDirectory";

		ExperimentProblem<DoubleSolution> experimentProblem = new ExperimentProblem<>(problem);
		List<ExperimentProblem<DoubleSolution>> problemList = new ArrayList<>(Collections.singletonList(experimentProblem)); 

		List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> algorithmList =
				configureAlgorithmList(problemList);

		Experiment<DoubleSolution, List<DoubleSolution>> experiment =
				new ExperimentBuilder<DoubleSolution, List<DoubleSolution>>("AntiSpamStudy")
				.setAlgorithmList(algorithmList)
				.setProblemList(problemList)
				.setExperimentBaseDirectory(experimentBaseDirectory)
				.setOutputParetoFrontFileName("FUN")
				.setOutputParetoSetFileName("VAR")
				.setReferenceFrontDirectory(experimentBaseDirectory+"/referenceFronts")
				.setIndicatorList(Arrays.asList(new PISAHypervolume<DoubleSolution>())) 
				.setIndependentRuns(INDEPENDENT_RUNS)
				.setNumberOfCores(8)
				.build();

		new ExecuteAlgorithms<>(experiment).run();
		new GenerateReferenceParetoSetAndFrontFromDoubleSolutions(experiment).run();
		new ComputeQualityIndicators<>(experiment).run() ;
		new GenerateLatexTablesWithStatistics(experiment).run() ;
		new GenerateBoxplotsWithR<>(experiment).setRows(1).setColumns(1).run() ;

	}

	/** M�todo respons�vel por gerar a lista com as solu��es para o problema analisado. */

	static List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> configureAlgorithmList(
			List<ExperimentProblem<DoubleSolution>> problemList) {
		List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> algorithms = new ArrayList<>();

		for (int i = 0; i < problemList.size(); i++) {
			Algorithm<List<DoubleSolution>> algorithm = new NSGAIIBuilder<>(
					problemList.get(i).getProblem(),
					new SBXCrossover(1.0, 5),
					new PolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
					.setMaxEvaluations(25000)
					.setPopulationSize(100)
					.build();
			algorithms.add(new ExperimentAlgorithm<>(algorithm, "NSGAII", problemList.get(i).getTag()));
		}

		return algorithms;
	}

}
