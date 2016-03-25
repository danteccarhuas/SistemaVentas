package mvc.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mvc.util.MysqlDBConexion;
import mvc.vo.Departamento_vo;
import mvc.vo.Distrito_vo;
import mvc.vo.Marca_vo;
import mvc.vo.Proveedor_vo;
import mvc.vo.Provincia_vo;
import mvc.vo.Ubigeo_vo;

public class Marca_models {

	public String RegistrarMarca(Marca_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_Ins_marca(?,?)";
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
	
	public String ActualizarMarca(Marca_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_UPD_marca(?,?)";
			cs = con.prepareCall(sql);
			cs.setString (1, bean.getDescripcion ());
			cs.setInt(2, bean.getIdmarca());					
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
	public String EliminarMarca(Marca_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_eliminar_marca(?)";
			cs = con.prepareCall(sql);
			cs.setInt(1, bean.getIdmarca());			
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
	
	
	public int TotalRegistroMarca(Marca_vo marca_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		int TotalRegistro = 0;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_TotaRegist_Marca(?,?)";
			cs=con.prepareCall(sql);
			cs.setString(1, marca_vo.getDescripcion());			
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
	
	public List<Marca_vo> ListarMarca(Marca_vo marca_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		List<Marca_vo> data= new ArrayList<Marca_vo>();
		Marca_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_Cons_Marca(?,?,?)";
			cs=con.prepareCall(sql);
			cs.setString(1, marca_vo.getDescripcion());			
			cs.setInt(2, marca_vo.getPaginador().getLimit());
			cs.setInt(3, marca_vo.getPaginador().getOffset());
			cs.execute();
			rs=cs.getResultSet();
			while(rs.next()){
				beans= new Marca_vo();
				beans.setIdmarca(rs.getInt("idmarca"));
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
	
	public Marca_vo obtenerDatosMarca(Marca_vo marca_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		
		Marca_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_obt_datosMarca(?)";
			cs=con.prepareCall(sql);
			cs.setInt(1, marca_vo.getIdmarca());			
			cs.execute();
			rs=cs.getResultSet();
			if(rs.next()){
				beans= new Marca_vo();
				beans.setIdmarca(rs.getInt("idmarca"));
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
