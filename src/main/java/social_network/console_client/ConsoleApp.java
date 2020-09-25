package social_network.console_client;

import social_network.SocialNetwork;
import social_network.exceptions.UnsupportedCommandException;
import social_network.output.Printer;
import social_network.output.TimeDeltaFormatter;
import social_network.repositories.*;
import social_network.services.FollowService;
import social_network.services.PostService;
import social_network.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ConsoleApp {
    private static PrintStream output;
    private static BufferedReader reader;
    private static ConsoleClient client;

    public static void main(String[] args) throws IOException, UnsupportedCommandException {
        ConsoleCommandParser commandParser = new ConsoleCommandParser();
        DateTimeGenerator dateTimeGenerator = new DateTimeGenerator();
        PostRepository postRepository = new InMemoryPostRepository(dateTimeGenerator);
        PostService postService = new PostService(postRepository);
        UserRepository userRepository = new InMemoryUserRepository();
        UserService userService = new UserService(userRepository);
        TimeDeltaFormatter timeDeltaFormatter = new TimeDeltaFormatter();
        output = System.out;
        Printer printer = new Printer(output, timeDeltaFormatter);
        FollowRepository followRepository = new InMemoryFollowRepository();
        FollowService followService = new FollowService(followRepository);
        ConsoleCommandFactory commandFactory = new ConsoleCommandFactory(postService, userService, printer, followService);
        SocialNetwork socialNetwork = new SocialNetwork();
        client = new ConsoleClient(commandParser, commandFactory, socialNetwork);

        reader = new BufferedReader(new InputStreamReader(System.in));
        startListener();
    }

    private static void startListener() throws IOException, UnsupportedCommandException {
        output.println("enter a command below");
        output.println("\"exit\" to exit");
        listen();
    }

    private static void listen() throws IOException, UnsupportedCommandException {
        output.print("> ");
        String input = reader.readLine();
        if (input.equals("exit")) {
            return;
        }
        client.execute(input);
        listen();
    }
}
