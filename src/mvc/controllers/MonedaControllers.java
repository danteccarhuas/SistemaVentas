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

import mvc.models.Marca_models;
import mvc.models.Moneda_models;
import mvc.vo.Marca_vo;
import mvc.vo.Moneda_vo;
import mvc.vo.Paginador_vo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class MonedaControllers
 */
@WebServlet("/moneda")
public class MonedaControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MonedaControllers() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("loadMoneda")) {
				loadMoneda(request, response);
			} 			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private void loadMoneda(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/frmMoneda.jsp").forward(request,
					response);
		} catch (Exception e) {
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("RegistrarModificarMoneda")) {
				RegistrarModificarMoneda(request, response);
			}	
			else if (metodo.equalsIgnoreCase("ListarMoneda")) {
				ListarMoneda(request, response);
			}
			else if (metodo.equalsIgnoreCase("TotalRegistrosMoneda")) {
				TotalRegistrosMoneda(request, response);
			}
			else if (metodo.equalsIgnoreCase("EliminarMoneda")) {
				EliminarMoneda(request, response);
			}
			else if (metodo.equalsIgnoreCase("ObtenerMoneda")) {
				ObtenerMoneda(request, response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void ObtenerMoneda(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Moneda_vo vo_moneda= new Moneda_vo();
			vo_moneda.setIdmoneda(Integer.parseInt(request.getParameter("codigo_moneda")));			
			vo_moneda = new Moneda_models().obtenerDatosmoneda(vo_moneda);			
			String json= new Gson().toJson(vo_moneda);			
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+json+"]";				
			response.getWriter().write(bothJson);		
			
		} catch (Exception e) {
			System.out.println("Error en el metodo ObtenerMoneda : "+e.getMessage());
		}		
		
	}


	private void EliminarMoneda(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String ind_respuesta= ""; 
			Moneda_vo vo_moneda= new Moneda_vo();
			vo_moneda.setIdmoneda(Integer.parseInt(request.getParameter("codmoneda")));			
			Map<String, String> Beanmap= new HashMap<String, String>();
			ind_respuesta = new Moneda_models().EliminarMarca(vo_moneda);
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
			System.out.println("Error en el metodo EliminarMoneda : "+e.getMessage());
		}
		
	}


	private void TotalRegistrosMoneda(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int TotalRegistro = 0;
			Moneda_vo  marca_vo= new Moneda_vo();
			marca_vo.setDescripcion(request.getParameter("txt_Descripcion_buscar").trim());
			TotalRegistro  = new Moneda_models().TotalRegistroMarca(marca_vo);
			Map<String, String> Beanmap= new HashMap<String, String>();
			Beanmap.put("TotalRegistro", String.valueOf(TotalRegistro));
			Gson gson= new GsonBuilder().setPrettyPrinting().create();
			String json= gson.toJson(Beanmap);
			response.getWriter().write(json);
		
		} catch (Exception e) {
			System.out.println("Error en el metodo TotalRegistrosMoneda "
					+ e.getMessage());
		}
		
	}


	private void ListarMoneda(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Moneda_vo  marca_vo= new Moneda_vo();
			List<Moneda_vo> listmoneda = new ArrayList<Moneda_vo>();
			Paginador_vo paginador_vo= new Paginador_vo();
			marca_vo.setDescripcion(request.getParameter("txt_Descripcion_buscar").trim());
			paginador_vo.setLimit(Integer.parseInt(request.getParameter("limit")));
			paginador_vo.setOffset(Integer.parseInt(request.getParameter("offset")));
			marca_vo.setPaginador(paginador_vo);
			listmoneda = new Moneda_models().ListarMarca(marca_vo);
			String json= new Gson().toJson(listmoneda);			
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+json+"]";				
			response.getWriter().write(bothJson);
		
		} catch (Exception e) {
			System.out.println("Error en el metodo ListarMoneda "
					+ e.getMessage());
		}
		
	}


	private void RegistrarModificarMoneda(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			 
			Moneda_vo  objeto= new Moneda_vo();
			int indAccion = Integer.parseInt(request.getParameter("hiddenindaccion"));
			objeto.setDescripcion(request.getParameter("txt_descripcion_guardar"));		
			objeto.setSimbolo(request.getParameter("txt_simbolo_guardar"));		
			
			String codigomoneda  = "";
			if (indAccion == 1) {
				Map<String, String> Beanmap= new HashMap<String, String>();
				codigomoneda = new Moneda_models().RegistrarMoneda(objeto);
				if(codigomoneda.equals("-1")){
					Beanmap.put("codigomoneda", String.valueOf("-1"));
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}else if(!codigomoneda.equals("-1")){
					Beanmap.put("codigomoneda", codigomoneda);
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}
			}else {
				objeto.setIdmoneda(Integer.parseInt(request.getParameter("hiddencodmoneda")));
				Map<String, String> Beanmap= new HashMap<String, String>();
				codigomoneda = new Moneda_models().ActualizarMoneda(objeto);
				if(codigomoneda.equals("-1")){
					Beanmap.put("codigomoneda", String.valueOf("-1"));
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}else if(!codigomoneda.equals("-1")){
					Beanmap.put("codigomoneda", codigomoneda);
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}
			}
			
		} catch (Exception e) {
			System.out.println("Error en el metodo RegistrarModificarMoneda : "+e.getMessage());
		}
		
	}

}
