package edu.curso.infraestructure;

import java.util.ArrayList;
import java.util.List;

import edu.curso.control.MedicamentoDAO;
import edu.curso.model.Medicamento;

public class MedicamentoImplMemory implements MedicamentoDAO {
    private List<Medicamento> medicamentos = new ArrayList<>();
    private long id = 1;

    @Override
    public List<Medicamento> findAll() {
        return new ArrayList<>(medicamentos);
    }

    @Override
    public void save(Medicamento m) {
        m.setId(this.id);
        medicamentos.add(m);
        this.id++;
    }

    @Override
    public void delete(Medicamento m) {
        medicamentos.remove(m);

    }

    @Override
    public void update(long id, Medicamento m) {
        for (int i = 0; i < medicamentos.size(); i++) {
            if (medicamentos.get(i).getId() == id) {
                medicamentos.set(i, m);
                return;
            }
        }

    }

    @Override
    public Medicamento searchById(long id) {
        for (Medicamento m : medicamentos) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    @Override
    public List<Medicamento> searchByName(String nome) {
        List<Medicamento> nomes = new ArrayList<>();

        for (Medicamento m : medicamentos) {
            if (m.getNome().toLowerCase().contains(nome.toLowerCase())) {
                nomes.add(m);
            }
        }

        return nomes;
    }

    @Override
    public Medicamento searchByCode(String codBarras) {
        for (Medicamento m : medicamentos) {
            if (m.getCodBarras().toLowerCase().contains(codBarras.toLowerCase())) {
                return m;
            }
        }
        return null;
    }

}
