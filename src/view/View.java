package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import auxiliares.OpcionesDeSimulacion;
import main.Principal;

import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View {

	private JFrame frame;
	private JTextField TF_tamañoAlbum;
	private JTextField TF_cantUsuarios;
	private JTextField TF_cantSimulaciones;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel ET_TamañoAlbum;
	private JLabel ET_CantUsuarios;
	private JLabel ET_TipoSimulacion;
	private JLabel ET_CantSimulaciones;
	private JLabel ET_Estadisticas;
	private JLabel ET_PaqProm;
	private JLabel ET_Tot;
	private JLabel ET_MejorCaso;
	private JLabel ET_Us;
	private JLabel ET_PeorCaso;
	private JLabel ET_Titulo;
	private JLabel ET_Advertencia;
	private JPanel panel;
	private JPanel panelAdvertencia;
	private JButton BTN_Iniciar;
	private JComboBox comboBox;
	private int tamanoAlbum;
	private int cantUsuarios;
	private int cantSimulaciones;
	private OpcionesDeSimulacion opcionElegida;
	private Principal principal;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(0, 0, 51));
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 51, 0));
		panel.setBounds(382, 81, 492, 316);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		panelAdvertencia = new JPanel();
		panelAdvertencia.setBackground(new Color(244, 164, 96));
		panelAdvertencia.setBounds(10, 364, 362, 57);
		frame.getContentPane().add(panelAdvertencia);
		panelAdvertencia.setLayout(null);
		panelAdvertencia.setVisible(false);
		
		IniciaEtiquetas();
		
		IniciaCamposDeTexto();
		
		comboBox = new JComboBox();
		comboBox.setBounds(145, 268, 170, 22);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Un solo Usuario", "N usuarios Colaborativos","N Usuarios No Colaborativos"}));
		frame.getContentPane().add(comboBox);
		
		BTN_Iniciar = new JButton("INICIAR SIMULACION");
		BTN_Iniciar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				limpiaAdvertencia();
				limpiaEstadisticas();
				levantaDatosCargados();
				
				if(validaDatos()) {
					principal = new Principal(tamanoAlbum, cantSimulaciones, cantUsuarios, opcionElegida);
					
					principal.correSimulacion();
					
					muestraEstadisticas();
				}
				else {
					System.out.println("advertencia");
					lanzaAdvertencia();
				}	
			}

		});
		
		BTN_Iniciar.setBounds(95, 311, 170, 35);
		frame.getContentPane().add(BTN_Iniciar);
		
	}
	
	private void lanzaAdvertencia() {
		panelAdvertencia.setVisible(true);
		ET_Advertencia.setVisible(true);
		ET_Advertencia.setText("Hay un erro en los datos ingresados");
	}
	
	private void limpiaAdvertencia() {
		panelAdvertencia.setVisible(false);
		ET_Advertencia.setVisible(false);
		
	}
	
	private boolean validaDatos() {
		
		boolean val1 =  tamanoAlbum >0;
		boolean val2 = cantSimulaciones >0;
		boolean val3 = cantUsuarios >0;
		boolean val4;
		
		if(opcionElegida == OpcionesDeSimulacion.UN_USUARIO && cantUsuarios !=1) {
			val4=false;
		}
		else {
			val4=true;
		}
		
		return val1 && val2 && val3 && val4;

	}
	
	private void muestraEstadisticas() {
		textField.setText(String.valueOf(principal.CantPaquetesPromedio()));
		textField_1.setText(String.valueOf(principal.CantPaquetesPromedioPorUsuario()));
		textField_2.setText(String.valueOf(principal.CantPaquetesOptimo()));
		textField_3.setText(String.valueOf(principal.CantPaquetesOptimoPorUsuario()));
		textField_4.setText(String.valueOf(principal.CantPaquetesPeor()));
		textField_5.setText(String.valueOf(principal.CantPaquetesPeorPorUsuario()));
	}
	
	private void limpiaEstadisticas() {
		textField.setText(String.valueOf(""));
		textField_1.setText(String.valueOf(""));
		textField_2.setText(String.valueOf(""));
		textField_3.setText(String.valueOf(""));
		textField_4.setText(String.valueOf(""));
		textField_5.setText(String.valueOf(""));
		
	}
	
	private void levantaDatosCargados() {
		
		String str=TF_tamañoAlbum.getText();
		tamanoAlbum = Integer.parseInt(str);
		
		str=TF_cantUsuarios.getText();
		cantUsuarios = Integer.parseInt(str);
		
		str=TF_cantSimulaciones.getText();
		cantSimulaciones = Integer.parseInt(str);
		
		opcionElegida = devuelveOpcioneElegida(comboBox.getSelectedIndex());
		
	}
	
	private OpcionesDeSimulacion devuelveOpcioneElegida(int num) {
		
		OpcionesDeSimulacion[] opciones = {OpcionesDeSimulacion.UN_USUARIO, OpcionesDeSimulacion.N_USUARIOS_COLABORATIVOS, OpcionesDeSimulacion.N_USUARIOS_NOCOLABORATIVOS};
		 
		return opciones[num];
	}
	
	private void IniciaEtiquetas() {
		ET_TamañoAlbum = new JLabel("Tamaño del album");
		ET_TamañoAlbum.setHorizontalAlignment(SwingConstants.RIGHT);
		ET_TamañoAlbum.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		ET_TamañoAlbum.setBounds(10, 96, 156, 28);
		frame.getContentPane().add(ET_TamañoAlbum);
		
		ET_CantUsuarios = new JLabel("Cantidad de usuarios");
		ET_CantUsuarios.setHorizontalAlignment(SwingConstants.RIGHT);
		ET_CantUsuarios.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		ET_CantUsuarios.setBounds(10, 135, 156, 28);
		frame.getContentPane().add(ET_CantUsuarios);
		
		ET_TipoSimulacion = new JLabel("Tipo de Simulacion");
		ET_TipoSimulacion.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		ET_TipoSimulacion.setBounds(10, 264, 156, 28);
		frame.getContentPane().add(ET_TipoSimulacion);
		
		ET_CantSimulaciones = new JLabel("Cantidad de Simulaciones");
		ET_CantSimulaciones.setHorizontalAlignment(SwingConstants.RIGHT);
		ET_CantSimulaciones.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		ET_CantSimulaciones.setBounds(0, 175, 170, 28);
		frame.getContentPane().add(ET_CantSimulaciones);
		
		ET_Estadisticas = new JLabel("ESTADISTICAS DE LA SIMULACION");
		ET_Estadisticas.setForeground(new Color(255, 255, 255));
		ET_Estadisticas.setBounds(117, 35, 274, 18);
		ET_Estadisticas.setHorizontalAlignment(SwingConstants.CENTER);
		ET_Estadisticas.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		panel.add(ET_Estadisticas);
		
		ET_PaqProm = new JLabel("PAQUETES PROMEDIO");
		ET_PaqProm.setHorizontalAlignment(SwingConstants.RIGHT);
		ET_PaqProm.setForeground(Color.WHITE);
		ET_PaqProm.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		ET_PaqProm.setBounds(10, 131, 220, 18);
		panel.add(ET_PaqProm);
		
		ET_Tot = new JLabel("TOTAL");
		ET_Tot.setHorizontalAlignment(SwingConstants.RIGHT);
		ET_Tot.setForeground(Color.WHITE);
		ET_Tot.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		ET_Tot.setBounds(246, 101, 58, 18);
		panel.add(ET_Tot);
		
		ET_MejorCaso = new JLabel("MEJOR CASO");
		ET_MejorCaso.setHorizontalAlignment(SwingConstants.RIGHT);
		ET_MejorCaso.setForeground(Color.WHITE);
		ET_MejorCaso.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		ET_MejorCaso.setBounds(20, 174, 210, 18);
		panel.add(ET_MejorCaso);
		
		ET_Us = new JLabel("/USUARIO");
		ET_Us.setHorizontalAlignment(SwingConstants.RIGHT);
		ET_Us.setForeground(Color.WHITE);
		ET_Us.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		ET_Us.setBounds(321, 101, 86, 18);
		panel.add(ET_Us);
		
		ET_PeorCaso = new JLabel("PEOR CASO");
		ET_PeorCaso.setHorizontalAlignment(SwingConstants.RIGHT);
		ET_PeorCaso.setForeground(Color.WHITE);
		ET_PeorCaso.setFont(new Font("Arial Narrow", Font.BOLD, 18));
		ET_PeorCaso.setBounds(10, 219, 220, 18);
		panel.add(ET_PeorCaso);
		
		ET_Titulo = new JLabel("SIMULACION DE LLENADO DEL ALBUM");
		ET_Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		ET_Titulo.setForeground(Color.DARK_GRAY);
		ET_Titulo.setFont(new Font("Arial Narrow", Font.BOLD, 24));
		ET_Titulo.setBounds(178, 11, 446, 44);
		frame.getContentPane().add(ET_Titulo);
		
		ET_Advertencia = new JLabel("");
		ET_Advertencia.setFont(new Font("Snap ITC", Font.BOLD, 15));
		ET_Advertencia.setBounds(10, 11, 342, 35);
		panelAdvertencia.add(ET_Advertencia);
		ET_Advertencia.setVisible(false);
		
	}

	private void IniciaCamposDeTexto() {
		TF_tamañoAlbum = new JTextField();
		TF_tamañoAlbum.setFont(new Font("Tahoma", Font.BOLD, 16));
		TF_tamañoAlbum.setHorizontalAlignment(SwingConstants.CENTER);
		TF_tamañoAlbum.setBounds(178, 96, 55, 28);
		frame.getContentPane().add(TF_tamañoAlbum);
		TF_tamañoAlbum.setColumns(10);
		
		TF_cantUsuarios = new JTextField();
		TF_cantUsuarios.setFont(new Font("Tahoma", Font.BOLD, 16));
		TF_cantUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		TF_cantUsuarios.setColumns(10);
		TF_cantUsuarios.setBounds(178, 134, 55, 28);
		frame.getContentPane().add(TF_cantUsuarios);
		
		TF_cantSimulaciones = new JTextField();
		TF_cantSimulaciones.setFont(new Font("Tahoma", Font.BOLD, 16));
		TF_cantSimulaciones.setHorizontalAlignment(SwingConstants.CENTER);
		TF_cantSimulaciones.setColumns(10);
		TF_cantSimulaciones.setBounds(178, 176, 55, 28);
		frame.getContentPane().add(TF_cantSimulaciones);
		
		textField = new JTextField();
		textField.setDisabledTextColor(new Color(0, 100, 0));
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);
		textField.setBounds(256, 128, 55, 31);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setDisabledTextColor(new Color(0, 100, 0));
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setColumns(10);
		textField_1.setBounds(336, 128, 55, 31);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setDisabledTextColor(new Color(0, 100, 0));
		textField_2.setEnabled(false);
		textField_2.setEditable(false);
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setColumns(10);
		textField_2.setBounds(256, 171, 55, 31);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setDisabledTextColor(new Color(0, 100, 0));
		textField_3.setEnabled(false);
		textField_3.setEditable(false);
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setColumns(10);
		textField_3.setBounds(336, 171, 55, 31);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setDisabledTextColor(new Color(0, 100, 0));
		textField_4.setEnabled(false);
		textField_4.setEditable(false);
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setColumns(10);
		textField_4.setBounds(256, 214, 55, 31);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setDisabledTextColor(new Color(0, 100, 0));
		textField_5.setEnabled(false);
		textField_5.setEditable(false);
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setColumns(10);
		textField_5.setBounds(336, 214, 55, 31);
		panel.add(textField_5);
	}
}
