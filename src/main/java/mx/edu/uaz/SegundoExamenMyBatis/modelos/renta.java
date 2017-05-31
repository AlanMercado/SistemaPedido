package mx.edu.uaz.SegundoExamenMyBatis.modelos;

import java.sql.Date;

public class renta {
	private int idRenta;
	private Date fecha;
	private int idUsuario;
	public renta(){
		
	}
	public int getIdRenta() {
		return idRenta;
	}
	public void setIdRenta(int idRenta) {
		this.idRenta = idRenta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
