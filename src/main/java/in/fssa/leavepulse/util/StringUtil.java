package in.fssa.leavepulse.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			throw new ValidationException(inputName.concat(" cannot be null or empty"));
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
		
		if (input < 1) throw new ValidationException("Invalid ".concat(inputName));
		
	}
	
	/**
	 * 
	 * @param name
	 * @param inputName
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidName(String name, String inputName) throws ValidationException {

		String regex = "^[A-Za-z\\s]{3,24}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(name.trim());
		if (matcher.matches() == false)
			throw new ValidationException(inputName.concat(" must contain only alphabets with minimum 3 letters and spaces are allowed"));
		
	}


}
