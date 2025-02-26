import java.util.Scanner;

public class Exemplo15 {
    //Analise:Ainda não sei 
    //Entradas: os valores
    //Saidas: valor decomposto em menores notas
    //Processos: Decompor os valores que entram pelo minimo de notas possivel

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("valor:");
        double valor = teclado.nextInt();

        double notas100 = valor / 100;
        valor = valor % 100;

        double notas50 = valor / 50;
        valor = valor % 50;

        double notas20 = valor / 20;
        valor = valor % 20;

        double notas10 = valor / 10;
        valor = valor % 10;

        double notas5 = valor / 5;
        valor = valor % 5;

        double notas2 = valor / 2;
        valor = valor % 2;

        double moeda1 = valor / 1;
        valor = valor % 1;

        double moeda50 = valor / 0.50;
        valor = valor % 0.50;

        double moeda25 = valor / 0.25;
        valor = valor % 0.25;

        double moeda10 = valor / 0.10;
        valor = valor % 0.10;

        double moeda5 = valor / 0.5;
        valor = valor % 0.5;

        System.out.println("Notas de R$100:" + notas100);
        System.out.println("Notas de R$50:" + notas50);
        System.out.println("Notas de R$20:" + notas20);
        System.out.println("Notas de R$10:" + notas10);
        System.out.println("Notas de R$5:" + notas5);
        System.out.println("Notas de R$2:" + notas2);
        System.out.println("moedas de 1:" + moeda1);
        System.out.println("moedas de 50:" + moeda50);
        System.out.println("moedas de 25:" + moeda25);
        System.out.println("moedas de 10:" + moeda10);
        System.out.println("moedas de 5:" + moeda5);

        teclado.close();
    
    }  
      
}
