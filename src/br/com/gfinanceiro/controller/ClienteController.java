package br.com.gfinanceiro.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.gfinanceiro.dao.ClienteDao;
import br.com.gfinanceiro.model.Cliente;

@WebServlet("/cliente")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ClienteDao dao = new ClienteDao();

		if (request.getParameter("metodo") != null) {
			List<Cliente> lstCliente = new ArrayList<Cliente>();
			String acao = (String) request.getParameter("metodo");
			Gson gson = new Gson();
			response.setContentType("application/json");

			if (acao.equals("listar")) {
				try {
					response.getWriter().append(dao.getAllCliente(10));
				} catch (Exception ex) {
					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getMessage() + "}";
					response.getWriter().print(error);
					ex.printStackTrace();
				}
			} else if (acao.equals("cadastrar") || acao.equals("editar")) {
				Cliente cliente = new Cliente();
				if(acao.equals("editar")){
					if (request.getParameter("idcliente") != null) {
						int idcliente = Integer.parseInt(request.getParameter("idcliente"));
						response.getWriter().append(dao.getClientePorId(idcliente));
					}
				}

				if (request.getParameter("nome") != null) {
					String nome = (String) request.getParameter("nome");
					cliente.setNome(nome);
				}
				if (request.getParameter("cpf") != null) {
					String cpf = request.getParameter("cpf");
					cliente.setCpf(cpf);
				}
				if (request.getParameter("ativo") != null) {
					String ativo = request.getParameter("ativo");
					cliente.setAtivo(Integer.parseInt(ativo));
				}
				try {
					if (acao.equals("cadastrar")) {
						
						dao.addCliente(cliente);
						lstCliente.add(cliente);
						String json = gson.toJson(cliente);
						String listData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
						response.getWriter().print(listData);
						
					} else if (acao.equals("editar")) {
						dao.updateCliente(cliente);
						//String json = gson.toJson(usuario);
						//String listData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
						//response.getWriter().print(listData);
					}
				} catch (Exception ex) {
					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getStackTrace().toString() + "}";
					response.getWriter().print(error);
				}
			} else if (acao.equals("excluir")) { 
				try {
					if (request.getParameter("idcliente") != null) {
						String idcliente = (String) request.getParameter("idcliente");
						dao.deleteCliente(Integer.parseInt(idcliente));
						String listData = "{\"Result\":\"OK\"}";
						response.getWriter().print(listData);
					}
				} catch (Exception ex) {
					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getStackTrace().toString() + "}";
					response.getWriter().print(error);
				}
			}
		}
	}

}
