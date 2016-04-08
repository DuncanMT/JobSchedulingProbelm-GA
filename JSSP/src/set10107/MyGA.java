package set10107;

import java.util.Arrays;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import modelP.JSSP;
import modelP.Problem;


public class MyGA extends SwingWorker<Integer, String> {

	private Random rand = new Random();
	private int populationSize;
	private int numOfMachines;
	private int numOfJobs;
	private double mutationRate;
	private int[][][] population;
	private int[][][] origonalpop;
	private int[] fitness;
	private Problem problem;
	private int tournamentSize;
	private JLabel resultsLabel;
	private int itterations;
	private String crossoverType;
	private JButton button;

	public MyGA(int problemNum, double mutationRate, int tournamentSize, int populationSize, JLabel resultsLabel, int itterations, String crossoverType, JButton button) {
		this.mutationRate = mutationRate;
		this.tournamentSize= tournamentSize;
		this.populationSize= populationSize;
		this.resultsLabel =resultsLabel;
		this.itterations = itterations;
		this.crossoverType = crossoverType;
		this.button = button;

		problem = JSSP.getProblem(problemNum);
		numOfMachines= problem.getNumberOfMachines();
		numOfJobs = problem.getNumberOfJobs();

		JSSP.printProblem(problem);

		population = new int[populationSize][numOfMachines][numOfJobs];
		fitness = new int[populationSize];

		for(int i = 0; i < populationSize; i++){
			population[i] = JSSP.getRandomSolution(problem);
			fitness[i] = JSSP.getFitness(population[i], problem);
		}
		origonalpop = copyOf3Dim(population);
	}

	@Override
	protected Integer doInBackground() throws Exception {

		int[][] result = new int[getNumOfMachines()][getNumOfJobs()];
		int iter=0;
		while(iter<itterations && !isCancelled()){
			int id1 = tournamentSelect();
			int id2 = tournamentSelect();

			if(crossoverType.equals("One-Point")){
				result = onePointCrossover(id1, id2);
			}else{
				result = twoPointCrossover(id1, id2);
			}

			int[][] mutant = mutate(result);	

			replace(mutant);
			iter++;

			setProgress((int)Math.round(iter * 100.0/itterations));
		}			

		return getBestFit();
	}

