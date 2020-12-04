package it.solving.pokeronline.web.utente;

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


import it.solving.pokeronline.dto.UtenteDTO;
import it.solving.pokeronline.model.StatoUtente;
import it.solving.pokeronline.model.Utente;
import it.solving.pokeronline.service.utente.UtenteService;

/**
 * Servlet implementation class ExecuteRegistrazioneUtenteServlet
 */
@WebServlet("/ExecuteRegistrazioneUtenteServlet")
public class ExecuteRegistrazioneUtenteServlet extends HttpServlet {
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
    public ExecuteRegistrazioneUtenteServlet() {
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
		//input registarzione
		String nomeInput = request.getParameter("nome");
		String cognomeInput = request.getParameter("cognome");
		String usernameInput= request.getParameter("username");
		String passwordInput = request.getParameter("password");
		
		
		//dto di controllo
		UtenteDTO utenteDTO = new UtenteDTO(nomeInput, cognomeInput, usernameInput, passwordInput);
		
		List<String> utenteErrors = utenteDTO.errors();
		for (Utente utente: utenteService.listAllUtenti()) {
			if(utente.getUsername().equals(usernameInput)) {
				utenteErrors.add("username non disponibile");
				request.setAttribute("utenteAttribute", utenteDTO);
				request.setAttribute("utenteErrors", utenteErrors);
				request.getRequestDispatcher("/utente/registrazione.jsp").forward(request, response);
				return;
			}
		}
		if (!utenteErrors.isEmpty()) {
			
			request.setAttribute("utenteAttribute", utenteDTO);
			request.setAttribute("utenteErrors", utenteErrors);
			request.getRequestDispatcher("/utente/registrazione.jsp").forward(request, response);
			return;
		}
		
		//se arrivo qui significa che va bene e converto
				Utente utenteInstance = UtenteDTO.buildModelFromDto(utenteDTO);
				utenteInstance.setStato(StatoUtente.CREATO);
				utenteInstance.setDataRegistrazione(LocalDate.now());
				
				utenteService.inserisciNuovo(utenteInstance);
				
				//vado in pagina con ok
				request.setAttribute("messaggioConferma", "Sei registrato! Attendi conferma account per accedere");
				request.getRequestDispatcher("/login.jsp").forward(request, response);

	}

}
