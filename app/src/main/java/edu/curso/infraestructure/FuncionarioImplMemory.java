package edu.curso.infraestructure;

import java.util.ArrayList;
import java.util.List;

import edu.curso.control.FuncionarioDAO;
import edu.curso.model.Funcionario;

public class FuncionarioImplMemory implements FuncionarioDAO {

    private List<Funcionario> funcionarios = new ArrayList<>();
    private long id = 1;

    @Override
    public List<Funcionario> findAll() {
        return new ArrayList<>(funcionarios);
    }

    @Override
    public void save(Funcionario fu) {
        fu.setId(this.id);
        funcionarios.add(fu);
        this.id++;
    }

    @Override
    public void delete(Funcionario fu) {
        funcionarios.remove(fu);
    }

    @Override
    public void update(long id, Funcionario fu) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getId() == id) {
                funcionarios.set(i, fu);
                return;
            }
        }

    }

    @Override
    public Funcionario findById(long id) {
        for (Funcionario fu : funcionarios) {
            if (fu.getId() == id) {
                return fu;
            }
        }
        return null;
    }

    @Override
    public List<Funcionario> findByName(String nome) {
        List<Funcionario> nomes = new ArrayList<>();

        for (Funcionario fu : funcionarios) {
            if (fu.getNome().toLowerCase().contains(nome.toLowerCase())) {
                nomes.add(fu);
            }
        }

        return nomes;
    }

    @Override
    public Funcionario findByRegistro(String registro) {
        for (Funcionario fu : funcionarios) {
            if (fu.getRegistro().toLowerCase().contains(registro.toLowerCase())) {
                return fu;
            }
        }
        return null;
    }

}
