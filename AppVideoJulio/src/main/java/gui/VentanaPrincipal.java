package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.Controlador;
import pulsador.IEncendidoListener;
import pulsador.Luz;
import tds.video.VideoWeb;
import umu.tds.componente.CargadorVideos;
import umu.tds.componente.Video;
import umu.tds.componente.VideoEvent;
import umu.tds.componente.Videos;

public class VentanaPrincipal {
	private JFrame frame;
	private VideoWeb videoWeb;
	private Controlador controlador;
	private CargadorVideos cv = new CargadorVideos();
	private List<String> etiquetasActuales;
	public Videos videosCargados;
	
	public VentanaPrincipal(VideoWeb videoweb) {
		controlador = Controlador.getUnicaInstancia();
		this.videoWeb = videoweb;
		initialize();
		frame.setVisible(true);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 325);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblAppvideo = new JLabel("APPVIDEO");
		lblAppvideo.setForeground(Color.RED);
		lblAppvideo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAppvideo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		panel.add(lblAppvideo);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new VentanaLoginRegistro(videoWeb);
			}
		});

		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.anchor = GridBagConstraints.WEST;
		gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogin.gridx = 3;
		gbc_btnLogin.gridy = 6;
		panel.add(btnLogin,gbc_btnLogin);
		
		JButton btnRegistro = new JButton("Registro");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaRegistro(frame);
			}
		});
		GridBagConstraints gbc_btnRegistro = new GridBagConstraints();
		gbc_btnRegistro.anchor = GridBagConstraints.EAST;
		gbc_btnRegistro.insets = new Insets(0, 0, 0, 5);
		gbc_btnRegistro.gridx = 4;
		gbc_btnRegistro.gridy = 6;
		panel.add(btnRegistro, gbc_btnRegistro);
		
		JButton btnSalir = new JButton("Salir de AppVideo");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.gridx = 5;
		gbc_btnSalir.gridy = 6;
		panel.add(btnSalir, gbc_btnSalir);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new VentanaLoginRegistro(videoWeb);
			}
		});
	
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogin.anchor = GridBagConstraints.WEST;
		gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogin.gridx = 3;
		gbc_btnLogin.gridy = 6;
		panel.add(btnLogout,gbc_btnLogout);
		
		JButton btnPremium = new JButton("Premium");
		btnPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//to do
			}
		});
	
		GridBagConstraints gbc_btnPremium = new GridBagConstraints();
		gbc_btnLogin.anchor = GridBagConstraints.WEST;
		gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogin.gridx = 3;
		gbc_btnLogin.gridy = 6;
		panel.add(btnPremium,gbc_btnPremium);
		
		
		
		JPanel panel1 = new JPanel();
		frame.getContentPane().add(panel1, BorderLayout.CENTER);
		cv.addVideoListener(controlador);
		final Luz luz_1 = new Luz();
		panel1.add(luz_1);
		luz_1.addEncendidoListener(new IEncendidoListener() {
			public void enteradoCambioEncendido(EventObject arg0) {
				JFileChooser fc = new JFileChooser();
				int seleccion = fc.showOpenDialog(frame);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File fichero = fc.getSelectedFile();
					String seleccionado = fichero.getAbsolutePath();
					//Llamar a la funcion que asigna el fichero al componente.
					System.out.println("Seleccionado fichero : " + seleccionado);
					cv.setArchivosVideos(seleccionado);
					etiquetasActuales = new ArrayList<String>();
					//VideoEvent ve=new VideoEvent(luz_1, videosCargados);
					//cv.notificarCambio(ve);
					System.out.println("he notificado el cambio");
				}
			}
		});
		
		JLabel lblUser = new JLabel("Hola "+controlador.getUsuarioActual().getNombre());
		lblUser.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUser.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		panel1.add(lblUser);
		
		JButton btnExplorar= new JButton("Explorar");
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new VentanaExplorar(videoWeb);
				frame.dispose();
			}
		});

		GridBagConstraints gbc_btnExplorar = new GridBagConstraints();
		gbc_btnExplorar.anchor = GridBagConstraints.WEST;
		gbc_btnExplorar.insets = new Insets(0, 0, 0, 5);
		gbc_btnExplorar.gridx = 3;
		gbc_btnExplorar.gridy = 6;
		panel1.add(btnExplorar,gbc_btnExplorar);
		
		JButton btnMisListas= new JButton("Mis Listas");
		btnMisListas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new VentanaMisListas(videoWeb);
				frame.dispose();
			}
		});

		GridBagConstraints gbc_btnMisListas = new GridBagConstraints();
		gbc_btnMisListas.anchor = GridBagConstraints.WEST;
		gbc_btnMisListas.insets = new Insets(0, 0, 0, 5);
		gbc_btnMisListas.gridx = 3;
		gbc_btnMisListas.gridy = 6;
		panel1.add(btnMisListas,gbc_btnMisListas);
		
		JButton btnRecientes= new JButton("Recientes");
		btnRecientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new VentanaRecientes(videoWeb);
				frame.dispose();
			}
		});

		GridBagConstraints gbc_btnRecientes = new GridBagConstraints();
		gbc_btnRecientes.anchor = GridBagConstraints.WEST;
		gbc_btnRecientes.insets = new Insets(0, 0, 0, 5);
		gbc_btnRecientes.gridx = 3;
		gbc_btnRecientes.gridy = 6;
		panel1.add(btnRecientes,gbc_btnRecientes);
		
		
		JButton btnNuevaLista= new JButton("Nueva Lista");
		btnNuevaLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new VentanaNuevaLista(videoWeb);
				frame.dispose();
			}
		});

		GridBagConstraints gbc_btnNuevaLista = new GridBagConstraints();
		gbc_btnNuevaLista.anchor = GridBagConstraints.WEST;
		gbc_btnNuevaLista.insets = new Insets(0, 0, 0, 5);
		gbc_btnNuevaLista.gridx = 3;
		gbc_btnNuevaLista.gridy = 6;
		panel1.add(btnNuevaLista,gbc_btnNuevaLista);
		

		
		/*
		   JTextField textFieldBuscar = new JTextField();
	        GridBagConstraints gbc_textFieldBuscar = new GridBagConstraints();
	        gbc_textFieldBuscar.gridwidth = 6;
	        gbc_textFieldBuscar.insets = new Insets(0, 0, 0, 5);
	        gbc_textFieldBuscar.fill = GridBagConstraints.HORIZONTAL;
	        gbc_textFieldBuscar.gridx = 1;
	        gbc_textFieldBuscar.gridy = 0;
	        panel1.add(textFieldBuscar, gbc_textFieldBuscar);
	        textFieldBuscar.setColumns(10);
			
	        
			JButton btnBuscar= new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//to do
				}
			});

			GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
			gbc_btnBuscar.anchor = GridBagConstraints.WEST;
			gbc_btnBuscar.insets = new Insets(0, 0, 0, 5);
			gbc_btnBuscar.gridx = 3;
			gbc_btnBuscar.gridy = 6;
			panel1.add(btnBuscar,gbc_btnBuscar);
			
			JButton btnBuscarNuevo= new JButton("Nueva Busqueda");
			btnBuscarNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//to do
				}
			});

			GridBagConstraints gbc_btnBuscarNuevo = new GridBagConstraints();
			gbc_btnBuscarNuevo.anchor = GridBagConstraints.WEST;
			gbc_btnBuscarNuevo.insets = new Insets(0, 0, 0, 5);
			gbc_btnBuscarNuevo.gridx = 3;
			gbc_btnBuscarNuevo.gridy = 6;
			panel1.add(btnBuscarNuevo,gbc_btnBuscarNuevo);
			
			//faltan las etiquetas*/
	}
	        
	public void mostrarVentana() {
		frame.setVisible(true);
		
	}
}
