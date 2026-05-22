package edu.curso.model;

import java.time.LocalDate;

public class Medicamento {
    private long id;
    private String nome;
    private String codBarras;
    private LocalDate dataEntrega;
    private LocalDate dataVencimento;
    private boolean farmPopular;
    private double valor;

    public Medicamento() {
        this.id = 0;
        this.nome = "";
        this.codBarras = "";
        this.dataEntrega = LocalDate.now();
        this.dataVencimento = LocalDate.now();
        this.farmPopular = false;
        this.valor = 0.0;
    }

    public Medicamento(long id, String nome, String codBarras, LocalDate dataEntrega,
        LocalDate dataVencimento, boolean farmPopular, double valor
    ) {
        this.id = id;
        this.nome = nome;
        this.codBarras = codBarras;
        this.dataEntrega = dataEntrega;
        this.dataVencimento = dataVencimento;
        this.farmPopular = farmPopular;
        this.valor = valor;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCodBarras() { return codBarras; }
    public void setCodBarras(String codBarras) { this.codBarras = codBarras; }

    public LocalDate getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(LocalDate dataEntrega) { this.dataEntrega = dataEntrega; }

    public LocalDate getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }

    public boolean isFarmPopular() { return farmPopular; }
    public void setFarmPopular(boolean farmPopular) { this.farmPopular = farmPopular; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    @Override
    public String toString() {
        msg.append("ID do Medicamento: ").append(this.getId()).append("\n");
        msg.append("Nome do Medicamento: ").append(this.getNome()).append("\n");
        msg.append("Codigo de Barras do Medicamento: ").append(this.getCodBarras()).append("\n");
        msg.append("Data de Entrega: ").append(this.getDataEntrega()).append("\n");
        msg.append("Data de Vencimento: ").append(this.getDataVencimento()).append("\n");
        msg.append("Farmacia Popular: ");

        if (this.isFarmPopular()) {
            msg.append("Sim").append("\n");
        } else {
            msg.append("Nao").append("\n");
        }

        msg.append("Valor do Medicamento: ").append(m.getValor());
        
        return msg.toString();
    }
}
