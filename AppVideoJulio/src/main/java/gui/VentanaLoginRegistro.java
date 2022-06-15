package gui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.SwingConstants;

import controlador.Controlador;
import tds.video.VideoWeb;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaLoginRegistro {
	
	private JFrame frame;
	private JTextField textUsuario;
	private JPasswordField textPassword;
	private VideoWeb videoWeb;
	private Controlador controlador;
	
	public VentanaLoginRegistro(VideoWeb videoweb) {
		this.videoWeb = videoweb;
		controlador = Controlador.getUnicaInstancia();
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
		
		JButton btnPremium = new JButton("Premium");
		btnPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Ingresa en tu cuenta para hacerte premium");
			}
		});
	
		GridBagConstraints gbc_btnPremium = new GridBagConstraints();
		gbc_btnLogin.anchor = GridBagConstraints.WEST;
		gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogin.gridx = 3;
		gbc_btnLogin.gridy = 6;
		panel.add(btnPremium,gbc_btnPremium);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{100, 0, 0, 0, 89, 0, 0};
		gbl_panel_1.rowHeights = new int[]{30, 0, 0, 0, 0, 50, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblUusario = new JLabel("Usuario :");
		GridBagConstraints gbc_lblUusario = new GridBagConstraints();
		gbc_lblUusario.anchor = GridBagConstraints.WEST;
		gbc_lblUusario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUusario.gridx = 3;
		gbc_lblUusario.gridy = 2;
		panel_1.add(lblUusario, gbc_lblUusario);
		
		textUsuario = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 2;
		panel_1.add(textUsuario, gbc_textField);
		textUsuario.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 3;
		gbc_lblPassword.gridy = 4;
		panel_1.add(lblPassword, gbc_lblPassword);
		
		textPassword = new JPasswordField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 4;
		gbc_textField_1.gridy = 4;
		panel_1.add(textPassword, gbc_textField_1);
		textPassword.setColumns(10);
		
		JPanel panel2 = new JPanel();
		frame.getContentPane().add(panel2, BorderLayout.SOUTH);
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String auxLogin = textUsuario.getText().trim();
				System.out.println(auxLogin);
				String auxPassword = String.valueOf(textPassword.getPassword());
				if(controlador.login(auxLogin, auxPassword)) {
					VentanaRecientes ventanaRecientes = new VentanaRecientes(videoWeb);
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(frame, "Login Incorrecto");
				}
			}
		});

		GridBagConstraints gbc_btnEntrar = new GridBagConstraints();
		gbc_btnEntrar.anchor = GridBagConstraints.WEST;
		gbc_btnEntrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnEntrar.gridx = 3;
		gbc_btnEntrar.gridy = 6;
		panel2.add(btnEntrar,gbc_btnEntrar);
		
	}
	public void mostrarVentana() {
		frame.setVisible(true);
		
	}

}
