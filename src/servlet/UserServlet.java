package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAOUser;
import beans.UserBean;

@WebServlet("/saveUser")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOUser daoUser = new DAOUser();

	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String action = request.getParameter("acao") != null ? request.getParameter("acao") : "";
			String user = request.getParameter("usuario");

			if (action.equals("delete")) {
				daoUser.delete(user);

			} else if (action.equals("edit")) {
				UserBean userBean = daoUser.findById(user);
				request.setAttribute("user", userBean);
			}
			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("usuarios", daoUser.listAll());
			request.setAttribute("acao", action);
			view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");

			String action = request.getParameter("acao") != null ? request.getParameter("acao") : "";
			if (!action.equalsIgnoreCase("reset")) {
				String id = request.getParameter("id");
				String login = request.getParameter("login");
				String senha = request.getParameter("senha");
				String nome = request.getParameter("nome");
				String fone = request.getParameter("fone");
				UserBean user = new UserBean(login, senha, nome, fone);

				// trabalho de classe service, ou bussiness object (BO)
				String errorMsg = null;
				if (login == null || login.trim().isEmpty())
					errorMsg = "Informar um login é obrigatório";
				if (senha == null || senha.trim().isEmpty()) {
					errorMsg = errorMsg == null ? "" : errorMsg + " <br/> ";
					errorMsg += "Informar uma senha é obrigatório";
				}
				boolean loginValid = false, passwordValid = false;
				if (errorMsg == null) {
					loginValid = daoUser.isLoginValid(login, id);
					passwordValid = daoUser.isPasswordValid(senha, id);
					if (!loginValid)
						errorMsg = "Login já usado em outro usuário";
					else if (!passwordValid)
						errorMsg = "Senha já usada em outro usuário";
				}
				if (!loginValid || !passwordValid) {
					request.setAttribute("usuarios", daoUser.listAll());
					request.setAttribute("errorMsg", errorMsg);
					user.setId((id == null || id.isEmpty() ? null : Long.parseLong(id)));
					request.setAttribute("user", user);
					view.forward(request, response);
					return;
				}

				if (id == null || id.isEmpty()) {
					daoUser.save(user);
					request.setAttribute("successMsg", "Usuário cadastrado com sucesso!");
				} else {
					user.setId(Long.parseLong(id));
					daoUser.update(user);
					request.setAttribute("successMsg", "Usuário atualizado com sucesso!");
				}
			}

			request.setAttribute("usuarios", daoUser.listAll());
			view.forward(request, response);
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
	}

}
