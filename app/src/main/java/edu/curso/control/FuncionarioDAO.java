package edu.curso.control;

import java.util.List;

import edu.curso.model.Funcionario;

public interface FuncionarioDAO {
    public void save(Funcionario fu);
    public void update(long id, Funcionario fu);
    public void delete(Funcionario fu);
    public List<Funcionario> findAll();
    public Funcionario findById(long id);
    public List<Funcionario> findByName(String nome);
    public Funcionario findByCnpj(String registro);
}
//