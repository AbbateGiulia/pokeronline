package it.solving.pokeronline.web.tavolo;

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
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solving.pokeronline.dto.TavoloDTO;
import it.solving.pokeronline.model.Tavolo;
import it.solving.pokeronline.model.Utente;
import it.solving.pokeronline.service.tavolo.TavoloService;
import it.solving.pokeronline.service.utente.UtenteService;
import it.solving.pokeronline.util.Util;

/**
 * Servlet implementation class ExecuteSearchPartitaServlet
 */
@WebServlet("/ExecuteSearchPartitaServlet")
public class ExecuteSearchPartitaServlet extends HttpServlet {
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
	public ExecuteSearchPartitaServlet() {
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
		
		//paramteri form
		String dataInput = request.getParameter("dataCreazione");
		String puntataMinima = request.getParameter("puntataMinima");
		String idGiocatore = request.getParameter("utenteId");
		String idCreatore = request.getParameter("creatoreId");
		 
		//validazione
		TavoloDTO tavoloDTO = new TavoloDTO();
		tavoloDTO.setDataCreazione(dataInput);
		tavoloDTO.setCreditoMinimo(puntataMinima);

		List<String> tavoloErrors = tavoloDTO.errorsSearchPartita();
		if (!tavoloErrors.isEmpty()) {
			request.setAttribute("tavoloAttribute", tavoloDTO);
			request.setAttribute("tavoloErrors", tavoloErrors);
			request.getRequestDispatcher("/tavolo/searchpartita.jsp").forward(request, response);
			return;
		}

		Tavolo tavoloSearch = TavoloDTO.buildModelFromDto(tavoloDTO);

		if (!Util.isEmptyOrNull(idCreatore)) {
			Utente creatore = utenteService.caricaSingoloUtenteEager(Long.parseLong(idCreatore));
			tavoloSearch.setUtenteCreatore(creatore);
		}

		if (!Util.isEmptyOrNull(idGiocatore)) {
			Set<Utente> giocatori = new HashSet<Utente>();
			Utente giocatore = utenteService.caricaSingoloUtente(Long.parseLong(idGiocatore));
			giocatori.add(giocatore);
			tavoloSearch.setGiocatori(giocatori);
		}
		
		//parametro utente in sessione
		HttpSession session = request.getSession();
		Utente user = (Utente) session.getAttribute("userInfo");
		Integer esperienza = user.getEsperienzaAccumulata();
		tavoloSearch.setEsperienzaMinima(esperienza);
		
		request.setAttribute("listaTavoliAttribute", tavoloService.findByExamplePartita(tavoloSearch));
		request.getRequestDispatcher("/tavolo/listapartite.jsp").forward(request, response);
	}

}
