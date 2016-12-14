package br.com.gfinanceiro.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;

import br.com.gfinanceiro.bd.DBUtil;
import br.com.gfinanceiro.model.Cliente;

public class ClienteDao {

	private Connection connection;

	public ClienteDao() {
		connection = DBUtil.getConnection();
	}

	// Todos selects da Classe cliente
	public String getAllCliente(int limit) {
		ClienteDao obj = new ClienteDao();
		String sql = "SELECT * FROM cliente where ativo = 1 ORDER BY idcliente limit " + limit;
		return obj.getAllCliente(sql);
	}
	
	public String getClientePorId(int idcliente) {
		ClienteDao obj = new ClienteDao();
		String sql = "select * from cliente where ativo = 1 AND idcliente="+idcliente;
		return obj.getClientePorId(sql);
	}
	
	/*
	 * get cliente por cpf
	 * 
	 * */
	public String getClientePorCpf(String cpf) {
		ClienteDao obj = new ClienteDao();
		String sql = "select * from cliente where cpf="+cpf;
		//o nome é por id porém ele pega por cpf
		return obj.getClientePorId(sql);
	}

	// CRUD - addCliente / deleteCliente / updateCliente / getClienteById
	public void addCliente(Cliente cliente) {
		try {
			java.sql.PreparedStatement prepare = connection
					.prepareStatement("INSERT INTO cliente (nome,cpf,ativo,saldo) VALUES (?,?,?,?)");
			prepare.setString(1, cliente.getNome());
			prepare.setString(2, cliente.getCpf());
			prepare.setInt(3, cliente.getAtivo());
			prepare.setString(4, cliente.getSaldo());
			prepare.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void deleteCliente(int idcliente) {
		try {
			java.sql.PreparedStatement prepare = connection.prepareStatement("UPDATE cliente set ativo = 0 WHERE idcliente = ?");
			prepare.setInt(1, idcliente);
			prepare.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void updateSaldo(int idcliente, String saldo) {
		try {
			java.sql.PreparedStatement prepare = connection.prepareStatement("UPDATE cliente set saldo = ? WHERE idcliente = ?");
			prepare.setString(1, saldo);
			prepare.setInt(2, idcliente);
			prepare.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	public void updateCliente(Cliente cliente) {
		try {
			java.sql.PreparedStatement prepare = connection.prepareStatement(
					"UPDATE cliente SET nome = ?, cpf = ?, ativo = ?, saldo=? WHERE idcliente = ?");
			prepare.setString(1, cliente.getNome());
			prepare.setString(2, cliente.getCpf());
			prepare.setInt(3, cliente.getAtivo());
			prepare.setString(4, cliente.getSaldo());
			prepare.setInt(5, cliente.getIdcliente());
			prepare.executeUpdate();
			System.err.println(prepare);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public String getAllCliente(String queryString) {

		ArrayList<Cliente> ArrayCliente = new ArrayList<Cliente>();
		String jsonClientes = "{}";

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);
			while (rs.next()) {
				String idcliente = rs.getString("idcliente");
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String dtaCadastro	= rs.getString("data_cadastro");
				String ativo = rs.getString("ativo");
				String saldo = rs.getString("saldo");

				Cliente myClass = new Cliente();
				myClass.setIdcliente(Integer.parseInt(idcliente));
				myClass.setNome(nome);
				myClass.setCpf(cpf);
				myClass.setData_cadastro(dtaCadastro);
				myClass.setSaldo(saldo);
				myClass.setAtivo(Integer.parseInt(ativo));
				ArrayCliente.add(myClass);
			}

			stmt.close();
			jsonClientes = new Gson().toJson(ArrayCliente);
			return jsonClientes;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return jsonClientes;
	}

	public String getClientePorId(String queryString) {
		ArrayList<Cliente> ArrayCliente = new ArrayList<Cliente>();
		String jsonCliente = "{}";
		// Integer.valueOf(request.getParameter("idcliente"))
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);
			while (rs.next()) {
				String idcliente = rs.getString("idcliente");
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String dtaCadastro	= rs.getString("data_cadastro");
				String ativo = rs.getString("ativo");
				String saldo = rs.getString("saldo");

				Cliente myClass = new Cliente();
				myClass.setIdcliente(Integer.parseInt(idcliente));
				myClass.setNome(nome);
				myClass.setCpf(cpf);
				myClass.setData_cadastro(dtaCadastro);
				myClass.setSaldo(saldo);
				myClass.setAtivo(Integer.parseInt(ativo));
				ArrayCliente.add(myClass);
			}

			stmt.close();
			jsonCliente = new Gson().toJson(ArrayCliente);
			return jsonCliente;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return jsonCliente;
	}	
	
	
}
