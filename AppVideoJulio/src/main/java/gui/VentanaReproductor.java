package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.Controlador;
import dominio.Etiqueta;
import dominio.Video;
import tds.video.VideoWeb;

public class VentanaReproductor {
	private JFrame frame;
	private VideoWeb videoWeb;
	private Video video;
	private JTextField textField;
	private Controlador controlador;
	
	public VentanaReproductor(VideoWeb unicaInstancia, Video video) {
		this.controlador=Controlador.getUnicaInstancia();
		this.videoWeb = unicaInstancia;
		this.video = video;
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 700, 550);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		
		
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 150, 0, 0, 150, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		String numReproducciones = video.getNumReproducciones() + " reproducciones";
		
		
		
		JLabel lblNewLabel = new JLabel(video.getTitulo());
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 3;
		gbc_panel_2.gridy = 1;
		panel_1.add(panel_2, gbc_panel_2);
		
		VideoWeb videoWeb_1 = videoWeb;
		panel_2.add(videoWeb_1);
		JLabel label = new JLabel(numReproducciones);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 3;
		gbc_label.gridy = 3;
		panel_1.add(label, gbc_label);
		
		JLabel lblEtiqueta = new JLabel("Etiquetas:");
		GridBagConstraints gbc_lblEtiqueta = new GridBagConstraints();
		gbc_lblEtiqueta.insets = new Insets(0, 0, 5, 5);
		gbc_lblEtiqueta.gridx = 3;
		gbc_lblEtiqueta.gridy = 5;
		panel_1.add(lblEtiqueta, gbc_lblEtiqueta);
		
		JScrollPane panelEtiq = new JScrollPane();
		GridBagConstraints gbc_panelEtiq = new GridBagConstraints();
		gbc_panelEtiq.insets = new Insets(0, 0, 5, 5);
		gbc_panelEtiq.fill = GridBagConstraints.BOTH;
		gbc_panelEtiq.gridx = 3;
		gbc_panelEtiq.gridy = 6;
		panel_1.add(panelEtiq, gbc_panelEtiq);
		
		final JPanel panelEtiq2 = new JPanel();
		panelEtiq.setViewportView(panelEtiq2);
		for (Etiqueta e : video.getEtiquetas()) {
			JLabel lblEtiq = new JLabel(e.getNombreEtiq());
			panelEtiq2.add(lblEtiq);
		}
		
		JPanel panelanadirEtiqueta = new JPanel();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 10;
		panel_1.add(panelanadirEtiqueta, gbc_textField);
		
		
		JLabel lblAadirEtiqueta = new JLabel("Añadir Etiqueta :");
		panelanadirEtiqueta.add(lblAadirEtiqueta);
		
		textField = new JTextField();
		textField.setColumns(10);
		panelanadirEtiqueta.add(textField);
		
		
		JButton btnAadir = new JButton("Añadir");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Etiqueta nuevaEtiq = new Etiqueta(textField.getText());
				if (textField.getText().isEmpty() == false && Controlador.getUnicaInstancia().existeEtiqueta(video, nuevaEtiq) == false) {
					Controlador.getUnicaInstancia().addEtiquetaVideo(video, nuevaEtiq);
					String nuevaEtiqueta=textField.getText().trim();
					JOptionPane.showMessageDialog(frame, "Has añadido una nueva etiqueta: "+ nuevaEtiqueta);
				} else {
					JOptionPane.showMessageDialog(frame, "El video ya contiene la etiqueta");
				}
			}
		});
		panelanadirEtiqueta.add(btnAadir);
		
		JButton btnPlay = new JButton("Play");
		panel.add(btnPlay);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				videoWeb.playVideo(video.getUrl());
				controlador.reproducirVideo(video);
				controlador.actualizarRecientes(video);
			}
		});
		
		JButton btnStop = new JButton("Stop");
		panel.add(btnStop);
		
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				videoWeb.cancel();
			}
		});
		
		JButton btnPause = new JButton("Volver");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				videoWeb.cancel();
				frame.dispose();
			}
		});
		panel.add(btnPause);
	}
}

