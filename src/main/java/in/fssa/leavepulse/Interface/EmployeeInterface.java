package in.fssa.leavepulse.Interface;

import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.model.Employee;

public interface EmployeeInterface extends Base<Employee> {
	
	public abstract int create(Employee employee) throws PersistenceException;

}
