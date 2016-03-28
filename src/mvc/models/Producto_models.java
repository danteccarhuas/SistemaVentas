package mvc.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mvc.util.MysqlDBConexion;
import mvc.vo.Categoria_vo;

public class Producto_models {

	public String RegistrarCategoria(Categoria_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_Ins_categoria(?,?)";
			cs = con.prepareCall(sql);
			cs.setString(1, bean.getDescripcion());
			cs.registerOutParameter(2, java.sql.Types.VARCHAR);
			cs.execute();
			codigo = cs.getString(2);

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
	
	public String ActualizarCategoria(Categoria_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_UPD_categoria(?,?)";
			cs = con.prepareCall(sql);
			cs.setString (1, bean.getDescripcion ());
			cs.setInt(2, bean.getIdcategoria());					
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
	public String EliminarCategoria(Categoria_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_eliminar_categoria(?)";
			cs = con.prepareCall(sql);
			cs.setInt(1, bean.getIdcategoria());			
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
	
	
	public int TotalRegistroCategoria(Categoria_vo categoria_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		int TotalRegistro = 0;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_TotaRegist_categoria(?,?)";
			cs=con.prepareCall(sql);
			cs.setString(1, categoria_vo.getDescripcion());			
			cs.registerOutParameter(2, java.sql.Types.INTEGER);
			cs.execute();
			TotalRegistro = cs.getInt(2);
			
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
	
	public List<Categoria_vo> ListarCategoria(Categoria_vo categoria_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		List<Categoria_vo> data= new ArrayList<Categoria_vo>();
		Categoria_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_Cons_categoria(?,?,?)";
			cs=con.prepareCall(sql);
			cs.setString(1, categoria_vo.getDescripcion());			
			cs.setInt(2, categoria_vo.getPaginador().getLimit());
			cs.setInt(3, categoria_vo.getPaginador().getOffset());
			cs.execute();
			rs=cs.getResultSet();
			while(rs.next()){
				beans= new Categoria_vo();
				beans.setIdcategoria(rs.getInt("idcategoria"));
				beans.setDescripcion(rs.getString("descripcion"));
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
	
	public Categoria_vo obtenerDatosCategoria(Categoria_vo categoria_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		
		Categoria_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_obt_datoscategoria(?)";
			cs=con.prepareCall(sql);
			cs.setInt(1, categoria_vo.getIdcategoria());			
			cs.execute();
			rs=cs.getResultSet();
			if(rs.next()){
				beans= new Categoria_vo();
				beans.setIdcategoria(rs.getInt("idcategoria"));
				beans.setDescripcion(rs.getString("descripcion"));
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
