package metodosDeOrdenacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HeapSort {

    public static void main(String[] args) {
        String nomeArquivoEntrada = "aleatorio.txt";
        // String nomeArquivoEntrada = "invertido.txt";
        // String nomeArquivoEntrada = "ordenado.txt";
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
            heapSort(numeros);
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

    public static void heapSort(int[] numeros) {
        int n = numeros.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(numeros, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = numeros[0];
            numeros[0] = numeros[i];
            numeros[i] = temp;

            // call max heapify on the reduced heap
            heapify(numeros, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    static void heapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
}