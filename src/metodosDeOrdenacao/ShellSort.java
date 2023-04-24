package metodosDeOrdenacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ShellSort {

    public static void main(String[] args) {
    	String nomeArquivoEntrada = "aleatorio.txt";
        //String nomeArquivoEntrada = "invertido.txt";
        //String nomeArquivoEntrada = "ordenado.txt";
        String nomeArquivoSaida = "arquivoSaida.txt";
        
        long t1 = System.currentTimeMillis();
        ordenarArquivoNumerosAleatorios(nomeArquivoEntrada, nomeArquivoSaida);
        long t2 = System.currentTimeMillis();
        
        System.out.println("Tempo de execução em ms: " + (t2 - t1));
        
    }

    public static void ordenarArquivoNumerosAleatorios(String nomeArquivoEntrada, String nomeArquivoSaida) {
        try {
            FileReader reader = new FileReader(nomeArquivoEntrada);
            BufferedReader bufferedReader = new BufferedReader(reader);
            int[] numeros = bufferedReader.lines().mapToInt(Integer::parseInt).toArray();
            shellSort(numeros);
            FileWriter writer = new FileWriter(nomeArquivoSaida);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (int numero : numeros) {
                bufferedWriter.write(numero + "\n");
            }
            bufferedWriter.close();
            writer.close();
            bufferedReader.close();
            reader.close();
            System.out.println("Numeros ordenados e salvos no arquivo " + nomeArquivoSaida + " com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao ordenar numeros do arquivo.");
            e.printStackTrace();
        }
    }

    public static void shellSort(int[] numeros) {
        int n = numeros.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && numeros[j] < numeros[j - h]; j -= h) {
                    int temp = numeros[j];
                    numeros[j] = numeros[j - h];
                    numeros[j - h] = temp;
                }
            }
            h /= 3;
        }
    }
}

