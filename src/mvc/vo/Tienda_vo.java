package mvc.vo;

import java.util.Date;

public class Tienda_vo {

	private int idtienda;
	private String descripcion;
	private String direccion;
	private String telefono;
	private Date fechacreacion;
	private Date fechamodificacion;
	private Paginador_vo paginador;
	private Parametrizador_vo estado;
	
	public Parametrizador_vo getEstado() {
		return estado;
	}
	public void setEstado(Parametrizador_vo estado) {
		this.estado = estado;
	}
	public Paginador_vo getPaginador() {
		return paginador;
	}
	public void setPaginador(Paginador_vo paginador) {
		this.paginador = paginador;
	}
	public int getIdtienda() {
		return idtienda;
	}
	public void setIdtienda(int idtienda) {
		this.idtienda = idtienda;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
