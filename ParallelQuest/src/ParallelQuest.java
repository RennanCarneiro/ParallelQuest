package ParallelQuest.src;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;
// Cores para console
class Colors {
    public static final String RESET = "\u001B[0m";
    public static final String AMARELO = "\u001B[33m";
    public static final String VERDE = "\u001B[32m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String AZUL = "\u001B[34m";
}

public class ParallelQuest {
    private static Scanner sc = new Scanner(System.in);
    private static int sessionScore = 0; // Pontuação da sessão atual
    private static String currentPlayer;
    private static Scoreboard scoreboard;

    public static void main(String[] args) throws Exception {
        scoreboard = new Scoreboard();
        
        System.out.println(Colors.AZUL + "Bem-vindo ao Parallel Quest!" + Colors.RESET);
        System.out.print("Por favor, digite seu nome de jogador: ");
        currentPlayer = sc.nextLine();

        while (true) {
            System.out.println("\n" + Colors.AZUL + "=== Parallel Quest ===" + Colors.RESET);
            System.out.println("Jogador: " + Colors.AMARELO + currentPlayer + Colors.RESET);
            System.out.println("1. Nível 1 - Soma em Paralelo");
            System.out.println("2. Nível 2 - Race Condition");
            System.out.println("3. Nível 3 - Quiz Educativo");
            System.out.println("4. Mini Desafio - Soma personalizada");
            System.out.println("5. Ver Pontuação da Sessão");
            System.out.println("6. Ver Scoreboard Geral");
            System.out.println("7. Limpar Scoreboard");
            System.out.println("8. Sair e Salvar");
            System.out.print("Escolha: ");
            int opc = sc.nextInt();
            switch (opc) {
                case 1: nivel1(); break;
                case 2: nivel2(); break;
                case 3: nivel3(); break;
                case 4: miniDesafio(); break;
                case 5: System.out.println("Pontuação da sessão atual: " + sessionScore); break;
                case 6: scoreboard.display(); break;
                case 7:
                    System.out.print("Tem certeza que deseja limpar todo o scoreboard? (s/n): ");
                    char confirm = sc.next().toLowerCase().charAt(0);
                    if (confirm == 's') {
                        scoreboard.clear();
                        System.out.println(Colors.VERDE + "Scoreboard foi limpo com sucesso!" + Colors.RESET);
                    } else {
                        System.out.println("Operação cancelada.");
                    }
                    break;
                case 8: 
                    scoreboard.updateScore(currentPlayer, sessionScore);
                    scoreboard.save();
                    System.out.println("Pontuação salva! Obrigado por jogar!\nSaindo..."); 
                    return;
                default: System.out.println("Opção inválida."); break;
            }
        }
    }

    // ===================== Nível 1 =====================
    private static void nivel1() throws InterruptedException {
        System.out.println("\n" + Colors.AZUL + "=== Nível 1: Soma em Paralelo ===" + Colors.RESET);
        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) array[i] = i + 1;

        System.out.print("Escolha o número de threads (1-8): ");
        int nThreads = sc.nextInt();

        int chunk = (int) Math.ceil((double) array.length / nThreads);
        long[] partialSums = new long[nThreads];

        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        CountDownLatch latch = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            int idx = i;
            int start = idx * chunk;
            int end = Math.min(start + chunk, array.length);

            executor.submit(() -> {
                long sum = 0;
                for (int j = start; j < end; j++) sum += array[j];
                partialSums[idx] = sum;
                System.out.println(Colors.AMARELO + "Thread " + (idx + 1) + " somou " + sum + Colors.RESET);
                latch.countDown();
            });
        }

        latch.await();
        executor.shutdown();

