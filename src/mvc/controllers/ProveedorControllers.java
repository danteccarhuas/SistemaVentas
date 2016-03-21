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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mvc.models.Proveedor_models;
import mvc.models.Ubigeo_models;
import mvc.vo.Departamento_vo;
import mvc.vo.Distrito_vo;
import mvc.vo.Paginador_vo;
import mvc.vo.Proveedor_vo;
import mvc.vo.Provincia_vo;
import mvc.vo.Ubigeo_vo;

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
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("loadProveedor")) {
				LoadProveedor(request, response);
			} 
			else if (metodo.equalsIgnoreCase("LoadComboDepartamento")) {
				LoadComboDepartamento(request, response);
			}
			else if (metodo.equals("LoadComboProvincia")) {
				LoadComboProvincia(request, response);
			} else if (metodo.equals("LoadComboDistrito")) {
				LoadComboDistrito(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("RegistrarModificarProveedor")) {
				RegistrarModificarProveedor(request, response);
			}
			else if (metodo.equalsIgnoreCase("LoadProveedores")) {
				LoadProveedores(request, response);
			}
			else if (metodo.equalsIgnoreCase("TotalRegistrosProveedores")) {
				TotalRegistrosProveedores(request, response);
			}
			else if (metodo.equalsIgnoreCase("EliminarProveedor")) {
				EliminarProveedor(request, response);
			}
			else if (metodo.equalsIgnoreCase("ObtenerDatosProveedor")) {
				ObtenerDatosProveedor(request, response);
			}
			else if (metodo.equalsIgnoreCase("ModificarProveedor")) {
				ModificarProveedor(request, response);
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void ModificarProveedor(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void ObtenerDatosProveedor(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void EliminarProveedor(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String ind_respuesta= ""; 
			Proveedor_vo vo_proveedor= new Proveedor_vo();
			vo_proveedor.setCodigoproveedor(request.getParameter("codprov"));			
			Map<String, String> Beanmap= new HashMap<String, String>();
			ind_respuesta = new Proveedor_models().EliminarProveedor(vo_proveedor);
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
			System.out.println("Error en el metodo RegistrarModificarProveedor : "+e.getMessage());
		}
		
	}

	private void TotalRegistrosProveedores(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int TotalRegistro = 0;
			Proveedor_vo  proveedor_vo= new Proveedor_vo();
			proveedor_vo.setCodigoproveedor(request.getParameter("txt_codigoprov_buscar").trim().equals("")?"0": request.getParameter("txt_codigoprov_buscar"));
			proveedor_vo.setRuc(request.getParameter("txt_ruc_buscar").trim().equals("")?"0": request.getParameter("txt_ruc_buscar"));
			proveedor_vo.setRazonsocial(request.getParameter("txt_razonsocial_buscar").trim());
			TotalRegistro  = new Proveedor_models().TotalRegistroProveedores(proveedor_vo);
			Map<String, String> Beanmap= new HashMap<String, String>();
			Beanmap.put("TotalRegistro", String.valueOf(TotalRegistro));
			Gson gson= new GsonBuilder().setPrettyPrinting().create();
			String json= gson.toJson(Beanmap);
			response.getWriter().write(json);
		
		} catch (Exception e) {
			System.out.println("Error en el metodo LoadComboDistrito "
					+ e.getMessage());
		}
		
	}

	private void LoadProveedores(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			Proveedor_vo  proveedor_vo= new Proveedor_vo();
			List<Proveedor_vo> listProveedor = new ArrayList<Proveedor_vo>();
			Paginador_vo paginador_vo= new Paginador_vo();
			proveedor_vo.setCodigoproveedor(request.getParameter("txt_codigoprov_buscar").trim().equals("")?"0": request.getParameter("txt_codigoprov_buscar"));
			proveedor_vo.setRuc(request.getParameter("txt_ruc_buscar").trim().equals("")?"0": request.getParameter("txt_ruc_buscar"));
			proveedor_vo.setRazonsocial(request.getParameter("txt_razonsocial_buscar").trim());
			paginador_vo.setLimit(Integer.parseInt(request.getParameter("limit")));
			paginador_vo.setOffset(Integer.parseInt(request.getParameter("offset")));
			proveedor_vo.setPaginador(paginador_vo);
			listProveedor = new Proveedor_models().ListarProveedores(proveedor_vo);
			String json= new Gson().toJson(listProveedor);			
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+json+"]";				
			response.getWriter().write(bothJson);
		
		} catch (Exception e) {
			System.out.println("Error en el metodo LoadComboDistrito "
					+ e.getMessage());
		}
		
	}
	
	private void LoadComboDistrito(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Provincia_vo bean = new Provincia_vo();
			bean.setIdprov(Integer.parseInt(request.getParameter("idvalue")));
			List<Distrito_vo> listDistrito = new ArrayList<Distrito_vo>();
			listDistrito = new Ubigeo_models().ListarDistrito(bean);
			String jsonDistrito = new Gson().toJson(listDistrito);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			String bothJson = "[" + jsonDistrito + "]";
			response.getWriter().write(bothJson);
		} catch (Exception e) {
			System.out.println("Error en el metodo LoadComboDistrito "
					+ e.getMessage());
		}
	}

	private void LoadComboProvincia(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Departamento_vo bean = new Departamento_vo();
			bean.setIddepar(Integer.parseInt(request.getParameter("idvalue")));
			List<Provincia_vo> listProvincia = new ArrayList<Provincia_vo>();
			listProvincia = new Ubigeo_models().ListarProvincia(bean);
			String jsonProvincia = new Gson().toJson(listProvincia);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			String bothJson = "[" + jsonProvincia + "]";
			response.getWriter().write(bothJson);
		} catch (Exception e) {
			System.out.println("Error en el metodo LoadComboProvincia "
					+ e.getMessage());
		}
	}

	private void LoadComboDepartamento(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<Departamento_vo> listDepartamento = new ArrayList<Departamento_vo>();
			listDepartamento = new Ubigeo_models().ListarDepartamento();
			String jsonDepartamento = new Gson().toJson(listDepartamento);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			String bothJson = "[" + jsonDepartamento + "]";
			response.getWriter().write(bothJson);
		} catch (Exception e) {
			System.out.println("Error en el metodo LoadComboDepartamento "
					+ e.getMessage());
		}

	}
	
	private void RegistrarModificarProveedor(HttpServletRequest request,
			HttpServletResponse response) {
		try {
 
			Proveedor_vo vo_proveedor= new Proveedor_vo();
			Ubigeo_vo vo_ubigeo =  new Ubigeo_vo();
			
			String strubigeo = "";
			int indAction = Integer.parseInt(request.getParameter("hiddenindaccion"));
			strubigeo= request.getParameter("cbo_departamento") + request.getParameter("cbo_provincia")+request.getParameter("cbo_distrito");
			vo_proveedor.setRazonsocial(request.getParameter("txt_razon_social"));			
			vo_proveedor.setCorreo(request.getParameter("txt_correo"));
			vo_proveedor.setFax(request.getParameter("txt_fax"));
			vo_proveedor.setTelefono(request.getParameter("txt_telefono"));
			vo_proveedor.setCelular(request.getParameter("txt_celular"));
			vo_proveedor.setSitioweb(request.getParameter("txt_sitioweb"));
			vo_proveedor.setRuc(request.getParameter("txt_ruc"));
			vo_proveedor.setDireccion(request.getParameter("txt_direccion"));
			vo_proveedor.setReferencia(request.getParameter("txt_referencia"));
			vo_proveedor.setContacto(request.getParameter("txt_contacto"));
			vo_proveedor.setEstado(Integer.parseInt( request.getParameter("cbo_estado")));
			vo_ubigeo.setUbigeo(Integer.parseInt( strubigeo));
			vo_proveedor.setUbigeo(vo_ubigeo);
			String codigoprovedor  = "";
			
			if (indAction == 1) {
				Map<String, String> Beanmap= new HashMap<String, String>();
				codigoprovedor = new Proveedor_models().RegistrarProveedor(vo_proveedor);
				if(codigoprovedor.equals("-1")){
					Beanmap.put("codigoproveedor", String.valueOf("-1"));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}else if(!codigoprovedor.equals("")){
					Beanmap.put("codigoproveedor", codigoprovedor);
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}
			}else {
				
			}
			
			
			
		} catch (Exception e) {
			System.out.println("Error en el metodo RegistrarProveedor : "+e.getMessage());
		}

	}

	private void LoadProveedor(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/frmProveedor.jsp").forward(request,
					response);
		} catch (Exception e) {
		}

	}

}
