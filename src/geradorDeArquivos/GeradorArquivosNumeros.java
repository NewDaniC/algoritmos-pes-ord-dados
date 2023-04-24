package geradorDeArquivos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class GeradorArquivosNumeros {

    public static void main(String[] args) {
        int tamanho = 2500000; // Tamanho dos arquivos a serem gerados
        gerarArquivoOrdenado(tamanho, "ordenado.txt");
        gerarArquivoInvertido(tamanho, "invertido.txt");
        gerarArquivoAleatorio(tamanho, "aleatorio.txt");
    }

    public static void gerarArquivoOrdenado(int tamanho, String nomeArquivo) {
        ArrayList<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= tamanho; i++) {
            numeros.add(i);
        }
        try {
            FileWriter writer = new FileWriter(nomeArquivo);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (int numero : numeros) {
                bufferedWriter.write(numero + "\n");
            }
            bufferedWriter.close();
            writer.close();
            System.out.println("Arquivo " + nomeArquivo + " gerado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao gerar arquivo " + nomeArquivo);
            e.printStackTrace();
        }
    }

    public static void gerarArquivoInvertido(int tamanho, String nomeArquivo) {
        ArrayList<Integer> numeros = new ArrayList<>();
        for (int i = tamanho; i >= 1; i--) {
            numeros.add(i);
        }
        try {
            FileWriter writer = new FileWriter(nomeArquivo);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (int numero : numeros) {
                bufferedWriter.write(numero + "\n");
            }
            bufferedWriter.close();
            writer.close();
            System.out.println("Arquivo " + nomeArquivo + " gerado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao gerar arquivo " + nomeArquivo);
            e.printStackTrace();
        }
    }

    public static void gerarArquivoAleatorio(int tamanho, String nomeArquivo) {
        try {
            FileWriter writer = new FileWriter(nomeArquivo);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            Random random = new Random();
            HashSet<Integer> numerosGerados = new HashSet<>();
            while (numerosGerados.size() < tamanho) {
                int numeroAleatorio = random.nextInt(tamanho * 10);
                numerosGerados.add(numeroAleatorio);
            }
            ArrayList<Integer> numeros = new ArrayList<>(numerosGerados);
            Collections.shuffle(numeros);
            for (int numero : numeros) {
                bufferedWriter.write(numero + "\n");
            }
            bufferedWriter.close();
            writer.close();
            System.out.println("Arquivo " + nomeArquivo + " gerado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao gerar arquivo " + nomeArquivo);
            e.printStackTrace();
        }
    }
}
