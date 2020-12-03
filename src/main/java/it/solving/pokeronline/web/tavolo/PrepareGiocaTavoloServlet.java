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

import it.solving.pokeronline.model.Tavolo;
import it.solving.pokeronline.model.Utente;
import it.solving.pokeronline.service.tavolo.TavoloService;


/**
 * Servlet implementation class PrepareGiocaTavoloServlet
 */
@WebServlet("/PrepareGiocaTavoloServlet")
public class PrepareGiocaTavoloServlet extends HttpServlet {
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
	public PrepareGiocaTavoloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tavoloId = request.getParameter("idTavolo");
		//carico tavolo
		Tavolo tavoloGioco = tavoloService.caricaSingoloTavolo(Long.parseLong(tavoloId));
		//utente in sessione
		HttpSession session = request.getSession();
		Utente user = (Utente) session.getAttribute("userInfo");
		//controllo sul credito disponibile
		if (user.getCreditoAccumulato() < tavoloGioco.getCreditoMinimo() ) {
			request.setAttribute("listaTavoliAttribute", tavoloService.listAllTavolo());
			request.setAttribute("errorMessage", "credito insufficiente per partecipare alla partita selezionata");
			request.getRequestDispatcher("/tavolo/listapartite.jsp").forward(request, response);
			return;
		}
		if (user.getEsperienzaAccumulata() < tavoloGioco.getEsperienzaMinima()) {
			request.setAttribute("listaTavoliAttribute", tavoloService.listAllTavolo());
			request.setAttribute("errorMessage", "esperienza insufficiente per partecipare alla partita selezionata");
			request.getRequestDispatcher("/tavolo/listapartite.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("utenteAttribute", user);
		request.setAttribute("tavoloAttribute", tavoloGioco);
		request.getRequestDispatcher("/tavolo/gioca.jsp").forward(request, response);

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
