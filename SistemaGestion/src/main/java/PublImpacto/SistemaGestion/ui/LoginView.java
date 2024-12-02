package PublImpacto.SistemaGestion.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import PublImpacto.SistemaGestion.controller.UsuarioController;
import PublImpacto.SistemaGestion.model.Usuario;

public class LoginView extends JFrame{
	
	private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, recoverPasswordButton;
    private UsuarioController usuarioController;

    public LoginView() {
        setTitle("Login - Sistema de Gestión de Pedidos");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        usuarioController = new UsuarioController();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Email:"));
        emailField = new JTextField(20);
        panel.add(emailField);

        panel.add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField(20);
        panel.add(passwordField);

        loginButton = new JButton("Iniciar Sesión");
        recoverPasswordButton = new JButton("Recuperar Contraseña");

        panel.add(loginButton);
        panel.add(recoverPasswordButton);

        add(panel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        recoverPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recoverPassword();
            }
        });
    }

    private void login() {
        String email = emailField.getText();
        char[] password = passwordField.getPassword();
        String passwordString = new String(password);

        Usuario usuario = usuarioController.authenticate(email, passwordString);
        if (usuario != null) {
          
            JOptionPane.showMessageDialog(this, "¡Bienvenido, " + usuario.getNombre() + "!");
            openMainWindow();
        } else {
           
            JOptionPane.showMessageDialog(this, "Correo electrónico o contraseña incorrectos.", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void recoverPassword() {
        JOptionPane.showMessageDialog(this, "Funcionalidad de recuperación de contraseña (pendiente de implementación).");
    }

    private void openMainWindow() {
       
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
        this.dispose();  
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
 
                LoginView loginView = new LoginView();
                loginView.setVisible(true);
            }
        });
    }

}
