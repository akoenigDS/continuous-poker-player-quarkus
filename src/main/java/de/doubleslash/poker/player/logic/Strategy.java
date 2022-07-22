package de.doubleslash.poker.player.logic;

import java.util.HashMap;
import java.util.Map;

import de.doubleslash.poker.player.data.Card;
import de.doubleslash.poker.player.data.Rank;
import de.doubleslash.poker.player.data.Table;

public class Strategy {

    public int decide(final Table table) {

        if (table.getCommunityCards().size() >= 3) {
            if (checkForTriple(table)) {
                return table.getMinimumBet() * 4;
            }
        }

        if (table.getCommunityCards().size() >= 3) {
            if (checkForFourple(table)) {
                return table.getPlayers().get(table.getActivePlayer()).getStack();            }
        }

        if (table.getPlayers().get(table.getActivePlayer()).getCards().get(0)
                .equals(table.getPlayers().get(table.getActivePlayer()).getCards().get(1))) {
            if (isHigherThanJack(table)) {
                return table.getMinimumBet() * 3;
            }
            return table.getMinimumBet() * 2;
        }
        return table.getMinimumBet();
    }

    private boolean isHigherThanJack(final Table table) {
        return table.getPlayers().get(table.getActivePlayer()).getCards().get(0).getRank().equals(Rank.QUEEN)
                || table.getPlayers().get(table.getActivePlayer()).getCards().get(0).getRank().equals(Rank.JACK)
                || table.getPlayers().get(table.getActivePlayer()).getCards().get(0).getRank().equals(Rank.KING)
                || table.getPlayers().get(table.getActivePlayer()).getCards().get(0).getRank().equals(Rank.ACE);
    }

    private boolean checkForTriple(final Table table) {
        boolean result = false;

        final Map<Card, Integer> cards = new HashMap<>();

        for (final Card ownCard : table.getPlayers().get(table.getActivePlayer()).getCards()) {
            cards.merge(ownCard, 1, Integer::sum);
        }
        for (final Card communityCard : table.getCommunityCards()) {
            cards.merge(communityCard, 1, Integer::sum);
        }

        for (var entry : cards.entrySet()) {
            if (entry.getValue() == 3) {
                return true;
            }
        }

        return result;
    }

    private boolean checkForFourple(final Table table) {
        boolean result = false;

        final Map<Card, Integer> cards = new HashMap<>();

        for (final Card ownCard : table.getPlayers().get(table.getActivePlayer()).getCards()) {
            cards.merge(ownCard, 1, Integer::sum);
        }
        for (final Card communityCard : table.getCommunityCards()) {
            cards.merge(communityCard, 1, Integer::sum);
        }

        for (var entry : cards.entrySet()) {
            if (entry.getValue() == 4) {
                return true;
            }
        }

        return result;
    }

}
