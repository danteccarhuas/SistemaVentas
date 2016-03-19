package mvc.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mvc.vo.Departamento_vo;
import mvc.vo.Distrito_vo;
import mvc.vo.Provincia_vo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mvc.util.MysqlDBConexion;



public class Ubigeo_models {
	
	public List<Departamento_vo> ListarDepartamento() {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		List<Departamento_vo> data= new ArrayList<Departamento_vo>();
		Departamento_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_Sel_Departamento";
			cs=con.prepareCall(sql);
			cs.execute();
			rs=cs.getResultSet();
			while(rs.next()){
				beans= new Departamento_vo();
				beans.setIddepar(rs.getInt("iddepar"));
				beans.setDepartamento(rs.getString("departamento"));
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
	
	
	public List<Provincia_vo> ListarProvincia(Departamento_vo bean) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		List<Provincia_vo> data= new ArrayList<Provincia_vo>();
		Provincia_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_Sel_Provincia(?)";
			cs=con.prepareCall(sql);
			cs.setInt(1, bean.getIddepar());
			cs.execute();
			rs=cs.getResultSet();
			while(rs.next()){
				beans= new Provincia_vo();
				beans.setIdprov(rs.getInt("idProv"));
				beans.setProvincia(rs.getString("provincia"));
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
	
	public List<Distrito_vo> ListarDistrito(Provincia_vo bean) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		List<Distrito_vo> data= new ArrayList<Distrito_vo>();
		Distrito_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_Sel_Distrito(?)";
			cs=con.prepareCall(sql);
			cs.setInt(1, bean.getIdprov());
			cs.execute();
			rs=cs.getResultSet();
			while(rs.next()){
				beans= new Distrito_vo();
				beans.setIddist(rs.getInt("idDist"));
				beans.setDistrito(rs.getString("distrito"));
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
}
