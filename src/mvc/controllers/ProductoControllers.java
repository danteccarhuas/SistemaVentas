package mvc.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductoControllers
 */
@WebServlet("/producto")
public class ProductoControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductoControllers() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("loadProducto")) {
				loadProducto(request, response);
			} 			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	private void loadProducto(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/frmProducto.jsp").forward(request,
					response);
		} catch (Exception e) {
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
