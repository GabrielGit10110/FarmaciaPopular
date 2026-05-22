package edu.curso.control;

import java.util.List;

import edu.curso.infraestructure.MedicamentoImplMariaDB;
import edu.curso.model.Medicamento;

public class MedicamentoController {
    private final MedicamentoDAO dao = new MedicamentoImplMariaDB();

    public void save() {

    }

    public void delete() {

    }

    public void update() {

    }

    public Medicamento searchById(long id) {
        return this.dao.searchById(id);
    }

    public List<Medicamento> searchByName(String name) {
        return this.dao.searchByName(name);
    }

    public Medicamento searchByCode(String codBarras) {
        return this.dao.searchByCode(codBarras);
    }

}
