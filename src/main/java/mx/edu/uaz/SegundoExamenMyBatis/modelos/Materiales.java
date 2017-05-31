package mx.edu.uaz.SegundoExamenMyBatis.modelos;

public class Materiales {
public int idMaterial;
public String nombre;
public String descripcion;
public int precio;
public int cantidad;
public String tipo;
public int precio_promo;
public String promo;
public Materiales(){
	
}

public int getCantidad() {
	return cantidad;
}

public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}

public int getIdMaterial() {
	return idMaterial;
}
public void setIdMaterial(int idMaterial) {
	this.idMaterial = idMaterial;
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
public int getPrecio() {
	return precio;
}
public void setPrecio(int precio) {
	this.precio = precio;
}
public String getTipo() {
	return tipo;
}
public void setTipo(String tipo) {
	this.tipo = tipo;
}

public int getPrecio_promo() {
	return precio_promo;
}

public void setPrecio_promo(int precio_promo) {
	this.precio_promo = precio_promo;
}

public String getPromo() {
	return promo;
}
public void setPromo(String promo) {
	this.promo = promo;
}

@Override
public String toString() {
	return this.nombre;
}

}
