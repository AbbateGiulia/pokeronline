package it.solving.pokeronline.web.utente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solving.pokeronline.model.Utente;
import it.solving.pokeronline.service.ruolo.RuoloService;
import it.solving.pokeronline.service.utente.UtenteService;
import it.solving.pokeronline.util.Util;

/**
 * Servlet implementation class PrepareUpdateUtenteServlet
 */
@WebServlet("/users/PrepareUpdateUtenteServlet")
public class PrepareUpdateUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private RuoloService ruoloService;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PrepareUpdateUtenteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idUtente = request.getParameter("idUtente");
		
		//controllo input da url
		if (Util.isEmptyOrNull(idUtente) || !Util.isNumber(idUtente)) {
			RequestDispatcher rd = request.getRequestDispatcher("/LogoutServlet");
			rd.forward(request, response);
			return;
		}
		Utente utenteUpdate = utenteService.caricaSingoloUtente(Long.parseLong(idUtente));
		request.setAttribute("listaRuoli", ruoloService.listAllRuolo());
		request.setAttribute("utenteAttribute", utenteUpdate);
		request.getRequestDispatcher("/utente/update.jsp").forward(request, response);
		;
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
