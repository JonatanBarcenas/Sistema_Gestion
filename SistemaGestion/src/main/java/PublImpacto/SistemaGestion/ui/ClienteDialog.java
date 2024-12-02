package PublImpacto.SistemaGestion.ui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import PublImpacto.SistemaGestion.controller.ClienteController;
import PublImpacto.SistemaGestion.model.Cliente;

public class ClienteDialog extends JDialog{

	private JTextField nombreField, emailField, telefonoField, empresaField;
    private JButton guardarButton, cancelarButton;
    private ClienteController clienteController;
    private Cliente cliente;

    public ClienteDialog(Frame parent, boolean modal) {
        super(parent, "Agregar Cliente", modal);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        clienteController = new ClienteController();

        nombreField = new JTextField(20);
        emailField = new JTextField(20);
        telefonoField = new JTextField(20);
        empresaField = new JTextField(20);

        guardarButton = new JButton("Guardar");
        cancelarButton = new JButton("Cancelar");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Teléfono:"));
        panel.add(telefonoField);
        panel.add(new JLabel("Empresa:"));
        panel.add(empresaField);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(guardarButton);
        buttonsPanel.add(cancelarButton);

        add(panel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCliente();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public ClienteDialog(Frame parent, boolean modal, Cliente cliente) {
        this(parent, modal);
        setTitle("Editar Cliente");

        this.cliente = cliente;

        nombreField.setText(cliente.getNombre());
        emailField.setText(cliente.getEmail());
        telefonoField.setText(cliente.getTelefono());
        empresaField.setText(cliente.getEmpresa());

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarCliente();
            }
        });
    }

    private void guardarCliente() {
        String nombre = nombreField.getText();
        String email = emailField.getText();
        String telefono = telefonoField.getText();
        String empresa = empresaField.getText();

        if (clienteController.createCliente(nombre, email, telefono, empresa)) {
            JOptionPane.showMessageDialog(this, "Cliente guardado con éxito.");
            dispose(); 
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarCliente() {
        cliente.setNombre(nombreField.getText());
        cliente.setEmail(emailField.getText());
        cliente.setTelefono(telefonoField.getText());
        cliente.setEmpresa(empresaField.getText());

        if (clienteController.updateCliente(cliente.getId(), cliente.getNombre(), cliente.getEmail(), cliente.getTelefono(), cliente.getEmpresa())) {
            JOptionPane.showMessageDialog(this, "Cliente editado con éxito.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al editar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
