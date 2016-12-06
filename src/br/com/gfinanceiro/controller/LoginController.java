package br.com.gfinanceiro.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.gfinanceiro.dao.UsuarioDao;
import br.com.gfinanceiro.model.Usuario;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UsuarioDao dao = new UsuarioDao();

		if (request.getParameter("metodo") != null) {
			String acao = (String) request.getParameter("metodo");
			Gson gson = new Gson();
			response.setContentType("application/json");
			
           if (acao.equals("entrar")) {
				if (request.getParameter("idusuario") != null) {
					int idusuario = Integer.parseInt(request.getParameter("idusuario"));
					response.getWriter().append(dao.getUsuarioPorId(idusuario));
				}
				
			}
		}
	}

}
