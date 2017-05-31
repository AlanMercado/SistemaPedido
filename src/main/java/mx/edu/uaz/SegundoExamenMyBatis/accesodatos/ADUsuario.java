package mx.edu.uaz.SegundoExamenMyBatis.accesodatos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.vaadin.ui.Notification;

import mx.edu.uaz.SegundoExamenMyBatis.config.PersistenciaSesion;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.usuario;


public class ADUsuario {
	public ADUsuario(){
		
	}
	
	public List<usuario> obtenerTodos(){
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		List<usuario> estados = null;
		try {
			estados = sesion.selectList("obtenerUser");
		} catch (Exception e) {
			Notification.show("No se puedieron recuperar los usuarios de la BD "+e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return estados;
	}

}
