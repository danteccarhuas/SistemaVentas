package mvc.vo;

public class Cliente_vo {

	
	private int idcliente;
	private String codigocliente;
	private String nombres;
	private String apellidos;
	private String correo;
	private String celular;
	private String telefono;
	private String fecha_creacion;
	private String fecha_modificacion;
	private Parametrizador_vo tipocliente;
	private String ruc;
	private String dni;
	private String estado;
	private Ubigeo_vo ubigeo;
	private Parametrizador_vo sexo;
	private Paginador_vo paginador;
	public int getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}
	public String getCodigocliente() {
		return codigocliente;
	}
	public void setCodigocliente(String codigocliente) {
		this.codigocliente = codigocliente;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public String getFecha_modificacion() {
		return fecha_modificacion;
	}
	public void setFecha_modificacion(String fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}
	public Parametrizador_vo getTipocliente() {
		return tipocliente;
	}
	public void setTipocliente(Parametrizador_vo tipocliente) {
		this.tipocliente = tipocliente;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Ubigeo_vo getUbigeo() {
		return ubigeo;
	}
	public void setUbigeo(Ubigeo_vo ubigeo) {
		this.ubigeo = ubigeo;
	}
	public Parametrizador_vo getSexo() {
		return sexo;
	}
	public void setSexo(Parametrizador_vo sexo) {
		this.sexo = sexo;
	}
	public Paginador_vo getPaginador() {
		return paginador;
	}
	public void setPaginador(Paginador_vo paginador) {
		this.paginador = paginador;
	}
	
	
	
}
