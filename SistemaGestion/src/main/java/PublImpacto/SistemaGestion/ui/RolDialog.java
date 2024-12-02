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

import PublImpacto.SistemaGestion.controller.RolController;
import PublImpacto.SistemaGestion.model.Rol;

public class RolDialog extends JDialog {
	
	 private JTextField nombreField, descripcionField;
	    private JButton guardarButton, cancelarButton;
	    private RolController rolController;
	    private Rol rol;

	    public RolDialog(Frame parent, boolean modal) {
	        super(parent, "Agregar Rol", modal);
	        setSize(400, 300);
	        setLocationRelativeTo(parent);

	        rolController = new RolController();

	        nombreField = new JTextField(20);
	        descripcionField = new JTextField(20);

	        guardarButton = new JButton("Guardar");
	        cancelarButton = new JButton("Cancelar");

	        JPanel panel = new JPanel();
	        panel.setLayout(new GridLayout(3, 2));

	        panel.add(new JLabel("Nombre del Rol:"));
	        panel.add(nombreField);
	        panel.add(new JLabel("Descripción:"));
	        panel.add(descripcionField);

	        JPanel buttonsPanel = new JPanel();
	        buttonsPanel.add(guardarButton);
	        buttonsPanel.add(cancelarButton);

	        add(panel, BorderLayout.CENTER);
	        add(buttonsPanel, BorderLayout.SOUTH);

	        guardarButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                guardarRol();
	            }
	        });

	        cancelarButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                dispose();
	            }
	        });
	    }

	    public RolDialog(Frame parent, boolean modal, Rol rol) {
	        this(parent, modal);
	        setTitle("Editar Rol");

	        this.rol = rol;

	        nombreField.setText(rol.getNombre());

	        guardarButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                editarRol();
	            }
	        });
	    }

	    private void guardarRol() {
	        String nombre = nombreField.getText();
	        String descripcion = descripcionField.getText();

	        if (rolController.createRol(nombre)) {
	            JOptionPane.showMessageDialog(this, "Rol guardado exitosamente.");
	            dispose();
	        } else {
	            JOptionPane.showMessageDialog(this, "Error al guardar el rol.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }

	    private void editarRol() {
	        rol.setNombre(nombreField.getText());
	      

	        if (rolController.updateRol(rol.getId(), rol.getNombre())) {
	            JOptionPane.showMessageDialog(this, "Rol editado exitosamente.");
	            dispose();
	        } else {
	            JOptionPane.showMessageDialog(this, "Error al editar el rol.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }

}
