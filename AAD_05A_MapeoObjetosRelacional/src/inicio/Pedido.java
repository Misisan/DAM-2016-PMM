package inicio;

import java.sql.Date;

public class Pedido {

	private Integer id;
	private Date fecha;
	
	public Pedido(Date fecha) {
		this.fecha=fecha;
	}
	
	public Pedido(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
