package projeto;

public class Util {
	private Util() {
		// TODO Auto-generated constructor stub
	}
	
	public static String nome(String param, boolean classe) {
		if (param == null || param.isEmpty()) {
			return param;
		}
		String str = param.toLowerCase();
		StringBuilder sb = new StringBuilder();
		boolean underline = false;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '_') {
				underline = true;
			} else {
				if (i == 0 && classe) {
					sb.append(Character.toUpperCase(c));
				} else if (underline) {
					sb.append(Character.toUpperCase(c));
					underline = false;
				} else {
					sb.append(c);
				}
			}
		}

		return sb.toString();
	}
}
