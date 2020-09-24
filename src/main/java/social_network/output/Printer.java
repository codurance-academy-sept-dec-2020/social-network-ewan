package social_network.output;

import social_network.entities.Post;

import java.io.PrintStream;
import java.util.List;

public class Printer {
    private PrintStream output;
    private TimeDeltaFormatter timeDeltaFormatter;

    public Printer(PrintStream output, TimeDeltaFormatter timeDeltaFormatter) {
        this.output = output;
        this.timeDeltaFormatter = timeDeltaFormatter;
    }

    public void printPosts(List<Post> posts) {
        for (Post post : posts) {
            String postLine = String.format("%s (%s)", post.text, timeDeltaFormatter.format(post.created_at));
            output.println(postLine);
        }
    }
}
