package it.solving.pokeronline.web.utente;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.solving.pokeronline.model.Utente;
import it.solving.pokeronline.service.utente.UtenteService;

/**
 * Servlet implementation class ExecuteVisualizzaUtenteServlet
 */
@WebServlet("/users/ExecuteVisualizzaUtenteServlet")
public class ExecuteVisualizzaUtenteServlet extends HttpServlet {
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
    public ExecuteVisualizzaUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idUtente = request.getParameter("idUtente");
		Utente utenteVisualizza = utenteService.caricaSingoloUtenteEager(Long.parseLong(idUtente));
		request.setAttribute("utenteAttribute", utenteVisualizza);
		//utilizzo autocomplete, no set lista ruoli
		//request.setAttribute("listaRuoli", utenteVisualizza.getRuoli());
		request.getRequestDispatcher("/utente/dettaglio.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
