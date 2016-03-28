package mvc.vo;

import java.util.Date;

public class Trabajador_vo {

	private int idtrabajador;
	private String codigotrabajador;
	private String nombres;
	private String apellidos;
	private String correo;
	private String telefono;
	private String dni;
	private Date fechanacimiento;
	private String direccion;
	private int estado;
	private String trabajadorbienvenida;
	private Paginador_vo paginador;
	private Tienda_vo tienda;
	
	
	public String getCodigotrabajador() {
		return codigotrabajador;
	}
	public void setCodigotrabajador(String codigotrabajador) {
		this.codigotrabajador = codigotrabajador;
	}
	public Paginador_vo getPaginador() {
		return paginador;
	}
	public void setPaginador(Paginador_vo paginador) {
		this.paginador = paginador;
	}
	public String getTrabajadorbienvenida() {
		return trabajadorbienvenida;
	}
	public void setTrabajadorbienvenida(String trabajadorbienvenida) {
		this.trabajadorbienvenida = trabajadorbienvenida;
	}
	
	public int getIdtrabajador() {
		return idtrabajador;
	}
	public void setIdtrabajador(int idtrabajador) {
		this.idtrabajador = idtrabajador;
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Date getFechanacimiento() {
		return fechanacimiento;
	}
	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Tienda_vo getTienda() {
		return tienda;
	}
	public void setTienda(Tienda_vo tienda) {
		this.tienda = tienda;
	}
	
	
	
}
