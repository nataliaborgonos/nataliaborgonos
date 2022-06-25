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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.DocumentException;

import controlador.Controlador;
import dominio.Etiqueta;
import dominio.RepositorioVideos;
import dominio.Video;
import tds.video.VideoWeb;

public class VentanaExplorar {
	private JFrame frame;
	private VideoWeb videoWeb;
	private Controlador controlador;
	private ModeloTabla modelo;
	private DefaultTableModel modeloE;
	private JTable tabla;
	private JTable tablaEtiquetas;
	private int filaSeleccionada;
	private Video videoSeleccionado;
	private boolean usuarioPremium;
	private JButton masVistos;
	private JButton generaPDF;
	private JButton botonFiltros;
	private LinkedList<Video> videosFiltrados = new LinkedList<Video>();
	private LinkedList<Etiqueta> etiquetasActuales = new LinkedList<Etiqueta>();

	public VentanaExplorar(VideoWeb videoweb) {
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
		panel.add(btnLogin, gbc_btnLogin);

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
		panel.add(btnLogout, gbc_btnLogout);

		JButton btnPremium = new JButton("Premium");
		btnPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.hacerPremium();
				if (controlador.isPremium()) {
					usuarioPremium = true;
					JOptionPane.showMessageDialog(frame, "Tu usuario ha pasado a ser premium");
					if (usuarioPremium) {
						masVistos = new JButton("Mas Vistos");
						masVistos.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								new VentanaMasVistos(videoWeb);
								frame.dispose();
							}
						});
						GridBagConstraints gbc_masVistos = new GridBagConstraints();
						gbc_masVistos.anchor = GridBagConstraints.WEST;
						gbc_masVistos.insets = new Insets(0, 0, 0, 5);
						gbc_masVistos.gridx = 3;
						gbc_masVistos.gridy = 6;
						panel.add(masVistos, gbc_masVistos);

						generaPDF = new JButton("Generar PDF de mis listas");
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
						panel.add(generaPDF, gbc_pdf);

						botonFiltros = new JButton("Gestionar filtros");
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
						panel.add(botonFiltros, gbc_botonFiltros);

