package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import dominio.ListaVideos;
import dominio.RepositorioVideos;
import dominio.Video;
import tds.video.VideoWeb;

public class VentanaNuevaLista {
	private JFrame frame;
	private VideoWeb videoWeb;
	private Controlador controlador;	
	private ModeloTabla modelo;
	private JTable tabla;
	private JTable tablaContenido;
	private Video videoSeleccionado;
	private ListaVideos actual;
	
	public VentanaNuevaLista(VideoWeb videoweb) {
		controlador=Controlador.getUnicaInstancia();
		this.videoWeb = videoweb;
		initialize();
		frame.setVisible(true);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 500);
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
				JLabel lblUser = new JLabel("Hola "+controlador.getUsuarioActual().getNombre());
				lblUser.setHorizontalAlignment(SwingConstants.TRAILING);
				lblUser.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
				panel1.add(lblUser);
		
		JButton btnExplorar= new JButton("Explorar");
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//to do
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
				//to do
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
		
		
		   final JTextField textFieldBuscar = new JTextField();
	        GridBagConstraints gbc_textFieldBuscar = new GridBagConstraints();
	        gbc_textFieldBuscar.gridwidth = 6;
	        gbc_textFieldBuscar.insets = new Insets(0, 0, 0, 5);
	        gbc_textFieldBuscar.fill = GridBagConstraints.HORIZONTAL;
	        gbc_textFieldBuscar.gridx = 1;
	        gbc_textFieldBuscar.gridy = 0;
	        panel1.add(textFieldBuscar, gbc_textFieldBuscar);
	        textFieldBuscar.setColumns(10);
	        
