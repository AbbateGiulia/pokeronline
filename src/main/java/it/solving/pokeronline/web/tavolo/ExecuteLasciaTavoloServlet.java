package it.solving.pokeronline.web.tavolo;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solving.pokeronline.model.Utente;
import it.solving.pokeronline.service.utente.UtenteService;

/**
 * Servlet implementation class ExecuteLasciaTavoloServlet
 */
@WebServlet("/ExecuteLasciaTavoloServlet")
public class ExecuteLasciaTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UtenteService utenteService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteLasciaTavoloServlet() {
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
		// uscita dal tavolo e ++ exp
		user.setTavolo(null);
		user.setEsperienzaAccumulata(user.getEsperienzaAccumulata() + 1);
		utenteService.aggiorna(user);
		request.setAttribute("successMessage", "Operazione effettuata correttamente");
		request.getRequestDispatcher("/tavolo/funzioni.jsp").forward(request, response);
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
