package edu.curso.infraestructure;

import java.util.ArrayList;
import java.util.List;

import edu.curso.model.Cliente;
import edu.curso.control.ClienteDAO;

public class ClienteImplMemory implements ClienteDAO {
    private List<Cliente> clientes = new ArrayList<>();
    private long id = 1;

    @Override
    public List<Cliente> findAll() {
        return new ArrayList<>(clientes);
    }

    @Override
    public void save(Cliente c) {
        c.setId(this.id);
        clientes.add(c);
        this.id++;
    }

    @Override
    public void delete(Cliente c) {
        clientes.remove(c);
    }

    @Override
    public void update(long id, Cliente c) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                clientes.set(i, c);
                return;
            }
        }

    }

    @Override
    public Cliente searchById(long id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Cliente> searchByName(String nome) {
        List<Cliente> nomes = new ArrayList<>();

        for (Cliente c : clientes) {
            if (c.getNome().toLowerCase().contains(nome.toLowerCase())) {
                nomes.add(c);
            }
        }

        return nomes;
    }

    @Override
    public Cliente searchByCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().toLowerCase().contains(cpf.toLowerCase())) {
                return c;
            }
        }
        
        return null;
    }
}
