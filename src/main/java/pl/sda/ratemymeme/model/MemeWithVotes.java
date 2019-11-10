package pl.sda.ratemymeme.model;

import java.util.List;

public class MemeWithVotes {

    private final Meme meme;
    private final List<String> votingUsers;

    public MemeWithVotes(Meme meme, List<String> votingUsers) {
        this.meme = meme;
        this.votingUsers = votingUsers;
    }


    public Meme getMeme() {
        return meme;
    }

    public List<String> getVotingUsers() {
        return votingUsers;
    }
}
