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
import mvc.vo.Tipodocumento_vo;
import mvc.vo.Ubigeo_vo;

public class Tipodocumento_models {

	public String RegistrarTipodocumento(Tipodocumento_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_Ins_tipodocumento(?,?,?)";
			cs = con.prepareCall(sql);
			cs.setString(1, bean.getDescripcion());
			cs.setString(2, bean.getAbreviatura());
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
	
	public String ActualizarTipodocumento(Tipodocumento_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_UPD_tipodocumento(?,?,?)";
			cs = con.prepareCall(sql);
			cs.setString (1, bean.getDescripcion ());
			cs.setString(2, bean.getAbreviatura());
			cs.setInt(3, bean.getIdtipodocumento());					
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
	public String EliminarTipodocumento(Tipodocumento_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_eliminar_tipodocumento(?)";
			cs = con.prepareCall(sql);
			cs.setInt(1, bean.getIdtipodocumento());			
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
	
	
	public int TotalRegistroTipodocumento(Tipodocumento_vo tipodocumento_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		int TotalRegistro = 0;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_TotaRegist_tipodocumento(?,?)";
			cs=con.prepareCall(sql);
			cs.setString(1, tipodocumento_vo.getDescripcion());			
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
	
	public List<Tipodocumento_vo> ListarTipodocumento(Tipodocumento_vo tipodocumento_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		List<Tipodocumento_vo> data= new ArrayList<Tipodocumento_vo>();
		Tipodocumento_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_Cons_tipodocumento(?,?,?)";
			cs=con.prepareCall(sql);
			cs.setString(1, tipodocumento_vo.getDescripcion());			
			cs.setInt(2, tipodocumento_vo.getPaginador().getLimit());
			cs.setInt(3, tipodocumento_vo.getPaginador().getOffset());
			cs.execute();
			rs=cs.getResultSet();
			while(rs.next()){
				beans= new Tipodocumento_vo();
				beans.setIdtipodocumento(rs.getInt("idtipodocumento"));
				beans.setDescripcion(rs.getString("descripcion"));
				beans.setAbreviatura(rs.getString("abreviatura"));
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
	
	public Tipodocumento_vo obtenerDatosTipodocumento(Tipodocumento_vo tipodocumento_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		
		Tipodocumento_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_obt_datostipodocumento(?)";
			cs=con.prepareCall(sql);
			cs.setInt(1, tipodocumento_vo.getIdtipodocumento());			
			cs.execute();
			rs=cs.getResultSet();
			if(rs.next()){
				beans= new Tipodocumento_vo();
				beans.setIdtipodocumento(rs.getInt("idtipodocumento"));
				beans.setDescripcion(rs.getString("descripcion"));
				beans.setAbreviatura(rs.getString("abreviatura"));
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
