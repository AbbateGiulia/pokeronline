package it.solving.pokeronline.web.utente;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solving.pokeronline.dto.UtenteDTO;
import it.solving.pokeronline.model.Ruolo;
import it.solving.pokeronline.model.Utente;
import it.solving.pokeronline.service.ruolo.RuoloService;
import it.solving.pokeronline.service.utente.UtenteService;

/**
 * Servlet implementation class ExecuteUpdateUtenteServlet
 */
@WebServlet("/users/ExecuteUpdateUtenteServlet")
public class ExecuteUpdateUtenteServlet extends HttpServlet {
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
	public ExecuteUpdateUtenteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idInputParam = request.getParameter("utenteId");
		String nomeInput = request.getParameter("nome");
		String cognomeInput = request.getParameter("cognome");
		String usernameInput = request.getParameter("username");
		String stato = request.getParameter("stato");
		String ruoli[] = request.getParameterValues("ruolo");

		// costruzione dto
		UtenteDTO utenteDTO = new UtenteDTO();
		utenteDTO.setNome(nomeInput);
		utenteDTO.setCognome(cognomeInput);
		utenteDTO.setUsername(usernameInput);
		utenteDTO.setRuoli(ruoli);
		utenteDTO.setStato(stato);
		utenteDTO.setId(Long.parseLong(idInputParam));

		List<String> utenteErrors = utenteDTO.errorsUpdate();
		if (!utenteErrors.isEmpty()) {
			request.setAttribute("utenteAttribute", utenteDTO);
			request.setAttribute("utenteErrors", utenteErrors);
			request.setAttribute("listaRuoli", ruoloService.listAllRuolo());
			request.getRequestDispatcher("/utente/update.jsp").forward(request, response);
			return;
		}

		// se arrivo qui significa che va bene ma controllo ruoli
		Set<Ruolo> setRuoliUpdate = new HashSet<Ruolo>();
		Utente utenteUpdate = utenteService.caricaSingoloUtente(Long.parseLong(idInputParam));
		
		for (String s : request.getParameterValues("ruolo")) {
			Ruolo ruoloInsert = null;
			try {
				ruoloInsert = ruoloService.caricaSingoloRuolo(Long.parseLong(s));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setRuoliUpdate.add(ruoloInsert);
		}

		// costruzione oggetto valido
		utenteUpdate.setRuoli(setRuoliUpdate);
		utenteUpdate.setNome(nomeInput);
		utenteUpdate.setCognome(cognomeInput);
		utenteUpdate.setUsername(usernameInput);

		utenteService.aggiorna(utenteUpdate);

		// vado in pagina con ok
		request.setAttribute("messaggioConferma", "Aggiornameto effettuato con successo");
		request.setAttribute("listaUtentiAttribute", utenteService.listAllUtenti());
		request.getRequestDispatcher("/utente/results.jsp").forward(request, response);

	}

}
