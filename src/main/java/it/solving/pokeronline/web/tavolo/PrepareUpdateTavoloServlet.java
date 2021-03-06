package it.solving.pokeronline.web.tavolo;

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

import it.solving.pokeronline.model.Tavolo;
import it.solving.pokeronline.model.Utente;
import it.solving.pokeronline.service.tavolo.TavoloService;
import it.solving.pokeronline.util.Util;

/**
 * Servlet implementation class PrepareUpdateTavoloServlet
 */
@WebServlet("/special/PrepareUpdateTavoloServlet")
public class PrepareUpdateTavoloServlet extends HttpServlet {
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
	public PrepareUpdateTavoloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idTavolo = request.getParameter("idTavolo");

		// controllo id valido in input da url
		if (Util.isEmptyOrNull(idTavolo) || !Util.isNumber(idTavolo)) {
			RequestDispatcher rd = request.getRequestDispatcher("/LogoutServlet");
			rd.forward(request, response);
			return;
		}
		Tavolo tavoloDettaglio = tavoloService.caricaSingoloTavoloEager(Long.parseLong(idTavolo));

		// impedire update per tavoli non vuoti
		if (tavoloDettaglio.getGiocatori().size() > 0) {
			request.setAttribute("errorMessage", "non puoi modificare tavolo con giocatori");
			Utente utente = (Utente) request.getSession().getAttribute("userInfo");
			Long id = utente.getId();
			request.setAttribute("listaTavoliAttribute", tavoloService.listAllTavoloUtente(id));
			request.getRequestDispatcher("/tavolo/results.jsp").forward(request, response);
			return;
		}

		request.setAttribute("tavoloAttribute", tavoloDettaglio);
		request.getRequestDispatcher("/tavolo/update.jsp").forward(request, response);
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
