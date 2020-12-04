package it.solving.pokeronline.web.utente;

import java.io.IOException;
import java.time.LocalDate;
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
import it.solving.pokeronline.model.StatoUtente;
import it.solving.pokeronline.model.Utente;
import it.solving.pokeronline.service.ruolo.RuoloService;
import it.solving.pokeronline.service.utente.UtenteService;
import it.solving.pokeronline.util.Util;

/**
 * Servlet implementation class ExecuteSearchUtenteServlet
 */
@WebServlet("/users/ExecuteSearchUtenteServlet")
public class ExecuteSearchUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private RuoloService ruoloService;

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
	public ExecuteSearchUtenteServlet() {
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

		// input registarzione
		String nomeInput = request.getParameter("nome");
		String cognomeInput = request.getParameter("cognome");
		String usernameInput = request.getParameter("username");
		String dataregistrazioneInput = request.getParameter("dataRegistrazione");
		String ruoloInput = request.getParameter("ruolo");
		String statoInput = request.getParameter("stato");

		// dto di controllo
		UtenteDTO utenteDTO = new UtenteDTO();
		utenteDTO.setNome(nomeInput);
		utenteDTO.setCognome(cognomeInput);
		utenteDTO.setUsername(usernameInput);
		utenteDTO.setDataRegistrazione(dataregistrazioneInput);

		List<String> utenteErrors = utenteDTO.errorsSearch();
		if (!utenteErrors.isEmpty()) {
			request.setAttribute("utenteAttribute", utenteDTO);
			request.setAttribute("utenteErrors", utenteErrors);
			request.setAttribute("listaRuoli", ruoloService.listAllRuolo());
			request.setAttribute("listaStati", StatoUtente.allStato);
			request.getRequestDispatcher("/utente/search.jsp").forward(request, response);
			return;
		}

		// se arrivo qui significa che va bene e converto
		Utente utenteInstance = UtenteDTO.buildModelFromDto(utenteDTO);
		
		//setto dipendenze solo se inserite in input
		if (!Util.isEmptyOrNull(statoInput)) {
			utenteInstance.setStato(Enum.valueOf(StatoUtente.class, statoInput));
		}
		if (!Util.isEmptyOrNull(ruoloInput) && Util.isNumber(ruoloInput)) {
			Ruolo ruoloUtente = ruoloService.caricaSingoloRuolo(Long.parseLong(ruoloInput));
			Set<Ruolo> ruoli = new HashSet<>();
			ruoli.add(ruoloUtente);
			utenteInstance.setRuoli(ruoli);
		}
		if (!Util.isEmptyOrNull(dataregistrazioneInput)) {
			utenteInstance.setDataRegistrazione(LocalDate.parse(dataregistrazioneInput));
		}
		// vado in pagina con ok
		request.setAttribute("listaUtentiAttribute", utenteService.findByExample(utenteInstance));
		request.setAttribute("messaggioConferma", "ricerca effettuata con successo");
		request.getRequestDispatcher("/utente/results.jsp").forward(request, response);

	}

}
