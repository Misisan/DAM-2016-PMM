package inicio;

public class Item {
	
	private Integer id;
	private String nombre;
	private Integer cantidad;
	
	public Item(String nombre, Integer cantidad) {
		this.nombre=nombre;
		this.cantidad=cantidad;
	}
	
	public Item(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
