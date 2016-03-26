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
import mvc.vo.Marca_vo;
import mvc.vo.Paginador_vo;
import mvc.vo.Proveedor_vo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class MarcaControllers
 */
@WebServlet("/marca")
public class MarcaControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarcaControllers() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("loadMarca")) {
				loadMarca(request, response);
			} 			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("RegistrarModificarMarca")) {
				RegistrarModificarMarca(request, response);
			}	
			else if (metodo.equalsIgnoreCase("ListarMarca")) {
				ListarMarca(request, response);
			}
			else if (metodo.equalsIgnoreCase("TotalRegistrosMarca")) {
				TotalRegistrosMarca(request, response);
			}
			else if (metodo.equalsIgnoreCase("EliminarMarca")) {
				EliminarMarca(request, response);
			}
			else if (metodo.equalsIgnoreCase("ObtenerMarca")) {
				ObtenerMarca(request, response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void ObtenerMarca(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Marca_vo vo_marca= new Marca_vo();
			vo_marca.setIdmarca(Integer.parseInt(request.getParameter("codigo_marca")));			
			vo_marca = new Marca_models().obtenerDatosMarca(vo_marca);			
			String json= new Gson().toJson(vo_marca);			
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+json+"]";				
			response.getWriter().write(bothJson);		
			
		} catch (Exception e) {
			System.out.println("Error en el metodo ObtenerMarca : "+e.getMessage());
		}		
	}


	private void EliminarMarca(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String ind_respuesta= ""; 
			Marca_vo vo_marca= new Marca_vo();
			vo_marca.setIdmarca(Integer.parseInt(request.getParameter("codmarca")));			
			Map<String, String> Beanmap= new HashMap<String, String>();
			ind_respuesta = new Marca_models().EliminarMarca(vo_marca);
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
			System.out.println("Error en el metodo EliminarMarca : "+e.getMessage());
		}
		
	}


	private void TotalRegistrosMarca(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int TotalRegistro = 0;
			Marca_vo  marca_vo= new Marca_vo();
			marca_vo.setDescripcion(request.getParameter("txt_Descripcion_buscar").trim());
			TotalRegistro  = new Marca_models().TotalRegistroMarca(marca_vo);
			Map<String, String> Beanmap= new HashMap<String, String>();
			Beanmap.put("TotalRegistro", String.valueOf(TotalRegistro));
			Gson gson= new GsonBuilder().setPrettyPrinting().create();
			String json= gson.toJson(Beanmap);
			response.getWriter().write(json);
		
		} catch (Exception e) {
			System.out.println("Error en el metodo TotalRegistrosMarca "
					+ e.getMessage());
		}
		
	}


	private void ListarMarca(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Marca_vo  marca_vo= new Marca_vo();
			List<Marca_vo> listmarca = new ArrayList<Marca_vo>();
			Paginador_vo paginador_vo= new Paginador_vo();
			marca_vo.setDescripcion(request.getParameter("txt_Descripcion_buscar").trim());
			paginador_vo.setLimit(Integer.parseInt(request.getParameter("limit")));
			paginador_vo.setOffset(Integer.parseInt(request.getParameter("offset")));
			marca_vo.setPaginador(paginador_vo);
			listmarca = new Marca_models().ListarMarca(marca_vo);
			String json= new Gson().toJson(listmarca);			
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+json+"]";				
			response.getWriter().write(bothJson);
		
		} catch (Exception e) {
			System.out.println("Error en el metodo LoadComboDistrito "
					+ e.getMessage());
		}
		
	}


	private void RegistrarModificarMarca(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			 
			Marca_vo  objeto= new Marca_vo();
			int indAccion = Integer.parseInt(request.getParameter("hiddenindaccion"));
			objeto.setDescripcion(request.getParameter("txt_descripcion_guardar"));			
			
			String codigomarca  = "";
			if (indAccion == 1) {
				Map<String, String> Beanmap= new HashMap<String, String>();
				codigomarca = new Marca_models().RegistrarMarca(objeto);
				if(codigomarca.equals("-1")){
					Beanmap.put("codigomarca", String.valueOf("-1"));
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}else if(!codigomarca.equals("-1")){
					Beanmap.put("codigomarca", codigomarca);
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}
			}else {
				objeto.setIdmarca(Integer.parseInt(request.getParameter("hiddencodmarca")));
				Map<String, String> Beanmap= new HashMap<String, String>();
				codigomarca = new Marca_models().ActualizarMarca(objeto);
				if(codigomarca.equals("-1")){
					Beanmap.put("codigomarca", String.valueOf("-1"));
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}else if(!codigomarca.equals("-1")){
					Beanmap.put("codigomarca", codigomarca);
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}
			}
			
		} catch (Exception e) {
			System.out.println("Error en el metodo RegistrarProveedor : "+e.getMessage());
		}
		
	}


	private void loadMarca(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/frmMarca.jsp").forward(request,
					response);
		} catch (Exception e) {
		}
		
	}




}