			JButton btnBuscar= new JButton("Buscar titulo");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String auxTitulo=textFieldBuscar.getText().trim();
					//	Set<String> videos = RepositorioVideos.getUnicaInstancia().getTitulos();
						List<Video> videos = RepositorioVideos.getUnicaInstancia().getVideos();
						for(Video v : videos) {
							if(v.getTitulo().equals(auxTitulo)) {
								modelo = (ModeloTabla) tabla.getModel();
								JLabel label = new JLabel(v.getTitulo());
			        			label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
			        			ImageIcon thumb = videoWeb.getThumb(v.getUrl());
			                	label.setIcon(thumb);
							//	modelo.insertRow(0, new Object[]{label});
								modelo.addRow(new Object[]{label.getIcon(),label.getText()});
								videoSeleccionado=RepositorioVideos.getUnicaInstancia().getVideo(auxTitulo);
							}
				}
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
					if (modelo != null) {
						modelo.setRowCount(0);
					}
				}
			});

			GridBagConstraints gbc_btnBuscarNuevo = new GridBagConstraints();
			gbc_btnBuscarNuevo.anchor = GridBagConstraints.WEST;
			gbc_btnBuscarNuevo.insets = new Insets(0, 0, 0, 5);
			gbc_btnBuscarNuevo.gridx = 3;
			gbc_btnBuscarNuevo.gridy = 6;
			panel1.add(btnBuscarNuevo,gbc_btnBuscarNuevo);
			

			tabla = new JTable(new ModeloTabla()) {
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
			panel1.add(tabla);
			JScrollPane scrollPane1 = new JScrollPane(tabla);
			panel1.add(scrollPane1);
			scrollPane1.setPreferredSize(new Dimension(350, 200));
			panel1.setPreferredSize(new Dimension(375, 175));
			// Añadimos el listener para que se marque el video seleccionado de la tabla
			
			tablaContenido = new JTable(new ModeloTabla()) {
				// De esta forma no se pueden editar las celdas de la tabla
				public boolean editCellAt(int fila, int columna, java.util.EventObject e) {
		            return false;
		        }
			};
			tablaContenido.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
			tablaContenido.setFont(new Font("Arial", Font.PLAIN, 14));
			tablaContenido.getColumnModel().getColumn(0).setPreferredWidth(20);
			//tabla.getColumnModel().getColumn(1).setPreferredWidth(20);
			tablaContenido.setRowHeight(60);
			panel1.add(tablaContenido);
			JScrollPane scrollPane2 = new JScrollPane(tablaContenido);
			panel1.add(scrollPane2);
			scrollPane2.setPreferredSize(new Dimension(350, 200));
			panel1.setPreferredSize(new Dimension(375, 175));
			// Añadimos el listener para que se marque la cancion seleccionada de la tabla
			
			JLabel labelPos=new JLabel();
			labelPos.setText("La lista de la izquierda muestra el contenido de la lista que se está tratando");
			panel1.add(labelPos);
			
			JPanel panel2 = new JPanel();
			frame.getContentPane().add(panel2, BorderLayout.SOUTH);
			
			JLabel lblLista = new JLabel("Introduce el nombre de la lista:");
			lblLista.setHorizontalAlignment(SwingConstants.TRAILING);
			lblLista.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
			panel2.add(lblLista);
			
			  final JTextField textFieldNombre = new JTextField();
		        GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		        gbc_textFieldNombre.gridwidth = 6;
		        gbc_textFieldNombre.insets = new Insets(0, 0, 0, 5);
		        gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		        gbc_textFieldNombre.gridx = 1;
		        gbc_textFieldNombre.gridy = 0;
		        panel2.add(textFieldNombre, gbc_textFieldNombre);
		        textFieldNombre.setColumns(10);
			
			JButton btnBuscaLista= new JButton("Buscar");
			btnBuscaLista.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					List<ListaVideos> lv= controlador.getUsuarioActual().getListaVideos();
					for(ListaVideos l : lv) {
						if(l.getNombreLista().equals(textFieldNombre.getText().trim())) {
							actual=l;
							JOptionPane.showMessageDialog(frame, "Has elegido la lista "+l.getNombreLista());
						}
					}
					for(Video v : actual.getLista()) {
						modelo = (ModeloTabla) tablaContenido.getModel();
						JLabel label = new JLabel(v.getTitulo());
	        			label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
	        			ImageIcon thumb = videoWeb.getThumb(v.getUrl());
	                	label.setIcon(thumb);
					//	modelo.insertRow(0, new Object[]{label});
						modelo.addRow(new Object[]{label.getIcon(),label.getText()});
						//modelo.insertRow(0, new Object[]{v.getTitulo()});	
					}
				}
			});
			
			GridBagConstraints gbc_btnBuscaLista = new GridBagConstraints();
			gbc_btnBuscaLista.anchor = GridBagConstraints.WEST;
			gbc_btnBuscaLista.insets = new Insets(0, 0, 0, 5);
			gbc_btnBuscaLista.gridx = 3;
			gbc_btnBuscaLista.gridy = 6;
			panel2.add(btnBuscaLista,gbc_btnBuscaLista);
			
			JButton btnEliminar= new JButton("Eliminar");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					List<ListaVideos> lv= controlador.getUsuarioActual().getListaVideos();
					if(!lv.isEmpty()) {
					for(ListaVideos l : lv) {
						if(l.getNombreLista().equals(textFieldNombre.getText().trim())) {
							actual=l;
							JOptionPane.showMessageDialog(frame, "Has eliminado la lista "+l.getNombreLista());
							lv.remove(l);
						}
					}
				}
				}
			});
			
			GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
			gbc_btnEliminar.anchor = GridBagConstraints.WEST;
			gbc_btnEliminar.insets = new Insets(0, 0, 0, 5);
			gbc_btnEliminar.gridx = 3;
			gbc_btnEliminar.gridy = 6;
			panel2.add(btnEliminar,gbc_btnEliminar);
			
			JButton btnAnadir= new JButton("Añadir lista");
			btnAnadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					List<ListaVideos> lv= controlador.getUsuarioActual().getListaVideos();
					if(!lv.isEmpty()) {
					for(ListaVideos l : lv) {
						if(l.getNombreLista().equals(textFieldNombre.getText().trim())) {
							JOptionPane.showMessageDialog(frame, "Esa lista ya existe");
						}
					}
					}else {
						ListaVideos nueva=new ListaVideos(textFieldNombre.getText().trim());
						controlador.getUsuarioActual().addListaVideos(nueva);
						JOptionPane.showMessageDialog(frame, "Has añadido la lista: "+ textFieldNombre.getText().trim());
						actual=nueva;
					}
				}
			});
			
			GridBagConstraints gbc_btnAnadir = new GridBagConstraints();
			gbc_btnAnadir.anchor = GridBagConstraints.WEST;
			gbc_btnAnadir.insets = new Insets(0, 0, 0, 5);
			gbc_btnAnadir.gridx = 3;
			gbc_btnAnadir.gridy = 6;
			panel2.add(btnAnadir,gbc_btnAnadir);
		
			JButton btnQuitar= new JButton("Quitar video");
			btnQuitar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(actual.getLista().contains(videoSeleccionado)) {
						actual.removeVideo(videoSeleccionado);
						JOptionPane.showMessageDialog(frame, "Has eliminado el video: "+ videoSeleccionado.getTitulo()+ " de la lista: "+actual.getNombreLista());
					}
				}
			});
			
			GridBagConstraints gbc_btnQuitar = new GridBagConstraints();
			gbc_btnQuitar.anchor = GridBagConstraints.WEST;
			gbc_btnQuitar.insets = new Insets(0, 0, 0, 5);
			gbc_btnQuitar.gridx = 3;
			gbc_btnQuitar.gridy = 6;
			panel2.add(btnQuitar,gbc_btnQuitar);
	
			JButton btnAddVideo= new JButton("Añadir video");
			btnAddVideo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					actual.addVideo(videoSeleccionado);
					controlador.addVideoLista(actual, videoSeleccionado);
					JOptionPane.showMessageDialog(frame, "Has añadido el video: "+ videoSeleccionado.getTitulo()+ " a la lista: "+actual.getNombreLista());
				}
			});
			
			GridBagConstraints gbc_btnAddVideo = new GridBagConstraints();
			gbc_btnAddVideo.anchor = GridBagConstraints.WEST;
			gbc_btnAddVideo.insets = new Insets(0, 0, 0, 5);
			gbc_btnAddVideo.gridx = 3;
			gbc_btnAddVideo.gridy = 6;
			panel2.add(btnAddVideo,gbc_btnAddVideo);
	}
	        
	public void mostrarVentana() {
		frame.setVisible(true);
		
	}
}
