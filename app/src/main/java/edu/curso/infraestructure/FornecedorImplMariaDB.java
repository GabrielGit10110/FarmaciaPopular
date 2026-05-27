package edu.curso.infraestructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import edu.curso.control.FornecedorDAO;
import edu.curso.model.Fornecedor;

public class FornecedorImplMariaDB implements FornecedorDAO {

    private static final String DB_URI = "jdbc:mariadb://localhost:3306/farmacia";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456";

    private Connection con;

    public FornecedorImplMariaDB() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(DB_URI, DB_USER, DB_PASS);
            System.out.println("Conectado ao banco de dados (Fornecedor)...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Fornecedor f) {
        try {
            String sql = "INSERT INTO fornecedor (nome, cnpj, endereco, telefone, email) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, f.getNome());
            stm.setString(2, f.getCnpj());
            stm.setString(3, f.getEndereco());
            stm.setString(4, f.getTelefone());
            stm.setString(5, f.getEmail());

            stm.executeUpdate();
            System.out.println("Fornecedor inserido no banco...");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar fornecedor");
            e.printStackTrace();
        }
    }

    @Override
    public void update(long id, Fornecedor f) {
        try {
            String sql = "UPDATE fornecedor SET nome=?, cnpj=?, endereco=?, telefone=?, email=? WHERE id=?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, f.getNome());
            stm.setString(2, f.getCnpj());
            stm.setString(3, f.getEndereco());
            stm.setString(4, f.getTelefone());
            stm.setString(5, f.getEmail());
            stm.setLong(6, id);

            stm.executeUpdate();
            System.out.println("Fornecedor atualizado...");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar fornecedor");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Fornecedor f) {
        try {
            String sql = "DELETE FROM fornecedor WHERE id=?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setLong(1, f.getId());

            stm.executeUpdate();
            System.out.println("Fornecedor removido...");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar fornecedor");
            e.printStackTrace();
        }
    }

    @Override
    public List<Fornecedor> findAll() {
        List<Fornecedor> lista = new ArrayList<>();

        try {
            String sql = "SELECT * FROM fornecedor";

            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Fornecedor f = new Fornecedor();

                f.setId(rs.getLong("id"));
                f.setNome(rs.getString("nome"));
                f.setCnpj(rs.getString("cnpj"));
                f.setEndereco(rs.getString("endereco"));
                f.setTelefone(rs.getString("telefone"));
                f.setEmail(rs.getString("email"));

                lista.add(f);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar fornecedores");
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public Fornecedor findById(long id) {
        Fornecedor f = null;

        try {
            String sql = "SELECT * FROM fornecedor WHERE id=?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setLong(1, id);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                f = new Fornecedor();

                f.setId(rs.getLong("id"));
                f.setNome(rs.getString("nome"));
                f.setCnpj(rs.getString("cnpj"));
                f.setEndereco(rs.getString("endereco"));
                f.setTelefone(rs.getString("telefone"));
                f.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar fornecedor por id");
            e.printStackTrace();
        }

        return f;
    }

    @Override
    public Fornecedor findByCnpj(String cnpj) {
        Fornecedor f = null;

        try {
            String sql = "SELECT * FROM fornecedor WHERE cnpj=?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, cnpj);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                f = new Fornecedor();

                f.setId(rs.getLong("id"));
                f.setNome(rs.getString("nome"));
                f.setCnpj(rs.getString("cnpj"));
                f.setEndereco(rs.getString("endereco"));
                f.setTelefone(rs.getString("telefone"));
                f.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar fornecedor por CNPJ");
            e.printStackTrace();
        }

        return f;
    }

    @Override
    public List<Fornecedor> findByNome(String nome) {
        List<Fornecedor> lista = new ArrayList<>();

        try {
            String sql = "SELECT * FROM fornecedor WHERE nome LIKE ?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, "%" + nome + "%");

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Fornecedor f = new Fornecedor();

                f.setId(rs.getLong("id"));
                f.setNome(rs.getString("nome"));
                f.setCnpj(rs.getString("cnpj"));
                f.setEndereco(rs.getString("endereco"));
                f.setTelefone(rs.getString("telefone"));
                f.setEmail(rs.getString("email"));

                lista.add(f);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar fornecedor por nome");
            e.printStackTrace();
        }

        return lista;
    }
}

