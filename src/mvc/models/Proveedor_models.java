package mvc.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mvc.util.MysqlDBConexion;
import mvc.vo.Departamento_vo;
import mvc.vo.Distrito_vo;
import mvc.vo.Proveedor_vo;
import mvc.vo.Provincia_vo;
import mvc.vo.Ubigeo_vo;

public class Proveedor_models {

	public String RegistrarProveedor(Proveedor_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_Ins_proveedor(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			cs = con.prepareCall(sql);
			cs.setString(1, bean.getRazonsocial());
			cs.setString(2, bean.getCorreo());
			cs.setString(3, bean.getFax());
			cs.setString(4, bean.getTelefono());
			cs.setString(5, bean.getCelular());
			cs.setString(6, bean.getSitioweb());
			cs.setString(7, bean.getRuc());
			cs.setString(8, bean.getDireccion());
			cs.setString(9, bean.getReferencia());
			cs.setString(10, bean.getContacto());
			cs.setInt(11, bean.getEstado());
			cs.setInt(12, bean.getUbigeo().getUbigeo());
			cs.registerOutParameter(13, java.sql.Types.VARCHAR);
			cs.execute();
			codigo = cs.getString(13);

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
	
	public String EliminarProveedor(Proveedor_vo bean) {
		String codigo = "";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = MysqlDBConexion.getConexion();
			String sql = "call usp_eliminar_Proveedores(?)";
			cs = con.prepareCall(sql);
			cs.setString(1, bean.getCodigoproveedor());			
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
	
	
	public int TotalRegistroProveedores(Proveedor_vo proveedor_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		int TotalRegistro = 0;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_TotaRegist_Proveedores(?,?,?,?)";
			cs=con.prepareCall(sql);
			cs.setString(1, proveedor_vo.getCodigoproveedor());
			cs.setString(2, proveedor_vo.getRazonsocial());
			cs.setString(3, proveedor_vo.getRuc());
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
	
	public List<Proveedor_vo> ListarProveedores(Proveedor_vo proveedor_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		List<Proveedor_vo> data= new ArrayList<Proveedor_vo>();
		Proveedor_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_Cons_Proveedores(?,?,?,?,?)";
			cs=con.prepareCall(sql);
			cs.setString(1, proveedor_vo.getCodigoproveedor());
			cs.setString(2, proveedor_vo.getRazonsocial());
			cs.setString(3, proveedor_vo.getRuc());
			cs.setInt(4, proveedor_vo.getPaginador().getLimit());
			cs.setInt(5, proveedor_vo.getPaginador().getOffset());
			cs.execute();
			rs=cs.getResultSet();
			while(rs.next()){
				beans= new Proveedor_vo();
				beans.setCodigoproveedor(rs.getString("codigoproveedor"));
				beans.setRazonsocial(rs.getString("razonsocial"));
				beans.setRuc(rs.getString("ruc"));
				beans.setCorreo(rs.getString("correo"));
				beans.setTelefono(rs.getString("telefono"));
				beans.setDireccion(rs.getString("direccion"));
				beans.setContacto(rs.getString("contacto"));
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
	
	public Proveedor_vo obtenerDatosProveedor(Proveedor_vo proveedor_vo) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		
		Proveedor_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_obt_datosProveedor(?)";
			cs=con.prepareCall(sql);
			cs.setString(1, proveedor_vo.getCodigoproveedor());			
			cs.execute();
			rs=cs.getResultSet();
			if(rs.next()){
				beans= new Proveedor_vo();
				beans.setCodigoproveedor(rs.getString("codigoproveedor"));
				beans.setRazonsocial(rs.getString("razonsocial"));
				beans.setRuc(rs.getString("ruc"));
				beans.setCorreo(rs.getString("correo"));
				beans.setFax(rs.getString("fax"));
				beans.setTelefono(rs.getString("telefono"));
				beans.setCelular(rs.getString("celular"));		
				beans.setSitioweb(rs.getString("sitioweb"));
				beans.setDireccion(rs.getString("direccion"));
				beans.setReferencia(rs.getString("referencia"));
				beans.setEstado(rs.getInt("estado"));	
				beans.setContacto(rs.getString("contacto"));
				
				Departamento_vo beandepartamento = new Departamento_vo();
				beandepartamento.setIddepar(rs.getInt("iddepar"));
				
				Provincia_vo beanprovincia = new Provincia_vo();
				beandepartamento.setIddepar(rs.getInt("idprov"));
				
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
