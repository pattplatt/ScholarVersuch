import org.jsoup.Jsoup;
	import org.jsoup.nodes.Document;
	import org.jsoup.nodes.Element;
	import org.jsoup.select.Elements;
	import java.util.Date;

	import java.io.FileWriter;
	import java.io.IOException;
	import java.nio.file.Files;
	import java.nio.file.Paths;
	import java.text.DateFormat;
	import java.text.SimpleDateFormat;

	//import javax.swing.text.Document;

	import com.google.gson.Gson;
	import com.google.gson.JsonIOException;
	import com.scraperapi.ScraperApiClient;
public class versuchNeu {
	
	static String search;
	static String time1;
	static String time2;
	
	static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    static Date date = new Date();
	
	public static void main(String[] args) throws JsonIOException, IOException {
		
		Gson gson = new Gson();
		// Enter your OWN ScraperAPI key here --------->
		ScraperApiClient client = new ScraperApiClient("ae454884f087b0cdf0e5b4f41012db51");
		
		//Search term and time frame
		search = "car";
		time1 = "2012";
		time2 = "2018";
		
		//Send request to Google Scholar
		String html =  client.get("https://scholar.google.com/scholar?hl=de&as_sdt=0%2C5&as_ylo=" + time1 + "&as_yhi=" + time2 + "&q=" + search +"&btnG=").result();		
		
		//export JSON to Desktop
		Document doc = Jsoup.parse(html);
		gson.toJson(html, new FileWriter("C:\\Users\\PC\\Desktop\\test.json"));
		
		//export HTML to Desktop
		Files.write(Paths.get("C:\\Users\\PC\\Desktop\\test5.html"), html.getBytes());
		
		//Extract search results from HTML file 
		Element links = doc.select("div.gs_ab_st").first();
		String text = links.text();
		//Print result
		System.out.println("Zwischen dem Zeitraum von " + time1 + " - " + time2 + " und dem Suchterm '" + search + "' gibt es " + text.substring(16, 50) + " " + dateFormat.format(date));

}}
