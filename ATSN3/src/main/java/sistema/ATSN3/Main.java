package sistema.ATSN3;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    private static final String API_URL = "https://historical-forecast-api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&start_date=%s&end_date=%s&hourly=temperature_2m";

    public static void main(String[] args) throws Exception {
        // Lista de capitais com suas latitudes e longitudes
        String[][] capitais = {
        	{"Aracaju", "-10.9167" , "-37.05"},
        	{"Belém", "-1.4558" , "-48.5039"},
        	{"Belo Horizonte" , "-19.9167" , "-43.9333"},
        	{"Boa Vista" , "2.81972" , "-60.67333"},
        	{"Brasília" , "-15.7939" , "-47.8828"},
        	{"Campo Grande" , "-20.44278" , "-54.64639"},
        	{"Cuiabá" , "-15.5989" , "-56.0949"},
        	{"Curitiba" , "-25.4297" , "-49.2711"},
        	{"Florianópolis" , "-27.5935" , "-48.55854"},
        	{"Fortaleza" , "-3.7275" , "-38.5275"},
        	{"Goiânia" , "-16.6667" , "-49.25"},
        	{"João Pessoa" , "-7.12" , "-34.88"},
        	{"Macapá" , "0.033" , "-51.05"},
        	{"Maceió" , "-9.66583" , "-35.73528"},
        	{"Manaus" , "-3.1189" , "-60.0217"},
        	{"Natal" , "-5.7833" , "-35.2"},
        	{"Palmas" , "-10.16745" , "-48.32766"},
        	{"Porto Alegre" , "-30.0331" , "-51.23"},
        	{"Porto Velho" , "-8.76194" , "-63.90389"},
        	{"Recife" , "-8.05" , "-34.9"},
        	{"Rio Branco" , "-9.97472" , "-67.81"},
            {"Rio de Janeiro", "-22.9111", "-43.2056"},
            {"Salvador" , "-12.9747" , "-38.4767"},
            {"São Luís" , "-2.5283" , "-44.3044"},
            {"São Paulo", "-23.55" , "-46.6333"},
            {"Teresina" , "-5.08917" , "-42.80194"},
            {"Vitória" , "-20.2889" , "-40.3083"}
            
            // Adicione as outras 25 capitais aqui...
        };

        // Versões do experimento
        runExperiment(capitais);
    }

    private static void runExperiment(String[][] capitais) throws Exception {
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < capitais.length; i++) {
            final int index = i;
                try {
                    String city = capitais[index][0];
                    String latitude = capitais[index][1];
                    String longitude = capitais[index][2];
                    System.out.printf("Fetching data for %s (Lat: %s, Lon: %s)%n", city, latitude, longitude);
                    fetchAndProcessWeatherData(city, latitude, longitude);
                } catch (Exception e) {
                    e.printStackTrace();
                };
        }
        
        
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken with 1 thread: " + (endTime - startTime) + "ms");
    }

    private static void fetchAndProcessWeatherData(String city, String latitude, String longitude) throws Exception {
        String startDate = "2024-01-01";
        String endDate = "2024-01-31";
        String url = String.format(API_URL, latitude, longitude, startDate, endDate);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(URI.create(url))
                                         .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonResponse = new JSONObject(response.body());
        System.out.println("Response JSON for " + city + ": " + jsonResponse.toString());
        processWeatherData(city, jsonResponse);
    }

    private static void processWeatherData(String city, JSONObject jsonData) {
        // Verifique se o objeto "hourly" existe
        if (!jsonData.has("hourly")) {
            System.out.println("No hourly data found for " + city);
            return;
        }

        JSONObject hourlyData = jsonData.getJSONObject("hourly");
        JSONArray temperatureArray = hourlyData.getJSONArray("temperature_2m");

        // Estrutura para armazenar as temperaturas por dia
        Map<String, List<Double>> dailyTemperatures = new HashMap<>();

        for (int i = 0; i < temperatureArray.length(); i++) {
            String datetime = hourlyData.getJSONArray("time").getString(i);
            String date = datetime.split("T")[0];
            double temp = temperatureArray.getDouble(i);

            dailyTemperatures.computeIfAbsent(date, k -> new ArrayList<>()).add(temp);
        }

        // Calcular média, mínima e máxima por dia
        Map<String, Double[]> dailyStats = new HashMap<>();
        for (String date : dailyTemperatures.keySet()) {
            List<Double> temps = dailyTemperatures.get(date);
            double min = Collections.min(temps);
            double max = Collections.max(temps);
            double avg = temps.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

            dailyStats.put(date, new Double[]{avg, min, max});
        }

        // Exibir os dados no console
        for (String date : dailyStats.keySet()) {
            Double[] stats = dailyStats.get(date);
            System.out.printf("%s - %s: Média = %.2f, Mínima = %.2f, Máxima = %.2f%n", city, date, stats[0], stats[1], stats[2]);
        }
    }
}
