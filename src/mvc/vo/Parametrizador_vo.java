package mvc.vo;

public class Parametrizador_vo {
	
	private int idparametrizador;
	private int idparamconsecutivo;
	private int valor;
	private String descripcion;
	private String simbolo;
	private int estado;
	private Funcionalidad_vo funcionalidad;
	
	
	public int getIdparametrizador() {
		return idparametrizador;
	}
	public void setIdparametrizador(int idparametrizador) {
		this.idparametrizador = idparametrizador;
	}
	public int getIdparamconsecutivo() {
		return idparamconsecutivo;
	}
	public void setIdparamconsecutivo(int idparamconsecutivo) {
		this.idparamconsecutivo = idparamconsecutivo;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
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
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Funcionalidad_vo getFuncionalidad() {
		return funcionalidad;
	}
	public void setFuncionalidad(Funcionalidad_vo funcionalidad) {
		this.funcionalidad = funcionalidad;
	}
	
	
	
}
