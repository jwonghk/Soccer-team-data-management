package model;

import java.util.ArrayList;
import java.util.List;

public class PlayersDeposits {

    private List<Player> listPlayersAvail;


    // Modifies: this
    // Effect: construct a team of avaiable players
    public PlayersDeposits() {
        listPlayersAvail = new ArrayList<Player>();
    }

    // Modifies: this
    // Effect: add a player to the list of available so far
    public void addPlayer(Player player) {
        listPlayersAvail.add(player);
    }

    // Modifies: this
    // Effect: remove a player from the list of available
    public void removePlayer(String playerName) {
        ArrayList<Player> newList = new ArrayList<Player>();
        for (Player p : listPlayersAvail) {
            if (! (p.getName().equals(playerName))) {
                newList.add(p);
            }
        }
        listPlayersAvail = newList;
    }


    // EFFECT: returns the size of the players in deposits
    public Integer sizePlayerDeposit() {
        return listPlayersAvail.size();
    }
}
