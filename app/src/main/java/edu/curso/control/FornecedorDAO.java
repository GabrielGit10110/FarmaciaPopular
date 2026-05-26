package edu.curso.control;

import java.util.List;

import edu.curso.model.Fornecedor;

public interface FornecedorDAO {
    public void save(Fornecedor f);
    public void update(long id, Fornecedor f);
    public void delete(Fornecedor f);
    public List<Fornecedor> findAll();
    public Fornecedor findById(long id);
    public List<Fornecedor> findByNome(String nome);
    public Fornecedor findByCnpj(String cnpj);

}