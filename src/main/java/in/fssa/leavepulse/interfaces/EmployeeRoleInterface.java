package in.fssa.leavepulse.interfaces;

import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.model.EmployeeRole;

public interface EmployeeRoleInterface extends Base<EmployeeRole> {
	
	public abstract void create(int employeeId, int managerId, int roleId) throws PersistenceException;

}
