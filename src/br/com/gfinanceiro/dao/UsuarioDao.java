package br.com.gfinanceiro.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;

import br.com.gfinanceiro.bd.DBUtil;
import br.com.gfinanceiro.model.Usuario;

public class UsuarioDao {

	private Connection connection;

	public UsuarioDao() {
		connection = DBUtil.getConnection();
	}

	// Todos selects da Classe Usuario
	public String getAllUsuario(int limit) {
		UsuarioDao obj = new UsuarioDao();
		String sql = "SELECT * FROM usuario ORDER BY idusuario limit " + limit;
		return obj.getAllUsuario(sql);
	}
	
	public String getUsuarioPorId(int idusuario) {
		UsuarioDao obj = new UsuarioDao();
		String sql = "select * from usuario where idusuario="+idusuario;
		return obj.getUsuarioPorId(sql);
	}

	// CRUD - addUsuario / deleteUsuario / updateUsuario / getUserById
	public void addUsuario(Usuario usuario) {
		try {
			java.sql.PreparedStatement prepare = connection
					.prepareStatement("INSERT INTO usuario (nome,email,usuario,senha,ativo) VALUES (?,?,?,?,?)");
			prepare.setString(1, usuario.getNome());
			prepare.setString(2, usuario.getEmail());
			prepare.setString(3, usuario.getUsuario());
			prepare.setString(4, usuario.getSenha());
			prepare.setInt(5, usuario.getAtivo());
			prepare.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void deleteUsuario(int Idusuario) {
		try {
			java.sql.PreparedStatement prepare = connection.prepareStatement("DELETE FROM usuario WHERE idusuario = ?");
			prepare.setInt(1, Idusuario);
			prepare.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void updateUsuario(Usuario usuario) {
		try {
			java.sql.PreparedStatement prepare = connection.prepareStatement(
					"UPDATE usuario SET nome = ?,email = ?, usuario = ?, senha = ?, ativo = ? WHERE idusuario = ?");
			prepare.setInt(1, usuario.getIdusuario());
			prepare.setString(2, usuario.getNome());
			prepare.setString(3, usuario.getEmail());
			prepare.setString(4, usuario.getUsuario());
			prepare.setString(5, usuario.getSenha());
			prepare.setInt(6, usuario.getAtivo());
			prepare.executeUpdate();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public String getAllUsuario(String queryString) {

		ArrayList<Usuario> ArrayUsuario = new ArrayList<Usuario>();
		String jsonUsuarios = "{}";

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);
			while (rs.next()) {
				String idusuario = rs.getString("idusuario");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String usuario = rs.getString("usuario");
				String senha = rs.getString("senha");
				String ativo = rs.getString("ativo");

				Usuario myClass = new Usuario();
				myClass.setIdusuario(Integer.parseInt(idusuario));
				myClass.setNome(nome);
				myClass.setEmail(email);
				myClass.setUsuario(usuario);
				myClass.setSenha(senha);
				myClass.setAtivo(Integer.parseInt(ativo));
				ArrayUsuario.add(myClass);
			}

			stmt.close();
			jsonUsuarios = new Gson().toJson(ArrayUsuario);
			return jsonUsuarios;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return jsonUsuarios;
	}

	/*public Usuario getUsuarioPorId(int idusuario) {
		Usuario usuario = new Usuario();
		try {
			java.sql.PreparedStatement prepare = connection.prepareStatement("select * from usuario where idusuario=?");
			prepare.setInt(1, idusuario);
			ResultSet rs = prepare.executeQuery();

			if (rs.next()) {
				usuario.setIdusuario(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setAtivo(rs.getInt("ativo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}*/

	public String getUsuarioPorId(String queryString) {
		ArrayList<Usuario> ArrayUsuario = new ArrayList<Usuario>();
		String jsonUsuario = "{}";

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);
			while (rs.next()) {
				String idusuario = rs.getString("idusuario");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String usuario = rs.getString("usuario");
				String senha = rs.getString("senha");
				String ativo = rs.getString("ativo");

				Usuario myClass = new Usuario();
				myClass.setIdusuario(Integer.parseInt(idusuario));
				myClass.setNome(nome);
				myClass.setEmail(email);
				myClass.setUsuario(usuario);
				myClass.setSenha(senha);
				myClass.setAtivo(Integer.parseInt(ativo));
				ArrayUsuario.add(myClass);
			}

			stmt.close();
			jsonUsuario = new Gson().toJson(ArrayUsuario);
			return jsonUsuario;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return jsonUsuario;
	}	
	
	
}
