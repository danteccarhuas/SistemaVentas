package mvc.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mvc.util.MysqlDBConexion;
import mvc.vo.Tienda_vo;
import mvc.vo.Trabajador_vo;
import mvc.vo.Usuario_vo;

public class Usuario_models {

	public Usuario_vo ValidarUsuario(Usuario_vo bean) {
		Usuario_vo beans = null;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs;
		try {
			con = MysqlDBConexion.getConexion();
			String callprocedure = "call usp_Sel_validarusuario(?,?)";
			cs = con.prepareCall(callprocedure);	
			cs.setString(1,bean.getUsuario());
			cs.setString(2,bean.getPassword());
			cs.execute();
			rs= cs.getResultSet();
			
			if(rs.next()){	
				beans= new Usuario_vo();
				Trabajador_vo trabajador = new Trabajador_vo();
				trabajador.setIdtrabajador(rs.getInt("idtrabajador"));
				trabajador.setNombre(rs.getString("nombres"));				
				trabajador.setEstado(rs.getInt("estado"));
				trabajador.setTrabajadorbienvenida(rs.getString("Bienvenido"));
				Tienda_vo beantienda = new Tienda_vo();
				beantienda.setIdtienda(rs.getInt("idtienda"));				
				trabajador.setTienda(beantienda);				
				beans.setTrabajador(trabajador);				
			}	

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cs != null)
					cs.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return beans;
	}

}
