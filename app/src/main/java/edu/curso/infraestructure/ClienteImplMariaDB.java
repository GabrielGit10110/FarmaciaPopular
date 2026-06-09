package edu.curso.infraestructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.curso.control.ClienteDAO;
import edu.curso.model.Cliente;

public class ClienteImplMariaDB implements ClienteDAO {

    private static final String DB_URI = "jdbc:mariadb://localhost:3306/farmacia";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456";

    private Connection con;

    public ClienteImplMariaDB() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(DB_URI, DB_USER, DB_PASS);
            System.out.println("Conectado ao banco de dados do Cliente");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o Driver...");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados");
            e.printStackTrace();
        }

    }

    @Override
    public void save(Cliente c) {
        try {
            String sql = "INSERT INTO cliente (nome, cpf, endereco, telefone, data_nascimento, r_popular) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, c.getNome());
            stm.setString(2, c.getCpf());
            stm.setString(3, c.getEndereco());
            stm.setString(4, c.getTelefone());
            stm.setDate(5, java.sql.Date.valueOf(c.getDataNascimento()));
            stm.setString(6, c.getRPopular());

            stm.executeUpdate();
            System.out.println("Cliente inserido no banco...");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar o cliente");
            e.printStackTrace();
        }
    }

    @Override
    public void update(long id, Cliente c) {
         try {

            String sql = "UPDATE cliente SET nome=?, cpf=?, endereco=?, telefone=?, data_nascimento=?, r_popular=?"+
                         "WHERE id = ? ";
    
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, c.getNome());
            stm.setString(2, c.getCpf());
            stm.setString(3, c.getEndereco());
            stm.setString(4, c.getTelefone());
            stm.setDate(5, java.sql.Date.valueOf(c.getDataNascimento()));
            stm.setString(6, c.getRPopular());

            stm.setLong(7, c.getId());

            stm.executeUpdate();
            System.out.println("Cliente atualizado no banco...");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar informacoes do cliente");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Cliente c) {
        try {
            String sql = "DELETE FROM cliente " + "WHERE id = ?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setLong(1, c.getId());
            
            stm.executeUpdate();
            System.out.println("Cliente está sendo removido do banco...");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar o cliente");
            e.printStackTrace();
        }
    }

    @Override
    public List<Cliente> findAll() {
          List<Cliente> lista = new ArrayList<>();

        try {
            String sqlCommand = "SELECT * FROM cliente ";

            PreparedStatement stm = con.prepareStatement(sqlCommand);
            ResultSet rs = stm.executeQuery();
            System.out.println("Buscando todos os clientes do banco...");
            while (rs.next()) {
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
                String rPopular = rs.getString("r_popular");

                Cliente c = new Cliente(
                    id,
                    nome,
                    cpf,
                    endereco,
                    telefone,
                    dataNascimento,
                    rPopular
                );

                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar no banco de dados");
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public Cliente searchById(long id) {
        Cliente c = null;

        try {
            String sql = "SELECT * FROM cliente "+
                         "WHERE id = ?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();

            System.out.println("Cliente encontrado no banco...");

            if (rs.next()) {
                c = new Cliente();

                long idCliente = rs.getLong("id");
                String nome =  rs.getString("nome");
                String cpf = rs.getString("cpf");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                LocalDate dataNascimento = rs.getDate("data_de_nascimento").toLocalDate();
                String rPopular = rs.getString("registro_popular");
                
                c.setId(idCliente);
                c.setNome(nome);
                c.setCpf(cpf);
                c.setEndereco(endereco);
                c.setTelefone(telefone);
                c.setDataNascimento(dataNascimento);
                c.setRPopular(rPopular);
               
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente pelo id");
            e.printStackTrace();
        }

        return c;
    }

    @Override
    public List<Cliente> searchByName(String nome) {
       List<Cliente> lista = new ArrayList<>();

        try {
            String sql = "SELECT * FROM cliente "+
                         "WHERE nome LIKE ?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, "%" + nome + "%");
            ResultSet rs = stm.executeQuery();

            System.out.println("Clientes encontrados no banco...");
            while (rs.next()) {
                Cliente c = new Cliente();

                long idCliente = rs.getLong("id");
                String nomeCliente =  rs.getString("nome");
                String cpf = rs.getString("cpf");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
                String rPopular = rs.getString("r_popular");
                
                c.setId(idCliente);
                c.setNome(nomeCliente);
                c.setCpf(cpf);
                c.setEndereco(endereco);
                c.setTelefone(telefone);
                c.setDataNascimento(dataNascimento);
                c.setRPopular(rPopular);

                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar clientes pelo nome");
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public Cliente searchByCpf(String cpf) {
        Cliente c = null;

        try {
            String sql = "SELECT * FROM cliente WHERE cpf=?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, cpf);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                c = new Cliente();

                c.setId(rs.getLong("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEndereco(rs.getString("endereco"));
                c.setTelefone(rs.getString("telefone"));
                c.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                c.setRPopular(rs.getString("r_popular"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar o cliente por CPF");
            e.printStackTrace();
        }

        return c;
    }

}
