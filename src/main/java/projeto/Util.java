/***
 * Classe com métodos de utilitários
 * @author Danilo Rocha
 * @author Heitor Lima
 * @author Jônatas Henrique
 */

package projeto;

import java.io.File;

public class Util {
	private Util() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Remove os _ e converte param para um padrão Java
	 * @param param String com o nome da coluna ou tabela que precisa ser tratado
	 * @param classe  -> se verdadeiro a primeira letra será maiúscula
	 * @return
	 */
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
	
	/**
	 * Exclui um diretorio e seus arquivos
	 * @param directory
	 */
	public static void deleteDir(File d) {
		if (d.exists() && d.isDirectory()) {
			File[] files = d.listFiles();
			for (File f : files) {
				if (f.isDirectory()) {
					deleteDir(f);
				} else {
					f.delete();
				}
			}
			d.delete();
		}
	}
	
	/**
	 * Cria um diretório se ele n existir com permissões 777 (se possível)
	 * @param d
	 */
	public static void criarDiretorio777(File d)
	{
		if(!d.exists())
		{
			d.mkdir();
			d.setReadable(true);
			d.setWritable(true);
			d.setExecutable(true);
		}
	}
}
