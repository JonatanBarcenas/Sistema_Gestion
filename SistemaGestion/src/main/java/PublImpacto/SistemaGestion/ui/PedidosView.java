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

import PublImpacto.SistemaGestion.controller.PedidoController;
import PublImpacto.SistemaGestion.model.Pedido;

public class PedidosView extends JPanel{
	
	private JTable pedidosTable;
    private JButton btnModificar, btnEliminar, btnActualizar;
    private PedidoController pedidoController;

    public PedidosView() {
        setLayout(new BorderLayout());

        pedidoController = new PedidoController();

        String[] columnNames = {"ID", "Título", "Descripción", "Fecha Creación", "Fecha Entrega", "Estado", "Prioridad"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        pedidosTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(pedidosTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        btnModificar = new JButton("Modificar Pedido");
        btnEliminar = new JButton("Eliminar Pedido");
        btnActualizar = new JButton("Actualizar Lista");

        buttonPanel.add(btnModificar);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnActualizar);

        add(buttonPanel, BorderLayout.SOUTH);

        loadPedidos();

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPedidos();
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = pedidosTable.getSelectedRow();
                if (selectedRow != -1) {
                    int pedidoId = (int) pedidosTable.getValueAt(selectedRow, 0);
                    Pedido pedido = pedidoController.getPedidoById(pedidoId);
                    if (pedido != null) {
                        modifyPedidoDialog(pedido);
                    }
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = pedidosTable.getSelectedRow();
                if (selectedRow != -1) {
                    int pedidoId = (int) pedidosTable.getValueAt(selectedRow, 0);
                    pedidoController.deletePedido(pedidoId);
                    loadPedidos(); 
                }
            }
        });
    }

    private void loadPedidos() {
        List<Pedido> pedidos = pedidoController.getAllPedidos();
        DefaultTableModel model = (DefaultTableModel) pedidosTable.getModel();
        model.setRowCount(0); 

        for (Pedido pedido : pedidos) {
            model.addRow(new Object[]{
                pedido.getId(),
                pedido.getTitulo(),
                pedido.getDescripcion(),
                pedido.getFechaCreacion(),
                pedido.getFechaEntrega(),
                pedido.getEstadoId(),
                pedido.getPrioridadId()
            });
        }
    }

    private void modifyPedidoDialog(Pedido pedido) {
        NuevoPedidoDialog dialog = new NuevoPedidoDialog(null, pedido);
        dialog.setVisible(true);
    }

}
