package set10107;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class FitnessWindow extends JFrame {

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

	private JLabel lblBestFitness;
	private JLabel bestFitArray;


	public FitnessWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 266, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblFitnesses = new JLabel("Fitnesses");
		lblFitnesses.setHorizontalAlignment(SwingConstants.CENTER);
		lblFitnesses.setBounds(93, 11, 46, 14);
		contentPane.add(lblFitnesses);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 36, 178, 286);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		lblBestFitness = new JLabel("Best Fitness");
		lblBestFitness.setHorizontalAlignment(SwingConstants.CENTER);
		lblBestFitness.setBounds(81, 333, 72, 14);
		contentPane.add(lblBestFitness);
		
		bestFitArray = new JLabel("");
		bestFitArray.setHorizontalAlignment(SwingConstants.CENTER);
		bestFitArray.setBounds(81, 359, 72, 14);
		contentPane.add(bestFitArray);
	}
	
	public JLabel getBestFitArray() {
		return this.bestFitArray;
	}
}
