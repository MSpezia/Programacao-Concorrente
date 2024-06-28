package sistema.ATSN3;

import java.util.List;

public class ExperimentThreeThreads extends Thread{
	
	private String[][] capitais;
	private long tempo = 0;


	public ExperimentThreeThreads(List<String[]> listaCapitais) {
		this.capitais = converteListaParaArray(listaCapitais);
	}
	
	private static String[][] converteListaParaArray(List<String[]> lista) {
        // Cria um array do tamanho da lista
        String[][] array = new String[lista.size()][];

        // Preenche o array com os elementos da lista
        for (int i = 0; i < lista.size(); i++) {
            array[i] = lista.get(i);
        }

        return array;
    }
	
	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < capitais.length; i++) {
            final int index = i;
                try {
                    String city = capitais[index][0];
                    String latitude = capitais[index][1];
                    String longitude = capitais[index][2];
                    System.out.printf("Fetching data for %s (Lat: %s, Lon: %s)%n", city, latitude, longitude);
                    new FetchWeatherData().fetchAndProcessWeatherData(city, latitude, longitude);
                } catch (Exception e) {
                    e.printStackTrace();
                };
        }
        
        
        long endTime = System.currentTimeMillis();
        setTempo(endTime - startTime);
        //System.out.println("Time taken with 3 thread: " + (endTime - startTime) + "ms");
	}
	
	public long getTempo() {
		return tempo;
	}

	public void setTempo(long tempo) {
		this.tempo = tempo;
	}
	
}
