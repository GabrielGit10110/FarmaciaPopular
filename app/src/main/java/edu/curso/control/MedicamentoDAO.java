package edu.curso.control;

import java.util.List;

import edu.curso.model.Medicamento;

public interface MedicamentoDAO {
    List<Medicamento> findAll();
    void save(Medicamento m);
    void delete(Medicamento m);
    void update(long id, Medicamento m);
    Medicamento searchById(long id);
    List<Medicamento> searchByName(String nome);
    Medicamento searchByCode(String codBarras);
}
