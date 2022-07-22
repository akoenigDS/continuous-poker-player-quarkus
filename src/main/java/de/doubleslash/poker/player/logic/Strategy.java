package de.doubleslash.poker.player.logic;

import java.util.HashMap;
import java.util.Map;

import de.doubleslash.poker.player.data.Card;
import de.doubleslash.poker.player.data.Rank;
import de.doubleslash.poker.player.data.Suit;
import de.doubleslash.poker.player.data.Table;

public class Strategy {

    public int decide(final Table table) {

        if (table.getCommunityCards().size() >= 3) {
            return checkForNSameCards(table, 4);
        }

        if (table.getCommunityCards().size() >= 3) {
            return checkForNSameCards(table, 4);
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

    private int checkForNSameCards(final Table table, final int n) {
        final boolean result = false;

        final Map<Card, Integer> cards = new HashMap<>();
        final Map<Suit, Integer> suits = new HashMap<>();

        for (final Card ownCard : table.getPlayers().get(table.getActivePlayer()).getCards()) {
            cards.merge(ownCard, 1, Integer::sum);
            suits.merge(ownCard.getSuit(), 1, Integer::sum);
        }
        for (final Card communityCard : table.getCommunityCards()) {
            cards.merge(communityCard, 1, Integer::sum);
            suits.merge(communityCard.getSuit(), 1, Integer::sum);
        }

        for(var entry : suits.entrySet()) {
            if (entry.getValue() == 5) {
                return table.getMinimumBet()*100;
            }
        }

        for (var entry : cards.entrySet()) {
            if (entry.getValue() == n) {
                return table.getMinimumBet() * 4;
            }
        }

        return table.getMinimumBet();
    }

}
