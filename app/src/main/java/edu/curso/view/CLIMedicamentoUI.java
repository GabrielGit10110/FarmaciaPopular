package edu.curso.view;

import java.time.LocalDate;
import java.util.Scanner;

import edu.curso.control.MedicamentoController;
import edu.curso.infraestructure.MedicamentoImplMariaDB;

public class CLIMedicamentoUI {

    private final MedicamentoController controller =
        new MedicamentoController(new MedicamentoImplMariaDB());

    private final Scanner sc = new Scanner(System.in);
    private int indice = 0;

    public void iniciar() {

        int opcao;

        do {
            System.out.println("\n==== MENU MEDICAMENTO ====");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Deletar");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {

                case 1:
                    cadastrar();
                    break;

                case 2:
                    listar();
                    break;

                case 3:
                    deletar();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void cadastrar() {
        System.out.println("\n=== Cadastro ===");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Código de Barras: ");
        String cod = sc.nextLine();

        System.out.print("Valor: ");
        double valor = sc.nextDouble();

        Medicamento m = new Medicamento(this.indice, nome, cod, LocalDate.now(), LocalDate.now().plusMonths(6));

        sc.nextLine(); 

        controller.save(m);

        System.out.println("Medicamento cadastrado!");
    }

    private void listar() {
        System.out.println("\n=== Lista ===");

        controller.getLista().forEach(m -> {
            System.out.println(m);
        });
    }

    private void deletar() {
        listar();

        System.out.print("\nDigite o índice para deletar: ");
        int i = sc.nextInt();
        sc.nextLine();

        controller.delete(indice);

        System.out.println("Medicamento deletado!");
    }

    public static void main(String[] args) {
        new CLIMedicamentoUI().iniciar();
    }
}
