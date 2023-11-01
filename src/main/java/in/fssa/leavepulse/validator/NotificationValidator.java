package in.fssa.leavepulse.validator;

import in.fssa.leavepulse.dao.EmployeeDAO;
import in.fssa.leavepulse.dao.NotificationDAO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Notification;
import in.fssa.leavepulse.util.StringUtil;

public class NotificationValidator {
	
	/**
	 * 
	 * @param notification
	 * @throws ValidationException
	 */
	public static void validateNotification(Notification notification) throws ValidationException {

		if (notification == null)
			throw new ValidationException("Notification cannot be null");
	}

	/**
	 * 
	 * @param notificationId
	 * @throws ValidationException
	 */
	public static void validateNotificationId(int notificationId) throws ValidationException {

		StringUtil.rejectIfInvalidId(notificationId, "Notification Id");

	}

	/**
	 * 
	 * @param requestId
	 * @throws ValidationException
	 */
	public static void checkNotificationIdIs(int notificationId) throws ValidationException {

		try {
			NotificationDAO notificationDAO = new NotificationDAO();
			if (notificationDAO.checkNotificationIdIs(notificationId) == false)
				throw new ValidationException("Notification Id not found");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

	}
	
	/**
	 * 
	 * @param employeeId
	 * @param userType
	 * @throws ValidationException
	 */
	public static void validateEmployeeId(int employeeId, String userType) throws ValidationException {

		StringUtil.rejectIfInvalidId(employeeId, userType + " Id");

	}

	/**
	 * 
	 * @param employeeId
	 * @param userType
	 * @throws ValidationException
	 */
	public static void checkEmployeeIdIs(int employeeId, String userType) throws ValidationException {

		try {
			EmployeeDAO employeeDAO = new EmployeeDAO();
			if (employeeDAO.findEmployeeByEmployeeId(employeeId) == null)
				throw new ValidationException(userType + " Id not found");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

	}
	
	public static void validateMessage(char message) throws ValidationException {
		
		if (!"ACNR".contains(message + ""))
			throw new ValidationException("Invalid Message");
		
	}

}
