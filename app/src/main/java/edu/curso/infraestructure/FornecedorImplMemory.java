package edu.curso.infraestructure;

import java.util.ArrayList;
import java.util.List;

import edu.curso.control.FornecedorDAO;
import edu.curso.model.Fornecedor;

public class FornecedorImplMemory implements FornecedorDAO {
    private List<Fornecedor> fornecedores = new ArrayList<>();
    private long id = 1;

    @Override
    public List<Fornecedor> findAll() {
        return new ArrayList<>(fornecedores);
    }

    @Override
    public void save(Fornecedor f) {
        f.setId(this.id);
        fornecedores.add(f);
        this.id++;
    }

    @Override
    public void delete(Fornecedor f) {
        fornecedores.remove(f);

    }

    @Override
    public void update(long id, Fornecedor f) {
        for (int i = 0; i < fornecedores.size(); i++) {
            if (fornecedores.get(i).getId() == id) {
                fornecedores.set(i, f);
                return;
            }
        }

    }

    @Override
    public Fornecedor findById(long id) {
        for (Fornecedor f : fornecedores) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    @Override
    public List<Fornecedor> findByNome(String nome) {
        List<Fornecedor> nomes = new ArrayList<>();

        for (Fornecedor f : fornecedores) {
            if (f.getNome().toLowerCase().contains(nome.toLowerCase())) {
                nomes.add(f);
            }
        }

        return nomes;
    }

    @Override
    public Fornecedor findByCnpj(String codBarras) {
        for (Fornecedor f : fornecedores) {
            if (f.getCnpj().toLowerCase().contains(codBarras.toLowerCase())) {
                return f;
            }
        }
        return null;
    }

}