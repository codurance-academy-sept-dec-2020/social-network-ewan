package social_network;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import social_network.commands.Command;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SocialNetworkShould {

    @Mock
    private Command command;

    @InjectMocks
    private SocialNetwork socialNetwork;

    @Test
    void execute_given_command() {
        socialNetwork.run(command);
        verify(command).execute();
    }
}
