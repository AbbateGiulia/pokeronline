package it.solving.pokeronline.web.tavolo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solving.pokeronline.dto.TavoloDTO;
import it.solving.pokeronline.model.Tavolo;
import it.solving.pokeronline.model.Utente;
import it.solving.pokeronline.service.tavolo.TavoloService;
import it.solving.pokeronline.service.utente.UtenteService;

/**
 * Servlet implementation class ExecuteInsertTavoloServlet
 */
@WebServlet("/special/ExecuteInsertTavoloServlet")
public class ExecuteInsertTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private TavoloService tavoloService;

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
	public ExecuteInsertTavoloServlet() {
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

		String idCreatore = request.getParameter("userId");
		String denominazioneInput = request.getParameter("denominazione");
		String esperienzaMinimaInput = request.getParameter("esperienzaMinima");
		String puntataMinima = request.getParameter("puntataMinima");

		TavoloDTO tavoloDTO = new TavoloDTO(denominazioneInput, esperienzaMinimaInput, puntataMinima, idCreatore);

		List<String> tavoloErrors = tavoloDTO.errors();
		if (!tavoloErrors.isEmpty()) {

			request.setAttribute("tavoloAttribute", tavoloDTO);
			request.setAttribute("tavoloErrors", tavoloErrors);
			request.getRequestDispatcher("/tavolo/insert.jsp").forward(request, response);
			return;
		}

		// se arrivo qui significa che va bene e converto
		Tavolo tavoloInstance = TavoloDTO.buildModelFromDto(tavoloDTO);
		tavoloInstance.setDataCreazione(LocalDate.now());

		Utente utenteCreatore = utenteService.caricaSingoloUtente(Long.parseLong(idCreatore));

		tavoloInstance.setUtenteCreatore(utenteCreatore);

		tavoloService.inserisciNuovo(tavoloInstance);

		// vado in pagina con ok
		request.setAttribute("messaggioConferma", "Inserimento effettuato con successo!");
		request.getRequestDispatcher("/tavolo/search.jsp").forward(request, response);
	}

}
