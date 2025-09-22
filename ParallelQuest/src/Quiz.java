package ParallelQuest.src;
import java.util.*;

public class Quiz {
    private List<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
        loadQuestions();
    }

    private void loadQuestions() {
        // ====================== Introdução ======================
        questions.add(new Question(
            "Qual é a principal vantagem da programação paralela?",
            "b",
            "a) Reduzir complexidade\nb) Acelerar execução de tarefas simultâneas\nc) Diminuir consumo de memória\nd) Facilitar depuração"
        ));
        questions.add(new Question(
            "Qual a dificuldade mais comum da programação paralela?",
            "b",
            "a) Design de interface\nb) Concorrência e sincronização\nc) Instalação do Java\nd) Layout de banco de dados"
        ));
        questions.add(new Question(
            "Qual é o objetivo das plataformas de execução paralela?",
            "b",
            "a) Facilitar interface gráfica\nb) Executar tarefas em múltiplos processadores\nc) Compilar programas mais rápido\nd) Controlar cores do console"
        ));
        questions.add(new Question(
            "O que significa suporte computacional em paralelismo?",
            "a",
            "a) Capacidade de um sistema gerenciar múltiplas tarefas simultâneas\nb) Configuração de cores do console\nc) Definir tipos de variáveis\nd) Criar gráficos automaticamente"
        ));
        questions.add(new Question(
            "Qual classificação é feita pela Taxonomia de Flynn?",
            "b",
            "a) Linguagens de programação\nb) Arquiteturas paralelas (SISD, SIMD, MISD, MIMD)\nc) Sistemas operacionais\nd) Tipos de processadores"
        ));

        // ====================== Threads e Processos ======================
        questions.add(new Question(
            "Qual a diferença principal entre thread e processo?",
            "a",
            "a) Threads compartilham memória, processos não\nb) Processos compartilham memória, threads não\nc) Não há diferença\nd) Threads são mais lentas"
        ));
        questions.add(new Question(
            "Qual problema ocorre quando múltiplas threads acessam o mesmo recurso sem controle?",
            "b",
            "a) Deadlock\nb) Race condition\nc) Pipeline\nd) Overflow"
        ));
        questions.add(new Question(
            "O que é necessário para evitar race condition?",
            "b",
            "a) Variáveis globais\nb) Sincronização / locks\nc) Criar mais threads\nd) Arrays compartilhados"
        ));
        questions.add(new Question(
            "Qual estrutura em Java permite o controle de acesso exclusivo a recursos compartilhados?",
            "a",
            "a) Semáforos\nb) ArrayList\nc) Scanner\nd) Threads"
        ));
        questions.add(new Question(
            "O que é concorrência na API Java?",
            "b",
            "a) Execução sequencial\nb) Execução de múltiplas threads simultaneamente\nc) Criação de funções\nd) Controle de memória"
        ));

        // ====================== Paralelismo em Java ======================
        questions.add(new Question(
            "Qual biblioteca é usada para programação paralela padrão em Java?",
            "b",
            "a) java.io\nb) java.util.concurrent\nc) java.awt\nd) java.lang"
        ));
        questions.add(new Question(
            "O que a interface ExecutorService faz?",
            "a",
            "a) Cria threads e gerencia tarefas\nb) Exibe gráficos\nc) Aumenta a memória\nd) Manipula arrays"
        ));
        questions.add(new Question(
            "Qual primitiva Java é usada para garantir exclusão mútua?",
            "a",
            "a) Locks\nb) Array\nc) Scanner\nd) List"
        ));
        questions.add(new Question(
            "O que é MPI em Java?",
            "b",
            "a) Interface gráfica\nb) Biblioteca para comunicação em paralelo distribuído\nc) Sistema de arquivos\nd) Criação de threads"
        ));
        questions.add(new Question(
            "O que Monitores fazem em Java?",
            "a",
            "a) Controlam acesso a recursos compartilhados\nb) Executam cálculos matemáticos\nc) Criam arrays\nd) Aumentam desempenho automaticamente"
        ));

        // ====================== Métricas de desempenho e modelos ======================
        questions.add(new Question(
            "Qual métrica mede aceleração de um programa paralelo em relação ao sequencial?",
            "a",
            "a) Speedup\nb) Throughput\nc) Latência\nd) Eficiência"
        ));
        questions.add(new Question(
            "O que mede a eficiência de um programa paralelo?",
            "a",
            "a) Tempo de execução / número de threads\nb) Número de variáveis\nc) Tamanho do array\nd) Memória usada"
        ));
        questions.add(new Question(
            "Qual modelo de paralelismo divide tarefas em estágios?",
            "b",
            "a) Paralelismo de dados\nb) Paralelismo de tarefas\nc) Memória compartilhada\nd) Threads em sequência"
        ));
        questions.add(new Question(
            "O que é pipeline em programação paralela?",
            "a",
            "a) Sequência de tarefas onde cada estágio processa dados simultaneamente\nb) Execução sequencial\nc) Criação de threads\nd) Memória compartilhada"
        ));
        questions.add(new Question(
            "Qual é a vantagem do paralelismo de dados?",
            "a",
            "a) Processar múltiplos dados simultaneamente\nb) Reduzir a memória\nc) Criar gráficos\nd) Evitar deadlocks"
        ));

        // ====================== Técnicas de otimização e concorrência ======================
        questions.add(new Question(
            "Qual técnica previne inconsistência de dados em threads?",
            "a",
            "a) Semáforos\nb) Loop infinito\nc) Variáveis locais\nd) Arrays compartilhados"
        ));
        questions.add(new Question(
            "Qual função do monitor em Java?",
            "a",
            "a) Garantir exclusão mútua\nb) Criar threads\nc) Somar arrays\nd) Executar cálculos"
        ));
        questions.add(new Question(
            "O que faz um lock em programação paralela?",
            "a",
            "a) Bloqueia acesso a recurso\nb) Acelera a execução\nc) Cria threads\nd) Divide memória"
        ));
        questions.add(new Question(
            "Qual é a melhor prática para otimizar tarefas paralelas?",
            "b",
            "a) Evitar sincronização\nb) Dividir tarefas em pequenas unidades\nc) Criar apenas uma thread\nd) Usar arrays grandes"
        ));
        questions.add(new Question(
            "Por que precisamos de técnicas de refinamento em aplicações paralelas?",
            "a",
            "a) Melhorar desempenho e evitar erros de concorrência\nb) Reduzir cores do console\nc) Alterar variáveis globais\nd) Criar threads extras"
        ));
    }

    public List<Question> getQuestions() {
        Collections.shuffle(questions);
        return questions;
    }

    public static class Question {
        private String prompt;
        private String answer;
        private String options;

        public Question(String prompt, String answer, String options) {
            this.prompt = prompt;
            this.answer = answer;
            this.options = options;
        }

        public String getPrompt() {
            return prompt;
        }

        public String getAnswer() {
            return answer;
        }

        public String getOptions() {
            return options;
        }
    }
}
