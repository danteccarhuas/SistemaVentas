package mvc.vo;

import java.io.Serializable;
import java.util.Date;

public class Submenu_vo implements Serializable{

	private int idsubmenu;
	private String descripcion;
	private String url;
	private Date fechacreacion;
	private Date fechamodificacion;
	
	public int getIdsubmenu() {
		return idsubmenu;
	}
	public void setIdsubmenu(int idsubmenu) {
		this.idsubmenu = idsubmenu;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
