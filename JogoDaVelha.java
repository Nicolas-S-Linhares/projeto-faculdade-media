import java.util.Random;
import java.util.Scanner;

class JogoDaVelha {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        JogoDaVelha_Mapa mapa = new JogoDaVelha_Mapa();
        JogoDaVelha_Jogador jogador = new JogoDaVelha_Jogador(teclado);
        JogoDaVelha_PC pc = new JogoDaVelha_PC();
        
        boolean jogarNovamente;
        do {
            mapa.limparMapa();
            boolean vezDoJogador = mapa.sortearInicio();
            int jogadas = 0;
            boolean jogoEmAndamento = true;
            
            while (jogoEmAndamento) {
                mapa.desenharMapa(jogadas);
                
                if (vezDoJogador) {
                    jogador.jogar(mapa);
                    if (mapa.verificarVitoria('X')) {
                        System.out.println("... Jogador GANHOU!");
                        jogoEmAndamento = false;
                    }
                } else {
                    pc.jogar(mapa);
                    if (mapa.verificarVitoria('O')) {
                        System.out.println("... PC GANHOU!");
                        jogoEmAndamento = false;
                    }
                }
                
                jogadas++;
                if (jogoEmAndamento && jogadas == 9) {
                    mapa.desenharMapa(jogadas);
                    System.out.println("... EMPATOU!");
                    jogoEmAndamento = false;
                }
                
                vezDoJogador = !vezDoJogador;
            }
            
            System.out.print("Deseja jogar novamente (s/n)? ");
            jogarNovamente = teclado.next().equalsIgnoreCase("s");
            
        } while (jogarNovamente);
        teclado.close();
    }
}

class JogoDaVelha_Mapa {
    private char[][] mapa = new char[3][3];
    private Random random = new Random();
    
    public void limparMapa() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mapa[i][j] = ' ';
            }
        }
    }
    
    public boolean sortearInicio() {
        return random.nextBoolean();
    }
    
    public void desenharMapa(int jogada) {
        System.out.println("------------- .. jogada: " + jogada);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + mapa[i][j] + " ");
                if (j < 2) System.out.print("|");
            }
            System.out.println("\n-------------");
        }
    }
    
    public boolean verificarVitoria(char jogador) {
        for (int i = 0; i < 3; i++) {
            if (mapa[i][0] == jogador && mapa[i][1] == jogador && mapa[i][2] == jogador) return true;
            if (mapa[0][i] == jogador && mapa[1][i] == jogador && mapa[2][i] == jogador) return true;
        }
        if (mapa[0][0] == jogador && mapa[1][1] == jogador && mapa[2][2] == jogador) return true;
        if (mapa[0][2] == jogador && mapa[1][1] == jogador && mapa[2][0] == jogador) return true;
        return false;
    }
    
    public boolean marcarJogada(int linha, int coluna, char jogador) {
        if (linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && mapa[linha][coluna] == ' ') {
            mapa[linha][coluna] = jogador;
            return true;
        }
        return false;
    }
}

class JogoDaVelha_Jogador {
    private Scanner teclado;
    
    public JogoDaVelha_Jogador(Scanner teclado) {
        this.teclado = teclado;
    }
    
    public void jogar(JogoDaVelha_Mapa mapa) {
        int linha, coluna;
        boolean jogadaValida;
        do {
            System.out.println("Jogador ..\n  linha: ");
            linha = teclado.nextInt() - 1;
            System.out.println("  coluna: ");
            coluna = teclado.nextInt() - 1;
            jogadaValida = mapa.marcarJogada(linha, coluna, 'X');
            if (!jogadaValida) System.out.println("Posição inválida! Tente novamente.");
        } while (!jogadaValida);
    }
}

class JogoDaVelha_PC {
    private Random random = new Random();
    
    public void jogar(JogoDaVelha_Mapa mapa) {
        int linha, coluna;
        boolean jogadaValida;
        do {
            linha = random.nextInt(3);
            coluna = random.nextInt(3);
            jogadaValida = mapa.marcarJogada(linha, coluna, 'O');
        } while (!jogadaValida);
        System.out.println("PC[" + linha + "," + coluna + "]");
    }
}
