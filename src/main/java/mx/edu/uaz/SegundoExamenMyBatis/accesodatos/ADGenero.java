package mx.edu.uaz.SegundoExamenMyBatis.accesodatos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.vaadin.ui.Notification;

import mx.edu.uaz.SegundoExamenMyBatis.config.PersistenciaSesion;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.*;

public class ADGenero {
	
	public ADGenero(){
		
	}
	
	public List<genero> obtenerTodos(){
		List<genero> generos =null;
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			generos = sesion.selectList("todosGeneros");
			
		} catch (Exception e) {
			Notification.show("Error al recuperar la lista de generos ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return generos;
	}

}
