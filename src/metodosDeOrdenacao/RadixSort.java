package metodosDeOrdenacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RadixSort {

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
            radixSort(numeros);
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

    public static void radixSort(int[] numeros) {
        int maior = encontraMaior(numeros);
        for (int exp = 1; maior / exp > 0; exp *= 10) {
            countSort(numeros, exp);
        }
    }

    public static void countSort(int[] numeros, int exp) {
        int n = numeros.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            int digito = (numeros[i] / exp) % 10;
            count[digito]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digito = (numeros[i] / exp) % 10;
            output[count[digito] - 1] = numeros[i];
            count[digito]--;
        }

        for (int i = 0; i < n; i++) {
            numeros[i] = output[i];
        }
    }

    public static int encontraMaior(int[] numeros) {
        int maior = numeros[0];
        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i] > maior) {
                maior = numeros[i];
            }
        }
        return maior;
    }
}
