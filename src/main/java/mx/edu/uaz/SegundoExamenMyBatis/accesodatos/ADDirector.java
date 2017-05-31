package mx.edu.uaz.SegundoExamenMyBatis.accesodatos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.vaadin.ui.Notification;

import mx.edu.uaz.SegundoExamenMyBatis.config.PersistenciaSesion;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.*;

public class ADDirector {
	
	public ADDirector(){
		
	}
	
	public List<director> obtenerTodos(){
		List<director> directores =null;
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			directores = sesion.selectList("todosDirectores");
			
		} catch (Exception e) {
			Notification.show("Error al recuperar la lista de directores ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return directores;
	}
	

}
