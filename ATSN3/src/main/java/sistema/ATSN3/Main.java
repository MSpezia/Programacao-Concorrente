package sistema.ATSN3;

import java.util.ArrayList;
import java.util.List;

public class Main {

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
            
        };
        
        long[] tempo = new long[4];
        // Versões do experimento
        tempo[0] = runExperiment(capitais, 1);
        tempo[1] = runExperiment(capitais, 3);
        tempo[2] = runExperiment(capitais, 9);
        tempo[3] = runExperiment(capitais, 27);
        
        for(int i = 0; i < 4; i++) {
        	System.out.println("Time taken with "+ (int)Math.pow(3, i) +" thread: " + tempo[i] + "ms");
        }
        
    }
    
    private static long runExperiment(String[][] capitais, int numeroThreads) { 
        List<String[]> listaCapitais = new ArrayList<String[]>();
		int index = 0;
		int quantidadeRequisicao = 27/numeroThreads;

		Threads[] threads = new Threads[numeroThreads];
        for (int i = 0; i < numeroThreads; i++) {
        	while(index < (quantidadeRequisicao * (i + 1))) {
        		listaCapitais.add(capitais[index]);
                index ++;
            }
            threads[i] = new Threads(listaCapitais);
            listaCapitais.clear();
        }
        for (Threads thread : threads) {
            thread.start();
        }
        for (Threads thread : threads) {
        	try {
                // Aguarda a thread terminar
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            };
        }
        long tempo = 0;
        for (Threads thread : threads) {
            tempo += thread.getTempo();
        }
        return tempo;
        
    }
    

    

    
}
