package set10107;


import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import modelP.JSSP;
import modelP.Problem;

public class MainWindow extends JFrame implements ActionListener, PropertyChangeListener{

	private static final long serialVersionUID = -3442371711085306828L;
	private JPanel contentPane;
	private JPanel panel;

	private Integer[] problemValues = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
			16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38,
			39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61,
			62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84,
			85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106,
			107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125,
			126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142};

	private JLabel lblProblemNumber, lblMutationRate, lblPopulationSize, lblTournamentSize, lblResult, lblShowResult, lblCrossoverType, lblNumberOfIterations;

	private JComboBox<Integer> problemNumCombo;
	private JComboBox<String> comboCrossoverType;

	private JSpinner spinnerPopulationSize, spinnerTournamentSize, spinnerItterations, spinnerMutationRate;

	private JButton btnRun;
	private JButton btnCancel;
	private MyEA ea;
	private JProgressBar progressBar;
	private JButton btnViewFitnesses;
	private JButton btnViewPopulatuon;
	private JButton btnTest;
	private JButton btnSave;
	private JButton btnLoad;
	final JFileChooser fc = new JFileChooser();
	

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				MainWindow mw = new MainWindow();
				mw.setVisible(true);
			}
		});
	}

	public MainWindow() {
		File workingDirectory = new File(System.getProperty("user.dir"));
		fc.setCurrentDirectory(workingDirectory);
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 283, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 11, 233, 193);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(10, 2, 0, 0));

		lblProblemNumber = new JLabel("Problem Number ");
		lblProblemNumber.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblProblemNumber);

		problemNumCombo = new JComboBox<Integer>();
		problemNumCombo.setMaximumRowCount(12);
		problemNumCombo.setModel(new DefaultComboBoxModel<Integer>(problemValues));
		problemNumCombo.setSelectedIndex(85);
		panel.add(problemNumCombo);

		lblNumberOfIterations = new JLabel("Number of Iterations");
		lblNumberOfIterations.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNumberOfIterations);

		spinnerItterations = new JSpinner();
		spinnerItterations.setModel(new SpinnerNumberModel(new Integer(1000), null, null, new Integer(1)));
		panel.add(spinnerItterations);

		lblPopulationSize = new JLabel("Population Size");
		lblPopulationSize.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblPopulationSize);

		spinnerPopulationSize = new JSpinner();
		spinnerPopulationSize.setModel(new SpinnerNumberModel(new Integer(100), null, null, new Integer(1)));
		panel.add(spinnerPopulationSize);

		lblMutationRate = new JLabel("Mutation Rate");
		lblMutationRate.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblMutationRate);

		spinnerMutationRate = new JSpinner();
		spinnerMutationRate.setModel(new SpinnerNumberModel(new Double(0.2), null, null, new Double(0.01)));
		panel.add(spinnerMutationRate);

		lblTournamentSize = new JLabel("Tournament Size");
		lblTournamentSize.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTournamentSize);

		spinnerTournamentSize = new JSpinner();
		spinnerTournamentSize.setModel(new SpinnerNumberModel(new Integer(2), null, null, new Integer(1)));
		panel.add(spinnerTournamentSize);

		lblCrossoverType = new JLabel("Crossover Type");
		lblCrossoverType.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblCrossoverType);

		comboCrossoverType = new JComboBox<String>();
		comboCrossoverType.setModel(new DefaultComboBoxModel<String>(new String[] {"One-Point", "Two-Point"}));
		panel.add(comboCrossoverType);

		lblResult = new JLabel("Result");
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblResult);

		lblShowResult = new JLabel("");
		lblShowResult.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblShowResult);

		btnRun = new JButton("Run");
		btnRun.setActionCommand("start");
		btnRun.addActionListener(this);
		/*btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int problemId = (int) problemNumCombo.getSelectedItem();
				double mutationRate = (double) spinnerMutationRate.getValue();
				int tournamentSize = (int) spinnerTournamentSize.getValue();
				int populationSize = (int) spinnerPopulationSize.getValue();
				String crossoverType = (String)comboCrossoverType.getSelectedItem();
				int itterations = (int) spinnerItterations.getValue();


				MyEA ea = new MyEA(problemId, mutationRate, tournamentSize, populationSize, lblShowResult, itterations, crossoverType);

				int[][] result = new int[ea.getNumOfMachines()][ea.getNumOfJobs()];

				int iter=0;
				while(iter<itterations){
					int id1 = ea.tournamentSelect();
					int id2 = ea.tournamentSelect();

					if(crossoverType.equals("One-Point")){
						result = ea.onePointCrossover(id1, id2);
					}else{
						result = ea.twoPointCrossover(id1, id2);
					}

					int[][] mutant = ea.mutate(result);	

					ea.replace(mutant);

					//if(iter%10==0){
					//	System.out.println("Iter : " +iter);
						//ea.getBestChromo();
					//}
					iter++;
				}			

				lblShowResult.setText(Integer.toString(ea.getBestChromo()));
			}
		});*/

		panel.add(btnRun);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ea.cancel(true);
			}});
		panel.add(btnCancel);
		
		btnViewFitnesses = new JButton("View Fitnesses");
		panel.add(btnViewFitnesses);
		
		btnViewPopulatuon = new JButton("View Population");
		panel.add(btnViewPopulatuon);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ea.saveBestSol();
			}
		});
		panel.add(btnSave);
		
		btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(MainWindow.this);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            loadSol(file.getName());
		        } 
			}
		});
		panel.add(btnLoad);
		btnViewPopulatuon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PopulationWindow pw = new PopulationWindow();
				ea.ViewPopulations(pw.getTextArea(), pw.getTextArea_1());
				pw.setVisible(true);
			}
		});
		btnViewFitnesses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FitnessWindow fw = new FitnessWindow();
				ea.ViewFitnesses(fw.getTextArea(), fw.getTextArea_1(), fw.getBestFitArray(), fw.getBestFitFunc());
				fw.setVisible(true);
			}
		});
		
				progressBar = new JProgressBar();
				progressBar.setBounds(10, 224, 233, 14);
				contentPane.add(progressBar);
				
				btnTest = new JButton("Test");
				btnTest.setEnabled(false);
				btnTest.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ea.test();
					}
				});
				btnTest.setBounds(83, 249, 89, 23);
				contentPane.add(btnTest);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			progressBar.setValue(progress);
		} 
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		btnRun.setEnabled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		//Instances of javax.swing.SwingWorker are not reusuable, so
		//we create new instances as needed.
		int problemId = (int) problemNumCombo.getSelectedItem();
		double mutationRate = (double) spinnerMutationRate.getValue();
		int tournamentSize = (int) spinnerTournamentSize.getValue();
		int populationSize = (int) spinnerPopulationSize.getValue();
		String crossoverType = (String)comboCrossoverType.getSelectedItem();
		int itterations = (int) spinnerItterations.getValue();


		ea = new MyEA(problemId, mutationRate, tournamentSize, populationSize, lblShowResult, itterations, crossoverType, btnRun);

		ea.addPropertyChangeListener(this);
		ea.execute();

	}
	
	public void loadSol(String filename){
		int[][] solution2 = JSSP.loadSolution(filename); 

		int id = JSSP.getProblemIdFromSolution(filename);
		Problem problem2 = JSSP.getProblem(id);

		JSSP.printSolution(solution2, problem2);


		/**
		 * Display a saved solution graphically 
		 */
		JSSP.displaySolution(filename);	
	}
}
