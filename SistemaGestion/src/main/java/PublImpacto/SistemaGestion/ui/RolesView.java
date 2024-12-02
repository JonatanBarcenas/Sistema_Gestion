package PublImpacto.SistemaGestion.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import PublImpacto.SistemaGestion.controller.RolController;
import PublImpacto.SistemaGestion.model.Rol;

public class RolesView extends JPanel{

	private JTable rolesTable;
    private JButton btnAgregar, btnEditar, btnEliminar;
    private RolController rolController;

    public RolesView() {
        setLayout(new BorderLayout());

        rolController = new RolController();

        String[] columnNames = {"ID", "Rol"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        rolesTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(rolesTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        btnAgregar = new JButton("Agregar Rol");
        btnEditar = new JButton("Editar Rol");
        btnEliminar = new JButton("Eliminar Rol");

        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnEliminar);

        add(buttonPanel, BorderLayout.SOUTH);

        loadRoles();

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRolDialog();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = rolesTable.getSelectedRow();
                if (selectedRow != -1) {
                    int rolId = (int) rolesTable.getValueAt(selectedRow, 0);
                    Rol rol = rolController.getRolById(rolId);
                    if (rol != null) {
                        editRolDialog(rol);
                    }
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = rolesTable.getSelectedRow();
                if (selectedRow != -1) {
                    int rolId = (int) rolesTable.getValueAt(selectedRow, 0);
                    rolController.deleteRol(rolId);
                    loadRoles(); 
                }
            }
        });
    }

    private void loadRoles() {
        List<Rol> roles = rolController.getAllRoles();
        DefaultTableModel model = (DefaultTableModel) rolesTable.getModel();
        model.setRowCount(0); 
        for (Rol rol : roles) {
            model.addRow(new Object[]{
                rol.getId(),
                rol.getNombre()
            });
        }
    }

    private void addRolDialog() {
        RolDialog dialog = new RolDialog(null, true);
        dialog.setVisible(true);
    }
    
    private void editRolDialog(Rol rol) {
        RolDialog dialog = new RolDialog(null, true, rol);
        dialog.setVisible(true);
    }
}
