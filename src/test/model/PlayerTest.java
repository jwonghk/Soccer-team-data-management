package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    // delete or rename this class!

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Player player5;

    private Team team;

    @BeforeEach
    void runBefore() {
        player1 = new Player("bierhoff", 20, 3, 3, 3, "striker");
        player2 = new Player("moller", 5, 0, 4, 4, "midfield");
        player3 = new Player("ziegler", 7, 2, 6, 6, "defence");
        player4 = new Player("klinsmen", 18, 0, 7, 7, "striker");
        player5 = new Player("gomez", 7, 2, 4, 4, "striker");
        team = new Team();
    }

    @Test
    void testPlayerConstructor() {
        assertEquals("bierhoff", player1.getName());
        assertEquals(20, player1.getGoals());
        assertEquals(3, player1.getConceal());
        assertEquals(3, player1.getGameWon());
        assertEquals(3, player1.getGameLost());
        assertEquals("striker", player1.getPosition());
    }

    @Test
    void testPlayerSetters() {
        player1.setPosition("midfield");
        player1.setGoals(1);
        player1.setConceal(4);
        player1.setWon(5);
        player1.setLost(6);

        assertEquals(player1.getGoals(),1);
        assertEquals(player1.getConceal(),4);
        assertEquals(player1.getGameWon(),5);
        assertEquals(player1.getGameLost(),6);
        assertEquals(player1.getPosition(),"midfield");
    }


    @Test
    void testTeamPlayerAddingRemoving() {
        assertEquals(0, team.currentTeamSize());
        team.addPlayerTeam(player1);
        team.addPlayerTeam(player2);
        team.addPlayerTeam(player3);
        team.addPlayerTeam(player4);
        team.addPlayerTeam(player5);

        assertEquals(5, team.currentTeamSize());
        assertEquals(team.returnPlayerCurrentTeam("gomez"), player5);
        assertEquals(team.returnPlayerCurrentTeam("ziegler"), player3);
        assertEquals(team.returnPlayerCurrentTeam("andreas"), null);


        team.removePlayerTeam("bierhoff");
        assertEquals(team.currentTeamSize(), 4);
        team.removePlayerTeam("gomez");
        assertEquals(team.currentTeamSize(),3);

    }

    @Test
    void testTeamPlayerListing() {
        team.addPlayerTeam(player1);
        team.addPlayerTeam(player2);
        team.addPlayerTeam(player3);
        team.addPlayerTeam(player4);
        team.addPlayerTeam(player5);

        List<String> ListPlayerName = new ArrayList<String>();
        ListPlayerName = team.listPlayer();
        assertTrue(ListPlayerName.size() == 5);
        assertTrue(ListPlayerName.get(0).equals("bierhoff"));
        assertTrue(ListPlayerName.get(1).equals("moller"));
        assertTrue(ListPlayerName.get(2).equals("ziegler"));
        assertTrue(ListPlayerName.get(3).equals("klinsmen"));
        assertTrue(ListPlayerName.get(4).equals("gomez"));
    }


    @Test
    void testPlayerDeposits() {
        PlayersDeposits playersDeposit = new PlayersDeposits();
        assertEquals(playersDeposit.sizePlayerDeposit(), 0);
        playersDeposit.addPlayer(player1);
        assertEquals(playersDeposit.sizePlayerDeposit(),1);

        playersDeposit.addPlayer(player2);
        playersDeposit.addPlayer(player3);
        assertTrue(playersDeposit.sizePlayerDeposit().equals(3));

        playersDeposit.removePlayer("bierhoff");
        assertTrue(playersDeposit.sizePlayerDeposit().equals(2));


    }

}