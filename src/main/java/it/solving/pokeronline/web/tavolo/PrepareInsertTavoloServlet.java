package it.solving.pokeronline.web.tavolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.solving.pokeronline.dto.TavoloDTO;
import it.solving.pokeronline.model.Utente;

/**
 * Servlet implementation class PrepareInsertTavoloServlet
 */
@WebServlet("/special/PrepareInsertTavoloServlet")
public class PrepareInsertTavoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareInsertTavoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session =request.getSession();
		Utente user = (Utente)session.getAttribute("userInfo");
		TavoloDTO tavoloDTO = new TavoloDTO (user.getId().toString());
		request.setAttribute("tavoloAttribute", tavoloDTO);
		request.getRequestDispatcher("/tavolo/insert.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
