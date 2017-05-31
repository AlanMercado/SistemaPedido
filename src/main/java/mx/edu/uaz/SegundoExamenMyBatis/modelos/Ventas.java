package mx.edu.uaz.SegundoExamenMyBatis.modelos;

import java.sql.Date;

public class Ventas {
	public int idVenta;
	public String idMaterial;
	public int costo_total;
	public Date fecha_venta;
	public int idPedido;
	public Ventas(){
		
	}
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public String getIdMaterial() {
		return idMaterial;
	}
	public void setIdMaterial(String idMaterial) {
		this.idMaterial = idMaterial;
	}
	
	public int getCosto_total() {
		return costo_total;
	}
	public void setCosto_total(int costo_total) {
		this.costo_total = costo_total;
	}
	public Date getFecha_venta() {
		return fecha_venta;
	}
	public void setFecha_venta(Date fecha_venta) {
		this.fecha_venta = fecha_venta;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	
}
