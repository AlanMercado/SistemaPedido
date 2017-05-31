package mx.edu.uaz.SegundoExamenMyBatis.accesodatos;

import org.apache.ibatis.session.SqlSession;

import com.vaadin.ui.Notification;

import mx.edu.uaz.SegundoExamenMyBatis.config.PersistenciaSesion;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.detallerenta;


public class ADDetallerenta {
	public ADDetallerenta(){
		
	}
	public boolean guardar(detallerenta renta){
		boolean ok = false;
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			sesion.insert("guardardetallerenta", renta);
			sesion.commit();
			ok = true;
		} catch (Exception e) {
			Notification.show("Error al guardar el detalle renta ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return ok;
	}
	
}
