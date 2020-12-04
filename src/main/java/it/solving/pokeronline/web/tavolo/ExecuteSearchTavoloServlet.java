package it.solving.pokeronline.web.tavolo;

import java.io.IOException;
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
 * Servlet implementation class ExecuteSearchTavoloServlet
 */
@WebServlet("/special/ExecuteSearchTavoloServlet")
public class ExecuteSearchTavoloServlet extends HttpServlet {
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
    public ExecuteSearchTavoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idCreatore = request.getParameter("userId");
		String denominazioneInput= request.getParameter("denominazione");
		String dataCreazioneInput= request.getParameter("dataCreazione");
		String puntataMinima = request.getParameter("puntataMinima");
		
		
		TavoloDTO tavoloDTO = new TavoloDTO(denominazioneInput, puntataMinima, dataCreazioneInput);
		tavoloDTO.setIdCreatore(idCreatore);
		
		List<String> tavoloErrors = tavoloDTO.errorsSearch();
		if (!tavoloErrors.isEmpty()) {			
			request.setAttribute("tavoloAttribute", tavoloDTO);
			request.setAttribute("tavoloErrors", tavoloErrors);
			request.getRequestDispatcher("/tavolo/search.jsp").forward(request, response);
			return;
		}
		
		//se arrivo qui significa che va bene e converto
		Tavolo tavoloInstance = TavoloDTO.buildModelFromDto(tavoloDTO);
		
		Utente utenteCreatore =utenteService.caricaSingoloUtente(Long.parseLong(idCreatore));
		 
		tavoloInstance.setUtenteCreatore(utenteCreatore);
		
		  
		
		//vado in pagina con ok
		request.setAttribute("messaggioConferma", "Ricerca effettuata con successo!");
		request.setAttribute("listaTavoliAttribute", tavoloService.findByExample(tavoloInstance));
		request.getRequestDispatcher("/tavolo/results.jsp").forward(request, response);
	}

}
