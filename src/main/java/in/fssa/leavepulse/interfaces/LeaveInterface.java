package in.fssa.leavepulse.interfaces;

import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.model.Leave;

public interface LeaveInterface extends Base<Leave> {

	public abstract void create(Leave leave) throws PersistenceException;
	
}
