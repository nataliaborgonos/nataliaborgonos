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

//controlador
public class App extends JFrame{
	private JPanel contentPane;
	private static VideoWeb videoWeb;
	private ListaVideos topTen = new ListaVideos("Top Ten");
	Usuario usuarioActual;

	public App() {
		VideoWeb videoWeb = new VideoWeb();
	}
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VentanaLoginRegistro ventana = new VentanaLoginRegistro(videoWeb);
						//VentanaPrincipal ventana = new VentanaPrincipal(videoWeb);
						//VentanaNuevaLista ventana = new VentanaNuevaLista(videoWeb);
						//VentanaMisListas ventana = new VentanaMisListas(videoWeb);
						//VentanaRecientes ventana = new VentanaRecientes(videoWeb);
						ventana.mostrarVentana();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		});
		/*
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 760, 650);

		contentPane = (JPanel) getContentPane();
		contentPane.setSize(new Dimension(760, 630)); //20 de barra de título
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JLabel titulo = new JLabel("Sin título",JLabel.CENTER);
		titulo.setMinimumSize(new Dimension(760, 60));
		titulo.setPreferredSize(new Dimension(760, 60));
		titulo.setMaximumSize(new Dimension(760, 60));
		Font ftit= new Font ("Arial",Font.BOLD,24);
		titulo.setFont(ftit);
		//titulo.setAlignmentX(0.5f); //centrar
		titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPanel botones = new JPanel ();
		botones.setMinimumSize(new Dimension(760, 60));
		botones.setPreferredSize(new Dimension(760, 60));
		botones.setMaximumSize(new Dimension(760, 60));
		botones.setLayout(new FlowLayout());

		JButton btUno = new JButton("Musica");
		JButton btDos = new JButton("Pelicula");
		JButton btTres = new JButton("Sorpresa");
		JButton btCancelar = new JButton("Cancelar");
		btCancelar.setBackground(Color.RED);
		btCancelar.setForeground(Color.WHITE);

		botones.add(btUno);
		botones.add(btDos);
		botones.add(btTres);
		botones.add(Box.createHorizontalStrut(50));
		botones.add(btCancelar);

		JLabel reprod=new JLabel("reproduciendo:");
		reprod.setAlignmentX(0.5f);
		JLabel miniatura= new JLabel();
		miniatura.setAlignmentX(0.5f);

		JLabel copyright=new JLabel(videoWeb.getVersion());
		copyright.setAlignmentX(0.5f);
		contentPane.add(titulo);
		contentPane.add(videoWeb);
		contentPane.add(botones);
		contentPane.add(reprod);
		contentPane.add(miniatura);
		contentPane.add(copyright);

		btUno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				titulo.setText("Rasputin");
				miniatura.setIcon(videoWeb.getThumb("https://www.youtube.com/watch?v=rk7ITikbhs4"));
				videoWeb.playVideo("https://www.youtube.com/watch?v=hnRphfqIvsM");
				validate();
			}

		});
		btDos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				titulo.setText("Jacinto Durante Representante");
				miniatura.setIcon(videoWeb.getThumb("https://www.youtube.com/watch?v=EdVMSYomYJY"));
				videoWeb.playVideo("https://www.youtube.com/watch?v=EdVMSYomYJY");
				validate();
			}

		});
		btTres.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				titulo.setText("Si tu padre sabe de efectos especiales");
				miniatura.setIcon(videoWeb.getThumb("https://www.youtube.com/watch?v=0243Z0YXPpY"));
				videoWeb.playVideo("https://www.youtube.com/watch?v=0243Z0YXPpY");
				validate();
			}

		});
		btCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				titulo.setText("Sin título");
				miniatura.setIcon(null);
				videoWeb.cancel();
			}
		});
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					videoWeb = new VideoWeb();
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}*/
}
}