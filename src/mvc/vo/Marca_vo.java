package mvc.vo;

public class Marca_vo {

	private int idmarca;
	private String descripcion;
	private Paginador_vo paginador;
	
	
	
	public Paginador_vo getPaginador() {
		return paginador;
	}
	public void setPaginador(Paginador_vo paginador) {
		this.paginador = paginador;
	}
	public int getIdmarca() {
		return idmarca;
	}
	public void setIdmarca(int idmarca) {
		this.idmarca = idmarca;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
