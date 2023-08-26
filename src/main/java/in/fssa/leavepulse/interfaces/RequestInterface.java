package in.fssa.leavepulse.interfaces;

import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.model.Request;

public interface RequestInterface extends Base<Request> {

	public abstract void create(Request request) throws PersistenceException;

}