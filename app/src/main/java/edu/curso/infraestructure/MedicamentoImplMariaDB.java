package edu.curso.infraestructure;

import java.time.LocalDate;
import java.util.List;

import edu.curso.control.MedicamentoDAO;
import edu.curso.model.Medicamento;

public class MedicamentoImplMariaDB implements MedicamentoDAO {

    @Override
    public List<Medicamento> findAll() {
        System.out.println("Buscando todos os itens no banco de dados...");

        return List.of(
            new Medicamento(1, "Dipirona", "123", LocalDate.now(), LocalDate.now().plusMonths(6), true, 10.0),
            new Medicamento(2, "Paracetamol", "132", LocalDate.now(), LocalDate.now().plusMonths(8), false, 15.0),
            new Medicamento(3, "Loratadina", "231", LocalDate.now(), LocalDate.now().plusMonths(12), true, 10.0)
        );
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

        return null;
    }

    @Override
    public List<Medicamento> searchByName(String nome) {
        System.out.println("Buscando medicamentos pelo nome no banco...");

        return null;
    }

    @Override
    public Medicamento searchByCode(String codBarras) {
        System.out.println("Bsucando medicamentos pelo codigo no banco...");

        return null;
    }

}
