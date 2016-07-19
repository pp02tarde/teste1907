package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;
import entidades.Fornecedor;

public class FornecedorDao {

    private Connection connection;

    public FornecedorDao() {

	try {
	    this.connection = new ConnectionFactory().getConnection();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public void salvar(Fornecedor fornecedor) {

	String sql = "INSERT INTO fornecedor (nome, cnpj, endereco, data_fundacao) VALUES (?,?,?,?)";
	PreparedStatement stmt;
	try {
	    stmt = connection.prepareStatement(sql);

	    stmt.setString(1, fornecedor.getNome());
	    stmt.setString(2, fornecedor.getCnpj());
	    stmt.setString(3, fornecedor.getEndereco());
	    stmt.setDate(4, new java.sql.Date(fornecedor.getData_fundacao().getTime()));

	    stmt.execute();
	    connection.close();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public void alterar(Fornecedor fornecedor) {

	String sql = "UPDATE fornecedor SET nome = ?, cnpj = ?, endereco = ?, data_fundacao = ? WHERE id = ?";
	PreparedStatement stmt;
	try {
	    stmt = connection.prepareStatement(sql);

	    stmt.setString(1, fornecedor.getNome());
	    stmt.setString(2, fornecedor.getCnpj());
	    stmt.setString(3, fornecedor.getEndereco());
	    stmt.setDate(4, new java.sql.Date(fornecedor.getData_fundacao().getTime()));
	    stmt.setInt(5, fornecedor.getId());

	    stmt.execute();
	    connection.close();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public void remover(int idFornecedor) {

	String sql = "DELETE FROM fornecedor WHERE id = ?";
	PreparedStatement stmt;
	try {
	    stmt = connection.prepareStatement(sql);
	    stmt.setInt(1, idFornecedor);
	    stmt.execute();
	    connection.close();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public List<Fornecedor> listar() {

	try {
	    List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();
	    PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM fornecedor ORDER BY nome");

	    ResultSet rs = stmt.executeQuery();

	    while (rs.next()) {
		listaFornecedor.add(montarObjeto(rs));
	    }

	    rs.close();
	    stmt.close();
	    connection.close();

	    return listaFornecedor;
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public List<Fornecedor> pesquisar(String nome, String dataFundacao) {

	try {
	    List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();

	    PreparedStatement stmt = null;

	    if (!nome.equals("") && dataFundacao.equals("")) {
		stmt = this.connection.prepareStatement("SELECT * FROM fornecedor WHERE nome like ? ORDER BY nome");
		stmt.setString(1, '%' + nome + '%');
	    } else if (nome.equals("") && !dataFundacao.equals("")) {
		stmt = this.connection.prepareStatement("SELECT * FROM fornecedor WHERE dataFundacao like ? ORDER BY nome");
		stmt.setString(1, '%' + dataFundacao + '%');
	    } else if (!nome.equals("") && !dataFundacao.equals("")) {
		stmt = this.connection.prepareStatement("SELECT * FROM fornecedor WHERE nome like ? AND dataFundacao like ? ORDER BY nome");
		stmt.setString(1, '%' + nome + '%');
		stmt.setString(2, '%' + dataFundacao + '%');
	    } else {
		stmt = this.connection.prepareStatement("SELECT * FROM fornecedor ORDER BY nome");
	    }

	    ResultSet rs = stmt.executeQuery();

	    while (rs.next()) {
		listaFornecedor.add(montarObjeto(rs));
	    }

	    rs.close();
	    stmt.close();
	    	

	    return listaFornecedor;
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public Fornecedor buscarPorId(int id) {

	try {
	    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM fornecedor WHERE id = ?");
	    stmt.setInt(1, id);
	    ResultSet rs = stmt.executeQuery();

	    Fornecedor fornecedor = null;
	    if (rs.next()) {
		fornecedor = montarObjeto(rs);
	    }

	    rs.close();
	    stmt.close();
	    connection.close();
	    return fornecedor;
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    private Fornecedor montarObjeto(ResultSet rs) throws SQLException {

	Fornecedor fornecedor = new Fornecedor();
	fornecedor.setId(rs.getInt("id"));
	fornecedor.setNome(rs.getString("nome"));
	fornecedor.setCnpj(rs.getString("cnpj"));
	fornecedor.setEndereco(rs.getString("endereco"));
	fornecedor.setData_fundacao(rs.getDate("data_fundacao"));
	return fornecedor;
    }
}
