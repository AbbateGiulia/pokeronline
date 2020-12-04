package it.solving.pokeronline.web.tavolo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solving.pokeronline.model.Utente;
import it.solving.pokeronline.service.utente.UtenteService;
import it.solving.pokeronline.util.Util;

/**
 * Servlet implementation class ExecuteShopCreditoServlet
 */
@WebServlet("/ExecuteShopCreditoServlet")
public class ExecuteShopCreditoServlet extends HttpServlet {
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
	public ExecuteShopCreditoServlet() {
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
		String idUtente = request.getParameter("idUtente");
		String credito = request.getParameter("credito");

		if (Util.isEmptyOrNull(idUtente) || !Util.isNumber(idUtente)) {
			RequestDispatcher rd = request.getRequestDispatcher("/LogoutServlet");
			rd.forward(request, response);
			return;
		}

		if (!Util.isNumber(credito)) {
			request.setAttribute("errorMessage", "il credito deve essere un numero!");
			request.getRequestDispatcher("/tavolo/credito.jsp").forward(request, response);
			;
			return;
		}
		Utente utente = utenteService.caricaSingoloUtente(Long.parseLong(idUtente));
		Integer creditoUtente = utente.getCreditoAccumulato();
		creditoUtente += Integer.parseInt(credito);
		utente.setCreditoAccumulato(creditoUtente);
		utenteService.aggiorna(utente);
		// aggiorno utente in sessione
		HttpSession session = request.getSession();
		Utente user = (Utente) session.getAttribute("userInfo");
		user.setCreditoAccumulato(creditoUtente);
		utenteService.aggiorna(user);

		request.setAttribute("successMessage", "Aggiunto credito + " + credito + " con successo!");
		request.getRequestDispatcher("/tavolo/credito.jsp").forward(request, response);
		;

	}

}
