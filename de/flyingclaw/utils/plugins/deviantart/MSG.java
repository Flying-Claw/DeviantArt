package de.flyingclaw.utils.plugins.deviantart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class MSG {

	public static String
	input 		= get("Input_please"),
	convert		= get("convert"),
	error 		= get("Error"),
	exit		= get("P_Ended"),
	format		= get("Format");
	
	public static String get(String key){
		String value = null;
		try{
			File path = new File("Plugins/DeviantArt/Config.properties");
			if(! path.exists()){
				path.getParentFile().mkdir();
				path.createNewFile();
				addDefaults(path);
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(path))));
			String strLine;
			while((strLine = br.readLine()) != null) {
				if(strLine.split(":")[0].equalsIgnoreCase(key)){
					value = strLine.split(":")[1];
				}
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return (value == null)?"":value;
	}
	public static void addDefaults(File f){
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write("Input_please: Den zu konvertierenden String bitte eingeben..Exit beendet das Programm.");
			bw.newLine();
			bw.write("convert:Konvertieren? Wenn ja, tippe convert - Andernfalls geht es einfach weiter.");
			bw.newLine();
			bw.write("Error:Fehler beim einlesen!");
			bw.newLine();
			bw.write("P_Ended:Programm beendet.");
			bw.newLine();
			bw.write("Format:[spoiler=[url=%source]%title[/url] by %profil (%width x %height)][url=%asource][img]%asource[/img][/url][spoiler=Beschreibung][quote=%autor]%desc[/quote][/spoiler][/spoiler]");
			bw.newLine();
			bw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
