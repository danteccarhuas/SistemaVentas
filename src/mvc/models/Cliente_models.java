package mvc.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mvc.util.MysqlDBConexion;
import mvc.vo.Categoria_vo;
import mvc.vo.Cliente_vo;

public class Cliente_models {

	public String RegistrarCliente(Cliente_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_Ins_Cliente(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			cs = con.prepareCall(sql);
			cs.setString(1, bean.getNombres());
			cs.setString(2, bean.getApellidos());
			cs.setString(3, bean.getCorreo());
			cs.setString(4, bean.getCelular());
			cs.setString(5, bean.getTelefono());
			cs.setString(6, bean.getDireccion());
			cs.setString(7, bean.getReferencia());
			cs.setInt(8, bean.getTipopersona().getValor());
			cs.setString(9, bean.getRuc());
			cs.setString(10, bean.getDni());
			cs.setInt(11, bean.getEstado().getValor());
			cs.setInt(12, bean.getUbigeo().getUbigeo());
			cs.setInt(13, bean.getSexo().getValor());
			cs.registerOutParameter(14, java.sql.Types.VARCHAR);
			cs.execute();
			codigo = cs.getString(14);

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
	
	public String ActualizarCliente(Cliente_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_Upd_Cliente(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			cs = con.prepareCall(sql);
			cs.setString(1, bean.getNombres());
			cs.setString(2, bean.getApellidos());
			cs.setString(3, bean.getCorreo());
			cs.setString(4, bean.getCelular());
			cs.setString(5, bean.getTelefono());
			cs.setString(6, bean.getDireccion());
			cs.setString(7, bean.getReferencia());
			cs.setInt(8, bean.getTipopersona().getValor());
			cs.setString(9, bean.getRuc());
			cs.setString(10, bean.getDni());
			cs.setInt(11, bean.getEstado().getValor());
			cs.setInt(12, bean.getUbigeo().getUbigeo());
			cs.setInt(13, bean.getSexo().getValor());
			cs.setString(14, bean.getCodigocliente());					
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
	public String EliminarCliente(Cliente_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_eliminar_categoria(?)";
			cs = con.prepareCall(sql);
			cs.setInt(1, bean.getIdcliente());			
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
	
	
	public int TotalRegistroCliente(Cliente_vo cliente_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		int TotalRegistro = 0;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_TotaRegist_categoria(?,?)";
			cs=con.prepareCall(sql);
			cs.setString(1, cliente_vo.getNombres());			
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
	
	public List<Cliente_vo> ListarCliente(Cliente_vo cliente_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		List<Cliente_vo> data= new ArrayList<Cliente_vo>();
		Cliente_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_Cons_categoria(?,?,?)";
			cs=con.prepareCall(sql);
			//cs.setInt(1, cliente_vo.getTipocliente().getValor());
			cs.setString(2, cliente_vo.getNombres());
			cs.setString(3, cliente_vo.getDni());
			cs.setString(4, cliente_vo.getRuc());			
			cs.setInt(5, cliente_vo.getPaginador().getLimit());
			cs.setInt(6, cliente_vo.getPaginador().getOffset());
			cs.execute();
			rs=cs.getResultSet();
			while(rs.next()){
				beans= new Cliente_vo();
				beans.setIdcliente(rs.getInt("idcategoria"));
				beans.setNombres(rs.getString("descripcion"));
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
	
	public Cliente_vo obtenerDatosCliente(Cliente_vo cliente_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		
		Cliente_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_obt_datoscategoria(?)";
			cs=con.prepareCall(sql);
			cs.setInt(1, cliente_vo.getIdcliente());			
			cs.execute();
			rs=cs.getResultSet();
			if(rs.next()){
				beans= new Cliente_vo();
				beans.setIdcliente(rs.getInt("idcategoria"));
				beans.setNombres(rs.getString("descripcion"));
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
