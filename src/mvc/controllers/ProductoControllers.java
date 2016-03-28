package mvc.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.models.Categoria_models;
import mvc.models.Marca_models;
import mvc.models.Parametrizador_models;
import mvc.models.Ubigeo_models;
import mvc.vo.Categoria_vo;
import mvc.vo.Departamento_vo;
import mvc.vo.Funcionalidad_vo;
import mvc.vo.Marca_vo;
import mvc.vo.Paginador_vo;
import mvc.vo.Parametrizador_vo;

import com.google.gson.Gson;

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
		try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("RegistrarModificarProducto")) {
				RegistrarModificarProducto(request, response);
			}
			else if (metodo.equalsIgnoreCase("ListarProducto")) {
				ListarProducto(request, response);
			}
			else if (metodo.equalsIgnoreCase("TotalRegistrosProducto")) {
				TotalRegistrosProducto(request, response);
			}
			else if (metodo.equalsIgnoreCase("EliminarProducto")) {
				EliminarProducto(request, response);
			}
			else if (metodo.equalsIgnoreCase("ObtenerDatosProducto")) {
				ObtenerDatosProducto(request, response);
			}	
			else if (metodo.equalsIgnoreCase("LoadCombo")) {
				LoadCombo(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void LoadCombo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<Marca_vo> listMarca = new ArrayList<Marca_vo>();
			List<Categoria_vo> listCategoria = new ArrayList<Categoria_vo>();
			List<Parametrizador_vo> listGenero = new ArrayList<Parametrizador_vo>();
			Funcionalidad_vo funcionalidad = new Funcionalidad_vo();
			funcionalidad.setIdfuncionalidad(2);				
			
			listMarca = new Marca_models().ListarMarcaCombo();			
			listCategoria = new Categoria_models().ListarCategoriaCombo();			
			listGenero= new Parametrizador_models().ListarValores(funcionalidad);
			
			String jsonMarca = new Gson().toJson(listMarca);
			String jsonCategoria = new Gson().toJson(listCategoria);
			String jsonGenero = new Gson().toJson(listGenero);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
		
			String bothJson = "["+jsonMarca+","+jsonCategoria+","+jsonGenero+"]";					
			response.getWriter().write(bothJson);
		} catch (Exception e) {
			System.out.println("Error en el metodo LoadCombo "
					+ e.getMessage());
		}

		
	}

	private void ObtenerDatosProducto(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void EliminarProducto(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void TotalRegistrosProducto(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void ListarProducto(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void RegistrarModificarProducto(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

}
