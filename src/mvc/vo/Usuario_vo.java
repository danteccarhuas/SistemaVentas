package mvc.vo;

import java.util.Date;

public class Usuario_vo {
	private int idusuario;
	private String usuario;
	private String password;
	private Date fechacreacion;
	private Date fechamodificacion;
	private Rol_vo rol;
	
	
	private Trabajador_vo trabajador;
	public Trabajador_vo getTrabajador() {
		return trabajador;
	}
	public void setTrabajador(Trabajador_vo trabajador) {
		this.trabajador = trabajador;
	}
	public int getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Rol_vo getRol() {
		return rol;
	}
	public void setRol(Rol_vo rol) {
		this.rol = rol;
	}
	
	
}
