package de.doubleslash.poker.player.logic;

import de.doubleslash.poker.player.data.Table;

public class Strategy {

   public int decide(final Table table) {
      System.out.println(table);

      if (table.getPlayers().get(table.getActivePlayer()).getCards().get(0).equals(table.getPlayers().get(table.getActivePlayer()).getCards().get(1)))
      {
         return table.getMinimumBet()*2;
      }
      return table.getMinimumBet();
   }

}
