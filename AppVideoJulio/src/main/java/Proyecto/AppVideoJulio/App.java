package Proyecto.AppVideoJulio;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dominio.ListaVideos;
import dominio.Usuario;
import gui.VentanaLoginRegistro;
import gui.VentanaMisListas;
import gui.VentanaNuevaLista;
import gui.VentanaPrincipal;
import gui.VentanaRecientes;
import tds.video.VideoWeb;


public class App extends JFrame{
	private JPanel contentPane;
	private static VideoWeb videoWeb;
	private ListaVideos topTen = new ListaVideos("Top Ten");
	Usuario usuarioActual;

	public App() {
		
	}
		public static void main(String[] args) {
			final VideoWeb videoWeb = new VideoWeb();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VentanaLoginRegistro ventana = new VentanaLoginRegistro(videoWeb);
						ventana.mostrarVentana();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		});
		
}
}
