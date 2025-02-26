import java.util.Scanner;

public class Exemplo14 {
    //Analise:Ainda não sei 
    //Entradas: os valores
    //Saidas: valor decomposto em menores notas
    //Processos: Decompor os valores que entram pelo minimo de notas possivel

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("valor:");
        int valor = teclado.nextInt();

        int notas100 = valor / 100;
        valor = valor % 100;

        int notas50 = valor / 50;
        valor = valor % 50;

        int notas20 = valor / 20;
        valor = valor % 20;

        int notas10 = valor / 10;
        valor = valor % 10;

        int notas5 = valor / 5;
        valor = valor % 5;

        int notas2 = valor / 2;
        valor = valor % 2;

        int notas1 = valor / 1;
        valor = valor % 1;

        System.out.println("Notas de 100:" + notas100);
        System.out.println("Notas de 50:" + notas50);
        System.out.println("Notas de 20:" + notas20);
        System.out.println("Notas de 10:" + notas10);
        System.out.println("Notas de 5:" + notas5);
        System.out.println("Notas de 2:" + notas2);
        System.out.println("Notas de 1:" + notas1);

        teclado.close();
    
    }  
      
}
