package mvc.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.models.Parametrizador_models;
import mvc.models.Tienda_models;
import mvc.models.Trabajador_models;
import mvc.vo.Funcionalidad_vo;
import mvc.vo.Paginador_vo;
import mvc.vo.Parametrizador_vo;
import mvc.vo.Tienda_vo;
import mvc.vo.Trabajador_vo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class TrabajadorControllers
 */
@WebServlet("/trabajador")
public class TrabajadorControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TrabajadorControllers() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {	
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("loadTrabajador")) {
				LoadTrabajador(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("RegistrarModificarTrabajador")) {
				RegistrarModificarTrabajador(request, response);
			}else if (metodo.equalsIgnoreCase("TotalRegistrosTrabajadores")) {
				TotalRegistrosTrabajadores(request, response);
			}else if (metodo.equalsIgnoreCase("ListarTrabajadores")) {
				ListarTrabajadores(request, response);
			}else if (metodo.equalsIgnoreCase("LoadEstados")) {
				LoadEstados(request, response);
			}else if (metodo.equalsIgnoreCase("ObtenerDatosTrabajador")) {
				ObtenerDatosTrabajador(request, response);
			}else if (metodo.equalsIgnoreCase("EliminarTrabajador")) {
				EliminarTrabajador(request, response);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void EliminarTrabajador(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String ind_respuesta= ""; 
			Trabajador_vo vo_trabajador= new Trabajador_vo();
			vo_trabajador.setCodigotrabajador(request.getParameter("codtrab"));			
			Map<String, String> Beanmap= new HashMap<String, String>();
			ind_respuesta = new Trabajador_models().EliminarTrabajador(vo_trabajador);
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
			System.out.println("Error en el metodo EliminarTrabajador : "+e.getMessage());
		}
	}


	private void ObtenerDatosTrabajador(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Trabajador_vo vo_trabajador= new Trabajador_vo();
			vo_trabajador.setCodigotrabajador(request.getParameter("codigo_trab"));			
			vo_trabajador = new Trabajador_models().obtenerDatosTrabajador(vo_trabajador);
			if(vo_trabajador !=null){
				String json= new Gson().toJson(vo_trabajador);			
				response.setContentType("application/json"); 
				response.setCharacterEncoding("utf-8"); 
				String bothJson = "["+json+"]";				
				response.getWriter().write(bothJson);
			}
			
		} catch (Exception e) {
			System.out.println("Error en el metodo ObtenerDatosTrabajador : "+e.getMessage());
		}
	}


	private void LoadEstados(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<Parametrizador_vo> listEstado = new ArrayList<Parametrizador_vo>();
			Funcionalidad_vo funcionalidad = new Funcionalidad_vo();
			funcionalidad.setIdfuncionalidad(1);
			listEstado= new Parametrizador_models().ListarEstado(funcionalidad);
			
			List<Tienda_vo> listTienda=new ArrayList<Tienda_vo>();
			listTienda=new Tienda_models().CargarComboTienda();
			String jsonEstado = new Gson().toJson(listEstado);
			String jsonTienda = new Gson().toJson(listTienda);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			String bothJson = "["+jsonEstado+","+jsonTienda+"]";			
			response.getWriter().write(bothJson);
		} catch (Exception e) {
			System.out.println("Error en el metodo LoadEstados "
					+ e.getMessage());
		}
	}


	private void LoadTrabajador(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.sendRedirect("frmTrabajador.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void ListarTrabajadores(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Trabajador_vo  trabajador_vo= new Trabajador_vo();
			List<Trabajador_vo> listTrabajador = new ArrayList<Trabajador_vo>();
			Paginador_vo paginador_vo= new Paginador_vo();
			trabajador_vo.setCodigotrabajador(request.getParameter("txt_codigotrab_buscar").trim().equals("")?"0": request.getParameter("txt_codigotrab_buscar"));
			trabajador_vo.setDni(request.getParameter("txt_dni_buscar").trim().equals("")?"0": request.getParameter("txt_dni_buscar"));
			trabajador_vo.setNombres(request.getParameter("txt_nombre_buscar").trim());
			paginador_vo.setLimit(Integer.parseInt(request.getParameter("limit")));
			paginador_vo.setOffset(Integer.parseInt(request.getParameter("offset")));
			trabajador_vo.setPaginador(paginador_vo);
			listTrabajador = new Trabajador_models().ListarTrabajadores(trabajador_vo);
			String json= new Gson().toJson(listTrabajador);	
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+json+"]";				
			response.getWriter().write(bothJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	private void RegistrarModificarTrabajador(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Trabajador_vo vo_trabajador= new Trabajador_vo();
			
			int indAccion = Integer.parseInt(request.getParameter("hiddenindaccion"));
			vo_trabajador.setNombres(request.getParameter("txt_nombres"));
			vo_trabajador.setApellidos(request.getParameter("txt_apellidos"));
			vo_trabajador.setCorreo(request.getParameter("txt_correo"));
			vo_trabajador.setTelefono(request.getParameter("txt_telefono"));
			vo_trabajador.setDni(request.getParameter("txt_dni"));
			Date date=new Date();
			SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
			date=formato.parse(request.getParameter("txt_fec_nac"));
			vo_trabajador.setFechanacimiento(date);
			vo_trabajador.setDireccion(request.getParameter("txt_direccion"));
			vo_trabajador.setEstado(Integer.parseInt(request.getParameter("cbo_estado")));
			Tienda_vo tienda=new Tienda_vo();
			tienda.setIdtienda(Integer.parseInt(request.getParameter("cbo_tienda")));
			vo_trabajador.setTienda(tienda);
			
			String codigotrabajador  = "";
			if (indAccion == 1) {
				Map<String, String> Beanmap= new HashMap<String, String>();
				codigotrabajador = new Trabajador_models().RegistrarTrabajador(vo_trabajador);
				if(codigotrabajador.equals("-1")){
					Beanmap.put("codigotrabajador", String.valueOf("-1"));
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}else if(!codigotrabajador.equals("-1")){
					Beanmap.put("codigotrabajador", codigotrabajador);
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}
			}else {
				vo_trabajador.setCodigotrabajador(request.getParameter("hiddencodtrab"));
				Map<String, String> Beanmap= new HashMap<String, String>();
				codigotrabajador = new Trabajador_models().ActualizarTrabajador(vo_trabajador);
				if(codigotrabajador.equals("-1")){
					Beanmap.put("codigotrabajador", String.valueOf("-1"));
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}else if(!codigotrabajador.equals("-1")){
					Beanmap.put("codigotrabajador", codigotrabajador);
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}
			}
		} catch (Exception e) {
			System.out.println("Error en el metodo RegistrarModificarTrabajador : "+e.getMessage());
		}
	}
	
	private void TotalRegistrosTrabajadores(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int TotalRegistro = 0;
			Trabajador_vo  trabajador_vo= new Trabajador_vo();
			trabajador_vo.setCodigotrabajador(request.getParameter("txt_codigotrab_buscar").trim().equals("")?"0": request.getParameter("txt_codigotrab_buscar"));
			trabajador_vo.setDni(request.getParameter("txt_dni_buscar").trim().equals("")?"0": request.getParameter("txt_dni_buscar"));
			trabajador_vo.setNombres(request.getParameter("txt_nombres_buscar").trim());
			TotalRegistro  = new Trabajador_models().TotalRegistroTrabajadores(trabajador_vo);
			Map<String, String> Beanmap= new HashMap<String, String>();
			Beanmap.put("TotalRegistro", String.valueOf(TotalRegistro));
			Gson gson= new GsonBuilder().setPrettyPrinting().create();
			String json= gson.toJson(Beanmap);
			response.getWriter().write(json);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
