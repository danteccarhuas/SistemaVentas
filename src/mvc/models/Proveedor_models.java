package mvc.models;

import java.sql.CallableStatement;
import java.sql.Connection;

import mvc.util.MysqlDBConexion;
import mvc.vo.Proveedor_vo;

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
}
