package mvc.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mvc.util.MysqlDBConexion;
import mvc.vo.Tienda_vo;
import mvc.vo.Trabajador_vo;

public class Trabajador_models {
	
	public String RegistrarTrabajador(Trabajador_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_Ins_trabajador(?,?,?,?,?,?,?,?,?,?)";
			cs = con.prepareCall(sql);
			cs.setString(1, bean.getNombres());
			cs.setString(2, bean.getApellidos());
			cs.setString(3, bean.getCorreo());
			cs.setString(4, bean.getTelefono());
			cs.setString(5, bean.getDni());
			cs.setString(6, formato.format(bean.getFechanacimiento()));
			cs.setString(7, bean.getDireccion());
			cs.setInt(8, bean.getEstado());
			cs.setInt(9, bean.getTienda().getIdtienda());
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
	
	public String ActualizarTrabajador(Trabajador_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_UPD_trabajador(?,?,?,?,?,?,?,?,?,?)";
			cs = con.prepareCall(sql);
			cs.setString(1, bean.getCodigotrabajador());
			cs.setString(2, bean.getNombres());
			cs.setString(3, bean.getApellidos());
			cs.setString(4, bean.getCorreo());
			cs.setString(5, bean.getTelefono());
			cs.setString(6, bean.getDni());
			cs.setString(7, formato.format(bean.getFechanacimiento()));
			cs.setString(8, bean.getDireccion());
			cs.setInt(9, bean.getEstado());
			cs.setInt(10, bean.getTienda().getIdtienda());			
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
	
	public int TotalRegistroTrabajadores(Trabajador_vo trabajador_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		int TotalRegistro = 0;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_TotaRegist_Trabajadores(?,?,?,?)";
			cs=con.prepareCall(sql);
			cs.setString(1, trabajador_vo.getCodigotrabajador());
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
			cs.setString(1, trabajador_vo.getCodigotrabajador());
			cs.setString(2, trabajador_vo.getNombres());
			cs.setString(3, trabajador_vo.getDni());
			cs.setInt(4, trabajador_vo.getPaginador().getLimit());
			cs.setInt(5, trabajador_vo.getPaginador().getOffset());
			cs.execute();
			rs=cs.getResultSet();
			while(rs.next()){
				beans= new Trabajador_vo();
				beans.setCodigotrabajador(rs.getString("codigotrabajador"));
				beans.setNombres(rs.getString("trabajador"));
				beans.setCorreo(rs.getString("correo"));
				beans.setDni(rs.getString("dni"));
				beans.setDireccion(rs.getString("direccion"));
				beans.setTelefono(rs.getString("telefono"));
				Tienda_vo tienda=new Tienda_vo();
				tienda.setDescripcion(rs.getString("Descripcion"));
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
	
	public Trabajador_vo obtenerDatosTrabajador(Trabajador_vo trabajador_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
		Trabajador_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_obt_datosTrabajador(?)";
			cs=con.prepareCall(sql);
			cs.setString(1, trabajador_vo.getCodigotrabajador());			
			cs.execute();
			rs=cs.getResultSet();
			if(rs.next()){
				beans= new Trabajador_vo();
				beans.setCodigotrabajador(rs.getString("codigotrabajador"));
				beans.setNombres(rs.getString("nombres"));
				beans.setApellidos(rs.getString("apellidos"));
				beans.setCorreo(rs.getString("correo"));
				beans.setTelefono(rs.getString("telefono"));
				beans.setDni(rs.getString("dni"));
				beans.setFechanacimiento(formato.parse(rs.getString("fechanacimiento")));		
				beans.setDireccion(rs.getString("direccion"));
				beans.setEstado(rs.getInt("estado"));
				Tienda_vo tienda= new Tienda_vo();
				tienda.setIdtienda(rs.getInt("idtienda"));
				beans.setTienda(tienda);	

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
	
	public String EliminarTrabajador(Trabajador_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_eliminar_Trabajadores(?)";
			cs = con.prepareCall(sql);
			cs.setString(1, bean.getCodigotrabajador());			
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
}
