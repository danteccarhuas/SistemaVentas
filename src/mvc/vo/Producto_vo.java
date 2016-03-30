package mvc.vo;

public class Producto_vo {

	private int idproducto;
	private String codigoproducto;
	private String nombre;
	private String descripcion;
	private String talla;
	private double preciocompra;
	private double precioventa;
	private String fechacreacion;
	private String fechamodificacion;
	private Categoria_vo categoria;
	private Marca_vo marca;
	private Parametrizador_vo genero;
	private Parametrizador_vo estado;
	private Paginador_vo paginador;
	
	public String getCodigoproducto() {
		return codigoproducto;
	}
	public void setCodigoproducto(String codigoproducto) {
		this.codigoproducto = codigoproducto;
	}
	public String getTalla() {
		return talla;
	}
	public void setTalla(String talla) {
		this.talla = talla;
	}
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
	public Parametrizador_vo getGenero() {
		return genero;
	}
	public void setGenero(Parametrizador_vo genero) {
		this.genero = genero;
	}
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
	
	
	

 }
