package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Team {
    protected List<Player> currentTeam;

    public Team() {
        currentTeam = new ArrayList<Player>();
    }


    // Modifies: this
    // Effect: add a player to the current playing team for a coming game
    public void addPlayerTeam(Player p) {
        currentTeam.add(p);
    }

    // Requires: player must be in the list already
    // Modifies: this
    // Effect: remove a player from the current playing team
    public void removePlayerTeam(String p) {
        ArrayList<Player> nextGameSquad = new ArrayList<Player>();
        for (Player play : currentTeam) {
            if (!(play.getName().equals(p))) {
                nextGameSquad.add(play);
            }
        }
        currentTeam = nextGameSquad;
    }

    // Effect: return the name of all players in current Team
    // Require: team must be non-empty
    public List<String> listPlayer() {
        List<String> playerName = new ArrayList<String>();
        for (Player p : currentTeam) {
            playerName.add(p.getName());
        }
        return playerName;
    }

    // EFFECT: return a player based on the name
    public Player returnPlayerCurrentTeam(String playerName) {
        Player playerInCurrentTeam = null;
        for (Player p : currentTeam) {
            if (p.getName().equals(playerName)) {
                playerInCurrentTeam = p;
                break;
            }
        }
        return playerInCurrentTeam;
    }

    // EFFECT: return the size of current team
    public Integer currentTeamSize() {
        return currentTeam.size();
    }
}