        long total = Arrays.stream(partialSums).sum();
        System.out.println(Colors.VERDE + "Soma total do array: " + total + Colors.RESET);
        sessionScore += 50;
        System.out.println("💡 Demonstra divisão de tarefas e paralelismo de dados.");
    }

    // ===================== Nível 2 =====================
    private static void nivel2() throws InterruptedException {
        System.out.println("\n" + Colors.AZUL + "=== Nível 2: Race Condition ===" + Colors.RESET);
        System.out.println("Você deve incrementar um contador com 1000 threads.");
        System.out.print("Deseja usar sincronização? (s/n): ");
        char choice = sc.next().toLowerCase().charAt(0);

        int threadsCount = 1000;
        Counter counter = new Counter();

        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(threadsCount);

        for (int i = 0; i < threadsCount; i++) {
            executor.submit(() -> {
                if (choice == 's') counter.safeIncrement();
                else counter.unsafeIncrement();
                latch.countDown();
            });
        }

        latch.await();
        executor.shutdown();

        int expected = threadsCount;
        System.out.println("Valor final do contador: " + counter.value);
        System.out.println("Esperado: " + expected);
        if (counter.value == expected) {
            System.out.println(Colors.VERDE + "Sem race condition! +50 pontos" + Colors.RESET);
            sessionScore += 50;
        } else {
            System.out.println(Colors.VERMELHO + "Race condition ocorreu!" + Colors.RESET);
        }
    }

    static class Counter {
        int value = 0;
        synchronized void safeIncrement() { value++; }
        void unsafeIncrement() { value++; }
    }

    private static void nivel3() {
    System.out.println("\n" + Colors.AZUL + "=== Nível 3: Quiz Educativo ===" + Colors.RESET);
    sc.nextLine(); // limpar buffer

    Quiz quiz = new Quiz();
    List<Quiz.Question> questions = quiz.getQuestions();
    int quizScore = 0;

    for (Quiz.Question q : questions) {
        System.out.println("\n" + q.getPrompt());
        System.out.println(q.getOptions());
        System.out.print("Resposta: ");
        String answer = sc.nextLine().trim().toLowerCase();
        if (answer.equals(q.getAnswer())) {
            System.out.println(Colors.VERDE + "Correto!" + Colors.RESET);
            quizScore += 10; // Pontos por acerto
        } else {
            System.out.println(Colors.VERMELHO + "Errado! Resposta correta: " + q.getAnswer() + Colors.RESET);
        }
    }
    sessionScore += quizScore;
    System.out.println("\nPontuação do quiz: " + quizScore + "/" + (questions.size() * 10));
}

    // Classe Question duplicada removida para usar a de Quiz.java
    static class Question {
        String prompt;
        String answer;
        String options;
        Question(String p, String a, String o) { prompt = p; answer = a; options = o; }
    }

    // ===================== Mini Desafio =====================
    private static void miniDesafio() throws InterruptedException {
        System.out.println("\n" + Colors.AZUL + "=== Mini Desafio: Soma Personalizada ===" + Colors.RESET);
        sc.nextLine(); // limpar buffer
        System.out.print("Digite os números do array separados por espaço: ");
        String[] input = sc.nextLine().trim().split("\\s+");
        int[] array = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();

        System.out.print("Escolha o número de threads (1-8): ");
        int nThreads = sc.nextInt();

        int chunk = (int) Math.ceil((double) array.length / nThreads);
        long[] partialSums = new long[nThreads];

        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        CountDownLatch latch = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            int idx = i;
            int start = idx * chunk;
            int end = Math.min(start + chunk, array.length);

            executor.submit(() -> {
                long sum = 0;
                for (int j = start; j < end; j++) sum += array[j];
                partialSums[idx] = sum;
                System.out.println(Colors.AMARELO + "Thread " + (idx + 1) + " somou " + sum + Colors.RESET);
                latch.countDown();
            });
        }

        latch.await();
        executor.shutdown();

        long total = Arrays.stream(partialSums).sum();
        System.out.println(Colors.VERDE + "Soma total do array: " + total + Colors.RESET);
        sessionScore += 25; // Pontuação do mini desafio
        System.out.println("💡 Demonstra paralelismo de dados aplicado a um array personalizado.");
    }

    // ===================== Scoreboard =====================
    static class Scoreboard {
        private static final String FILENAME = "scoreboard.dat";
        private Map<String, Integer> scores;

        @SuppressWarnings("unchecked")
        Scoreboard() {
            File file = new File(FILENAME);
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    scores = (Map<String, Integer>) ois.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Erro ao carregar scoreboard. Um novo será criado.");
                    scores = new HashMap<>();
                }
            } else {
                scores = new HashMap<>();
            }
        }

        public void updateScore(String playerName, int sessionScore) {
            // Adiciona a pontuação da sessão à pontuação total do jogador
            scores.put(playerName, scores.getOrDefault(playerName, 0) + sessionScore);
        }

        public void clear() {
            scores.clear();
            save(); // Salva o scoreboard vazio para persistir a limpeza
        }

        public void save() {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
                oos.writeObject(scores);
            } catch (IOException e) {
                System.err.println("Erro ao salvar scoreboard: " + e.getMessage());
            }
        }

        public void display() {
            System.out.println("\n" + Colors.AZUL + "--- Scoreboard Geral ---" + Colors.RESET);
            if (scores.isEmpty()) {
                System.out.println("Nenhuma pontuação registrada ainda.");
                return;
            }

            // Cria uma lista a partir do mapa para poder ordená-la
            List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>(scores.entrySet());
            // Ordena em ordem decrescente de pontuação
            sortedScores.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

            int rank = 1;
            for (Map.Entry<String, Integer> entry : sortedScores) {
                System.out.printf("%d. %s: %d pontos\n", rank++, entry.getKey(), entry.getValue());
            }
            System.out.println(Colors.AZUL + "------------------------" + Colors.RESET);
        }
    }
}
