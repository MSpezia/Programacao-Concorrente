package sistema.ATSN3;

import java.util.List;

public class Threads extends Thread{
	
	private String[][] capitais;
	private long tempo = 0;


	public Threads(List<String[]> listaCapitais) {
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

        
        for (int i = 0; i < capitais.length; i++) {
            final int index = i;
                try {
                	//separa a lista de capitais para mandar na requisição http
                    String city = capitais[index][0];
                    String latitude = capitais[index][1];
                    String longitude = capitais[index][2];
                    System.out.printf("Buscando data para %s (Lat: %s, Lon: %s)%n", city, latitude, longitude);
                    new FetchWeatherData().fetchAndProcessWeatherData(city, latitude, longitude);
                } catch (Exception e) {
                    e.printStackTrace();
                };
        }
        
        
	}
	
	public long getTempo() {
		return tempo;
	}

	public void setTempo(long tempo) {
		this.tempo = tempo;
	}
	
}
