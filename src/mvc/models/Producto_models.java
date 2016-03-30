package mvc.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mvc.util.MysqlDBConexion;
import mvc.vo.Categoria_vo;
import mvc.vo.Marca_vo;
import mvc.vo.Parametrizador_vo;
import mvc.vo.Producto_vo;

public class Producto_models {

	public String RegistrarProducto(Producto_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_Ins_producto(?,?,?,?,?,?,?,?,?,?)";
			cs = con.prepareCall(sql);
			cs.setString(1, bean.getNombre());
			cs.setString(2, bean.getDescripcion());
			cs.setInt(3, bean.getEstado().getValor());
			cs.setDouble(4, bean.getPreciocompra());
			cs.setDouble(5, bean.getPrecioventa());
			cs.setInt(6, bean.getMarca().getIdmarca());
			cs.setInt(7, bean.getCategoria().getIdcategoria());
			cs.setInt(8, bean.getGenero().getValor());
			cs.setString(9, bean.getTalla());
			cs.registerOutParameter(10, java.sql.Types.VARCHAR);
			cs.execute();
			codigo = cs.getString(10);

		} catch (Exception e) {
			e.printStackTrace();
			codigo = "-1";
		} finally {
			try {
				if (con != null)
					con.close();
				if (cs != null)
					cs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return codigo;
	}
	
	public String ActualizarProducto(Producto_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_UPD_producto(?,?,?,?,?,?,?,?,?,?)";
			cs = con.prepareCall(sql);
			cs.setString(1, bean.getNombre());
			cs.setString(2, bean.getDescripcion());
			cs.setInt(3, bean.getEstado().getValor());
			cs.setDouble(4, bean.getPreciocompra());
			cs.setDouble(5, bean.getPrecioventa());
			cs.setInt(6, bean.getMarca().getIdmarca());
			cs.setInt(7, bean.getCategoria().getIdcategoria());
			cs.setInt(8, bean.getGenero().getValor());
			cs.setString(9, bean.getTalla());			
			cs.setString(10, bean.getCodigoproducto());
			cs.execute();
			codigo = "1";	
		} catch (Exception e) {
			e.printStackTrace();
			codigo = "-1";
		} finally {
			try {
				if (con != null)
					con.close();
				if (cs != null)
					cs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return codigo;
	}
	public String EliminarProducto(Producto_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_eliminar_Producto(?)";
			cs = con.prepareCall(sql);
			cs.setString(1, bean.getCodigoproducto());			
			cs.execute();	
			codigo = "1";
		} catch (Exception e) {
			e.printStackTrace();
			codigo = "-1";
		} finally {
			try {
				if (con != null)
					con.close();
				if (cs != null)
					cs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return codigo;
	}
	
	
	public int TotalRegistroProducto(Producto_vo producto_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		int TotalRegistro = 0;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_TotaRegist_Producto(?,?,?,?,?,?)";
			cs=con.prepareCall(sql);
			cs.setString(1, producto_vo.getCodigoproducto());
			cs.setString(2, producto_vo.getNombre());		
			cs.setInt(3, producto_vo.getMarca().getIdmarca());		
			cs.setInt(4, producto_vo.getCategoria().getIdcategoria());
			cs.setInt(5, producto_vo.getGenero().getValor());		
			cs.registerOutParameter(6, java.sql.Types.INTEGER);
			cs.execute();
			TotalRegistro = cs.getInt(6);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(con!=null)  con.close();
				if(rs!=null) rs.close();
				if(cs!=null) cs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return TotalRegistro;
	}
	
	public List<Producto_vo> ListarProducto(Producto_vo producto_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		List<Producto_vo> data= new ArrayList<Producto_vo>();
		Producto_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_Cons_Producto(?,?,?,?,?,?,?)";
			cs=con.prepareCall(sql);
			cs.setString(1, producto_vo.getCodigoproducto());
			cs.setString(2, producto_vo.getNombre());		
			cs.setInt(3, producto_vo.getMarca().getIdmarca());		
			cs.setInt(4, producto_vo.getCategoria().getIdcategoria());
			cs.setInt(5, producto_vo.getGenero().getValor());					
			cs.setInt(6, producto_vo.getPaginador().getLimit());
			cs.setInt(7, producto_vo.getPaginador().getOffset());
			cs.execute();
			rs=cs.getResultSet();
			while(rs.next()){
				beans= new Producto_vo();
				beans.setCodigoproducto(rs.getString("codigoproducto"));
				beans.setNombre(rs.getString("nombre"));
				beans.setPreciocompra(rs.getDouble("preciocompra"));
				beans.setPrecioventa(rs.getDouble("precioventa"));
				beans.setTalla(rs.getString("talla"));
				
				Marca_vo marca = new Marca_vo();
				marca.setIdmarca(rs.getInt("idmarca"));
				marca.setDescripcion(rs.getString("descripmarca"));
				
				Categoria_vo categoria = new Categoria_vo();
				categoria.setIdcategoria(rs.getInt("idcategoria"));		
				categoria.setDescripcion(rs.getString("descripcategoria"));
				
				Parametrizador_vo genero =  new Parametrizador_vo();
				genero.setValor(rs.getInt("idgenero"));
				genero.setDescripcion(rs.getString("descripgenero"));
				
				beans.setMarca(marca);
				beans.setCategoria(categoria);
				beans.setGenero(genero);
				
				data.add(beans);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(con!=null)  con.close();
				if(rs!=null) rs.close();
				if(cs!=null) cs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return data;
	}
	
	public Producto_vo obtenerDatosProducto(Producto_vo producto_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		
		Producto_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_obt_datosProducto(?)";
			cs=con.prepareCall(sql);
			cs.setString(1, producto_vo.getCodigoproducto());			
			cs.execute();
			rs=cs.getResultSet();
			if(rs.next()){
				beans= new Producto_vo();
				
				beans.setCodigoproducto(rs.getString("codigoproducto"));
				beans.setNombre(rs.getString("nombre"));
				beans.setDescripcion(rs.getString("descripcion"));
				beans.setPreciocompra(rs.getDouble("preciocompra"));
				beans.setPrecioventa(rs.getDouble("precioventa"));
				beans.setTalla(rs.getString("talla"));
				
				Marca_vo marca = new Marca_vo();
				marca.setIdmarca(rs.getInt("idmarca"));
				marca.setDescripcion(rs.getString("descripmarca"));
				
				Categoria_vo categoria = new Categoria_vo();
				categoria.setIdcategoria(rs.getInt("idcategoria"));		
				categoria.setDescripcion(rs.getString("descripcategoria"));
				
				Parametrizador_vo genero =  new Parametrizador_vo();
				genero.setValor(rs.getInt("idgenero"));
				genero.setDescripcion(rs.getString("descripgenero"));
				
				Parametrizador_vo estado =  new Parametrizador_vo();
				estado.setValor(rs.getInt("estado"));
				
				beans.setMarca(marca);
				beans.setCategoria(categoria);
				beans.setGenero(genero);
				beans.setEstado(estado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(con!=null)  con.close();
				if(rs!=null) rs.close();
				if(cs!=null) cs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return beans;
	}
	
}
