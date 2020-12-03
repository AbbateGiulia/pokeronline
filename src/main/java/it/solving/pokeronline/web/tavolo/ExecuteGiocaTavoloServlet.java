package it.solving.pokeronline.web.tavolo;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solving.pokeronline.model.Tavolo;
import it.solving.pokeronline.model.Utente;
import it.solving.pokeronline.service.tavolo.TavoloService;
import it.solving.pokeronline.service.utente.UtenteService;

/**
 * Servlet implementation class ExecuteGiocaTavoloServlet
 */
@WebServlet("/ExecuteGiocaTavoloServlet")
public class ExecuteGiocaTavoloServlet extends HttpServlet {
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
    public ExecuteGiocaTavoloServlet() {
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
		String tavoloId = request.getParameter("idTavolo");
		String utenteId = request.getParameter("idUtente");
		
		Utente utenteInGioco =utenteService.caricaSingoloUtente(Long.parseLong(utenteId));
		Tavolo tavolo=tavoloService.caricaSingoloTavolo(Long.parseLong(tavoloId));
		utenteInGioco.setTavolo(tavolo);
		
		if(tavolo.getCreditoMinimo() > utenteInGioco.getCreditoAccumulato()) {
			request.setAttribute("errorMessage", "non raggiugni la puntata minima per partecipare!");
			request.setAttribute("tavoloAttribute", tavolo);
			request.setAttribute("utenteAttribute", utenteInGioco);
			request.getRequestDispatcher("/tavolo/gioca.jsp").forward(request, response);
			return;
		}
		
		double segno = Math.random();
		//se segno >=0.5 segno positivo, negativo altrimenti.
		if(segno >=0.5) {
			segno = 1;
		}else {
			segno = -1;
		}
		Integer somma=(int)(Math.random()*1000);
		Integer tot = (int) segno*somma;
		
		
		Integer credito = utenteInGioco.getCreditoAccumulato();
		credito += tot;
		if(credito < 0) {
			utenteInGioco.setCreditoAccumulato(0);
			utenteService.aggiorna(utenteInGioco);
			request.setAttribute("errorMessage", "hai terminato il credito!");
			request.setAttribute("tavoloAttribute", tavolo);
			request.setAttribute("utenteAttribute", utenteInGioco);
			request.getRequestDispatcher("/tavolo/gioca.jsp").forward(request, response);
			return;
		}
		utenteInGioco.setCreditoAccumulato(credito);
		utenteService.aggiorna(utenteInGioco);
		if(tot>=0) {
			request.setAttribute("risultatoPositivo", "Hai vinto " + tot + "euro");
		}else {
			request.setAttribute("risultatoNegativo", "Hai perso " + tot + "euro");
		}
		request.setAttribute("tavoloAttribute", tavolo);
		request.setAttribute("utenteAttribute", utenteInGioco);
		request.getRequestDispatcher("/tavolo/gioca.jsp").forward(request, response);
		
		
		

	}

}
