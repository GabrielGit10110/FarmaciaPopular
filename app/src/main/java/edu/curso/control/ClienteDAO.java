package edu.curso.control;

import java.util.List;

import edu.curso.model.Cliente;

public interface ClienteDAO {

    public void save(Cliente c);
    public void update(long id, Cliente c);
    public void delete(Cliente c);
    public List<Cliente> findAll();
    public Cliente searchById(long id);
    public List<Cliente> searchByName(String nome);
    public Cliente searchByCpf(String cpf);

}
