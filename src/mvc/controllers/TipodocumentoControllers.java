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

import mvc.models.Moneda_models;
import mvc.models.Tipodocumento_models;
import mvc.vo.Moneda_vo;
import mvc.vo.Paginador_vo;
import mvc.vo.Tipodocumento_vo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class TipodocumentoControllers
 */
@WebServlet("/tipodocumento")
public class TipodocumentoControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipodocumentoControllers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("loadTipodocumento")) {
				loadTipodocumento(request, response);
			} 			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void loadTipodocumento(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/frmTipoDocumento.jsp").forward(request,
					response);
		} catch (Exception e) {
		}		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("RegistrarModificarTipodocumento")) {
				RegistrarModificarTipodocumento(request, response);
			}	
			else if (metodo.equalsIgnoreCase("ListarTipodocumento")) {
				ListarTipodocumento(request, response);
			}
			else if (metodo.equalsIgnoreCase("TotalRegistrosTipodocumento")) {
				TotalRegistrosTipodocumento(request, response);
			}
			else if (metodo.equalsIgnoreCase("EliminarTipodocumento")) {
				EliminarTipodocumento(request, response);
			}
			else if (metodo.equalsIgnoreCase("ObtenerTipodocumento")) {
				ObtenerTipodocumento(request, response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void ObtenerTipodocumento(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Tipodocumento_vo  tipodocumento_vo= new Tipodocumento_vo();
			tipodocumento_vo.setIdtipodocumento(Integer.parseInt(request.getParameter("codigo_tipodocumento")));			
			tipodocumento_vo = new Tipodocumento_models().obtenerDatosTipodocumento(tipodocumento_vo);			
			String json= new Gson().toJson(tipodocumento_vo);			
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+json+"]";				
			response.getWriter().write(bothJson);		
			
		} catch (Exception e) {
			System.out.println("Error en el metodo ObtenerTipodocumento : "+e.getMessage());
		}		
		
	}

	private void EliminarTipodocumento(HttpServletRequest request,
			HttpServletResponse response) {
		
		try {
			String ind_respuesta= ""; 
			Tipodocumento_vo  tipodocumento_vo= new Tipodocumento_vo();
			tipodocumento_vo.setIdtipodocumento(Integer.parseInt(request.getParameter("codtipodocumento")));			
			Map<String, String> Beanmap= new HashMap<String, String>();
			ind_respuesta = new Tipodocumento_models().EliminarTipodocumento(tipodocumento_vo);
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
			System.out.println("Error en el metodo EliminarTipodocumento : "+e.getMessage());
		}
		
	}

	private void TotalRegistrosTipodocumento(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int TotalRegistro = 0;
			Tipodocumento_vo  tipodocumento_vo= new Tipodocumento_vo();
			tipodocumento_vo.setDescripcion(request.getParameter("txt_Descripcion_buscar").trim());
			TotalRegistro  = new Tipodocumento_models().TotalRegistroTipodocumento(tipodocumento_vo);
			Map<String, String> Beanmap= new HashMap<String, String>();
			Beanmap.put("TotalRegistro", String.valueOf(TotalRegistro));
			Gson gson= new GsonBuilder().setPrettyPrinting().create();
			String json= gson.toJson(Beanmap);
			response.getWriter().write(json);
		
		} catch (Exception e) {
			System.out.println("Error en el metodo EliminarTipodocumento "
					+ e.getMessage());
		}
		
	}

	private void ListarTipodocumento(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Tipodocumento_vo  tipodocumento_vo= new Tipodocumento_vo();
			List<Tipodocumento_vo> listmoneda = new ArrayList<Tipodocumento_vo>();
			Paginador_vo paginador_vo= new Paginador_vo();
			tipodocumento_vo.setDescripcion(request.getParameter("txt_Descripcion_buscar").trim());
			paginador_vo.setLimit(Integer.parseInt(request.getParameter("limit")));
			paginador_vo.setOffset(Integer.parseInt(request.getParameter("offset")));
			tipodocumento_vo.setPaginador(paginador_vo);
			listmoneda = new Tipodocumento_models().ListarTipodocumento(tipodocumento_vo);
			String json= new Gson().toJson(listmoneda);			
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+json+"]";				
			response.getWriter().write(bothJson);
		
		} catch (Exception e) {
			System.out.println("Error en el metodo ListarTipodocumento "
					+ e.getMessage());
		}
		
	}

	private void RegistrarModificarTipodocumento(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			 
			Tipodocumento_vo   objeto= new Tipodocumento_vo();
			int indAccion = Integer.parseInt(request.getParameter("hiddenindaccion"));
			objeto.setDescripcion(request.getParameter("txt_descripcion_guardar"));		
			objeto.setAbreviatura(request.getParameter("txt_Abreviatura_guardar"));		
			
			String codigotipodocumento  = "";
			if (indAccion == 1) {
				Map<String, String> Beanmap= new HashMap<String, String>();
				codigotipodocumento = new Tipodocumento_models().RegistrarTipodocumento(objeto);
				if(codigotipodocumento.equals("-1")){
					Beanmap.put("codigotipodocumento", String.valueOf("-1"));
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}else if(!codigotipodocumento.equals("-1")){
					Beanmap.put("codigotipodocumento", codigotipodocumento);
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}
			}else {
				objeto.setIdtipodocumento(Integer.parseInt(request.getParameter("hiddencodTipodocumento")));
				Map<String, String> Beanmap= new HashMap<String, String>();
				codigotipodocumento = new Tipodocumento_models().ActualizarTipodocumento(objeto);
				if(codigotipodocumento.equals("-1")){
					Beanmap.put("codigotipodocumento", String.valueOf("-1"));
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}else if(!codigotipodocumento.equals("-1")){
					Beanmap.put("codigotipodocumento", codigotipodocumento);
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}
			}
			
		} catch (Exception e) {
			System.out.println("Error en el metodo RegistrarModificarTipodocumento : "+e.getMessage());
		}
		
	}

}
