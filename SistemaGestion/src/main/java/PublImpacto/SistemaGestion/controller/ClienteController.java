package PublImpacto.SistemaGestion.controller;

import java.util.List;

import PublImpacto.SistemaGestion.dao.ClienteDAO;
import PublImpacto.SistemaGestion.model.Cliente;

public class ClienteController {
	
	private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public boolean createCliente(String nombre, String email, String telefono, String empresa) {
        Cliente cliente = new Cliente(0, nombre, email, telefono, empresa);
        return clienteDAO.createCliente(cliente);
    }

    public List<Cliente> getAllClientes() {
        return clienteDAO.getAllClientes();
    }

    public Cliente getClienteById(int id) {
        return clienteDAO.getClienteById(id);
    }

    public boolean updateCliente(int id, String nombre, String email, String telefono, String empresa) {
        Cliente cliente = new Cliente(id, nombre, email, telefono, empresa);
        return clienteDAO.updateCliente(cliente);
    }

    public boolean deleteCliente(int id) {
        return clienteDAO.deleteCliente(id);
    }

}
