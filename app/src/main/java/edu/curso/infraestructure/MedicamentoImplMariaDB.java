package edu.curso.infraestructure;

import java.util.List;

import edu.curso.control.MedicamentoDAO;
import edu.curso.model.Medicamento;

public class MedicamentoImplMariaDB implements MedicamentoDAO {

    @Override
    public List<Medicamento> findAll() {
        System.out.println("Buscando todos os itens no banco de dados...");
    }

    @Override
    public void save(Medicamento m) {
        System.out.println("Salvando medicamento no banco...");
    }

    @Override
    public void delete(Medicamento m) {
        System.out.println("Deletando medicamento do banco...");
    }

    @Override
    public void update(long id, Medicamento m) {
        System.out.println("Atualizando medicamento no banco...");
    }

    @Override
    public Medicamento searchById(long id) {
        System.out.println("Buscando medicamento pelo id no banco...");
    }

    @Override
    public List<Medicamento> searchByName(String nome) {
        System.out.println("Buscando medicamentos pelo nome no banco...");
    }

    @Override
    public Medicamento searchByCode(String codBarras) {
        System.out.println("Bsucando medicamentos pelo codigo no banco...");
    }

}
