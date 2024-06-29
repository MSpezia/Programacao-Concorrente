package sistema.ATSN3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProcessWeatherData {
	
	public ProcessWeatherData() {
		
	}
	
	public synchronized void processWeatherData(String city, JSONObject jsonData) {
        // Verifique se o objeto "hourly" existe
        if (!jsonData.has("hourly")) {
            System.out.println("No hourly data found for " + city);
            return;
        }

        JSONObject hourlyData = jsonData.getJSONObject("hourly");
        JSONArray temperatureArray = hourlyData.getJSONArray("temperature_2m");

        // Estrutura para armazenar as temperaturas por dia
        Map<String, List<Double>> dailyTemperatures = new LinkedHashMap<>();

        for (int i = 0; i < temperatureArray.length(); i++) {
            String datetime = hourlyData.getJSONArray("time").getString(i);
            String date = datetime.split("T")[0];
            double temp = temperatureArray.getDouble(i);

            dailyTemperatures.computeIfAbsent(date, k -> new ArrayList<>()).add(temp);
        }

        // Calcular média, mínima e máxima por dia
        Map<String, Double[]> dailyStats = new LinkedHashMap<>();
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
