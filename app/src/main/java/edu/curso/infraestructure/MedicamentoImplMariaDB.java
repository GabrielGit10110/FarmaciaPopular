package edu.curso.infraestructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.curso.control.MedicamentoDAO;
import edu.curso.model.Medicamento;

public class MedicamentoImplMariaDB implements MedicamentoDAO {
    private static final String DB_URI = "jdbc:mariadb://localhost:3306/farmacia";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456";

    private Connection con;

    public MedicamentoImplMariaDB() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver carregado...");
            con = DriverManager.getConnection(DB_URI, DB_USER, DB_PASS);
            System.out.println("Conectando ao banco de dados...");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o Driver...");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados");
            e.printStackTrace();
        }

    }

    @Override
    public List<Medicamento> findAll() {
        List<Medicamento> lista = new ArrayList<>();

        try {
            String sqlCommand = "SELECT * FROM medicamento ";

            PreparedStatement stm = con.prepareStatement(sqlCommand);
            ResultSet rs = stm.executeQuery();
            System.out.println("Buscando todos os medicamentos do banco...");
            while (rs.next()) {
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String codBarras = rs.getString("codigo_de_barras");
                LocalDate dataEntrega = rs.getDate("data_de_entrega").toLocalDate();
                LocalDate dataVencimento = rs.getDate("data_de_vencimento").toLocalDate();
                boolean farmPopular = rs.getBoolean("farmacia_popular");
                double valor = rs.getDouble("valor");

                Medicamento m = new Medicamento(
                    id,
                    nome,
                    codBarras,
                    dataEntrega,
                    dataVencimento,
                    farmPopular,
                    valor
                );

                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar no banco de dados");
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public void save(Medicamento m) {
        try {
            String sql = "INSERT INTO medicamento (nome, codigo_de_barras, data_de_entrega, "+
                         "data_de_vencimento, farmacia_popular, valor) "+
                         "VALUES(?, ?, ?, ?, ?, ?)";
            
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, m.getNome());
            stm.setString(2, m.getCodBarras());
            stm.setDate(3, 
                java.sql.Date.valueOf(m.getDataEntrega())
            );
            stm.setDate(4, 
                java.sql.Date.valueOf(m.getDataVencimento())
            );
            stm.setBoolean(5, m.isFarmPopular());
            stm.setDouble(6, m.getValor());

            stm.executeUpdate();
            System.out.println("Medicamento inserido no banco...");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar o medicamento");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Medicamento m) {
        try {
            String sql = "DELETE FROM medicamento "+
                         "WHERE id = ?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setLong(1, m.getId());
            
            stm.executeUpdate();
            System.out.println("Medicamento removido do banco...");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar o medicamento");
            e.printStackTrace();
        }
    }

    @Override
    public void update(long id, Medicamento m) {
        try {
            String sql = "UPDATE medicamento SET nome=?, codigo_de_barras=?, data_de_entrega=?, "+
                         "data_de_vencimento=?, farmacia_popular=?, valor=? "+
                         "WHERE id = ? ";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, m.getNome());
            stm.setString(2, m.getCodBarras());
            stm.setDate(3, 
                java.sql.Date.valueOf(m.getDataEntrega())
            );
            stm.setDate(4, 
                java.sql.Date.valueOf(m.getDataVencimento())
            );
            stm.setBoolean(5, m.isFarmPopular());
            stm.setDouble(6, m.getValor());

            stm.setLong(7, m.getId());

            stm.executeUpdate();
            System.out.println("Medicamento atualizado no banco...");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar informacoes do medicamento");
            e.printStackTrace();
        }
    }

    @Override
    public Medicamento searchById(long id) {
        Medicamento m = new Medicamento();

        try {
            String sql = "SELECT * FROM medicamento "+
                         "WHERE id = ?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            System.out.println("Medicamento encontrado no banco...");
            while (rs.next()) {
                long idMedicamento = rs.getLong("id");
                String nome =  rs.getString("nome");
                String codBarras = rs.getString("codigo_de_barras");
                LocalDate dataEntrega = rs.getDate("data_de_entrega").toLocalDate();
                LocalDate dataVencimento = rs.getDate("data_de_vencimento").toLocalDate();
                boolean farmPopular = rs.getBoolean("farmacia_popular");
                double valor = rs.getDouble("valor");

                m.setId(idMedicamento);
                m.setNome(nome);
                m.setCodBarras(codBarras);
                m.setDataEntrega(dataEntrega);
                m.setDataVencimento(dataVencimento);
                m.setFarmPopular(farmPopular);
                m.setValor(valor);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar medicamento por id");
            e.printStackTrace();
        }

        return m;
    }

    @Override
    public List<Medicamento> searchByName(String nome) {
        List<Medicamento> lista = new ArrayList<>();

        try {
            String sql = "SELECT * FROM medicamento "+
                         "WHERE nome LIKE ?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, "%" + nome + "%");
            ResultSet rs = stm.executeQuery();
            System.out.println("Medicamentos encontrados no banco...");
            while (rs.next()) {
                Medicamento m = new Medicamento();

                long idMedicamento = rs.getLong("id");
                String nomeMedicamento =  rs.getString("nome");
                String codBarras = rs.getString("codigo_de_barras");
                LocalDate dataEntrega = rs.getDate("data_de_entrega").toLocalDate();
                LocalDate dataVencimento = rs.getDate("data_de_vencimento").toLocalDate();
                boolean farmPopular = rs.getBoolean("farmacia_popular");
                double valor = rs.getDouble("valor");

                m.setId(idMedicamento);
                m.setNome(nomeMedicamento);
                m.setCodBarras(codBarras);
                m.setDataEntrega(dataEntrega);
                m.setDataVencimento(dataVencimento);
                m.setFarmPopular(farmPopular);
                m.setValor(valor);

                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar medicamentos por nome");
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public Medicamento searchByCode(String codBarras) {
        Medicamento m = new Medicamento();

        try {
            String sql = "SELECT * FROM medicamento "+
                         "WHERE codigo_de_barras = ?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, codBarras);
            ResultSet rs = stm.executeQuery();
            System.out.println("Medicamento encontrado no banco...");
            while (rs.next()) {
                long idMedicamento = rs.getLong("id");
                String nome =  rs.getString("nome");
                String codBarrasMedicamento = rs.getString("codigo_de_barras");
                LocalDate dataEntrega = rs.getDate("data_de_entrega").toLocalDate();
                LocalDate dataVencimento = rs.getDate("data_de_vencimento").toLocalDate();
                boolean farmPopular = rs.getBoolean("farmacia_popular");
                double valor = rs.getDouble("valor");

                m.setId(idMedicamento);
                m.setNome(nome);
                m.setCodBarras(codBarrasMedicamento);
                m.setDataEntrega(dataEntrega);
                m.setDataVencimento(dataVencimento);
                m.setFarmPopular(farmPopular);
                m.setValor(valor);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar medicamento por codigo de barras");
            e.printStackTrace();
        }

        return m;

    }

}
