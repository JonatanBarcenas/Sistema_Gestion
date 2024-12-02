package PublImpacto.SistemaGestion.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainWindow extends JFrame{
	
	private JMenuBar menuBar;
    private JMenu pedidosMenu, clientesMenu, informesMenu, adminMenu, ayudaMenu;
    private JPanel mainPanel;  
    private JLabel statusLabel;  

    public MainWindow() {
        setTitle("Sistema de Gestión de Pedidos - Imprenta");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
     
        menuBar = new JMenuBar();

        pedidosMenu = new JMenu("Pedidos");
        JMenuItem gestionarPedidosItem = new JMenuItem("Gestionar Pedidos");
        JMenuItem nuevoPedidoItem = new JMenuItem("Nuevo Pedido");
        pedidosMenu.add(nuevoPedidoItem);
        pedidosMenu.add(gestionarPedidosItem);

        clientesMenu = new JMenu("Clientes");
        JMenuItem gestionarClientesItem = new JMenuItem("Gestionar Clientes");
        clientesMenu.add(gestionarClientesItem);

        informesMenu = new JMenu("Informes");
        JMenuItem generarInformeItem = new JMenuItem("Generar Informe");
        informesMenu.add(generarInformeItem);

        adminMenu = new JMenu("Administración");
        JMenuItem gestionarUsuariosItem = new JMenuItem("Gestionar Usuarios");
        JMenuItem gestionarRolesItem = new JMenuItem("Gestionar Roles");
        adminMenu.add(gestionarUsuariosItem);
        adminMenu.add(gestionarRolesItem);

        ayudaMenu = new JMenu("Ayuda");
        JMenuItem acercaDeItem = new JMenuItem("Acerca de...");
        ayudaMenu.add(acercaDeItem);

        menuBar.add(pedidosMenu);
        menuBar.add(clientesMenu);
        menuBar.add(informesMenu);
        menuBar.add(adminMenu);
        menuBar.add(ayudaMenu);
        setJMenuBar(menuBar);

        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());  
        add(mainPanel, BorderLayout.CENTER);

        JPanel statusPanel = new JPanel();
        statusLabel = new JLabel("Usuario: ");
        statusPanel.add(statusLabel);
        add(statusPanel, BorderLayout.SOUTH);

        nuevoPedidoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPedidosView();
            }
        });

        gestionarPedidosItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openGestionarPedidosView();
            }
        });

        gestionarClientesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openGestionarClientesView();
            }
        });

        generarInformeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openInformesView();
            }
        });

        gestionarUsuariosItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openGestionarUsuariosView();
            }
        });

        gestionarRolesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openGestionarRolesView();
            }
        });

        acercaDeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAcercaDe();
            }
        });
    }

    private void openPedidosView() {
        PedidosView pedidosView = new PedidosView();
        mainPanel.removeAll();
        mainPanel.add(pedidosView);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void openGestionarPedidosView() {
        PedidosView pedidosView = new PedidosView();
        mainPanel.removeAll();
        mainPanel.add(pedidosView);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void openGestionarClientesView() {
        ClientesView clientesView = new ClientesView();
        mainPanel.removeAll();
        mainPanel.add(clientesView);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void openInformesView() {
        InformesView informesView = new InformesView();
        mainPanel.removeAll();
        mainPanel.add(informesView);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void openGestionarUsuariosView() {
        UsuariosView usuariosView = new UsuariosView();
        mainPanel.removeAll();
        mainPanel.add(usuariosView);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void openGestionarRolesView() {
        RolesView rolesView = new RolesView();
        mainPanel.removeAll();
        mainPanel.add(rolesView);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showAcercaDe() {
        JOptionPane.showMessageDialog(this, "Sistema de Gestión de Pedidos - Imprenta\nVersión 1.0", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
    }

    public void setUserStatus(String username) {
        statusLabel.setText("Usuario: " + username);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
            }
        });
    }

}
