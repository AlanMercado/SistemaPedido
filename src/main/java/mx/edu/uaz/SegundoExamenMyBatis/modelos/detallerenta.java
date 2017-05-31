package mx.edu.uaz.SegundoExamenMyBatis.modelos;

public class detallerenta {
	private int idDetalleRenta;
	private int idRenta;
	private int idPelicula;
	public detallerenta(){
		
	}
	public int getIdDetalleRenta() {
		return idDetalleRenta;
	}
	public void setIdDetalleRenta(int idDetalleRenta) {
		this.idDetalleRenta = idDetalleRenta;
	}
	public int getIdRenta() {
		return idRenta;
	}
	public void setIdRenta(int idRenta) {
		this.idRenta = idRenta;
	}
	public int getIdPelicula() {
		return idPelicula;
	}
	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}
	
}
