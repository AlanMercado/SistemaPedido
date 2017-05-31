package mx.edu.uaz.SegundoExamenMyBatis.accesodatos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.vaadin.ui.Notification;

import mx.edu.uaz.SegundoExamenMyBatis.config.PersistenciaSesion;

import mx.edu.uaz.SegundoExamenMyBatis.modelos.renta;


public class ADRenta {

	public ADRenta(){
		
	}
	public boolean guardar(renta renta){
		boolean ok = false;
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			sesion.insert("guardarRenta", renta);
			sesion.commit();
			ok = true;
		} catch (Exception e) {
			Notification.show("Error al guardar la renta ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return ok;
	}
	
	public List<renta> obtenerTodos(){
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		List<renta> estados = null;
		try {
			estados = sesion.selectList("obtenerTodosRenta");
		} catch (Exception e) {
			Notification.show("No se puedieron recuperar las rentas de la BD "+e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return estados;
	}
}
