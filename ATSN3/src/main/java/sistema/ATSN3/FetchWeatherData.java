package sistema.ATSN3;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class FetchWeatherData {
	
	private static final String API_URL = "https://historical-forecast-api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&start_date=%s&end_date=%s&hourly=temperature_2m";
	
	public FetchWeatherData() {
		
	}


	public void fetchAndProcessWeatherData(String city, String latitude, String longitude) throws Exception {
        String startDate = "2024-01-01";
        String endDate = "2024-01-31";
        String url = String.format(API_URL, latitude, longitude, startDate, endDate);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(URI.create(url))
                                         .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonResponse = new JSONObject(response.body());
        //System.out.println("Response JSON for " + city + ": " + jsonResponse.toString());
        new ProcessWeatherData().processWeatherData(city, jsonResponse);
    }
}
