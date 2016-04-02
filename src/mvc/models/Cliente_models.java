package mvc.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mvc.util.MysqlDBConexion;
import mvc.vo.Categoria_vo;
import mvc.vo.Cliente_vo;
import mvc.vo.Departamento_vo;
import mvc.vo.Distrito_vo;
import mvc.vo.Parametrizador_vo;
import mvc.vo.Provincia_vo;
import mvc.vo.Ubigeo_vo;

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
			String sql = "call usp_eliminar_cliente(?)";
			cs = con.prepareCall(sql);
			cs.setString(1, bean.getCodigocliente());			
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
			String sql= "call usp_TotaRegist_Cliente(?,?,?,?,?,?)";
			cs=con.prepareCall(sql);
			cs.setString(1, cliente_vo.getCodigocliente());	
			cs.setString(2, cliente_vo.getNombres());
			cs.setInt(3, cliente_vo.getTipopersona().getValor());
			cs.setString(4, cliente_vo.getRuc());
			cs.setString(5, cliente_vo.getDni());
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
	
	public List<Cliente_vo> ListarCliente(Cliente_vo cliente_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		List<Cliente_vo> data= new ArrayList<Cliente_vo>();
		Cliente_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_Cons_Cliente(?,?,?,?,?,?,?)";
			cs=con.prepareCall(sql);
			cs.setString(1, cliente_vo.getCodigocliente());	
			cs.setString(2, cliente_vo.getNombres());
			cs.setInt(3, cliente_vo.getTipopersona().getValor());
			cs.setString(4, cliente_vo.getRuc());
			cs.setString(5, cliente_vo.getDni());		
			cs.setInt(6, cliente_vo.getPaginador().getLimit());
			cs.setInt(7, cliente_vo.getPaginador().getOffset());
			cs.execute();
			rs=cs.getResultSet();
			while(rs.next()){
				beans= new Cliente_vo();
				beans.setCodigocliente(rs.getString("codigocliente"));
				beans.setNombres(rs.getString("nombres") + " " +rs.getString("apellidos"));
				beans.setCorreo(rs.getString("correo"));
				beans.setCelular(rs.getString("celular"));
				Parametrizador_vo tipopersona= new Parametrizador_vo();
				tipopersona.setValor(rs.getInt("idtipocliente"));
				tipopersona.setDescripcion(rs.getString("descripTipocliente"));
				beans.setDni(rs.getString("dni"));
				beans.setRuc(rs.getString("ruc"));
				beans.setTipopersona(tipopersona);
				Parametrizador_vo genero= new Parametrizador_vo();
				genero.setValor(rs.getInt("idgenero"));
				genero.setDescripcion(rs.getString("descripgenero"));		
				beans.setSexo(genero);
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
			String sql= "call usp_obt_datosCliente(?)";
			cs=con.prepareCall(sql);
			cs.setString(1, cliente_vo.getCodigocliente());			
			cs.execute();
			rs=cs.getResultSet();
			if(rs.next()){
				beans= new Cliente_vo();
				beans.setCodigocliente(rs.getString("codigocliente"));
				beans.setNombres(rs.getString("nombres"));
				beans.setApellidos(rs.getString("apellidos"));
				beans.setCorreo(rs.getString("correo"));
				beans.setCelular(rs.getString("celular"));
				beans.setTelefono(rs.getString("telefono"));
				beans.setDireccion(rs.getString("direccion"));
				beans.setReferencia(rs.getString("referencia"));
				Parametrizador_vo tipopersona= new Parametrizador_vo();
				tipopersona.setValor(rs.getInt("idtipocliente"));
				beans.setDni(rs.getString("dni"));
				beans.setRuc(rs.getString("ruc"));
				beans.setTipopersona(tipopersona);
				Parametrizador_vo estado= new Parametrizador_vo();
				estado.setValor(rs.getInt("estado"));
				beans.setEstado(estado);				
				Parametrizador_vo genero= new Parametrizador_vo();
				genero.setValor(rs.getInt("idgenero"));
				beans.setSexo(genero);
				
				Departamento_vo beandepartamento = new Departamento_vo();
				beandepartamento.setIddepar(rs.getInt("iddepar"));
				
				Provincia_vo beanprovincia = new Provincia_vo();
				beanprovincia.setIdprov(rs.getInt("idprov"));
				
				Distrito_vo beandistrito = new Distrito_vo();
				beandistrito.setIddist(rs.getInt("iddist"));
				
				Ubigeo_vo ubigeo_vo =  new Ubigeo_vo();
				ubigeo_vo.setDepartamento(beandepartamento);
				ubigeo_vo.setProvincia(beanprovincia);
				ubigeo_vo.setDistrito(beandistrito);
				beans.setUbigeo(ubigeo_vo);
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
