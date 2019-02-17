package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAOProduct;
import DAO.DAOUser;
import beans.ProductBean;
import beans.UserBean;

@WebServlet("/saveProduct")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOProduct daoProduct = new DAOProduct();

	public ProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String action = request.getParameter("acao") != null ? request.getParameter("acao") : "";
			String product = request.getParameter("produto");

			if (action.equals("delete")) {
				daoProduct.delete(product);

			} else if (action.equals("edit")) {
				ProductBean productBean = daoProduct.findById(product);
				request.setAttribute("product", productBean);
			}
			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("produtos", daoProduct.listAll());
			request.setAttribute("acao", action);
			view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("produtos", daoProduct.listAll());

			String action = request.getParameter("acao") != null ? request.getParameter("acao") : "";
			if (!action.equalsIgnoreCase("reset")) {
				String errorMsg = null;
				String id = request.getParameter("id");
				String nome = request.getParameter("nome");
				String quantidade = request.getParameter("quantidade");
				String valor = request.getParameter("valor");
				ProductBean product = new ProductBean(nome);
				if (nome == null || nome.trim().isEmpty()) {
					errorMsg = "Informar o nome do produto é obrigatório";
				}
				if (quantidade == null || quantidade.trim().isEmpty()) {
					errorMsg = errorMsg == null ? "" : errorMsg + " <br/> ";
					errorMsg += "Informar a quantidade do produto é obrigatório";

				} else {
					product.setQuantidade(Double.valueOf(quantidade));
				}
				if (valor == null || valor.trim().isEmpty()) {
					errorMsg = errorMsg == null ? "" : errorMsg + " <br/> ";
					errorMsg += "Informar o valor do produto é obrigatório";

				} else {
					product.setValor(Double.valueOf(valor));
				}
				boolean nameValid = daoProduct.isNameValid(nome, id);
				if (!nameValid) {
					errorMsg = "Já existe um produto com esse nome";
				}
				if (errorMsg != null || !nameValid) {

					request.setAttribute("errorMsg", errorMsg);
					product.setId((id == null || id.isEmpty()) ? null : Long.parseLong(id));
					request.setAttribute("product", product);
					view.forward(request, response);
					return;
				}
				if (id == null || id.isEmpty()) {
					daoProduct.save(product);
					request.setAttribute("successMsg", "Produto cadastrado com sucesso!");
				} else {
					product.setId(Long.valueOf(id));
					daoProduct.update(product);
					request.setAttribute("successMsg", "Produto atualizado com sucesso!");
				}
			}
			request.setAttribute("produtos", daoProduct.listAll());
			view.forward(request, response);
		} catch (

		SQLException e) {

			e.printStackTrace();
		}
	}

}
