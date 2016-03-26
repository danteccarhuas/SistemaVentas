package mvc.vo;

public class Categoria_vo {

	private int idcategoria;
	private String descripcion;
	private int estado;
	private String fecharegistro;
	private int fechamodificacion;
	private Paginador_vo paginador;
	public int getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
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
	public String getFecharegistro() {
		return fecharegistro;
	}
	public void setFecharegistro(String fecharegistro) {
		this.fecharegistro = fecharegistro;
	}
	public int getFechamodificacion() {
		return fechamodificacion;
	}
	public void setFechamodificacion(int fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}
	public Paginador_vo getPaginador() {
		return paginador;
	}
	public void setPaginador(Paginador_vo paginador) {
		this.paginador = paginador;
	}
	
		
}
