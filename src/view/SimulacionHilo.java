package view;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import main.Principal;

public class SimulacionHilo extends SwingWorker<int[], Integer>{

	private Principal _principal;
	private JProgressBar _barra;
	private List<JTextField> _arrayTextFields;

	
	public SimulacionHilo(Principal principal,JProgressBar barra, List<JTextField> arrayTextFields) {
		_principal = principal;
		_barra=barra;
		_arrayTextFields = arrayTextFields;

	}
	
	
	@Override
	protected int[] doInBackground() throws Exception {
		_barra.setIndeterminate(true);
		_principal.correSimulacion();
		
		return null;
	}
	
	
	@Override
	public void done() {

			mostrarEstadisticas();
			_barra.setIndeterminate(false);
	}

	
	private void mostrarEstadisticas() {
		_arrayTextFields.get(0).setText(String.valueOf(_principal.CantPaquetesPromedio()));
		_arrayTextFields.get(1).setText(String.valueOf(_principal.CantPaquetesPromedioPorUsuario()));
		_arrayTextFields.get(2).setText(String.valueOf(_principal.CantPaquetesOptimo()));
		_arrayTextFields.get(3).setText(String.valueOf(_principal.CantPaquetesOptimoPorUsuario()));
		_arrayTextFields.get(4).setText(String.valueOf(_principal.CantPaquetesPeor()));
		_arrayTextFields.get(5).setText(String.valueOf(_principal.CantPaquetesPeorPorUsuario()));
	}
	

}
