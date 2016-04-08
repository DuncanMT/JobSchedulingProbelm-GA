package set10107;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PopulationWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3311596218938494736L;
	private JPanel contentPane;
	private JLabel lblNewPopulation;
	private JLabel lblOrigonalPopulation;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTextArea textArea;
	private JTextArea textArea_1;

	
	public PopulationWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1033, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewPopulation = new JLabel("New Population");
		lblNewPopulation.setBounds(385, 27, 110, 14);
		contentPane.add(lblNewPopulation);
		
		lblOrigonalPopulation = new JLabel("Origonal Population");
		lblOrigonalPopulation.setBounds(385, 282, 110, 14);
		contentPane.add(lblOrigonalPopulation);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 48, 971, 223);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(24, 319, 971, 205);
		contentPane.add(scrollPane_1);
		
		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
	}


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

}
