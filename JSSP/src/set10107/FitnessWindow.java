package set10107;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class FitnessWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9178352040948423709L;
	
	private JPanel contentPane;
	private JLabel lblFitnesses;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	public JTextArea getTextArea() {
		return textArea;
	}


	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}


	public JTextArea getTextArea_1() {
		return textArea_1;
	}


	public void setTextArea_1(JTextArea textArea_1) {
		this.textArea_1 = textArea_1;
	}


	private JScrollPane scrollPane_1;
	private JTextArea textArea_1;
	private JLabel lblBestFitness;
	private JLabel lblBestFitness_1;
	private JLabel bestFitArray;
	private JLabel bestFitFunc;


	public FitnessWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblFitnesses = new JLabel("Fitnesses");
		lblFitnesses.setBounds(190, 11, 46, 14);
		contentPane.add(lblFitnesses);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 36, 178, 286);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(234, 36, 163, 286);
		contentPane.add(scrollPane_1);
		
		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		lblBestFitness = new JLabel("Best Fitness");
		lblBestFitness.setBounds(100, 333, 72, 14);
		contentPane.add(lblBestFitness);
		
		lblBestFitness_1 = new JLabel("Best Fitness");
		lblBestFitness_1.setBounds(288, 333, 65, 14);
		contentPane.add(lblBestFitness_1);
		
		bestFitArray = new JLabel("");
		bestFitArray.setBounds(100, 358, 72, 14);
		contentPane.add(bestFitArray);
		
		bestFitFunc = new JLabel("");
		bestFitFunc.setBounds(288, 358, 65, 14);
		contentPane.add(bestFitFunc);
	}


	public JLabel getBestFitArray() {
		return bestFitArray;
	}


	public void setBestFitArray(JLabel bestFitArray) {
		this.bestFitArray = bestFitArray;
	}


	public JLabel getBestFitFunc() {
		return bestFitFunc;
	}


	public void setBestFitFunc(JLabel bestFitFunc) {
		this.bestFitFunc = bestFitFunc;
	}
}
