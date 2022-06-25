package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.DocumentException;

import controlador.Controlador;
import dominio.ListaVideos;
import dominio.Video;
import tds.video.VideoWeb;

public class VentanaMisListas {
	private JFrame frame;
	private VideoWeb videoWeb;
	private Controlador controlador;
	private ModeloTabla modelo;
	private JTable tabla;
	private Video videoSeleccionado;
	private int filaSeleccionada;
	private ListaVideos listaActual;
	private boolean usuarioPremium;
	private JButton masVistos;
	private JButton generaPDF;
	private JButton botonFiltros;
	
	public VentanaMisListas(VideoWeb videoweb) {
		controlador = Controlador.getUnicaInstancia();
		this.videoWeb = videoweb;
		initialize();
		frame.setVisible(true);
	}
	
	@SuppressWarnings("serial")
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
					if(usuarioPremium) {
						masVistos=new JButton("Mas Vistos");
						masVistos.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								new VentanaMasVistos(videoWeb);
							}
						});
						GridBagConstraints gbc_masVistos = new GridBagConstraints();
						gbc_masVistos.anchor = GridBagConstraints.WEST;
						gbc_masVistos.insets = new Insets(0, 0, 0, 5);
						gbc_masVistos.gridx = 3;
						gbc_masVistos.gridy = 6;
						panel.add(masVistos,gbc_masVistos);
						
						generaPDF=new JButton("Generar PDF de mis listas");
						generaPDF.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								try {
									controlador.generaPdf();
									JOptionPane.showMessageDialog(frame, "Se ha guardado el PDF en tu equipo.");
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (DocumentException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});
						GridBagConstraints gbc_pdf = new GridBagConstraints();
						gbc_pdf.anchor = GridBagConstraints.WEST;
						gbc_pdf.insets = new Insets(0, 0, 0, 5);
						gbc_pdf.gridx = 3;
						gbc_pdf.gridy = 6;
						panel.add(generaPDF,gbc_pdf);
						
						botonFiltros=new JButton("Gestionar filtros");
						botonFiltros.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								new VentanaFiltros(videoWeb);
								frame.dispose();
							}
						});
						GridBagConstraints gbc_botonFiltros = new GridBagConstraints();
						gbc_botonFiltros.anchor = GridBagConstraints.WEST;
						gbc_botonFiltros.insets = new Insets(0, 0, 0, 5);
						gbc_botonFiltros.gridx = 3;
						gbc_botonFiltros.gridy = 6;
						panel.add(botonFiltros,gbc_botonFiltros);
						
						SwingUtilities.updateComponentTreeUI(frame);
					}
				}else {
					usuarioPremium=false;
					JOptionPane.showMessageDialog(frame,"Tu usuario ha dejado de ser premium");
					panel.remove(masVistos);
					panel.remove(generaPDF);
					panel.remove(botonFiltros);
					SwingUtilities.updateComponentTreeUI(frame);
				}
			}
		
		});
	
		GridBagConstraints gbc_btnPremium = new GridBagConstraints();
		gbc_btnLogin.anchor = GridBagConstraints.WEST;
		gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogin.gridx = 3;
		gbc_btnLogin.gridy = 6;
		panel.add(btnPremium,gbc_btnPremium);
		
		
		
		if(controlador.isPremium()) {
			usuarioPremium=true;
			if(usuarioPremium) {
				masVistos=new JButton("Mas Vistos");
				masVistos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						new VentanaMasVistos(videoWeb);
					}
				});
				GridBagConstraints gbc_masVistos = new GridBagConstraints();
				gbc_masVistos.anchor = GridBagConstraints.WEST;
				gbc_masVistos.insets = new Insets(0, 0, 0, 5);
				gbc_masVistos.gridx = 3;
				gbc_masVistos.gridy = 6;
				panel.add(masVistos,gbc_masVistos);
				
				generaPDF=new JButton("Generar PDF de mis listas");
				generaPDF.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try {
							controlador.generaPdf();
							JOptionPane.showMessageDialog(frame, "Se ha guardado el PDF en tu equipo.");
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (DocumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				GridBagConstraints gbc_pdf = new GridBagConstraints();
				gbc_pdf.anchor = GridBagConstraints.WEST;
				gbc_pdf.insets = new Insets(0, 0, 0, 5);
				gbc_pdf.gridx = 3;
				gbc_pdf.gridy = 6;
				panel.add(generaPDF,gbc_pdf);
				
				botonFiltros=new JButton("Gestionar filtros");
				botonFiltros.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new VentanaFiltros(videoWeb);
						frame.dispose();
					}
				});
				GridBagConstraints gbc_botonFiltros = new GridBagConstraints();
				gbc_botonFiltros.anchor = GridBagConstraints.WEST;
				gbc_botonFiltros.insets = new Insets(0, 0, 0, 5);
				gbc_botonFiltros.gridx = 3;
				gbc_botonFiltros.gridy = 6;
				panel.add(botonFiltros,gbc_botonFiltros);
			}
		}
		
		JPanel panel1 = new JPanel();
		frame.getContentPane().add(panel1, BorderLayout.CENTER);

				JLabel lblUser = new JLabel("Hola "+controlador.getUsuarioActual().getLogin());
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
			JPanel panel2 = new JPanel();
			frame.getContentPane().add(panel2, BorderLayout.EAST);
			
			JLabel lblLista = new JLabel("Selecciona la lista:");
			lblLista.setHorizontalAlignment(SwingConstants.TRAILING);
			lblLista.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
			panel2.add(lblLista);
			
			   
			   Vector<String> optionsToChoose = new Vector<String>();
			  for(ListaVideos lv : controlador.getUsuarioActual().getListaVideos()) {
				  optionsToChoose.add(lv.getNombreLista());
			  }

		        JComboBox<String> jComboBox = new JComboBox<String>(optionsToChoose);
		        jComboBox.setBounds(80, 50, 140, 20);
		        panel2.add(jComboBox);
		       

				tabla = new JTable(new ModeloTabla() {
					public boolean editCellAt(int fila, int columna, java.util.EventObject e) {
			            return false;
			        }
				});
				tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
				tabla.setFont(new Font("Arial", Font.PLAIN, 14));
				tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
				tabla.setRowHeight(60);
				panel1.add(tabla);
				JScrollPane scrollPane2 = new JScrollPane(tabla);
				panel1.add(scrollPane2);
				scrollPane2.setPreferredSize(new Dimension(350, 200));
				panel1.setPreferredSize(new Dimension(375, 175));
		        
		        String itemSeleccionado = (String)jComboBox.getSelectedItem();
		        List<ListaVideos> lv = controlador.getUsuarioActual().getListaVideos();
		        for(ListaVideos l : lv) {
		        	if(itemSeleccionado.equals(l.getNombreLista())) {
		        		listaActual=l;
		        		for(Video v : l.getLista()) {
		        			modelo = (ModeloTabla) tabla.getModel();
		        			JLabel label = new JLabel(v.getTitulo());
		        			label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		        			ImageIcon thumb = videoWeb.getThumb(v.getUrl());
		                	label.setIcon(thumb);
							modelo.addRow(new Object[]{label.getIcon(),label.getText()});
		        		}
		        	}
		        }
			
		        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
					public void valueChanged(ListSelectionEvent event) {
			            filaSeleccionada= tabla.getSelectedRow();
			            videoSeleccionado = listaActual.getLista().get(filaSeleccionada);
			        }
				});
	
		    	JButton btnReproducir= new JButton("Reproducir");
				btnReproducir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						 new VentanaReproductor(videoWeb, videoSeleccionado);	
					}
				});
				
				GridBagConstraints gbc_btnReproducir = new GridBagConstraints();
				gbc_btnReproducir.anchor = GridBagConstraints.WEST;
				gbc_btnReproducir.insets = new Insets(0, 0, 0, 5);
				gbc_btnReproducir.gridx = 3;
				gbc_btnReproducir.gridy = 6;
				panel2.add(btnReproducir,gbc_btnReproducir);
		
	}
	        
	public void mostrarVentana() {
		frame.setVisible(true);
		
	}
}
