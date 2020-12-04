package it.solving.pokeronline.web.tavolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.solving.pokeronline.model.Tavolo;
import it.solving.pokeronline.model.Utente;

/**
 * Servlet implementation class GoToLastGameServlet
 */
@WebServlet("/GoToLastGameServlet")
public class GoToLastGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GoToLastGameServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Utente user = (Utente) session.getAttribute("userInfo");
		Tavolo tavolo = user.getTavolo();
		request.setAttribute("utenteAttribute", user);
		request.setAttribute("tavoloAttribute", tavolo);
		request.getRequestDispatcher("/tavolo/gioca.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
