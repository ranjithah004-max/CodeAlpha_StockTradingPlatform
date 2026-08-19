import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AIChatbot {

    static Map<String, String> chatbot = new HashMap<>();

    // Add questions and answers
    static void loadData() {

        chatbot.put("hello", "Hello! How can I help you?");
        chatbot.put("hi", "Hi! Nice to meet you.");

        chatbot.put("what is java",
                "Java is a high-level, object-oriented programming language.");

        chatbot.put("what is html",
                "HTML is used to create the structure of web pages.");

        chatbot.put("what is css",
                "CSS is used to style web pages.");

        chatbot.put("what is javascript",
                "JavaScript is used to make web pages interactive.");

        chatbot.put("what is nlp",
                "NLP stands for Natural Language Processing.");

        chatbot.put("what is machine learning",
                "Machine Learning allows computers to learn from data.");

        chatbot.put("what is a chatbot",
                "A chatbot is a program that communicates with users.");

        chatbot.put("what is github",
                "GitHub is a platform used to store and manage code.");

        chatbot.put("what is oops",
                "OOP stands for Object-Oriented Programming.");
    }

    // Find answer
    static String getAnswer(String question) {

        question = question.toLowerCase().trim();

        String answer = chatbot.get(question);

        if (answer != null) {
            return answer;
        }

        return "Sorry, I don't understand that question.";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        loadData();

        System.out.println("==============================");
        System.out.println("        AI CHATBOT");
        System.out.println("==============================");

        System.out.println("Bot: Hello! Ask me a question.");
        System.out.println("Bot: Type 'bye' to exit.");

        while (true) {

            System.out.print("\nYou: ");
            String question = sc.nextLine();

            // Exit chatbot
            if (question.equalsIgnoreCase("bye")) {

                System.out.println("Bot: Goodbye!");
                break;
            }

            String answer = getAnswer(question);

            System.out.println("Bot: " + answer);
        }

        sc.close();
    }
}