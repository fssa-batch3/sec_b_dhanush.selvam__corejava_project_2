package in.fssa.leavepulse;

import java.security.NoSuchAlgorithmException;
import in.fssa.leavepulse.exception.ServiceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.util.PasswordUtil;

public class app {

	public static void main(String[] args) throws ValidationException, ServiceException {
		
	 String password = "Aa!12345";
		
		String hash;
			try {
				hash = PasswordUtil.encryptPassword(password);
				System.out.println(hash);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
