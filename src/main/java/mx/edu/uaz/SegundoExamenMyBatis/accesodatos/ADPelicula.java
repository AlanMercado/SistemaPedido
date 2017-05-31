package mx.edu.uaz.SegundoExamenMyBatis.accesodatos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Grid.Column;

import mx.edu.uaz.SegundoExamenMyBatis.config.PersistenciaSesion;
import mx.edu.uaz.SegundoExamenMyBatis.modelos.pelicula;


public class ADPelicula {
	public ADPelicula(){
		
	}
	
	public boolean guardar(pelicula pelicula){
		boolean ok = false;
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			sesion.insert("guardarPelicula", pelicula);
			sesion.commit();
			ok = true;
		} catch (Exception e) {
			Notification.show("Error al guardar pelicula ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return ok;
	}
	
	
	public List<pelicula> obtenerRentadas(int pelicula){
		boolean ok = false;
		List<pelicula> peliculas =null;
		
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			peliculas = sesion.selectList("obtenPeliculasRentadas",pelicula);
		} catch (Exception e) {
			Notification.show("Error al guardar el usuario ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return peliculas;
	}
	
	public boolean actualizar(pelicula pelicula){
		boolean ok = false;
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			
			sesion.insert("actualizarPelicula", pelicula);
			sesion.commit();
			ok = true;
		} catch (Exception e) {
			Notification.show("Error al Actualizar la Pelicula ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return ok;
	}
	public boolean eliminar( List<Integer> pelicula){
		
		boolean ok = false;
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			sesion.delete("borrarPelicula", pelicula);
			sesion.commit();
			ok = true;
		} catch (Exception e) {
			Notification.show("Error al borrar la pelicula ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return ok;
	}
	
	public List<pelicula> obtenerTodos(){
		List<pelicula> peliculas =null;
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			peliculas = sesion.selectList("todosPeliculas");
			
		} catch (Exception e) {
			Notification.show("Error al recuperar la lista de Peliculas ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return peliculas;
	}
	
	public Collection<String> obtenerTodos2(){
		Collection<String> peliculas =null;
		SqlSession sesion = PersistenciaSesion.getSqlMapper().openSession();
		try {
			peliculas = sesion.selectList("todosPeliculas");
			
		} catch (Exception e) {
			Notification.show("Error al recuperar la lista de Peliculas ", e.getCause().getMessage(),Notification.Type.ERROR_MESSAGE);
		}
		finally {
			sesion.close();
		}
		return peliculas;
	}
}

