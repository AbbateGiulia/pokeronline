package it.solving.pokeronline.web.utente;


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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.solving.pokeronline.model.Utente;
import it.solving.pokeronline.service.utente.UtenteService;

@WebServlet("/SearchUtenteAjaxServlet")
public class SearchUtenteAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UtenteService utenteService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public SearchUtenteAjaxServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String term = request.getParameter("term"); // data term sulla funzione ajax
		String filter = request.getParameter("filter"); // in base al parametro carico liste diverse
		//list all utenti like term
		if (filter.equals("1")) {
			List<Utente> listaUtenti = utenteService.cercaByUsernameILike(term);
			String json = buildJsonResponse(listaUtenti);
			response.getWriter().write(json);
		//lista all utenti like term e con ruolo special
		} else {
			List<Utente> listaSpecial = utenteService.listAllUtentiSpecial("Special Player", "Admin", term);
			String json = buildJsonResponse(listaSpecial);
			response.getWriter().write(json);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	private String buildJsonResponse(List<Utente> listaUtenti) {
		JsonArray ja = new JsonArray();

		for (Utente utenteElement : listaUtenti) {
			JsonObject jo = new JsonObject();
			jo.addProperty("value", utenteElement.getId());
			jo.addProperty("label", utenteElement.getUsername());
			ja.add(jo);
		}

		return new Gson().toJson(ja);
	}

}
