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
import mvc.models.Parametrizador_models;
import mvc.models.Tienda_models;
import mvc.models.Ubigeo_models;
import mvc.vo.Categoria_vo;
import mvc.vo.Departamento_vo;
import mvc.vo.Funcionalidad_vo;
import mvc.vo.Paginador_vo;
import mvc.vo.Parametrizador_vo;
import mvc.vo.Tienda_vo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class TiendaControllers
 */
@WebServlet("/tienda")
public class TiendaControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public TiendaControllers() {
        super();       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("loadTienda")) {
				loadTienda(request, response);
			} 			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadTienda(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/frmTienda.jsp").forward(request,
					response);
		} catch (Exception e) {
		}	
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("RegistrarModificarTienda")) {
				RegistrarModificarTienda(request, response);
			}
			else if (metodo.equalsIgnoreCase("ListarTienda")) {
				ListarTienda(request, response);
			}
			else if (metodo.equalsIgnoreCase("TotalRegistrosTienda")) {
				TotalRegistrosTienda(request, response);
			}
			else if (metodo.equalsIgnoreCase("EliminarTienda")) {
				EliminarTienda(request, response);
			}
			else if (metodo.equalsIgnoreCase("ObtenerDatosTienda")) {
				ObtenerDatosTienda(request, response);
			}	
			else if (metodo.equalsIgnoreCase("LoadEstados")) {
				LoadEstados(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void LoadEstados(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<Parametrizador_vo> listEstado = new ArrayList<Parametrizador_vo>();
			Funcionalidad_vo funcionalidad = new Funcionalidad_vo();
			funcionalidad.setIdfuncionalidad(1);
			listEstado= new Parametrizador_models().ListarEstado(funcionalidad);			
			String jsonEstado = new Gson().toJson(listEstado);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			String bothJson = "["+jsonEstado+"]";			
			response.getWriter().write(bothJson);
		} catch (Exception e) {
			System.out.println("Error en el metodo LoadEstados "
					+ e.getMessage());
		}
			
	}

	private void ObtenerDatosTienda(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Tienda_vo  tienda_vo= new Tienda_vo();
			tienda_vo.setIdtienda(Integer.parseInt(request.getParameter("codtienda")));			
			tienda_vo = new Tienda_models().obtenerDatosTienda(tienda_vo);			
			String json= new Gson().toJson(tienda_vo);			
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+json+"]";				
			response.getWriter().write(bothJson);		
			
		} catch (Exception e) {
			System.out.println("Error en el metodo ObtenerDatosTienda : "+e.getMessage());
		}				
		
	}

	private void EliminarTienda(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String ind_respuesta= ""; 
			Tienda_vo  tienda_vo= new Tienda_vo();
			tienda_vo.setIdtienda(Integer.parseInt(request.getParameter("codtienda")));			
			Map<String, String> Beanmap= new HashMap<String, String>();
			ind_respuesta = new Tienda_models().EliminarTienda(tienda_vo);
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
			System.out.println("Error en el metodo EliminarTienda : "+e.getMessage());
		}
		
	}

	private void TotalRegistrosTienda(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int TotalRegistro = 0;
			Tienda_vo  tienda_vo= new Tienda_vo();
			tienda_vo.setDescripcion(request.getParameter("txt_Descripcion_buscar").trim());
			TotalRegistro  = new Tienda_models().TotalRegistroTienda(tienda_vo);
			Map<String, String> Beanmap= new HashMap<String, String>();
			Beanmap.put("TotalRegistro", String.valueOf(TotalRegistro));
			Gson gson= new GsonBuilder().setPrettyPrinting().create();
			String json= gson.toJson(Beanmap);
			response.getWriter().write(json);
		
		} catch (Exception e) {
			System.out.println("Error en el metodo TotalRegistrosTienda "
					+ e.getMessage());
		}
		
	}

	private void ListarTienda(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Tienda_vo  tienda_vo= new Tienda_vo();
			List<Tienda_vo> listtienda = new ArrayList<Tienda_vo>();
			Paginador_vo paginador_vo= new Paginador_vo();
			tienda_vo.setDescripcion(request.getParameter("txt_Descripcion_buscar").trim());
			paginador_vo.setLimit(Integer.parseInt(request.getParameter("limit")));
			paginador_vo.setOffset(Integer.parseInt(request.getParameter("offset")));
			tienda_vo.setPaginador(paginador_vo);
			listtienda = new Tienda_models().ListarTienda(tienda_vo);
			String json= new Gson().toJson(listtienda);
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+json+"]";				
			response.getWriter().write(bothJson);
		
		} catch (Exception e) {
			System.out.println("Error en el metodo ListarTienda "
					+ e.getMessage());
		}
	}

	private void RegistrarModificarTienda(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			
			Tienda_vo  objeto= new Tienda_vo();
			int indAccion = Integer.parseInt(request.getParameter("hiddenindaccion"));
			objeto.setDescripcion(request.getParameter("txt_descripcion_guardar"));		
			objeto.setDireccion(request.getParameter("txt_direccion_guardar"));			
			objeto.setTelefono(request.getParameter("txt_telefono_guardar"));	
			Parametrizador_vo estado= new Parametrizador_vo();
			estado.setValor(Integer.parseInt(request.getParameter("cbo_estado")));
			objeto.setEstado(estado);
			
			String codigotienda  = "";
			if (indAccion == 1) {
				Map<String, String> Beanmap= new HashMap<String, String>();
				codigotienda = new Tienda_models().RegistrarTienda(objeto);
				if(codigotienda.equals("-1")){
					Beanmap.put("codigotienda", String.valueOf("-1"));
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}else if(!codigotienda.equals("-1")){
					Beanmap.put("codigotienda", codigotienda);
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}
			}else {
				objeto.setIdtienda(Integer.parseInt(request.getParameter("hiddencodtienda")));
				Map<String, String> Beanmap= new HashMap<String, String>();
				codigotienda = new Tienda_models().ActualizarTienda(objeto);
				if(codigotienda.equals("-1")){
					Beanmap.put("codigotienda", String.valueOf("-1"));
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}else if(!codigotienda.equals("-1")){
					Beanmap.put("codigotienda", codigotienda);
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}
			}
			
		} catch (Exception e) {
			System.out.println("Error en el metodo RegistrarModificarTienda : "+e.getMessage());
		}
		
	}

}
