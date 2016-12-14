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

import br.com.gfinanceiro.bd.DBUtil;
import br.com.gfinanceiro.dao.ClienteDao;
import br.com.gfinanceiro.model.Cliente;

@WebServlet("/cliente")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
    }		
	
	
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
					response.getWriter().append(dao.getAllCliente(DBUtil.LIMIT));
				} catch (Exception ex) {
					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getMessage() + "}";
					response.getWriter().print(error);
					ex.printStackTrace();
				}
			} else if (acao.equals("cadastrar")) {
				Cliente cliente = new Cliente();

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
				if (request.getParameter("saldo") != null) {
					String saldo = request.getParameter("saldo");
					cliente.setSaldo(saldo);
				}
				try {
					dao.addCliente(cliente);
					lstCliente.add(cliente);
					String json = gson.toJson(cliente);
					String listData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
					response.getWriter().print(listData);

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
			} else if (acao.equals("editar")){
			
				if (request.getParameter("idcliente") != null) {
					int idcliente = Integer.parseInt(request.getParameter("idcliente"));
					response.getWriter().append(dao.getClientePorId(idcliente));
				}
			}else if (acao.equals("atualiza")) {
				
				Cliente cliente = new Cliente();
				if (request.getParameter("idcliente") != null) {
					int idcliente = Integer.parseInt(request.getParameter("idcliente"));
					cliente.setIdcliente(idcliente);
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
				if (request.getParameter("saldo") != null) {
					String saldo = request.getParameter("saldo");
					cliente.setSaldo(saldo);
				}
				try {
					dao.updateCliente(cliente);
					String json = gson.toJson(cliente);
					String listData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
					response.getWriter().print(listData);
				} catch (Exception ex) {
					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getStackTrace().toString() + "}";
					response.getWriter().print(error);
				}
			}
		}
	}

}