	protected void done()
	{
		try
		{
			button.setEnabled(true);
			setProgress(0);
			if(!isCancelled())
				resultsLabel.setText(Integer.toString(get()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	public int tournamentSelect(){
		int i, picked, bestIndex, bestFit, fit;

		picked = rand.nextInt(populationSize);
		//bestFit = JSSP.getFitness(population[picked], problem);
		bestFit = fitness[picked];
		bestIndex = picked;

		for (i = 0; i < tournamentSize - 1; i++)
		{
			picked = rand.nextInt(populationSize);
			//fit = JSSP.getFitness(population[picked], problem);
			fit = fitness[picked];
			if (fit > bestFit)
			{
				bestFit = fit;
				bestIndex = picked;
			}
		}
		return bestIndex;
	}

	public int[][] mutate (int[][] child){

		//System.out.println("Child:"+twoDIntArrayToString(child));
		for(int j =0; j < numOfMachines; j++){
			for(int i =0 ; i < numOfJobs; i++){
				if(rand.nextDouble() < mutationRate){
					int swapId = rand.nextInt(numOfJobs);
					while(swapId == i){
						swapId = rand.nextInt(numOfJobs);
					}

					int buffer = child[j][i];
					child[j][i] = child[j][swapId];
					child[j][swapId] = buffer;
				}
			}
		}

		//System.out.println("Child:"+twoDIntArrayToString(child));
		return child;
	}

	public int[][] onePointCrossover(int id1, int id2){

		int cutpoint = rand.nextInt(numOfMachines+1);

		//System.out.println("Cutpoint "+ cutpoint);
		int[][] parent1 = copyOf2Dim(population[id1]);
		int[][] parent2 = copyOf2Dim(population[id2]);	

		//System.out.println("Parent1:"+twoDIntArrayToString(parent1));
		//System.out.println("Parent2:"+twoDIntArrayToString(parent2));

		for(int i=0; i < cutpoint; i++)
			System.arraycopy(parent2[i], 0, parent1[i], 0, parent2[i].length);

		//System.out.println("Child:"+twoDIntArrayToString(parent1));
		return parent1;
	}

	public int[][] twoPointCrossover(int id1, int id2){

		int cutpoint1 = rand.nextInt(numOfMachines+1);
		int cutpoint2 = rand.nextInt(numOfMachines+1);

		while(cutpoint1==cutpoint2){
			cutpoint1 = rand.nextInt(numOfMachines+1);
			cutpoint2 = rand.nextInt(numOfMachines+1);	
		}

		if(cutpoint1 > cutpoint2){
			int buffer = cutpoint1;
			cutpoint1 = cutpoint2;
			cutpoint2 = buffer;	
		}

		//System.out.println("Cutpoints "+ cutpoint1+":"+cutpoint2);

		int[][] parent1 = copyOf2Dim(population[id1]);
		int[][] parent2 = copyOf2Dim(population[id2]);	

		//System.out.println("Parent1:"+twoDIntArrayToString(parent1));
		//System.out.println("Parent2:"+twoDIntArrayToString(parent2));

		for(int i=0; i < cutpoint1; i++)
			System.arraycopy(parent2[i], 0, parent1[i], 0, parent2[i].length);

		for(int i=cutpoint2; i < numOfMachines; i++)
			System.arraycopy(parent2[i], 0, parent1[i], 0, parent2[i].length);

		//System.out.println("Child:"+twoDIntArrayToString(parent1));

		return parent1;
	}

	public void replace(int[][] child)
	{

		//int worst=JSSP.getFitness(population[0], problem);
		int worst=fitness[0];
		int childFitness = JSSP.getFitness(child, problem);
		int worstIndex=0;

		for (int i=0;i<populationSize;i++){
			//int fit = JSSP.getFitness(population[i], problem);
			int fit = fitness[i];
			if (fit > worst)
			{
				worstIndex = i;
				worst = fit;
			}
		}

		if (childFitness < worst)
		{
			population[worstIndex]= copyOf2Dim(child);
			fitness[worstIndex] = childFitness;
		}
	}

	public Integer getBestFit (){
		//int best=JSSP.getFitness(population[0], problem);
		int best=fitness[0];
		int fit;

		for(int i=0;i<populationSize;i++){
			//fit=JSSP.getFitness(population[i], problem);
			fit=fitness[i];
			if (fit < best){
				best = fit;
			}
		}
		//System.out.println("Best Fitness: "+fitness[bestIndex]);

		//JSSP.printSolution(population[bestIndex], problem);
		return best;
		//String filename = JSSP.saveSolution(population[bestIndex], problem);
		//JSSP.displaySolution(filename);	
	}

	public int[][] getBestSol(){
		int best=fitness[0];
		int bestId=0;
		int fit;

		for(int i=0;i<populationSize;i++){
			//fit=JSSP.getFitness(population[i], problem);
			fit=fitness[i];
			if (fit < best){
				best = fit;
				bestId=i;
			}
		}
		return copyOf2Dim(population[bestId]);
	}

	public void saveBestSol(){
		JSSP.saveSolution(getBestSol(), problem);
	}

	public String twoDIntArrayToString(int[][] array){
		String output = "";
		for(int[] inArray : array){
			output+=Arrays.toString(inArray)+", ";
		}
		return output;
	}

	public void ViewFitnesses(JTextArea view1, JTextArea view2, JLabel arraybest, JLabel funcbest){
		int best =fitness[0];
		int best2 = JSSP.getFitness(population[0], problem);
		int fit=0;

		for(int i=0;i<populationSize;i++){
			view1.append(i+": "+fitness[i]+"\n");
			if(best > fitness[i]){
				best = fitness[i];
			}

			fit = JSSP.getFitness(population[i], problem);
			view2.append(i+": "+fit+"\n");
			if(best2 > fit){
				best2 = fit;
			}			
		}
		arraybest.setText(Integer.toString(best));
		funcbest.setText(Integer.toString(best2));

	}

	public void ViewPopulations(JTextArea view1, JTextArea view2){
		for(int i=0;i<populationSize;i++){
			view1.append(i+": "+twoDIntArrayToString(population[i])+"\n");
			view2.append(i+": "+twoDIntArrayToString(origonalpop[i])+"\n");			
		}	
	}

	public int getNumOfMachines() {
		return numOfMachines;
	}

	public int getNumOfJobs() {
		return numOfJobs;
	}

	public int[][][] copyOf3Dim(int[][][] array) {
		int[][][] copy;
		copy = new int[array.length][][];
		for (int i = 0; i < array.length; i++) {
			copy[i] = new int[array[i].length][];
			for (int j = 0; j < array[i].length; j++) {
				copy[i][j] = new int[array[i][j].length];
				System.arraycopy(array[i][j], 0, copy[i][j], 0, 
						array[i][j].length);
			}
		}
		return copy;
	} 

	public static int[][] copyOf2Dim(int[][] src) {
		int length = src.length;
		int[][] target = new int[length][src[0].length];
		for (int i = 0; i < length; i++) {
			System.arraycopy(src[i], 0, target[i], 0, src[i].length);
		}
		return target;
	}

	public void test(){


	}
}
