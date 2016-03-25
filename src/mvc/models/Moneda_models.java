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
import mvc.vo.Moneda_vo;
import mvc.vo.Proveedor_vo;
import mvc.vo.Provincia_vo;
import mvc.vo.Ubigeo_vo;

public class Moneda_models {

	public String RegistrarMoneda(Moneda_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_Ins_moneda(?,?,?)";
			cs = con.prepareCall(sql);
			cs.setString(1, bean.getDescripcion());
			cs.setString(2, bean.getSimbolo());
			cs.registerOutParameter(3, java.sql.Types.VARCHAR);
			cs.execute();
			codigo = cs.getString(3);

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
	
	public String ActualizarMoneda(Moneda_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_UPD_moneda(?,?,?)";
			cs = con.prepareCall(sql);
			cs.setString (1, bean.getDescripcion ());
			cs.setString(2, bean.getSimbolo());
			cs.setInt(3, bean.getIdmoneda());					
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
	public String EliminarMarca(Moneda_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_eliminar_moneda(?)";
			cs = con.prepareCall(sql);
			cs.setInt(1, bean.getIdmoneda());			
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
	
	
	public int TotalRegistroMarca(Moneda_vo moneda_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		int TotalRegistro = 0;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_TotaRegist_Marca(?,?)";
			cs=con.prepareCall(sql);
			cs.setString(1, moneda_vo.getDescripcion());			
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
	
	public List<Moneda_vo> ListarMarca(Moneda_vo moneda_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		List<Moneda_vo> data= new ArrayList<Moneda_vo>();
		Moneda_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_Cons_moneda(?,?,?)";
			cs=con.prepareCall(sql);
			cs.setString(1, moneda_vo.getDescripcion());			
			cs.setInt(2, moneda_vo.getPaginador().getLimit());
			cs.setInt(3, moneda_vo.getPaginador().getOffset());
			cs.execute();
			rs=cs.getResultSet();
			while(rs.next()){
				beans= new Moneda_vo();
				beans.setIdmoneda(rs.getInt("idmoneda"));
				beans.setDescripcion(rs.getString("descripcion"));
				beans.setSimbolo(rs.getString("simbolo"));
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
	
	public Moneda_vo obtenerDatosmoneda(Moneda_vo moneda_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		
		Moneda_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_obt_datosmoneda(?)";
			cs=con.prepareCall(sql);
			cs.setInt(1, moneda_vo.getIdmoneda());			
			cs.execute();
			rs=cs.getResultSet();
			if(rs.next()){
				beans= new Moneda_vo();
				beans.setIdmoneda(rs.getInt("idmoneda"));
				beans.setDescripcion(rs.getString("descripcion"));
				beans.setSimbolo(rs.getString("simbolo"));
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
