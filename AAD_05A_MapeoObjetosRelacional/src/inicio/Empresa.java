package inicio;

public class Empresa {

	private String cif;
	private String nombre;
	private Integer empleados;
	private String direccion;
	
	public Empresa(String nombre, String cif, Integer empleados, String direccion) {
		this.nombre=nombre;
		this.cif=cif;
		this.empleados=empleados;
		this.direccion=direccion;
	}
	
	public Empresa(){}
	
	public void setCIF(String cif){
		this.cif=cif;
	}
	
	public String getCIF(){
		return cif;
	}
	
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	
	public String getNombre(){
		return nombre;
	}

	public Integer getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Integer empleados) {
		this.empleados = empleados;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}
