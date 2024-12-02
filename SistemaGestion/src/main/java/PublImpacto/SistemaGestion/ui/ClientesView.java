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

import PublImpacto.SistemaGestion.controller.ClienteController;
import PublImpacto.SistemaGestion.model.Cliente;

public class ClientesView extends JPanel {
	
	private JTable clientesTable;
    private JButton btnAgregar, btnEditar, btnEliminar;
    private ClienteController clienteController;

    public ClientesView() {
        setLayout(new BorderLayout());

        clienteController = new ClienteController();

        String[] columnNames = {"ID", "Nombre", "Email", "Teléfono", "Empresa"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        clientesTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(clientesTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        btnAgregar = new JButton("Agregar Cliente");
        btnEditar = new JButton("Editar Cliente");
        btnEliminar = new JButton("Eliminar Cliente");

        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnEliminar);

        add(buttonPanel, BorderLayout.SOUTH);

        loadClientes();

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addClienteDialog();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = clientesTable.getSelectedRow();
                if (selectedRow != -1) {
                    int clienteId = (int) clientesTable.getValueAt(selectedRow, 0);
                    Cliente cliente = clienteController.getClienteById(clienteId);
                    if (cliente != null) {
                        editClienteDialog(cliente);
                    }
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = clientesTable.getSelectedRow();
                if (selectedRow != -1) {
                    int clienteId = (int) clientesTable.getValueAt(selectedRow, 0);
                    clienteController.deleteCliente(clienteId);
                    loadClientes(); 
                }
            }
        });
    }

    private void loadClientes() {
        List<Cliente> clientes = clienteController.getAllClientes();
        DefaultTableModel model = (DefaultTableModel) clientesTable.getModel();
        model.setRowCount(0); 

        for (Cliente cliente : clientes) {
            model.addRow(new Object[]{
                cliente.getId(),
                cliente.getNombre(),
                cliente.getEmail(),
                cliente.getTelefono(),
                cliente.getEmpresa()
            });
        }
    }

    private void addClienteDialog() {
        ClienteDialog dialog = new ClienteDialog(null, true);
        dialog.setVisible(true);
    }

    private void editClienteDialog(Cliente cliente) {
        ClienteDialog dialog = new ClienteDialog(null, true, cliente);
        dialog.setVisible(true);
    }

}
