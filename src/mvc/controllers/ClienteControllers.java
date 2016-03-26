package mvc.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ClienteControllers
 */
@WebServlet("/cliente")
public class ClienteControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteControllers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("loadCliente")) {
				loadCliente(request, response);
			} 			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadCliente(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/frmCliente.jsp").forward(request,
					response);
		} catch (Exception e) {
		}	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("RegistrarModificarCliente")) {
				RegistrarModificarCliente(request, response);
			}	
			else if (metodo.equalsIgnoreCase("ListarCliente")) {
				ListarCliente(request, response);
			}
			else if (metodo.equalsIgnoreCase("TotalRegistrosCliente")) {
				TotalRegistrosCliente(request, response);
			}
			else if (metodo.equalsIgnoreCase("EliminarCliente")) {
				EliminarCliente(request, response);
			}
			else if (metodo.equalsIgnoreCase("ObtenerCliente")) {
				ObtenerCliente(request, response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void ObtenerCliente(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void EliminarCliente(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void TotalRegistrosCliente(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void ListarCliente(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void RegistrarModificarCliente(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

}
