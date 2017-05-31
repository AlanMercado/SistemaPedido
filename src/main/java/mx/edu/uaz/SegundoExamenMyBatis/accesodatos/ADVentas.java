package mx.edu.uaz.SegundoExamenMyBatis.accesodatos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.vaadin.ui.Notification;

import mx.edu.uaz.SegundoExamenMyBatis.config.PersistenciaSesion;

import mx.edu.uaz.SegundoExamenMyBatis.modelos.Ventas;



public class ADVentas {
	public ADVentas(){
		
	}
	public List<Ventas> obtenerVentas(){
		boolean ok = false;
		List<Ventas> peliculas =null;
		
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			peliculas = sesion.selectList("todasVentas");
		} catch (Exception e) {
			Notification.show("Error al obtener la venta ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return peliculas;
	}
	
	public boolean guardar(Ventas venta){
		boolean ok = false;
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			sesion.insert("guardarVentas", venta);
			sesion.commit();
			ok = true;
		} catch (Exception e) {
			Notification.show("Error al guardar la venta ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return ok;
	}
	
}
