package mvc.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mvc.util.MysqlDBConexion;
import mvc.vo.Tienda_vo;
import mvc.vo.Trabajador_vo;

public class Trabajador_models {
	public int TotalRegistroTrabajadores(Trabajador_vo trabajador_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		int TotalRegistro = 0;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_TotaRegist_Trabajadores(?,?,?,?)";
			cs=con.prepareCall(sql);
			cs.setInt(1, trabajador_vo.getIdtrabajador());
			cs.setString(2, trabajador_vo.getNombres());
			cs.setString(3, trabajador_vo.getDni());
			cs.registerOutParameter(4, java.sql.Types.INTEGER);
			cs.execute();
			TotalRegistro = cs.getInt(4);
			
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
	
	public List<Trabajador_vo> ListarTrabajadores(Trabajador_vo trabajador_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		List<Trabajador_vo> data= new ArrayList<Trabajador_vo>();
		Trabajador_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_Cons_Trabajadores(?,?,?,?,?)";
			cs=con.prepareCall(sql);
			cs.setInt(1, trabajador_vo.getIdtrabajador());
			cs.setString(2, trabajador_vo.getNombres());
			cs.setString(3, trabajador_vo.getDni());
			cs.setInt(4, trabajador_vo.getPaginador().getLimit());
			cs.setInt(5, trabajador_vo.getPaginador().getOffset());
			cs.execute();
			rs=cs.getResultSet();
			while(rs.next()){
				beans= new Trabajador_vo();
				beans.setIdtrabajador(rs.getInt("idtrabajador"));
				beans.setNombres(rs.getString("nombres"));
				beans.setCorreo(rs.getString("correo"));
				beans.setDni(rs.getString("dni"));
				beans.setDireccion(rs.getString("direccion"));
				beans.setTelefono(rs.getString("telefono"));
				Tienda_vo tienda=new Tienda_vo();
				tienda.setDescripcion(rs.getString(""));
				beans.setTienda(tienda);
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
