package mvc.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mvc.util.MysqlDBConexion;
import mvc.vo.Funcionalidad_vo;
import mvc.vo.Parametrizador_vo;

public class Parametrizador_models {

	
	public List<Parametrizador_vo> ListarValores(Funcionalidad_vo objeto) {
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		List<Parametrizador_vo> data= new ArrayList<Parametrizador_vo>();
		Parametrizador_vo beans=null;
		try {
			con= MysqlDBConexion.getConexion();;
			String sql= "call usp_Sel_pametrizador(?)";
			cs=con.prepareCall(sql);
			cs.setInt(1, objeto.getIdfuncionalidad());				
			cs.execute();
			rs=cs.getResultSet();
			while(rs.next()){
				beans= new Parametrizador_vo();
				beans.setValor(rs.getInt("valor"));
				beans.setDescripcion(rs.getString("descripcion"));
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
