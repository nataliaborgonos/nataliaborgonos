package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import controlador.Controlador;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaRegistro {
	private JFrame frame;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textEmail;
	private JTextField textUsuario;
	private JTextField textPassword;
	private JTextField textRepitePassword;
	private JLabel lblErrorNombre;
	private JLabel lblErrorfechanacim;
	private JLabel lblErrorpassword;
	private JLabel lblErrorusuario;
	private JLabel lblRepitePassword;
	private JLabel lblErrorLasContraseas;
	private JDateChooser dateChooser;
	private Controlador controlador;
	
	public VentanaRegistro(JFrame frameanterior) {
	controlador = Controlador.getUnicaInstancia();
	frame = new JFrame();
	frame.setBounds(100, 100, 800, 300);
	frame.setVisible(true);
	
	JPanel panel = new JPanel();
	frame.getContentPane().add(panel, BorderLayout.NORTH);
	
	JLabel lblRegistro = new JLabel("Registro de usuario");
	lblRegistro.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
	panel.add(lblRegistro);
	

	JPanel panel2 = new JPanel();
	frame.getContentPane().add(panel2, BorderLayout.NORTH);
	
	JLabel lblAppvideo = new JLabel("APPVIDEO");
	lblAppvideo.setForeground(Color.RED);
	lblAppvideo.setHorizontalAlignment(SwingConstants.TRAILING);
	lblAppvideo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
	panel2.add(lblAppvideo);
	
	
	JButton btnLogin = new JButton("Login");
	btnLogin.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			frame.dispose();
		}
	});

	GridBagConstraints gbc_btnLogin = new GridBagConstraints();
	gbc_btnLogin.anchor = GridBagConstraints.WEST;
	gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
	gbc_btnLogin.gridx = 3;
	gbc_btnLogin.gridy = 6;
	panel2.add(btnLogin,gbc_btnLogin);
	
	JButton btnRegistro1 = new JButton("Registro");
	btnRegistro1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new VentanaRegistro(frame);
		}
	});
	GridBagConstraints gbc_btnRegistro1 = new GridBagConstraints();
	gbc_btnRegistro1.anchor = GridBagConstraints.EAST;
	gbc_btnRegistro1.insets = new Insets(0, 0, 0, 5);
	gbc_btnRegistro1.gridx = 4;
	gbc_btnRegistro1.gridy = 6;
	panel2.add(btnRegistro1, gbc_btnRegistro1);
	
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
	panel2.add(btnSalir, gbc_btnSalir);
	

	JButton btnPremium = new JButton("Premium");
	btnPremium.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(frame, "Ingresa en tu cuenta para hacerte premium");
		}
	});
	

	GridBagConstraints gbc_btnPremium = new GridBagConstraints();
	gbc_btnPremium.anchor = GridBagConstraints.WEST;
	gbc_btnPremium.insets = new Insets(0, 0, 0, 5);
	gbc_btnPremium.gridx = 3;
	gbc_btnPremium.gridy = 6;
	panel2.add(btnPremium,gbc_btnPremium);

	
	JPanel panel_1 = new JPanel();
	frame.getContentPane().add(panel_1, BorderLayout.CENTER);
	GridBagLayout gbl_panel_1 = new GridBagLayout();
	gbl_panel_1.columnWidths = new int[]{40, 0, 0, 0, 0, 0, 0, 0};
	gbl_panel_1.rowHeights = new int[]{20, 0, 0, 0, 0, 20, 0, 0};
	gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
	gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	panel_1.setLayout(gbl_panel_1);
	
	JLabel lblNombre = new JLabel("Nombre:");
	GridBagConstraints gbc_lblNombre = new GridBagConstraints();
	gbc_lblNombre.anchor = GridBagConstraints.WEST;
	gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
	gbc_lblNombre.gridx = 1;
	gbc_lblNombre.gridy = 1;
	panel_1.add(lblNombre, gbc_lblNombre);
	
	textNombre = new JTextField();
	GridBagConstraints gbc_textField_6_1 = new GridBagConstraints();
	gbc_textField_6_1.insets = new Insets(0, 0, 5, 5);
	gbc_textField_6_1.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField_6_1.gridx = 2;
	gbc_textField_6_1.gridy = 1;
	panel_1.add(textNombre, gbc_textField_6_1);
	textNombre.setColumns(10);
	
	lblErrorusuario = new JLabel("Error: Introduce tu usuario");
	lblErrorusuario.setForeground(Color.RED);
	GridBagConstraints gbc_lblErrorusuario = new GridBagConstraints();
	gbc_lblErrorusuario.insets = new Insets(0, 0, 5, 5);
	gbc_lblErrorusuario.gridx = 3;
	gbc_lblErrorusuario.gridy = 1;
	panel_1.add(lblErrorusuario, gbc_lblErrorusuario);
	
	JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento:");
	GridBagConstraints gbc_lblFechaDeNacimiento = new GridBagConstraints();
	gbc_lblFechaDeNacimiento.anchor = GridBagConstraints.WEST;
	gbc_lblFechaDeNacimiento.insets = new Insets(0, 0, 5, 5);
	gbc_lblFechaDeNacimiento.gridx = 5;
	gbc_lblFechaDeNacimiento.gridy = 1;
	panel_1.add(lblFechaDeNacimiento, gbc_lblFechaDeNacimiento);
	
	dateChooser = new JDateChooser();
	GridBagConstraints gbc_dateChooser = new GridBagConstraints();
	gbc_dateChooser.insets = new Insets(0, 0, 5, 0);
	gbc_dateChooser.fill = GridBagConstraints.BOTH;
	gbc_dateChooser.gridx = 6;
	gbc_dateChooser.gridy = 1;
	panel_1.add(dateChooser, gbc_dateChooser);
	
	
	JLabel lblApellidos = new JLabel("Apellidos:");
	GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
	gbc_lblApellidos.anchor = GridBagConstraints.EAST;
	gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
	gbc_lblApellidos.gridx = 1;
	gbc_lblApellidos.gridy = 2;
	panel_1.add(lblApellidos, gbc_lblApellidos);
	
	textApellidos = new JTextField();
	GridBagConstraints gbc_textField_1 = new GridBagConstraints();
	gbc_textField_1.insets = new Insets(0, 0, 5, 5);
	gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField_1.gridx = 2;
	gbc_textField_1.gridy = 2;
	panel_1.add(textApellidos, gbc_textField_1);
	textApellidos.setColumns(10);
	
	lblErrorpassword = new JLabel("Error: Introduce tu password");
	lblErrorpassword.setForeground(Color.RED);
	GridBagConstraints gbc_lblErrorpassword = new GridBagConstraints();
	gbc_lblErrorpassword.insets = new Insets(0, 0, 5, 5);
	gbc_lblErrorpassword.gridx = 3;
	gbc_lblErrorpassword.gridy = 2;
	panel_1.add(lblErrorpassword, gbc_lblErrorpassword);
	
	JLabel lblEmail = new JLabel("Email:");
	GridBagConstraints gbc_lblEmail = new GridBagConstraints();
	gbc_lblEmail.anchor = GridBagConstraints.WEST;
	gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
	gbc_lblEmail.gridx = 5;
	gbc_lblEmail.gridy = 2;
	panel_1.add(lblEmail, gbc_lblEmail);
	
	textEmail = new JTextField();
	GridBagConstraints gbc_textField_3 = new GridBagConstraints();
	gbc_textField_3.insets = new Insets(0, 0, 5, 0);
	gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField_3.gridx = 6;
	gbc_textField_3.gridy = 2;
	panel_1.add(textEmail, gbc_textField_3);
	textEmail.setColumns(10);
	
	JLabel lblUsuario = new JLabel("Usuario:");
	GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
	gbc_lblUsuario.anchor = GridBagConstraints.WEST;
	gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
	gbc_lblUsuario.gridx = 1;
	gbc_lblUsuario.gridy = 3;
	panel_1.add(lblUsuario, gbc_lblUsuario);
	
	textUsuario = new JTextField();
	GridBagConstraints gbc_textField_4 = new GridBagConstraints();
	gbc_textField_4.insets = new Insets(0, 0, 5, 5);
	gbc_textField_4.fill = GridBagConstraints.BOTH;
	gbc_textField_4.gridx = 2;
	gbc_textField_4.gridy = 3;
	panel_1.add(textUsuario, gbc_textField_4);
	textUsuario.setColumns(10);
	
	lblErrorNombre = new JLabel("Error: Rellena la casilla nombre.");
	lblErrorNombre.setForeground(Color.RED);
	GridBagConstraints gbc_lblError = new GridBagConstraints();
	gbc_lblError.insets = new Insets(0, 0, 5, 5);
	gbc_lblError.gridx = 3;
	gbc_lblError.gridy = 3;
	panel_1.add(lblErrorNombre, gbc_lblError);
	
	JLabel lblPassword = new JLabel("Password:");
	GridBagConstraints gbc_lblPassword = new GridBagConstraints();
	gbc_lblPassword.anchor = GridBagConstraints.WEST;
	gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
	gbc_lblPassword.gridx = 5;
	gbc_lblPassword.gridy = 3;
	panel_1.add(lblPassword, gbc_lblPassword);
	
	textPassword = new JTextField();
	GridBagConstraints gbc_textField_5 = new GridBagConstraints();
	gbc_textField_5.insets = new Insets(0, 0, 5, 0);
	gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField_5.gridx = 6;
	gbc_textField_5.gridy = 3;
	panel_1.add(textPassword, gbc_textField_5);
	textPassword.setColumns(10);
	
	JButton btnRegistro = new JButton("Registrarse");
	btnRegistro.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {	
			String auxNombre = textNombre.getText().trim();
			String auxApellidos = textApellidos.getText().trim();
			String auxEmail = textEmail.getText().trim();
			String auxLogin = textUsuario.getText().trim();
			String auxPassword = textPassword.getText().trim();
			Date fechaNacim = dateChooser.getDate();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			if (checkErrores() == true) {
				String auxfechaNacim = sdf.format(fechaNacim);
				ocultarErrores();
				if(controlador.registrarUsuario(auxNombre, auxApellidos, auxEmail, auxfechaNacim, auxLogin, auxPassword)) {
					JOptionPane.showMessageDialog(frame, "Usuario dado de alta correctamente");
					frame.dispose();
					} else {
					JOptionPane.showMessageDialog(frame, "Ya existe un usuario con ese login");
					}
			}
			
			
		}
	});
	
	lblErrorfechanacim = new JLabel("Error : Introduce Fecha Nacimiento");
	lblErrorfechanacim.setForeground(Color.RED);
	GridBagConstraints gbc_lblErrorfechanacim = new GridBagConstraints();
	gbc_lblErrorfechanacim.insets = new Insets(0, 0, 5, 5);
	gbc_lblErrorfechanacim.gridx = 3;
	gbc_lblErrorfechanacim.gridy = 4;
	panel_1.add(lblErrorfechanacim, gbc_lblErrorfechanacim);
	
	lblRepitePassword = new JLabel("Repite Password:");
	GridBagConstraints gbc_lblRepitePassword = new GridBagConstraints();
	gbc_lblRepitePassword.anchor = GridBagConstraints.WEST;
	gbc_lblRepitePassword.insets = new Insets(0, 0, 5, 5);
	gbc_lblRepitePassword.gridx = 5;
	gbc_lblRepitePassword.gridy = 4;
	panel_1.add(lblRepitePassword, gbc_lblRepitePassword);
	
	textRepitePassword = new JTextField();
	GridBagConstraints gbc_textRepitePassword = new GridBagConstraints();
	gbc_textRepitePassword.fill = GridBagConstraints.HORIZONTAL;
	gbc_textRepitePassword.insets = new Insets(0, 0, 5, 0);
	gbc_textRepitePassword.gridx = 6;
	gbc_textRepitePassword.gridy = 4;
	panel_1.add(textRepitePassword, gbc_textRepitePassword);
	textRepitePassword.setColumns(10);
	
	lblErrorLasContraseas = new JLabel("Error: Los passwords no coinciden");
	lblErrorLasContraseas.setForeground(Color.RED);
	GridBagConstraints gbc_lblErrorLasContraseas = new GridBagConstraints();
	gbc_lblErrorLasContraseas.insets = new Insets(0, 0, 5, 5);
	gbc_lblErrorLasContraseas.gridx = 3;
	gbc_lblErrorLasContraseas.gridy = 5;
	panel_1.add(lblErrorLasContraseas, gbc_lblErrorLasContraseas);
	GridBagConstraints gbc_btnRegistro = new GridBagConstraints();
	gbc_btnRegistro.anchor = GridBagConstraints.EAST;
	gbc_btnRegistro.insets = new Insets(0, 0, 0, 5);
	gbc_btnRegistro.gridx = 4;
	gbc_btnRegistro.gridy = 6;
	panel_1.add(btnRegistro, gbc_btnRegistro);
	
	
	ocultarErrores();

	
}

