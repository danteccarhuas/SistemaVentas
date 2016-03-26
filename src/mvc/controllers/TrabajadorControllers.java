package mvc.controllers;

import java.io.IOException;
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

import mvc.models.Proveedor_models;
import mvc.models.Trabajador_models;
import mvc.vo.Paginador_vo;
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
			}else if (metodo.equalsIgnoreCase("TotalRegistrosTrabajadores")) {
				TotalRegistrosTrabajadores(request, response);
			}else if (metodo.equalsIgnoreCase("ListarTrabajadores")) {
				ListarTrabajadores(request, response);
			}else if (metodo.equalsIgnoreCase("RegistrarModificarTrabajador")) {
				RegistrarModificarTrabajador(request, response);
			}
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
			System.out.println(new Date(request.getParameter("txt_fec_nac")));
			vo_trabajador.setFechanacimiento(new Date(request.getParameter("txt_fec_nac")));
			vo_trabajador.setDireccion(request.getParameter("txt_direccion"));
			vo_trabajador.setEstado(Integer.parseInt(request.getParameter("cbo_estado")));
			Tienda_vo tienda=new Tienda_vo();
			tienda.setIdtienda(Integer.parseInt(request.getParameter("cbo_tienda")));
			vo_trabajador.setTienda(tienda);
			
			String codigoprovedor  = "";
			/*if (indAccion == 1) {
				Map<String, String> Beanmap= new HashMap<String, String>();
				codigoprovedor = new Proveedor_models().RegistrarProveedor(vo_trabajador);
				if(codigoprovedor.equals("-1")){
					Beanmap.put("codigoproveedor", String.valueOf("-1"));
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}else if(!codigoprovedor.equals("-1")){
					Beanmap.put("codigoproveedor", codigoprovedor);
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}
			}*/
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
			trabajador_vo.setIdtrabajador(Integer.parseInt(request.getParameter("txt_codigotrab_buscar").trim().equals("")?"0": request.getParameter("txt_codigotrab_buscar")));
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

	private void TotalRegistrosTrabajadores(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int TotalRegistro = 0;
			Trabajador_vo  trabajador_vo= new Trabajador_vo();
			trabajador_vo.setIdtrabajador(Integer.parseInt(request.getParameter("txt_codigotrab_buscar").trim().equals("")?"0": request.getParameter("txt_codigotrab_buscar")));
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

	private void LoadTrabajador(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.sendRedirect("frmTrabajador.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
