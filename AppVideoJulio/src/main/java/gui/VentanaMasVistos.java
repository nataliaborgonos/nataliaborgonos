package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.Controlador;
import dominio.ListaVideos;
import dominio.Video;
import tds.video.VideoWeb;

public class VentanaMasVistos {
	private JFrame frame;
	private VideoWeb videoWeb;
	private Controlador controlador;
	private boolean usuarioPremium;
	private ModeloTablaTop modelo;
	private JTable tabla;
	private int filaSeleccionada;
	
	public VentanaMasVistos(VideoWeb videoweb) {
		controlador=Controlador.getUnicaInstancia();
		this.videoWeb = videoweb;
		initialize();
		frame.setVisible(true);
	} 
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		final JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblAppvideo = new JLabel("APPVIDEO");
		lblAppvideo.setForeground(Color.RED);
		lblAppvideo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAppvideo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		panel.add(lblAppvideo);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//to do
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
				controlador.hacerPremium();
				if(controlador.isPremium()) {
					usuarioPremium=true;
					JOptionPane.showMessageDialog(frame,"Tu usuario ha pasado a ser premium");	
				} else {
					usuarioPremium=false;
					JOptionPane.showMessageDialog(frame,"Tu usuario ha dejado de ser premium");
					new VentanaRecientes(videoWeb);
					frame.dispose();
					//SwingUtilities.updateComponentTreeUI(frame);
				}
				}
		});
	
		GridBagConstraints gbc_btnPremium = new GridBagConstraints();
		gbc_btnPremium.anchor = GridBagConstraints.WEST;
		gbc_btnPremium.insets = new Insets(0, 0, 0, 5);
		gbc_btnPremium.gridx = 3;
		gbc_btnPremium.gridy = 6;
		panel.add(btnPremium,gbc_btnPremium);
		
	
		
		JPanel panel1 = new JPanel();
		frame.getContentPane().add(panel1, BorderLayout.CENTER);
				JLabel lblUser = new JLabel("Hola "+controlador.getUsuarioActual().getNombre());
				lblUser.setHorizontalAlignment(SwingConstants.TRAILING);
				lblUser.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
				panel1.add(lblUser);
		
		JButton btnExplorar= new JButton("Explorar");
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//to do
				new VentanaExplorar(videoWeb);
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
				//to do
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
				//new VentanaMisListas(videoWeb);
				//frame.dispose();
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
		
		JButton btnPrincipal= new JButton("Carga Videos");
		btnPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new VentanaPrincipal(videoWeb);
				frame.dispose();
			}
		});

		GridBagConstraints gbc_btnPrincipal = new GridBagConstraints();
		gbc_btnPrincipal.anchor = GridBagConstraints.WEST;
		gbc_btnPrincipal.insets = new Insets(0, 0, 0, 5);
		gbc_btnPrincipal.gridx = 3;
		gbc_btnPrincipal.gridy = 6;
		panel1.add(btnPrincipal,gbc_btnPrincipal);
		
		JPanel panelVideos=new JPanel();
		frame.getContentPane().add(panelVideos,BorderLayout.WEST);
		JLabel lblLista = new JLabel("TOP TEN - Lista de videos más vistos por los usuarios:");
		lblLista.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLista.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		panelVideos.add(lblLista);
		tabla = new JTable(new ModeloTablaTop()) {
			// De esta forma no se pueden editar las celdas de la tabla
			public boolean editCellAt(int fila, int columna, java.util.EventObject e) {
	            return false;
	        }
		};
		tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		tabla.setFont(new Font("Arial", Font.PLAIN, 14));
		tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
		//tabla.getColumnModel().getColumn(1).setPreferredWidth(20);
		tabla.setRowHeight(60);
		panelVideos.add(tabla);
		JScrollPane scrollPane = new JScrollPane(tabla);
		panelVideos.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(350, 200));
		panelVideos.setPreferredSize(new Dimension(375, 175));
		// Añadimos el listener para que se marque la cancion seleccionada de la tabla
		tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
	            setFilaSeleccionada(tabla.getSelectedRow());
	        }
		});
		panelVideos.setVisible(true);
		controlador.actualizarTopTen();
		ListaVideos listaMasVistos= controlador.getTopTen();
		if(controlador.topTenEmpty()) {System.out.println("top ten vacia");}
		else {
		for(Video v : listaMasVistos.getLista()) {
			modelo = (ModeloTablaTop) tabla.getModel();
			JLabel label = new JLabel(v.getTitulo());
			label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
			ImageIcon thumb = videoWeb.getThumb(v.getUrl());
        	label.setIcon(thumb);
			modelo.addRow(new Object[]{label.getIcon(),label.getText(),v.getNumReproducciones()});
		}
		}
		
	}
	
	public void setFilaSeleccionada(int filaSeleccionada) {
		this.filaSeleccionada = filaSeleccionada;
	}
}