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

import mvc.models.Cliente_models;
import mvc.models.Parametrizador_models;
import mvc.models.Tienda_models;
import mvc.models.Ubigeo_models;
import mvc.vo.Cliente_vo;
import mvc.vo.Departamento_vo;
import mvc.vo.Distrito_vo;
import mvc.vo.Funcionalidad_vo;
import mvc.vo.Parametrizador_vo;
import mvc.vo.Provincia_vo;
import mvc.vo.Tienda_vo;
import mvc.vo.Ubigeo_vo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class ClienteControllers
 */
@WebServlet("/cliente")
public class ClienteControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteControllers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("loadCliente")) {
				loadCliente(request, response);
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
			List<Parametrizador_vo> listEstado = new ArrayList<Parametrizador_vo>();
			List<Parametrizador_vo> listTipoPersona = new ArrayList<Parametrizador_vo>();
			Funcionalidad_vo funcionalidad = new Funcionalidad_vo();
			funcionalidad.setIdfuncionalidad(1);
			listDepartamento = new Ubigeo_models().ListarDepartamento();
			listEstado= new Parametrizador_models().ListarValores(funcionalidad);
			funcionalidad.setIdfuncionalidad(3);
			listTipoPersona= new Parametrizador_models().ListarValores(funcionalidad);
			
			String jsonDepartamento = new Gson().toJson(listDepartamento);
			String jsonEstado = new Gson().toJson(listEstado);
			String jsonTipopersona = new Gson().toJson(listTipoPersona);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			//String bothJson = "[" + jsonDepartamento + "]";
			String bothJson = "["+jsonDepartamento+","+jsonEstado+","+jsonTipopersona+"]";		
			
			response.getWriter().write(bothJson);
		} catch (Exception e) {
			System.out.println("Error en el metodo LoadComboDepartamento "
					+ e.getMessage());
		}
		
	}

	private void loadCliente(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/frmCliente.jsp").forward(request,
					response);
		} catch (Exception e) {
		}	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("RegistrarModificarCliente")) {
				RegistrarModificarCliente(request, response);
			}	
			else if (metodo.equalsIgnoreCase("ListarCliente")) {
				ListarCliente(request, response);
			}
			else if (metodo.equalsIgnoreCase("TotalRegistrosCliente")) {
				TotalRegistrosCliente(request, response);
			}
			else if (metodo.equalsIgnoreCase("EliminarCliente")) {
				EliminarCliente(request, response);
			}
			else if (metodo.equalsIgnoreCase("ObtenerCliente")) {
				ObtenerCliente(request, response);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void ObtenerCliente(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void EliminarCliente(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void TotalRegistrosCliente(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void ListarCliente(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void RegistrarModificarCliente(HttpServletRequest request,
			HttpServletResponse response) {
try {
			String strubigeo  ="";
			Cliente_vo  objeto= new Cliente_vo();
			int indAccion = Integer.parseInt(request.getParameter("hiddenindaccion"));
			objeto.setNombres(request.getParameter("txt_nombre_guardar"));		
			objeto.setApellidos(request.getParameter("txt_apellidos_guardar"));			
			objeto.setCorreo(request.getParameter("txt_correo_guardar"));	
			objeto.setTelefono(request.getParameter("txt_telefono_guardar"));
			objeto.setCelular(request.getParameter("txt_celular_guardar"));
			objeto.setDni(request.getParameter("txt_dni_guardar"));
			objeto.setRuc(request.getParameter("txt_ruc_guardar"));
			objeto.setDireccion(request.getParameter("txt_direccion_guarda"));
			objeto.setReferencia(request.getParameter("txt_referencia_guardar"));
						
			
			Parametrizador_vo tipopersona= new Parametrizador_vo();
			tipopersona.setValor(Integer.parseInt(request.getParameter("cbo_tipopersona")));
			
			strubigeo= request.getParameter("cbo_departamento") + request.getParameter("cbo_provincia")+request.getParameter("cbo_distrito");
			Ubigeo_vo ubigeo_vo = new Ubigeo_vo();
			ubigeo_vo.setUbigeo(Integer.parseInt(strubigeo));
						
			Parametrizador_vo estado= new Parametrizador_vo();
			estado.setValor(Integer.parseInt(request.getParameter("cbo_estado")));
			
			Parametrizador_vo sexo= new Parametrizador_vo();
			sexo.setValor(Integer.parseInt(request.getParameter("generoRadios")));
			
			objeto.setTipopersona(tipopersona);
			objeto.setUbigeo(ubigeo_vo);
			objeto.setSexo(sexo);
			objeto.setEstado(estado);
			
				
			String codigocliente  = "";
			if (indAccion == 1) {
				Map<String, String> Beanmap= new HashMap<String, String>();
				codigocliente = new Cliente_models().RegistrarCliente(objeto);
				if(codigocliente.equals("-1")){
					Beanmap.put("codigocliente", String.valueOf("-1"));
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}else if(!codigocliente.equals("-1")){
					Beanmap.put("codigocliente", codigocliente);
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}
			}else {
				objeto.setCodigocliente(request.getParameter("hiddencodtienda"));
				Map<String, String> Beanmap= new HashMap<String, String>();
				codigocliente = new Cliente_models().ActualizarCliente(objeto);
				if(codigocliente.equals("-1")){
					Beanmap.put("codigocliente", String.valueOf("-1"));
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}else if(!codigocliente.equals("-1")){
					Beanmap.put("codigocliente", codigocliente);
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}
			}
			
		} catch (Exception e) {
			System.out.println("Error en el metodo RegistrarModificarCliente : "+e.getMessage());
		}
		
	}

}
