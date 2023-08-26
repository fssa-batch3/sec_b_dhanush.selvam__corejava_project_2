package in.fssa.leavepulse.interfaces;

import java.util.List; 

import in.fssa.leavepulse.exception.PersistenceException;

public interface Base<T> {
	
	public abstract List<T> getAll() throws PersistenceException;
	public abstract void update(int id, T object) throws PersistenceException;
	public abstract void delete(int id) throws PersistenceException;

}
