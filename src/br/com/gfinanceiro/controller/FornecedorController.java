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

import br.com.gfinanceiro.dao.FornecedorDao;
import br.com.gfinanceiro.model.Fornecedor;

@WebServlet("/fornecedor")
public class FornecedorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FornecedorDao dao = new FornecedorDao();

		if (request.getParameter("metodo") != null) {
			List<Fornecedor> lstFornecedor = new ArrayList<Fornecedor>();
			String acao = (String) request.getParameter("metodo");
			Gson gson = new Gson();
			response.setContentType("application/json");

			if (acao.equals("listar")) {
				try {
					response.getWriter().append(dao.getAllFornecedor(10));
				} catch (Exception ex) {
					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getMessage() + "}";
					response.getWriter().print(error);
					ex.printStackTrace();
				}
			} else if (acao.equals("cadastrar")) {
				Fornecedor fornecedor = new Fornecedor();

				if (request.getParameter("nome") != null) {
					String nome = (String) request.getParameter("nome");
					fornecedor.setNome(nome);
				}
				if (request.getParameter("cnpj") != null) {
					String cnpj = request.getParameter("cnpj");
					fornecedor.setCnpj(cnpj);
				}
				if (request.getParameter("ativo") != null) {
					String ativo = request.getParameter("ativo");
					fornecedor.setAtivo(Integer.parseInt(ativo));
				}
				try {
					dao.addFornecedor(fornecedor);

					lstFornecedor.add(fornecedor);
					String json = gson.toJson(fornecedor);
					String listData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
					response.getWriter().print(listData);

				} catch (Exception ex) {
					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getStackTrace().toString() + "}";
					response.getWriter().print(error);
				}
			} else if (acao.equals("excluir")) {
				try {
					if (request.getParameter("idfornecedor") != null) {
						String idfornecedor = (String) request.getParameter("idfornecedor");
						dao.deleteFornecedor(Integer.parseInt(idfornecedor));
						String listData = "{\"Result\":\"OK\"}";
						response.getWriter().print(listData);
					}
				} catch (Exception ex) {
					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getStackTrace().toString() + "}";
					response.getWriter().print(error);
				}
			} else if (acao.equals("editar")) {
				if (request.getParameter("idfornecedor") != null) {
					int idfornecedor = Integer.parseInt(request.getParameter("idfornecedor"));
					response.getWriter().append(dao.getFornecedorPorId(idfornecedor));
				}
			} else if (acao.equals("atualiza")) {
				Fornecedor fornecedor = new Fornecedor();
				if (request.getParameter("idfornecedor") != null) {
					int idfornecedor = Integer.parseInt(request.getParameter("idfornecedor"));
					fornecedor.setIdfornecedor(idfornecedor);
				}
				if (request.getParameter("nome") != null) {
					String nome = (String) request.getParameter("nome");
					fornecedor.setNome(nome);
				}
				if (request.getParameter("cnpj") != null) {
					String cnpj = request.getParameter("cnpj");
					fornecedor.setCnpj(cnpj);
				}
				if (request.getParameter("ativo") != null) {
					String ativo = request.getParameter("ativo");
					fornecedor.setAtivo(Integer.parseInt(ativo));
				}
				try {
					dao.updateFornecedor(fornecedor);
					String json = gson.toJson(fornecedor);
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
