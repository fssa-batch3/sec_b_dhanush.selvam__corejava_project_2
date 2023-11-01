package in.fssa.leavepulse.service;

import java.util.List;

import in.fssa.leavepulse.dao.NotificationDAO;
import in.fssa.leavepulse.dto.NotificationDTO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ServiceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Notification;
import in.fssa.leavepulse.validator.EmployeeValidator;
import in.fssa.leavepulse.validator.NotificationValidator;

public class NotificationService {
	
//	public boolean checkNotificationIdIs(int notificationId) throws ServiceException, ValidationException {
//		
//		try {
//			NotificationDAO notificationDAO = new NotificationDAO();
//			NotificationValidator.validateNotificationId(notificationId);
//			return notificationDAO.checkNotificationIdIs(notificationId);
//			
//		} catch (PersistenceException e) {
//			e.printStackTrace();
//			throw new ServiceException(e.getMessage());
//		}
//		
//	}
	
	public List<NotificationDTO> findAllNotificationByEmployeeId(int employeeId) throws ServiceException, ValidationException {
		
		try {
			NotificationDAO notificationDAO = new NotificationDAO();
			EmployeeValidator.validateEmployeeId(employeeId);
			EmployeeValidator.checkEmployeeIdIs(employeeId);
			return notificationDAO.findAllNotificationByEmployeeId(employeeId);
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}	
	
	public void create(Notification notification) throws ServiceException, ValidationException {
		
		try {
			NotificationDAO notificationDAO = new NotificationDAO();
			NotificationValidator.validateNotification(notification);
			NotificationValidator.validateEmployeeId(notification.getSender(), "Sender");
			NotificationValidator.validateEmployeeId(notification.getReceiver(), "Receiver");
			NotificationValidator.validateMessage(notification.getMessage());
			NotificationValidator.checkEmployeeIdIs(notification.getSender(), "Sender");
			NotificationValidator.checkEmployeeIdIs(notification.getReceiver(), "Receiver");
			notificationDAO.create(notification);
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}	
	
	public void delete(int notificationId) throws ServiceException, ValidationException {
		
		try {
			NotificationDAO notificationDAO = new NotificationDAO();
			NotificationValidator.validateNotificationId(notificationId);
			NotificationValidator.checkNotificationIdIs(notificationId);
			notificationDAO.delete(notificationId);
		
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}
	
}
