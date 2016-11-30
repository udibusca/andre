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

import br.com.gfinanceiro.dao.CategoriaDao;
import br.com.gfinanceiro.model.Categoria;

@WebServlet("/categoria")
public class CategoriaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CategoriaDao dao = new CategoriaDao();

		if (request.getParameter("metodo") != null) {
			List<Categoria> lstCategoria = new ArrayList<Categoria>();
			String acao = (String) request.getParameter("metodo");
			Gson gson = new Gson();
			response.setContentType("application/json");

			if (acao.equals("listar")) {
				try {
					response.getWriter().append(dao.getAllCategoria(10));
				} catch (Exception ex) {
					String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getMessage() + "}";
					response.getWriter().print(error);
					ex.printStackTrace();
				}
			} else if (acao.equals("cadastrar") || acao.equals("editar")) {
				Categoria categoria = new Categoria();
				if(acao.equals("editar")){
					if (request.getParameter("idcategoria") != null) {
						int idcategoria = Integer.parseInt(request.getParameter("idcategoria"));
						response.getWriter().append(dao.getCategoriaPorId(idcategoria));
					}
				}

				if (request.getParameter("nome") != null) {
					String nome = (String) request.getParameter("nome");
					categoria.setNome(nome);
				}
				if (request.getParameter("tipoCategoria") != null) {
					String tipoCategoria = request.getParameter("tipoCategoria");
					categoria.setTipoCategoria(Integer.valueOf(tipoCategoria));
				}
				if (request.getParameter("ativo") != null) {
					String ativo = request.getParameter("ativo");
					categoria.setAtivo(Integer.parseInt(ativo));
				}
				try {
					if (acao.equals("cadastrar")) {
						
						dao.addCategoria(categoria);
						lstCategoria.add(categoria);
						String json = gson.toJson(categoria);
						String listData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
						response.getWriter().print(listData);
						
					} else if (acao.equals("editar")) {
						dao.updateCategoria(categoria);
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
					if (request.getParameter("idcategoria") != null) {
						String idcategoria = (String) request.getParameter("idcategoria");
						dao.deleteCategoria(Integer.parseInt(idcategoria));
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
