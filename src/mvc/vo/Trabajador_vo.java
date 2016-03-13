package mvc.vo;

import java.util.Date;

public class Trabajador_vo {

	private int idtrabajador;
	private String nombre;
	private String apellidos;
	private String correo;
	private String telefono;
	private String dni;
	private Date fechanacimiento;
	private String direccion;
	private int estado;
	private String trabajadorbienvenida;
	
	
	

	public String getTrabajadorbienvenida() {
		return trabajadorbienvenida;
	}
	public void setTrabajadorbienvenida(String trabajadorbienvenida) {
		this.trabajadorbienvenida = trabajadorbienvenida;
	}
	private Tienda_vo tienda;
	public int getIdtrabajador() {
		return idtrabajador;
	}
	public void setIdtrabajador(int idtrabajador) {
		this.idtrabajador = idtrabajador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
