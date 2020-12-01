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

/**
 * Servlet implementation class ExecuteUpdateTavoloServlet
 */
@WebServlet("/special/ExecuteUpdateTavoloServlet")
public class ExecuteUpdateTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TavoloService tavoloService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteUpdateTavoloServlet() {
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
		String idTavolo = request.getParameter("tavoloId");
		String denominazioneInput= request.getParameter("denominazione");
		String esperienzaMinimaInput= request.getParameter("esperienzaMinima");
		String puntataMinima = request.getParameter("puntataMinima");
		
		TavoloDTO tavoloDTO = new TavoloDTO();
		tavoloDTO.setCreditoMinimo(puntataMinima);
		tavoloDTO.setDenominazione(denominazioneInput);
		tavoloDTO.setEsperienzaMinima(esperienzaMinimaInput);
		
		List<String> tavoloErrors = tavoloDTO.errors();
		if (!tavoloErrors.isEmpty()) {
			
			request.setAttribute("tavoloAttribute", tavoloDTO);
			request.setAttribute("tavoloErrors", tavoloErrors);
			request.getRequestDispatcher("/tavolo/update.jsp").forward(request, response);
			return;
		}
		
		//se arrivo qui significa che va bene ma controllo municipio
		Tavolo tavoloInstance = TavoloDTO.buildModelFromDto(tavoloDTO);
		
		Utente utente= (Utente) request.getSession().getAttribute("userInfo");
		
		Long id =utente.getId();
		 
		 
		 tavoloInstance.setId(Long.parseLong(idTavolo));
		 Tavolo tavoloUpdate =tavoloService.caricaSingoloTavolo(Long.parseLong(idTavolo));
		 tavoloInstance.setUtenteCreatore(utente);
		 tavoloInstance.setDataCreazione(tavoloUpdate.getDataCreazione());
		 
		 tavoloService.aggiorna(tavoloInstance);
		 
		
		
		//vado in pagina con ok
		request.setAttribute("messaggioConferma", "aggiornamento effettuato con successo!");
		request.setAttribute("listaTavoliAttribute", tavoloService.listAllTavoloUtente(id));
		request.getRequestDispatcher("/tavolo/results.jsp").forward(request, response);
	}

}
