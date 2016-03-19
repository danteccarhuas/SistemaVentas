package mvc.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Menu_vo  implements Serializable {

	private int idmenu ;
	private String descripcion;
	private int estado;
	private Date fechacreacion;
	private Date fechamodificacion;
	private String tipografia;
	
	
	public String getTipografia() {
		return tipografia;
	}
	public void setTipografia(String tipografia) {
		this.tipografia = tipografia;
	}
	public ArrayList<Submenu_vo> getListSubMenu() {
		return listSubMenu;
	}
	public void setListSubMenu(ArrayList<Submenu_vo> listSubMenu) {
		this.listSubMenu = listSubMenu;
	}
	private Submenu_vo submenu;
	private ArrayList<Submenu_vo>listSubMenu;

	public Submenu_vo getSubmenu() {
		return submenu;
	}
	public void setSubmenu(Submenu_vo submenu) {
		this.submenu = submenu;
	}
	public int getIdmenu() {
		return idmenu;
	}
	public void setIdmenu(int idmenu) {
		this.idmenu = idmenu;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Date getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public Date getFechamodificacion() {
		return fechamodificacion;
	}
	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	
}
