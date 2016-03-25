package mvc.vo;

public class Producto_vo {

	private int idproducto;
	private String nombre;
	private String descripcion;
	private int estado;
	private double preciocompra;
	private double precioventa;
	private String fechacreacion;
	private String fechamodificacion;
	private Categoria_vo categoria;
	private Marca_vo marca;
	private Parametrizador_vo parametrizador;
	
	public int getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public double getPreciocompra() {
		return preciocompra;
	}
	public void setPreciocompra(double preciocompra) {
		this.preciocompra = preciocompra;
	}
	public double getPrecioventa() {
		return precioventa;
	}
	public void setPrecioventa(double precioventa) {
		this.precioventa = precioventa;
	}
	public String getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(String fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public String getFechamodificacion() {
		return fechamodificacion;
	}
	public void setFechamodificacion(String fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}
	public Categoria_vo getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria_vo categoria) {
		this.categoria = categoria;
	}
	public Marca_vo getMarca() {
		return marca;
	}
	public void setMarca(Marca_vo marca) {
		this.marca = marca;
	}
	public Parametrizador_vo getParametrizador() {
		return parametrizador;
	}
	public void setParametrizador(Parametrizador_vo parametrizador) {
		this.parametrizador = parametrizador;
	}
	
	
	
}
