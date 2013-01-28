package de.flyingclaw.utils.plugins.deviantart;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ImageUtil {

	public BBCtoHTML_Parser parser = null;
	
	private String templink = "";
	private String link 	= "";
	private String quell 	= "";
	
	
	private String
	title 	= "N/A",
	desc	= "N/A",
	autor	= "N/A",
	profil	= "N/A",
	source	= "N/A";
	private int
	height 	= 0,
	width	= 0;
	
	
	//Constructor
	public ImageUtil(String link, BBCtoHTML_Parser p){
		this.parser = p;
		templink = link;
		try{
			setImageSource();
			sa();
		}catch(Exception e){
			System.out.println("Fehler!");
		}
		
	}
	
	

	
	
	public String format(String f){
		f = f.replaceAll("%source", getSource());
		f = f.replaceAll("%title", getTitel());
		f = f.replaceAll("%profil", getProfil());
		f = f.replaceAll("%desc", getDescription());
		f = f.replaceAll("%asource", getAbsoluteSource());
		f = f.replaceAll("%width", String.valueOf(getWidth()));
		f = f.replaceAll("%height", String.valueOf(getHeight()));
		f = f.replaceAll("%n", "\n");
		f = f.replaceAll("%autor", getAutor());
		return f;
	}
	
	
	/*private void setupAll() throws Exception {
		
		//Bild-Quelle setzen
		setIMGSource();
		//Autor setzen
		setAuthor();
		//Profil setzen
		setProfil();
		//Quellcode setten
		setSource();
		//Description setten
		setDescription();
		//Titel setzen
		setTitle();
		//Dimensionen setzen
		setDimensions();
	}*/
	
	
	
	
	
	
	
	private void sa() throws Exception{
		//Quellcode setten
		setSource();
		//Das Bild selbst
		setAbsoluteSource();
		//Setze Autor
		setAutor();
		//Profil setzen
		setProfil();
		//Description setzen
		setDescription();
		//Titel setzen
		setTitle();
		//Dimensionen setzen
		setDimensions();
	}
	
	private void setAutor(){
		if(quell.contains("\"pimp_deviation_artist\":\"")){
			autor = quell.split("\"pimp_deviation_artist\":\"")[1].split("\"")[0];
		}
	}
	
	
	private void setAbsoluteSource(){
		if(quell.contains("<meta name=\"og:image\" content=\"")){
			link = quell.split("<meta name=\"og:image\" content=\"")[1].split("\">")[0];
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	//Link
	public String getLink(){return link;}
	public void setLink(String l){this.link=l;}
	
	
	/**
	 *  GETTER 
	 */
	
	//Returns the Description of an Image
	public String getDescription(){
		return desc;
	}
	
	//Returns the Title of the Image
	public String getTitel(){
		return title;
	}
	
	//Returns the Author
	public String getAutor(){
		return autor;
	}
	
	//Returns the Profil-Link from the Author
	public String getProfil(){
		return profil;
	}
	
	//Returns the Link from the Image
	public String getSource(){
		return source;
	}
	
	//Returns the absolute Source
	public String getAbsoluteSource(){
		return link;
	}
	
	//Get the Dimensions
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	
	
	
	
	
	/**
	 * SETTER
	 */
	//Quellcode
	private void setSource() throws Exception{
		URLConnection urlc = new URL(getSource()).openConnection();
		InputStreamReader isr = new InputStreamReader(urlc.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		String line ="";
		 while((line = br.readLine()) != null){
			 quell = quell + "\n" + line;
		 }
		 br.close();
		 isr.close();
	}
	
	//Description
	private void setDescription() throws Exception{
		if(quell.contains("<div class=\"text-ctrl\">")){
			String start = quell.split("<div class=\"text-ctrl\">")[1];
			String start2= start.split("<div class=\"text block\">")[1];
			String end	= start2.split("</div")[0];
			desc = parser.parse(end);
			
		}
	}
	
	//Title
	private void setTitle() throws Exception{
		if(quell.contains("<title>")){
			title = quell.split("<title>")[1].split("</title>")[0].split(" by")[0];
			title = title.replaceAll("\\]", ")").replaceAll("\\[", "(");
		}
	}
	
	//Autor
	void setAuthor() throws Exception{
		autor = link.split("_by_")[1].split("-")[0];
	}
	
	//Profil
	private void setProfil() throws Exception{
		profil = "[url=http://" + getAutor() + ".deviantart.com]" + getAutor() + "[/url]";
	}
	
	//Bild-Link
	void setIMGSource() throws Exception{
		String png = link.split("_by_")[1].split("-")[1];
		source = "http://fav.me/" + png.split("\\.")[0];
	}
	
	
	private void setImageSource(){
		String last = templink.split("-")[templink.split("-").length - 1];
		if(last.contains(".")){
			source = "http://fav.me/" + last.split("\\.")[0];
		}else{
			source = templink;
		}
	}
	
	
	
	//Höhe & Breite
	private void setDimensions() throws Exception{
		width = Integer.valueOf(quell.split("\"deviation_width\":")[1].split(",")[0]);
		height= Integer.valueOf(quell.split("\"deviation_height\":")[1].split(",")[0]);
	}
	
	
	
	
	
	
}
