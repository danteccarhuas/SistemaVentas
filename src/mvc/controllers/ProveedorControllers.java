package mvc.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mvc.models.Ubigeo_models;
import mvc.vo.Departamento_vo;
import mvc.vo.Distrito_vo;
import mvc.vo.Provincia_vo;

/**
 * Servlet implementation class ProveedorControllers
 */
@WebServlet("/proveedor")
public class ProveedorControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProveedorControllers() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String metodo=request.getParameter("metodo");
			if(metodo.equalsIgnoreCase("loadProveedor")){
				LoadProveedor(request,response);
			}
			else if(metodo.equalsIgnoreCase("LoadComboDepartamento")){
				LoadComboDepartamento(request,response);
			}				
			
			else if(metodo.equals("LoadComboProvincia")){
				LoadComboProvincia(request,response);
			}
			else if(metodo.equals("LoadComboDistrito")){
				LoadComboDistrito(request,response);
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private void LoadComboDistrito(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Provincia_vo bean =  new Provincia_vo();
			bean.setIdprov(Integer.parseInt(request.getParameter("idvalue")));
			List<Distrito_vo> listDistrito= new ArrayList<Distrito_vo>();
			listDistrito = new Ubigeo_models().ListarDistrito(bean);
			String jsonDistrito= new Gson().toJson(listDistrito);
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+jsonDistrito+"]";				
			response.getWriter().write(bothJson);
		} catch (Exception e) {
			System.out.println("Error en el metodo LoadComboDistrito " +e.getMessage());
		}
	}
	private void LoadComboProvincia(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Departamento_vo bean =  new Departamento_vo();
			bean.setIddepar(Integer.parseInt(request.getParameter("idvalue")));
			List<Provincia_vo> listProvincia= new ArrayList<Provincia_vo>();
			listProvincia = new Ubigeo_models().ListarProvincia(bean);
			String jsonProvincia= new Gson().toJson(listProvincia);
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+jsonProvincia+"]";				
			response.getWriter().write(bothJson);
		} catch (Exception e) {
			System.out.println("Error en el metodo LoadComboProvincia " +e.getMessage());
		}	
	}

	private void LoadComboDepartamento(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<Departamento_vo> listDepartamento= new ArrayList<Departamento_vo>();
			listDepartamento = new Ubigeo_models().ListarDepartamento();
			String jsonDepartamento= new Gson().toJson(listDepartamento);
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+jsonDepartamento+"]";				
			response.getWriter().write(bothJson);
		} catch (Exception e) {
			System.out.println("Error en el metodo LoadComboDepartamento " +e.getMessage());
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}
	
	private void LoadProveedor(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/frmProveedor.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	

}
