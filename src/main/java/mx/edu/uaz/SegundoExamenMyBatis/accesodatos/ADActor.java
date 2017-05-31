package mx.edu.uaz.SegundoExamenMyBatis.accesodatos;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.vaadin.ui.Notification;

import mx.edu.uaz.SegundoExamenMyBatis.config.PersistenciaSesion;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.actor;

public class ADActor {
	
	public ADActor(){
		
	}
	
	public Collection<String> obtenerTodos(){
	
		Collection<String> actores=null;
		
		
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			actores=sesion.selectList("todosActores");
		}catch(Exception e){
			Notification.show("Error al recuperar la lista de Peliculas ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
	
		finally{
			sesion.close();
			
		}
		return actores;
	}

}
