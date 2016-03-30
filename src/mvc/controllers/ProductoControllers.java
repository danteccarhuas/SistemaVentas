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
import mvc.models.Parametrizador_models;
import mvc.models.Producto_models;
import mvc.models.Ubigeo_models;
import mvc.vo.Categoria_vo;
import mvc.vo.Departamento_vo;
import mvc.vo.Funcionalidad_vo;
import mvc.vo.Marca_vo;
import mvc.vo.Paginador_vo;
import mvc.vo.Parametrizador_vo;
import mvc.vo.Producto_vo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class ProductoControllers
 */
@WebServlet("/producto")
public class ProductoControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductoControllers() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("loadProducto")) {
				loadProducto(request, response);
			} 			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	private void loadProducto(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/frmProducto.jsp").forward(request,
					response);
		} catch (Exception e) {
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String metodo = request.getParameter("metodo");
			if (metodo.equalsIgnoreCase("RegistrarModificarProducto")) {
				RegistrarModificarProducto(request, response);
			}
			else if (metodo.equalsIgnoreCase("ListarProducto")) {
				ListarProducto(request, response);
			}
			else if (metodo.equalsIgnoreCase("TotalRegistrosProducto")) {
				TotalRegistrosProducto(request, response);
			}
			else if (metodo.equalsIgnoreCase("EliminarProducto")) {
				EliminarProducto(request, response);
			}
			else if (metodo.equalsIgnoreCase("ObtenerDatosProducto")) {
				ObtenerDatosProducto(request, response);
			}	
			else if (metodo.equalsIgnoreCase("LoadCombo")) {
				LoadCombo(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void LoadCombo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<Marca_vo> listMarca = new ArrayList<Marca_vo>();
			List<Categoria_vo> listCategoria = new ArrayList<Categoria_vo>();
			List<Parametrizador_vo> listGenero = new ArrayList<Parametrizador_vo>();
			List<Parametrizador_vo> listEstado = new ArrayList<Parametrizador_vo>();
			Funcionalidad_vo funcionalidad = new Funcionalidad_vo();
			funcionalidad.setIdfuncionalidad(2);				
			
			Funcionalidad_vo funcionalidadEstado = new Funcionalidad_vo();
			funcionalidadEstado.setIdfuncionalidad(1);
			
			
			listMarca = new Marca_models().ListarMarcaCombo();			
			listCategoria = new Categoria_models().ListarCategoriaCombo();			
			listGenero= new Parametrizador_models().ListarValores(funcionalidad);
			listEstado= new Parametrizador_models().ListarValores(funcionalidadEstado);
			
			String jsonMarca = new Gson().toJson(listMarca);
			String jsonCategoria = new Gson().toJson(listCategoria);
			String jsonGenero = new Gson().toJson(listGenero);
			String jsonEstado = new Gson().toJson(listEstado);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
		
			String bothJson = "["+jsonMarca+","+jsonCategoria+","+jsonGenero+","+jsonEstado+"]";					
			response.getWriter().write(bothJson);
		} catch (Exception e) {
			System.out.println("Error en el metodo LoadCombo "
					+ e.getMessage());
		}

		
	}

	private void ObtenerDatosProducto(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Producto_vo  producto_vo= new Producto_vo();
			producto_vo.setCodigoproducto(request.getParameter("codproducto").trim());			
			producto_vo = new Producto_models().obtenerDatosProducto(producto_vo);			
			String json= new Gson().toJson(producto_vo);			
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+json+"]";				
			response.getWriter().write(bothJson);		
			
		} catch (Exception e) {
			System.out.println("Error en el metodo ObtenerDatosProducto : "+e.getMessage());
		}	
	}

	private void EliminarProducto(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String ind_respuesta= ""; 
			Producto_vo  producto_vo= new Producto_vo();
			producto_vo.setCodigoproducto(request.getParameter("codproducto"));			
			Map<String, String> Beanmap= new HashMap<String, String>();
			ind_respuesta = new Producto_models().EliminarProducto(producto_vo);
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
			System.out.println("Error en el metodo EliminarProducto : "+e.getMessage());
		}
		
	}

	private void TotalRegistrosProducto(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int TotalRegistro = 0;
			Producto_vo  objeto= new Producto_vo();
			objeto.setCodigoproducto(request.getParameter("txt_codigo_buscar"));
			objeto.setNombre(request.getParameter("txt_nombre_buscar"));
			
			Marca_vo marca = new Marca_vo();
			marca.setIdmarca(Integer.parseInt(request.getParameter("cbo_marca_buscar").equals("")? "0" :request.getParameter("cbo_marca_buscar")));
			Categoria_vo categoria = new Categoria_vo();
			categoria.setIdcategoria(Integer.parseInt(request.getParameter("cbo_categoria_buscar").equals("")? "0" :request.getParameter("cbo_categoria_buscar")));		
			Parametrizador_vo genero =  new Parametrizador_vo();
			genero.setValor(Integer.parseInt(request.getParameter("cbo_genero_buscar").equals("")? "0" :request.getParameter("cbo_genero_buscar")));			
			
			objeto.setMarca(marca);
			objeto.setCategoria(categoria);
			objeto.setGenero(genero);
			
			TotalRegistro  = new Producto_models().TotalRegistroProducto(objeto);
			Map<String, String> Beanmap= new HashMap<String, String>();
			Beanmap.put("TotalRegistro", String.valueOf(TotalRegistro));
			Gson gson= new GsonBuilder().setPrettyPrinting().create();
			String json= gson.toJson(Beanmap);
			response.getWriter().write(json);
		
		} catch (Exception e) {
			System.out.println("Error en el metodo TotalRegistrosProducto "
					+ e.getMessage());
		}
		
		
	}

	private void ListarProducto(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			
			Producto_vo  objeto= new Producto_vo();
			Paginador_vo paginador_vo= new Paginador_vo();
			List<Producto_vo> listproducto = new ArrayList<Producto_vo>();
			objeto.setCodigoproducto(request.getParameter("txt_codigo_buscar"));
			objeto.setNombre(request.getParameter("txt_nombre_buscar"));
			Marca_vo marca = new Marca_vo();
			marca.setIdmarca(Integer.parseInt(request.getParameter("cbo_marca_buscar").equals("")? "0" :request.getParameter("cbo_marca_buscar")));
			Categoria_vo categoria = new Categoria_vo();
			categoria.setIdcategoria(Integer.parseInt(request.getParameter("cbo_categoria_buscar").equals("")? "0" :request.getParameter("cbo_categoria_buscar")));		
			Parametrizador_vo genero =  new Parametrizador_vo();
			genero.setValor(Integer.parseInt(request.getParameter("cbo_genero_buscar").equals("")? "0" :request.getParameter("cbo_genero_buscar")));			
			objeto.setMarca(marca);
			objeto.setCategoria(categoria);
			objeto.setGenero(genero);
			paginador_vo.setLimit(Integer.parseInt(request.getParameter("limit")));
			paginador_vo.setOffset(Integer.parseInt(request.getParameter("offset")));
			objeto.setPaginador(paginador_vo);
			listproducto = new Producto_models().ListarProducto(objeto);
			String json= new Gson().toJson(listproducto);			
			response.setContentType("application/json"); 
			response.setCharacterEncoding("utf-8"); 
			String bothJson = "["+json+"]";				
			response.getWriter().write(bothJson);
		
		} catch (Exception e) {
			System.out.println("Error en el metodo ListarProducto "
					+ e.getMessage());
		}
		
	}

	private void RegistrarModificarProducto(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			
			Producto_vo  objeto= new Producto_vo();
			int indAccion = Integer.parseInt(request.getParameter("hiddenindaccion"));
			objeto.setNombre(request.getParameter("txt_nombre_guardar"));			
			objeto.setDescripcion(request.getParameter("txt_descripcion_guardar"));
			Parametrizador_vo estado = new Parametrizador_vo();
			estado.setValor(Integer.parseInt(request.getParameter("cbo_estado")));
			objeto.setEstado(estado);
			objeto.setPreciocompra(Double.parseDouble(request.getParameter("txt_preciocompra_guardar")));
			objeto.setPrecioventa(Double.parseDouble(request.getParameter("txt_precioventa_guardar")));
			Marca_vo marca = new Marca_vo();
			marca.setIdmarca(Integer.parseInt(request.getParameter("cbo_marca")));
			objeto.setMarca(marca);
			Categoria_vo categoria = new Categoria_vo();
			categoria.setIdcategoria(Integer.parseInt(request.getParameter("cbo_categoria")));
			objeto.setCategoria(categoria);
			Parametrizador_vo genero =  new Parametrizador_vo();
			genero.setValor(Integer.parseInt(request.getParameter("cbo_genero")));
			objeto.setGenero(genero);
			objeto.setTalla(request.getParameter("txt_talla_guardar"));

			String codigoproducto  = "";
			if (indAccion == 1) {
				Map<String, String> Beanmap= new HashMap<String, String>();
				codigoproducto = new Producto_models().RegistrarProducto(objeto);
				if(codigoproducto.equals("-1")){
					Beanmap.put("codigoproducto", String.valueOf("-1"));
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}else if(!codigoproducto.equals("-1")){
					Beanmap.put("codigoproducto", codigoproducto);
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}
			}else {
				objeto.setCodigoproducto(request.getParameter("hiddencodproducto"));
				Map<String, String> Beanmap= new HashMap<String, String>();
				codigoproducto = new Producto_models().ActualizarProducto(objeto);
				if(codigoproducto.equals("-1")){
					Beanmap.put("codigoproducto", String.valueOf("-1"));
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}else if(!codigoproducto.equals("-1")){
					Beanmap.put("codigoproducto", codigoproducto);
					Beanmap.put("indAccion", String.valueOf(indAccion));
					Gson gson= new GsonBuilder().setPrettyPrinting().create();
					String json= gson.toJson(Beanmap);
					response.getWriter().write(json);
				}
			}
			
		} catch (Exception e) {
			System.out.println("Error en el metodo RegistrarModificarProducto : "+e.getMessage());
		}
		
	}

}
