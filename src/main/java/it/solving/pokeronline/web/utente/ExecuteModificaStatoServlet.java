package it.solving.pokeronline.web.utente;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solving.pokeronline.model.StatoUtente;
import it.solving.pokeronline.model.Utente;
import it.solving.pokeronline.service.utente.UtenteService;

/**
 * Servlet implementation class ExecuteModificaStatoServlet
 */
@WebServlet("/users/ExecuteModificaStatoServlet")
public class ExecuteModificaStatoServlet extends HttpServlet {
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
	public ExecuteModificaStatoServlet() {
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
		Utente utente = utenteService.caricaSingoloUtente(Long.parseLong(idUtente));

		// impedire attivazione utenti senza ruoli
		if (utente.getRuoli().size() == 0) {
			request.setAttribute("errorMessage", "Impossibile attivare utente senza ruolo");
			request.setAttribute("listaUtentiAttribute", utenteService.listAllUtenti());
			request.getRequestDispatcher("/utente/results.jsp").forward(request, response);
			return;
		}

		// disattiva
		if (utente.getStato().equals(StatoUtente.ATTIVO)) {
			utente.setStato(StatoUtente.DISABILITATO);
			utente.setTavolo(null);
			utenteService.aggiorna(utente);
			request.setAttribute("listaUtentiAttribute", utenteService.listAllUtenti());
			request.getRequestDispatcher("/utente/results.jsp").forward(request, response);
			return;
		}

		// attiva
		if (utente.getStato().equals(StatoUtente.DISABILITATO) || utente.getStato().equals(StatoUtente.CREATO)) {
			utente.setStato(StatoUtente.ATTIVO);
			utenteService.aggiorna(utente);
			request.setAttribute("listaUtentiAttribute", utenteService.listAllUtenti());
			request.getRequestDispatcher("/utente/results.jsp").forward(request, response);
			return;
		}

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
