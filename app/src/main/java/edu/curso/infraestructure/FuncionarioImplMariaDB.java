package edu.curso.infraestructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.curso.control.FuncionarioDAO;
import edu.curso.model.Funcionario;

public class FuncionarioImplMariaDB implements FuncionarioDAO {
    private static final String DB_URI = "jdbc:mariadb://localhost:3306/farmacia";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456";

    private Connection con;

    public FuncionarioImplMariaDB() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver carregado...");
            con = DriverManager.getConnection(DB_URI, DB_USER, DB_PASS);
            System.out.println("Conectando ao banco de dados (Funcionario)...");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o Driver...");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados");
            e.printStackTrace();
        }

    }

    @Override
    public List<Funcionario> findAll() {
        List<Funcionario> lista = new ArrayList<>();

        try {
            String sqlCommand = "SELECT * FROM funcionario ";

            PreparedStatement stm = con.prepareStatement(sqlCommand);
            ResultSet rs = stm.executeQuery();
            System.out.println("Buscando todos os funcionarios do banco...");
            while (rs.next()) {
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String registro = rs.getString("registro");
                String email = rs.getString("email");
                String cargo = rs.getString("cargo");

                Funcionario fu = new Funcionario(
                    id,
                    nome,
                    telefone,
                    registro,
                    email,
                    cargo
                );

                lista.add(fu);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar no banco de dados");
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void save(Funcionario fu) {
        try {
            String sql = "INSERT INTO funcionario (nome, telefone, registro, email, cargo) VALUES(?, ?, ?, ?, ?)";
            
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, fu.getNome());
            stm.setString(2, fu.getTelefone());
            stm.setString(3, fu.getRegistro());
            stm.setString(4, fu.getEmail());
            stm.setString(5, fu.getCargo());

            stm.executeUpdate();
            System.out.println("Funcionario inserido no banco...");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar o funcionario");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Funcionario fu) {
        try {
            String sql = "DELETE FROM funcionario "+
                         "WHERE id = ?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setLong(1, fu.getId());
            
            stm.executeUpdate();
            System.out.println("Funcionario removido do banco...");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar o funcionario");
            e.printStackTrace();
        }
    }

    @Override
    public void update(long id, Funcionario fu) {
        try {
            String sql = "UPDATE funcionario SET nome=?, telefone=?, registro=?, email=?, cargo=? "+
                         "WHERE id = ? ";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, fu.getNome());
            stm.setString(2, fu.getTelefone());
            stm.setString(3, fu.getRegistro());
            stm.setString(4, fu.getEmail());
            stm.setString(5, fu.getCargo());

            stm.setLong(6, fu.getId());

            stm.executeUpdate();
            System.out.println("Funcionario atualizado no banco...");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar informacoes do funcionario");
            e.printStackTrace();
        }
    }

    @Override
    public Funcionario findById(long id) {
        Funcionario fu = null;

        try {
            String sql = "SELECT * FROM funcionario "+
                         "WHERE id = ?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();

            System.out.println("Funcionario encontrado no banco...");

            if (rs.next()) {
                fu = new Funcionario();

                long idFuncionario = rs.getLong("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String registro = rs.getString("registro");
                String email = rs.getString("email");
                String cargo = rs.getString("cargo");

                fu.setId(idFuncionario);
                fu.setNome(nome);
                fu.setTelefone(telefone);
                fu.setRegistro(registro);
                fu.setEmail(email);
                fu.setCargo(cargo);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao salvar o medicamento");
            e.printStackTrace();
        }

        return fu;
    }

    @Override
    public List<Funcionario> findByName(String nome) {
        List<Funcionario> lista = new ArrayList<>();

        try {
            String sql = "SELECT * FROM funcionario "+
                         "WHERE nome LIKE ?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, "%" + nome + "%");
            ResultSet rs = stm.executeQuery();
            System.out.println("Funcionarios encontrados no banco...");
            while (rs.next()) {
                Funcionario fu = new Funcionario();

                long idFuncionario = rs.getLong("id");
                String nomeFuncionario =  rs.getString("nome");
                String telefone = rs.getString("telefone");
                String registro = rs.getString("registro");
                String email = rs.getString("email");
                String cargo = rs.getString("cargo");

                fu.setId(idFuncionario);
                fu.setNome(nomeFuncionario);
                fu.setTelefone(telefone);
                fu.setRegistro(registro);
                fu.setEmail(email);
                fu.setCargo(cargo);

                lista.add(fu);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionarios por nome");
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public Funcionario findByRegistro(String registroInput) {
        Funcionario fu = new Funcionario();

        try {
            String sql = "SELECT * FROM funcionario "+
                         "WHERE registro = ?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, registroInput);
            ResultSet rs = stm.executeQuery();
            System.out.println("Funcionario encontrado no banco...");
            while (rs.next()) {
                long idFuncionario = rs.getLong("id");
                String nome =  rs.getString("nome");
                String telefone = rs.getString("telefone");
                String registro = rs.getString("registro");
                String email = rs.getString("email");
                String cargo = rs.getString("cargo");

                fu.setId(idFuncionario);
                fu.setNome(nome);
                fu.setTelefone(telefone);
                fu.setRegistro(registro);
                fu.setEmail(email);
                fu.setCargo(cargo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionario por registro");
            e.printStackTrace();
        }

        return fu;
    }
            
}