package mx.edu.uaz.SegundoExamenMyBatis.accesodatos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.vaadin.ui.Notification;

import mx.edu.uaz.SegundoExamenMyBatis.config.PersistenciaSesion;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.peliculaactores;

public class ADPeliculaActores {
	
	public ADPeliculaActores(){
		
	}
	
	
	public List<peliculaactores> obtenerTodos(){
		
		List<peliculaactores> actores=null;
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			actores = sesion.selectList("todosPeliculasActores");
			
		} catch (Exception e) {
			Notification.show("Error al recuperar la lista de actores ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return actores;
		
	}

}
