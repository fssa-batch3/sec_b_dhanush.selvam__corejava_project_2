package in.fssa.leavepulse.util;

import in.fssa.leavepulse.exception.ValidationException;

public class StringUtil {
	
	/**
	 * 
	 * @param input
	 * @param inputName
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidString(String input, String inputName) throws ValidationException {

		if (input == null || "".equals(input.trim())) {
			throw new ValidationException(inputName.concat(" cannot be Null or Empty"));
		}

	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isValidString(String input) {
		if (input == null && "".equals(input)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isInvalidString(String input) {
		if (StringUtil.isValidString(input)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param input
	 * @param inputName
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidId(int input, String inputName) throws ValidationException {
		
		if (input < 1) throw new ValidationException(inputName.concat(" is invalid"));
		
	}


}
