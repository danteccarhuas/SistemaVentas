package mvc.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mvc.util.MysqlDBConexion;
import mvc.vo.Parametrizador_vo;
import mvc.vo.Tienda_vo;

public class Tienda_models {

	public String RegistrarTienda(Tienda_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_Ins_tienda(?,?,?,?,?)";
			cs = con.prepareCall(sql);
			cs.setString(1, bean.getDescripcion());
			cs.setString(2, bean.getDireccion());
			cs.setString(3, bean.getTelefono());
			cs.setInt(4, bean.getEstado().getValor());
			cs.registerOutParameter(5, java.sql.Types.VARCHAR);
			cs.execute();
			codigo = cs.getString(5);

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
	
	public String ActualizarTienda(Tienda_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_UPD_tienda(?,?,?,?,?)";
			cs = con.prepareCall(sql);
			cs.setString(1, bean.getDescripcion());
			cs.setString(2, bean.getDireccion());
			cs.setString(3, bean.getTelefono());
			cs.setInt(4, bean.getEstado().getValor());	
			cs.setInt(5, bean.getIdtienda());		
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
	public String EliminarTienda(Tienda_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_eliminar_tienda(?)";
			cs = con.prepareCall(sql);
			cs.setInt(1, bean.getIdtienda());			
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
	
	
	public int TotalRegistroTienda(Tienda_vo tienda_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		int TotalRegistro = 0;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_TotaRegist_tienda(?,?)";
			cs=con.prepareCall(sql);
			cs.setString(1, tienda_vo.getDescripcion());			
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
	
	public List<Tienda_vo> ListarTienda(Tienda_vo tienda_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		List<Tienda_vo> data= new ArrayList<Tienda_vo>();
		Tienda_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_Cons_tienda(?,?,?)";
			cs=con.prepareCall(sql);
			cs.setString(1, tienda_vo.getDescripcion());			
			cs.setInt(2, tienda_vo.getPaginador().getLimit());
			cs.setInt(3, tienda_vo.getPaginador().getOffset());
			cs.execute();
			rs=cs.getResultSet();
			while(rs.next()){
				beans= new Tienda_vo();
				beans.setIdtienda(rs.getInt("idtienda"));
				beans.setDescripcion(rs.getString("Descripcion"));
				beans.setDireccion(rs.getString("Direccion"));				
				beans.setTelefono(rs.getString("telefono"));
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
	
	public Tienda_vo obtenerDatosTienda(Tienda_vo categoria_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;		
		Tienda_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_obt_datostienda(?)";
			cs=con.prepareCall(sql);
			cs.setInt(1, categoria_vo.getIdtienda());			
			cs.execute();
			rs=cs.getResultSet();
			if(rs.next()){
				beans= new Tienda_vo();
				beans.setIdtienda(rs.getInt("idtienda"));
				beans.setDescripcion(rs.getString("Descripcion"));
				beans.setDireccion(rs.getString("Direccion"));				
				beans.setTelefono(rs.getString("telefono"));
				Parametrizador_vo estado = new Parametrizador_vo();
				estado.setValor(rs.getInt("estado"));
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
	
	public List<Tienda_vo> CargarComboTienda(){
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		List<Tienda_vo> data= new ArrayList<Tienda_vo>();
		Tienda_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();
			String sql= "call usp_Sel_Tienda()";
			cs=con.prepareCall(sql);			
			cs.execute();
			rs=cs.getResultSet();
			while(rs.next()){
				beans= new Tienda_vo();
				beans.setIdtienda(rs.getInt("idtienda"));
				beans.setDescripcion(rs.getString("Descripcion"));
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
