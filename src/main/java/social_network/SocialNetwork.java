package social_network;

import social_network.commands.Command;

public class SocialNetwork {
    public void run(Command command) {
        command.execute();
    }
}