public boolean checkErrores() {
	boolean ok = true;
	if (textNombre.getText().trim().isEmpty()) {
		lblErrorNombre.setVisible(true); 
		ok = false;
	} else {
		lblErrorNombre.setVisible(false);
	}
	
	if (dateChooser.getDate() == null) {
		lblErrorfechanacim.setVisible(true);
		ok = false;
	} else {
		lblErrorfechanacim.setVisible(false);
	}
	
	if (textUsuario.getText().trim().isEmpty()) {
		lblErrorusuario.setVisible(true); 
		ok = false;
	} else {
		lblErrorusuario.setVisible(false);
	}
	
	if (textPassword.getText().trim().isEmpty()) {
		lblErrorpassword.setVisible(true); 
		ok = false;
	} else {
		lblErrorpassword.setVisible(false);
	}
	
	if (!textPassword.getText().trim().equals(textRepitePassword.getText().trim())) {
		lblErrorLasContraseas.setVisible(true);
		ok = false;
	} else {
		lblErrorLasContraseas.setVisible(false);
	}
	
	return ok;
}

public void ocultarErrores() {
	lblErrorNombre.setVisible(false);
	lblErrorfechanacim.setVisible(false);
	lblErrorpassword.setVisible(false);
	lblErrorusuario.setVisible(false);
	lblErrorLasContraseas.setVisible(false);
	
}

public void mostrarVentana() {
	frame.setVisible(true);
	
}

}
