package mvc.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CategoriaControllers
 */
@WebServlet("/categoria")
public class CategoriaControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriaControllers() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("loadCategoria")) {
				loadCategoria(request, response);
			} 			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void loadCategoria(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/frmCategoria.jsp").forward(request,
					response);
		} catch (Exception e) {
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("RegistrarModificarCategoria")) {
				RegistrarModificarCategoria(request, response);
			}	
			else if (metodo.equalsIgnoreCase("ListarCategoria")) {
				ListarCategoria(request, response);
			}
			else if (metodo.equalsIgnoreCase("TotalRegistrosCategoria")) {
				TotalRegistrosCategoria(request, response);
			}
			else if (metodo.equalsIgnoreCase("EliminarCategoria")) {
				EliminarCategoria(request, response);
			}
			else if (metodo.equalsIgnoreCase("ObtenerCategoria")) {
				ObtenerCategoria(request, response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void TotalRegistrosCategoria(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	private void ObtenerCategoria(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	private void EliminarCategoria(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void ListarCategoria(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	private void RegistrarModificarCategoria(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

}
