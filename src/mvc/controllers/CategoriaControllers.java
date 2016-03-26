package mvc.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.models.Categoria_models;
import mvc.models.Marca_models;
import mvc.vo.Categoria_vo;
import mvc.vo.Marca_vo;
import mvc.vo.Paginador_vo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
		try {
			int TotalRegistro = 0;
			Categoria_vo  categoria_vo= new Categoria_vo();
			categoria_vo.setDescripcion(request.getParameter("txt_Descripcion_buscar").trim());
			TotalRegistro  = new Categoria_models().TotalRegistroCategoria(categoria_vo);
			Map<String, String> Beanmap= new HashMap<String, String>();
			Beanmap.put("TotalRegistro", String.valueOf(TotalRegistro));
			Gson gson= new GsonBuilder().setPrettyPrinting().create();
			String json= gson.toJson(Beanmap);
			response.getWriter().write(json);
		
		} catch (Exception e) {
			System.out.println("Error en el metodo TotalRegistrosCategoria "
					+ e.getMessage());
		}
		
	}


	private void ObtenerCategoria(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Categoria_vo  categoria_vo= new Categoria_vo();
			categoria_vo.setIdcategoria(Integer.parseInt(request.getParameter("codcategoria")));			
			categoria_vo = new Categoria_models().obtenerDatosCategoria(categoria_vo);			
			String json= new Gson().toJson(categoria_vo);			
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+json+"]";				
			response.getWriter().write(bothJson);		
			
		} catch (Exception e) {
			System.out.println("Error en el metodo ObtenerCategoria : "+e.getMessage());
		}				
	}


	private void EliminarCategoria(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String ind_respuesta= ""; 
			Categoria_vo  categoria_vo= new Categoria_vo();
			categoria_vo.setIdcategoria(Integer.parseInt(request.getParameter("codcategoria")));			
			Map<String, String> Beanmap= new HashMap<String, String>();
			ind_respuesta = new Categoria_models().EliminarCategoria(categoria_vo);
			if(ind_respuesta.equals("-1")){
				Beanmap.put("ind_respuesta", String.valueOf("-1"));
				Gson gson= new GsonBuilder().setPrettyPrinting().create();
				String json= gson.toJson(Beanmap);
				response.getWriter().write(json);
			}else if(!ind_respuesta.equals("")){
				Beanmap.put("ind_respuesta", ind_respuesta);
				Gson gson= new GsonBuilder().setPrettyPrinting().create();
				String json= gson.toJson(Beanmap);
				response.getWriter().write(json);
			}
			
		} catch (Exception e) {
			System.out.println("Error en el metodo EliminarCategoria : "+e.getMessage());
		}
		
	}

	private void ListarCategoria(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Categoria_vo  categoria_vo= new Categoria_vo();
			List<Categoria_vo> listcategoria = new ArrayList<Categoria_vo>();
			Paginador_vo paginador_vo= new Paginador_vo();
			categoria_vo.setDescripcion(request.getParameter("txt_Descripcion_buscar").trim());
			paginador_vo.setLimit(Integer.parseInt(request.getParameter("limit")));
			paginador_vo.setOffset(Integer.parseInt(request.getParameter("offset")));
			categoria_vo.setPaginador(paginador_vo);
			listcategoria = new Categoria_models().ListarCategoria(categoria_vo);
			String json= new Gson().toJson(listcategoria);			
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+json+"]";				
			response.getWriter().write(bothJson);
		
		} catch (Exception e) {
			System.out.println("Error en el metodo ListarCategoria "
					+ e.getMessage());
		}
		
	}


	private void RegistrarModificarCategoria(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			 
			Categoria_vo  objeto= new Categoria_vo();
			int indAccion = Integer.parseInt(request.getParameter("hiddenindaccion"));
			objeto.setDescripcion(request.getParameter("txt_descripcion_guardar"));			
			
			String codigocategoria  = "";
			if (indAccion == 1) {
				Map<String, String> Beanmap= new HashMap<String, String>();
				codigocategoria = new Categoria_models().RegistrarCategoria(objeto);
				if(codigocategoria.equals("-1")){
					Beanmap.put("codigocategoria", String.valueOf("-1"));
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}else if(!codigocategoria.equals("-1")){
					Beanmap.put("codigocategoria", codigocategoria);
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}
			}else {
				objeto.setIdcategoria(Integer.parseInt(request.getParameter("hiddencodcategoria")));
				Map<String, String> Beanmap= new HashMap<String, String>();
				codigocategoria = new Categoria_models().ActualizarCategoria(objeto);
				if(codigocategoria.equals("-1")){
					Beanmap.put("codigocategoria", String.valueOf("-1"));
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}else if(!codigocategoria.equals("-1")){
					Beanmap.put("codigocategoria", codigocategoria);
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}
			}
			
		} catch (Exception e) {
			System.out.println("Error en el metodo RegistrarModificarCategoria : "+e.getMessage());
		}
		
	}

}
