package PublImpacto.SistemaGestion.ui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import PublImpacto.SistemaGestion.controller.ClienteController;
import PublImpacto.SistemaGestion.controller.EstadoController;
import PublImpacto.SistemaGestion.controller.PedidoController;
import PublImpacto.SistemaGestion.controller.PrioridadController;
import PublImpacto.SistemaGestion.model.Cliente;
import PublImpacto.SistemaGestion.model.Estado;
import PublImpacto.SistemaGestion.model.Pedido;
import PublImpacto.SistemaGestion.model.Prioridad;
import PublImpacto.SistemaGestion.utils.DateUtils;

public class NuevoPedidoDialog extends JDialog{

	private JTextField tituloField, descripcionField;
    private JDateChooser fechaEntregaChooser;
    private JComboBox<Estado> estadoComboBox;
    private JComboBox<Prioridad> prioridadComboBox;
    private JComboBox<Cliente> clienteComboBox;
    private JComboBox<String> usuarioComboBox;
    private JButton guardarButton, cancelarButton;
    private PedidoController pedidoController;
    private Pedido pedido;

    public NuevoPedidoDialog(Frame parent, Pedido pedido) {
        super(parent, "Nuevo Pedido", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        pedidoController = new PedidoController();
        this.pedido = pedido;

        tituloField = new JTextField(20);
        descripcionField = new JTextField(20);
        fechaEntregaChooser = new JDateChooser();
        estadoComboBox = new JComboBox<>();
        prioridadComboBox = new JComboBox<>();
        clienteComboBox = new JComboBox<>();
        usuarioComboBox = new JComboBox<>(new String[]{"Usuario 1", "Usuario 2"});

        guardarButton = new JButton("Guardar");
        cancelarButton = new JButton("Cancelar");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        panel.add(new JLabel("Título:"));
        panel.add(tituloField);
        panel.add(new JLabel("Descripción:"));
        panel.add(descripcionField);
        panel.add(new JLabel("Fecha de Entrega:"));
        panel.add(fechaEntregaChooser);
        panel.add(new JLabel("Estado:"));
        panel.add(estadoComboBox);
        panel.add(new JLabel("Prioridad:"));
        panel.add(prioridadComboBox);
        panel.add(new JLabel("Cliente:"));
        panel.add(clienteComboBox);
        panel.add(new JLabel("Usuario Responsable:"));
        panel.add(usuarioComboBox);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(guardarButton);
        buttonsPanel.add(cancelarButton);

        add(panel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        cargarComboBoxes();

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPedido();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void cargarComboBoxes() {
        EstadoController estadoController = new EstadoController();
        List<Estado> estados = estadoController.getAllEstados();
        for (Estado estado : estados) {
            estadoComboBox.addItem(estado);  
        }

        PrioridadController prioridadController = new PrioridadController();
        List<Prioridad> prioridades = prioridadController.getAllPrioridades();
        for (Prioridad prioridad : prioridades) {
            prioridadComboBox.addItem(prioridad);  
        }

        ClienteController clienteController = new ClienteController();
        List<Cliente> clientes = clienteController.getAllClientes();
        for (Cliente cliente : clientes) {
            clienteComboBox.addItem(cliente); 
        }
    }

    private void guardarPedido() {
        String titulo = tituloField.getText();
        String descripcion = descripcionField.getText();
        java.util.Date fechaEntrega = fechaEntregaChooser.getDate();
        java.util.Date fechaCreacion = new java.util.Date();
        Estado estadoSeleccionado = (Estado) estadoComboBox.getSelectedItem();
        Prioridad prioridadSeleccionada = (Prioridad) prioridadComboBox.getSelectedItem();
        Cliente clienteSeleccionado = (Cliente) clienteComboBox.getSelectedItem();
        int usuario = (Integer) usuarioComboBox.getSelectedItem();

        boolean resultado = pedidoController.createPedido(titulo, descripcion, DateUtils.convertToLocalDateTime(fechaCreacion), DateUtils.convertToLocalDateTime(fechaEntrega), 
                estadoSeleccionado.getId(), prioridadSeleccionada.getId(), clienteSeleccionado.getId(), usuario);
        if (resultado) {
            JOptionPane.showMessageDialog(this, "Pedido creado exitosamente.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear el pedido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarPedido() {
        pedido.setTitulo(tituloField.getText());
        pedido.setDescripcion(descripcionField.getText());
        pedido.setFechaEntrega(pedido.getFechaEntrega());

        Estado estadoSeleccionado = (Estado) estadoComboBox.getSelectedItem();
        Prioridad prioridadSeleccionada = (Prioridad) prioridadComboBox.getSelectedItem();
        Cliente clienteSeleccionado = (Cliente) clienteComboBox.getSelectedItem();

        pedido.setEstadoId(estadoSeleccionado.getId());
        pedido.setPrioridadId(prioridadSeleccionada.getId());
        pedido.setClienteId(clienteSeleccionado.getId());
        pedido.setUsuarioId(1);

        boolean resultado = pedidoController.updatePedido(pedido.getId(), pedido.getTitulo(), pedido.getDescripcion(), pedido.getFechaEntrega(), pedido.getEstadoId(), pedido.getPrioridadId());
        if (resultado) {
            JOptionPane.showMessageDialog(this, "Pedido modificado exitosamente.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al modificar el pedido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	
}
