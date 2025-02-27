import java.util.Random;
import java.util.Scanner;

class JogoDaVelha_Mapa {
    private char[][] mapa = new char[3][3];
    public JogoDaVelha_Mapa() { limpaMapa(); }
    public int sortear(int inicio, int fim) { return new Random().nextInt(fim - inicio + 1) + inicio; }
    public void limpaMapa() { for (char[] linha : mapa) java.util.Arrays.fill(linha, ' '); }
    public void desenhaMapa(int jogada) {
        System.out.println("------------- .. jogada: " + jogada);
        for (int i = 0; i < 3; i++) {
            System.out.println("| " + mapa[i][0] + " | " + mapa[i][1] + " | " + mapa[i][2] + " |");
            if (i < 2) System.out.println("-------------");
        }
    }
    public boolean jogar(int l, int c, char jogador) { return mapa[l][c] == ' ' && (mapa[l][c] = jogador) == jogador; }
    public boolean ganhou(char j) {
        for (int i = 0; i < 3; i++)
            if ((mapa[i][0] == j && mapa[i][1] == j && mapa[i][2] == j) || (mapa[0][i] == j && mapa[1][i] == j && mapa[2][i] == j)) return true;
        return (mapa[0][0] == j && mapa[1][1] == j && mapa[2][2] == j) || (mapa[0][2] == j && mapa[1][1] == j && mapa[2][0] == j);
    }
}

class JogoDaVelha_Jogador {
    private JogoDaVelha_Mapa mapa;
    public JogoDaVelha_Jogador(JogoDaVelha_Mapa mapa) { this.mapa = mapa; }
    public boolean joga(Scanner teclado) {
        System.out.print("Jogador ..\n  linha: ");
        int l = teclado.nextInt() - 1;
        System.out.print("  coluna: ");
        int c = teclado.nextInt() - 1;
        return mapa.jogar(l, c, 'X');
    }
}

class JogoDaVelha_PC {
    private JogoDaVelha_Mapa mapa;
    public JogoDaVelha_PC(JogoDaVelha_Mapa mapa) { this.mapa = mapa; }
    public boolean joga(int jogada) {
        Random r = new Random();
        int l, c;
        do { l = r.nextInt(3); c = r.nextInt(3); } while (!mapa.jogar(l, c, 'O'));
        System.out.println("PC[" + l + "," + c + "]");
        return true;
    }
}

public class JogoDaVelha {
    private JogoDaVelha_Mapa mapa = new JogoDaVelha_Mapa();
    private JogoDaVelha_PC pc = new JogoDaVelha_PC(mapa);
    private JogoDaVelha_Jogador jogador = new JogoDaVelha_Jogador(mapa);
    
    public void jogar(Scanner teclado) {
        do {
            mapa.limpaMapa();
            char jogadorAtual = mapa.sortear(0, 1) == 0 ? 'X' : 'O';
            int jogadas = 0;
            while (jogadas < 9) {
                mapa.desenhaMapa(jogadas);
                if ((jogadorAtual == 'X' ? jogador.joga(teclado) : pc.joga(jogadas)) && mapa.ganhou(jogadorAtual)) {
                    mapa.desenhaMapa(jogadas + 1);
                    System.out.println("... " + (jogadorAtual == 'X' ? "Jogador X" : "PC O") + " GANHOU!");
                    break;
                }
                jogadorAtual = jogadorAtual == 'X' ? 'O' : 'X';
                jogadas++;
            }
            if (jogadas == 9) {
                mapa.desenhaMapa(jogadas);
                System.out.println("... EMPATOU!");
            }
            System.out.print("Deseja jogar novamente (s/n)? ");
        } while (teclado.next().equalsIgnoreCase("s"));
    }
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        new JogoDaVelha().jogar(teclado);
        teclado.close();
    }
}