						SwingUtilities.updateComponentTreeUI(frame);
					}
				} else {
					usuarioPremium = false;
					JOptionPane.showMessageDialog(frame, "Tu usuario ha dejado de ser premium");
					panel.remove(masVistos);
					panel.remove(generaPDF);
					panel.remove(botonFiltros);
					SwingUtilities.updateComponentTreeUI(frame);
				}
			}

		});

		GridBagConstraints gbc_btnPremium = new GridBagConstraints();
		gbc_btnPremium.anchor = GridBagConstraints.WEST;
		gbc_btnPremium.insets = new Insets(0, 0, 0, 5);
		gbc_btnPremium.gridx = 3;
		gbc_btnPremium.gridy = 6;
		panel.add(btnPremium, gbc_btnPremium);

		if (controlador.isPremium()) {
			usuarioPremium = true;
			if (usuarioPremium) {
				masVistos = new JButton("Mas Vistos");
				masVistos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						new VentanaMasVistos(videoWeb);
						frame.dispose();
					}
				});
				GridBagConstraints gbc_masVistos = new GridBagConstraints();
				gbc_masVistos.anchor = GridBagConstraints.WEST;
				gbc_masVistos.insets = new Insets(0, 0, 0, 5);
				gbc_masVistos.gridx = 3;
				gbc_masVistos.gridy = 6;
				panel.add(masVistos, gbc_masVistos);

				generaPDF = new JButton("Generar PDF de mis listas");
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
				panel.add(generaPDF, gbc_pdf);

				botonFiltros = new JButton("Gestionar filtros");
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
				panel.add(botonFiltros, gbc_botonFiltros);
			}
		}

		JPanel panel1 = new JPanel();
		frame.getContentPane().add(panel1, BorderLayout.CENTER);
		JLabel lblUser = new JLabel("Hola " + controlador.getUsuarioActual().getLogin());
		lblUser.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUser.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		panel1.add(lblUser);

		JButton btnExplorar = new JButton("Explorar");
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		GridBagConstraints gbc_btnExplorar = new GridBagConstraints();
		gbc_btnExplorar.anchor = GridBagConstraints.WEST;
		gbc_btnExplorar.insets = new Insets(0, 0, 0, 5);
		gbc_btnExplorar.gridx = 3;
		gbc_btnExplorar.gridy = 6;
		panel1.add(btnExplorar, gbc_btnExplorar);

		JButton btnMisListas = new JButton("Mis Listas");
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
		panel1.add(btnMisListas, gbc_btnMisListas);

		JButton btnRecientes = new JButton("Recientes");
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
		panel1.add(btnRecientes, gbc_btnRecientes);

		JButton btnNuevaLista = new JButton("Nueva Lista");
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
		panel1.add(btnNuevaLista, gbc_btnNuevaLista);

		JButton btnPrincipal = new JButton("Carga Videos");
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
		panel1.add(btnPrincipal, gbc_btnPrincipal);

		final JTextField textFieldBuscar = new JTextField();
		GridBagConstraints gbc_textFieldBuscar = new GridBagConstraints();
		gbc_textFieldBuscar.gridwidth = 6;
		gbc_textFieldBuscar.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldBuscar.gridx = 1;
		gbc_textFieldBuscar.gridy = 0;
		panel1.add(textFieldBuscar, gbc_textFieldBuscar);
		textFieldBuscar.setColumns(10);

		JButton btnBuscar = new JButton("Buscar titulo");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String auxTitulo = textFieldBuscar.getText().trim();
				List<Video> videosE = RepositorioVideos.getUnicaInstancia().getVideos();
				if (etiquetasActuales.isEmpty()) {
					for (Video v : videosE) {
						if (v.getTitulo().equals(auxTitulo)) {
							if (controlador.isFiltro()) {
								if (controlador.filtroMisListas()) {
									if (!controlador.buscarVideoFiltro(v)) {
										videosFiltrados.add(v);
										modelo = (ModeloTabla) tabla.getModel();
										JLabel label = new JLabel(v.getTitulo());
										label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
										ImageIcon thumb = videoWeb.getThumb(v.getUrl());
										label.setIcon(thumb);
										modelo.addRow(new Object[] { label.getIcon(), label.getText() });
									}
								} else if (!controlador.buscarVideoFiltro(v)) {
									videosFiltrados.add(v);
									modelo = (ModeloTabla) tabla.getModel();
									JLabel label = new JLabel(v.getTitulo());
									label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
									ImageIcon thumb = videoWeb.getThumb(v.getUrl());
									label.setIcon(thumb);
									modelo.addRow(new Object[] { label.getIcon(), label.getText() });
								}
							} else {
								videosFiltrados.add(v);
								modelo = (ModeloTabla) tabla.getModel();
								JLabel label = new JLabel(v.getTitulo());
								label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
								ImageIcon thumb = videoWeb.getThumb(v.getUrl());
								label.setIcon(thumb);
								modelo.addRow(new Object[] { label.getIcon(), label.getText() });
							}
						}
					}
				}
			}

		});

		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.anchor = GridBagConstraints.WEST;
		gbc_btnBuscar.insets = new Insets(0, 0, 0, 5);
		gbc_btnBuscar.gridx = 3;
		gbc_btnBuscar.gridy = 6;
		panel1.add(btnBuscar, gbc_btnBuscar);

		JButton btnBuscarNuevo = new JButton("Nueva Busqueda");
		btnBuscarNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (modelo != null) {
					modelo.setRowCount(0);
				}
				videosFiltrados.clear();
			}
		});

		GridBagConstraints gbc_btnBuscarNuevo = new GridBagConstraints();
		gbc_btnBuscarNuevo.anchor = GridBagConstraints.WEST;
		gbc_btnBuscarNuevo.insets = new Insets(0, 0, 0, 5);
		gbc_btnBuscarNuevo.gridx = 3;
		gbc_btnBuscarNuevo.gridy = 6;
		panel1.add(btnBuscarNuevo, gbc_btnBuscarNuevo);

		JPanel panelEtiquetas = new JPanel();
		frame.getContentPane().add(panelEtiquetas, BorderLayout.SOUTH);
		JLabel lblBuscarEtiqueta = new JLabel("Filtra el video por etiquetas (Terror, Intriga...) :");
		panelEtiquetas.add(lblBuscarEtiqueta);
		final JTextField textFieldBuscarEtiq = new JTextField();
		GridBagConstraints gbc_textFieldBuscarEtiq = new GridBagConstraints();
		gbc_textFieldBuscarEtiq.gridwidth = 6;
		gbc_textFieldBuscarEtiq.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldBuscarEtiq.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldBuscarEtiq.gridx = 1;
		gbc_textFieldBuscarEtiq.gridy = 0;
		panelEtiquetas.add(textFieldBuscarEtiq, gbc_textFieldBuscarEtiq);
		textFieldBuscarEtiq.setColumns(10);

		JButton btnBuscarEtiq = new JButton("Buscar etiqueta");
		btnBuscarEtiq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String auxEtiq = textFieldBuscarEtiq.getText().trim();
				modeloE = (DefaultTableModel) tablaEtiquetas.getModel();
				modeloE.insertRow(0, new Object[] { auxEtiq });
				Etiqueta et = new Etiqueta(auxEtiq);
				List<Video> videosE = RepositorioVideos.getUnicaInstancia().getVideos();
				if (etiquetasActuales.contains(et)) {
					JOptionPane.showMessageDialog(frame, "Ya has añadido esa etiqueta");
				} else {
					etiquetasActuales.add(et);
					for (Video v : videosE) {
						if (contieneEtiquetas(v)) {
							videosFiltrados.add(v);
							modelo = (ModeloTabla) tabla.getModel();
							JLabel label = new JLabel(v.getTitulo());
							label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
							ImageIcon thumb = videoWeb.getThumb(v.getUrl());
							label.setIcon(thumb);
							modelo.addRow(new Object[] { label.getIcon(), label.getText() });
						}
					}
				}
			}
		});

		GridBagConstraints gbc_btnBuscarEtiq = new GridBagConstraints();
		gbc_btnBuscarEtiq.anchor = GridBagConstraints.WEST;
		gbc_btnBuscarEtiq.insets = new Insets(0, 0, 0, 5);
		gbc_btnBuscarEtiq.gridx = 3;
		gbc_btnBuscarEtiq.gridy = 6;
		panelEtiquetas.add(btnBuscarEtiq, gbc_btnBuscarEtiq);

		JButton btnBuscarNuevaEtiqueta = new JButton("Nueva Etiqueta");
		btnBuscarNuevaEtiqueta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (modeloE != null) {
					modeloE.setRowCount(0);
				}
			}
		});

		GridBagConstraints gbc_btnBuscarNuevaEtiq = new GridBagConstraints();
		gbc_btnBuscarNuevaEtiq.anchor = GridBagConstraints.WEST;
		gbc_btnBuscarNuevaEtiq.insets = new Insets(0, 0, 0, 5);
		gbc_btnBuscarNuevaEtiq.gridx = 3;
		gbc_btnBuscarNuevaEtiq.gridy = 6;
		panelEtiquetas.add(btnBuscarNuevaEtiqueta, gbc_btnBuscarNuevaEtiq);

		JButton btnBuscarLimpiaEtiqueta = new JButton("Limpiar etiquetas");
		btnBuscarLimpiaEtiqueta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				etiquetasActuales.clear();
				if (modeloE != null) {
					modeloE.setRowCount(0);
				}
			}
		});

		GridBagConstraints gbc_btnBuscarLimpiaEtiq = new GridBagConstraints();
		gbc_btnBuscarLimpiaEtiq.anchor = GridBagConstraints.WEST;
		gbc_btnBuscarLimpiaEtiq.insets = new Insets(0, 0, 0, 5);
		gbc_btnBuscarLimpiaEtiq.gridx = 3;
		gbc_btnBuscarLimpiaEtiq.gridy = 6;
		panelEtiquetas.add(btnBuscarLimpiaEtiqueta, gbc_btnBuscarLimpiaEtiq);

		tablaEtiquetas = new JTable(new DefaultTableModel(null, new Object[] { "Etiqueta" })) {
			public boolean editCellAt(int fila, int columna, java.util.EventObject e) {
				return false;
			}
		};
		tablaEtiquetas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		tablaEtiquetas.setFont(new Font("Arial", Font.PLAIN, 14));
		tablaEtiquetas.getColumnModel().getColumn(0).setPreferredWidth(20);
		tablaEtiquetas.setRowHeight(20);
		panelEtiquetas.add(tablaEtiquetas);
		JScrollPane scrollPane1 = new JScrollPane(tablaEtiquetas);
		panelEtiquetas.add(scrollPane1);
		scrollPane1.setPreferredSize(new Dimension(350, 200));
		panelEtiquetas.setPreferredSize(new Dimension(375, 175));
		tablaEtiquetas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				setFilaSeleccionada(tablaEtiquetas.getSelectedRow());
			}
		});
		panelEtiquetas.setVisible(true);

		JPanel panel2 = new JPanel();
		frame.getContentPane().add(panel2, BorderLayout.EAST);

		JButton btnReproducir = new JButton("Reproducir video");
		btnReproducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tabla.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(frame, "Escoge un video que reproducir");
				} else {
					new VentanaReproductor(videoWeb, videoSeleccionado);
				}
			}
		});

		GridBagConstraints gbc_btnReproducir = new GridBagConstraints();
		gbc_btnReproducir.anchor = GridBagConstraints.WEST;
		gbc_btnReproducir.insets = new Insets(0, 0, 0, 5);
		gbc_btnReproducir.gridx = 3;
		gbc_btnReproducir.gridy = 6;
		panel2.add(btnReproducir, gbc_btnReproducir);
		tabla = new JTable(new ModeloTabla()) {
			public boolean editCellAt(int fila, int columna, java.util.EventObject e) {
				return false;
			}
		};
		tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		tabla.setFont(new Font("Arial", Font.PLAIN, 14));
		tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
		tabla.setRowHeight(60);
		panel2.add(tabla);
		JScrollPane scrollPane = new JScrollPane(tabla);
		panel2.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(350, 200));
		panel2.setPreferredSize(new Dimension(375, 175));
		tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				filaSeleccionada = tabla.getSelectedRow();
				if (filaSeleccionada != -1) {
					Video v = videosFiltrados.get(filaSeleccionada);
					videoSeleccionado = RepositorioVideos.getUnicaInstancia().getVideo(v.getTitulo());
				}
			}
		});
		panel2.setVisible(true);

	}

	public void setFilaSeleccionada(int filaSeleccionada) {
		this.filaSeleccionada = filaSeleccionada;
	}

	public boolean contieneEtiquetas(Video v) {
		for (Etiqueta e : etiquetasActuales) {
			if (controlador.existeEtiqueta(v, e)) {
				return true;
			}
		}
		return false;
	}
}
