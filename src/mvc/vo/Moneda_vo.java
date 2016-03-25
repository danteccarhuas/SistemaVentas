package mvc.vo;

public class Moneda_vo {

	private int idmoneda;
	private String descripcion;
	private String simbolo;
	private Paginador_vo paginador;
	
	public int getIdmoneda() {
		return idmoneda;
	}
	public void setIdmoneda(int idmoneda) {
		this.idmoneda = idmoneda;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getSimbolo() {
		return simbolo;
	}
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	public Paginador_vo getPaginador() {
		return paginador;
	}
	public void setPaginador(Paginador_vo paginador) {
		this.paginador = paginador;
	}

}
