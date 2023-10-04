package in.fssa.leavepulse.generator;

public class Generator {

	String alphabet = "abcdefghijklmnopqrstuvwyz";

	public String nameGenerator() {
		String name = "";

		for (int i = 0; i < 10; i++) {
			if (i == 0 || i == 6) {
				int alph = (int) (Math.random() * alphabet.length());
				char alp = alphabet.charAt(alph);
				char upperCaseAlp = Character.toUpperCase(alp);
				name += upperCaseAlp;
			} else if (i == 5) {
				name += " ";
			} else {
				int alph = (int) (Math.random() * alphabet.length());
				char alp = alphabet.charAt(alph);
				name += alp;
			}
		}
		return name;
	}

	public String emailGenerator() {
		String email = "";

		for (int i = 0; i < 10; i++) {
			if (i == 4) {
				email += ".";
			}
			int alph = (int) (Math.random() * alphabet.length());
			char alp = alphabet.charAt(alph);
			email += alp;
			if (i == 9) {
				email += "@gmail.com";
			}
		}
		return email;
	}

	public long numberGenenrator() {
		String nNum = "0123456789";
		String fNum = "9876";
		String num = "";
		for (int i = 0; i < 10; i++) {
			if (i == 0) {
				num += fNum.charAt((int) (Math.random() * fNum.length()));
			} else {
				num += nNum.charAt((int) (Math.random() * nNum.length()));
			}
		}
		long numericValue = Long.parseLong(num);
		return numericValue;
	}

}
