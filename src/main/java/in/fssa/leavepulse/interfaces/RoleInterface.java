package in.fssa.leavepulse.interfaces;

import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.model.Role;

public interface RoleInterface extends Base<Role> {

	public abstract void create(Role role) throws PersistenceException;

}