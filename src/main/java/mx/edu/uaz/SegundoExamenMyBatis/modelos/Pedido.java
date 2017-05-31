package mx.edu.uaz.SegundoExamenMyBatis.modelos;

import java.sql.Date;

public class Pedido {
public int idPedido;
public Date fecha_pedido;
public Date fecha_entrega;
public int total;
public Materiales idMaterial;
public int estatus;
public int cantidad;
public String idUsuario;
private int alto;
private int ancho;
public Pedido(){
	
}
public int getIdPedido() {
	return idPedido;
}
public void setIdPedido(int idPedido) {
	this.idPedido = idPedido;
}

public Date getFecha_pedido() {
	return fecha_pedido;
}
public void setFecha_pedido(Date fecha_pedido) {
	this.fecha_pedido = fecha_pedido;
}
public Date getFecha_entrega() {
	return fecha_entrega;
}
public void setFecha_entrega(Date fecha_entrega) {
	this.fecha_entrega = fecha_entrega;
}
public int getTotal() {
	return total;
}
public void setTotal(int total) {
	this.total = total;
}
public Materiales getIdMaterial() {
	return idMaterial;
}
public void setIdMaterial(Materiales idMaterial) {
	this.idMaterial = idMaterial;
}
public int getEstatus() {
	return estatus;
}
public void setEstatus(int estatus) {
	this.estatus = estatus;
}
public int getCantidad() {
	return cantidad;
}
public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}
public String getIdUsuario() {
	return idUsuario;
}
public void setIdUsuario(String idUsuario) {
	this.idUsuario = idUsuario;
}
public int getAlto() {
	return alto;
}
public void setAlto(int alto) {
	this.alto = alto;
}
public int getAncho() {
	return ancho;
}
public void setAncho(int ancho) {
	this.ancho = ancho;
}

}
