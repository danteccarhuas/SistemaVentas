package mvc.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mvc.util.MysqlDBConexion;
import mvc.vo.Menu_vo;
import mvc.vo.Submenu_vo;
import mvc.vo.Usuario_vo;

public class Menu_models {

	
	public ArrayList<Menu_vo> ListarMenu(Usuario_vo bean) {
		ArrayList<Menu_vo> listaDistinct = new ArrayList<Menu_vo>();
		ArrayList<Menu_vo> listMenu= new ArrayList<Menu_vo>();
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs;
		try {
			con = MysqlDBConexion.getConexion();
			String callprocedure = "call usp_Sel_Menu(?,?)";
			cs = con.prepareCall(callprocedure);	
			cs.setString(1,bean.getUsuario());
			cs.setString(2,bean.getPassword());
			cs.execute();
			rs= cs.getResultSet();
			Menu_vo menu= null;
			while(rs.next()){
				menu= new Menu_vo();
				menu.setIdmenu(rs.getInt("idmenu"));
				menu.setDescripcion(rs.getString("descripcion"));	
				menu.setTipografia(rs.getString("tipografia"));
				Submenu_vo submenu = new Submenu_vo();
				submenu.setIdsubmenu(rs.getInt("idsubmenu"));
				submenu.setDescripcion(rs.getString("descripcionsubmenu"));
				submenu.setUrl(rs.getString("url"));
				menu.setSubmenu(submenu);			
				listMenu.add(menu);
			}	
			/*Hacemos distincon en la lista de objetos*/
			Map<Integer, Menu_vo> mapMenu = new HashMap<Integer, Menu_vo>(listMenu.size());
			for (Menu_vo menuDTO : listMenu) {
				mapMenu.put(menuDTO.getIdmenu(), menuDTO);
			}
			for (java.util.Map.Entry<Integer, Menu_vo> p : mapMenu.entrySet()) {
				listaDistinct.add(p.getValue());			
			}
			
			/*Obtenemos los sub menu del menu princpal*/
			for (int i = 0; i < listaDistinct.size(); i++) {
				Menu_vo menuDTO =(Menu_vo)listaDistinct.get(i);	
				ArrayList<Submenu_vo> listSubMenu = new ArrayList<Submenu_vo>();
				for (int e = 0; e < listMenu.size(); e++) {						
					Submenu_vo submenu= null;
					if (menuDTO.getIdmenu()== listMenu.get(e).getIdmenu()){
						submenu = new Submenu_vo();
						submenu.setIdsubmenu(listMenu.get(e).getSubmenu().getIdsubmenu());
						submenu.setDescripcion(listMenu.get(e).getSubmenu().getDescripcion());
						submenu.setUrl(listMenu.get(e).getSubmenu().getUrl());
						listSubMenu.add(submenu);						
					}					
				}			
				listaDistinct.get(i).setListSubMenu(listSubMenu);
				/*for (int f = 0; f < listaDistinct.get(i).getListSubMenu().size(); f++) {
					Submenu_vo submenu = (Submenu_vo) listaDistinct.get(i).getListSubMenu().get(f);
					listaDistinct.get(i).getListSubMenu().get(f).setListSub_SubMenu(listSub_SubMenu(submenu));
				}*/
			}

	}catch(Exception e){
		e.printStackTrace();	
	}finally {
		try {
			if (cs != null)
				cs.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		return listaDistinct;
	}
	
	public String authenticate(String username, String password) {
		if (("prasad".equalsIgnoreCase(username))
				&& ("password".equals(password))) {
			return "success";
		} else {
			return "failure";
		}
	}
	
}
