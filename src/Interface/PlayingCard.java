package Interface;

import java.util.Objects;

public class PlayingCard implements Card{

    private Rank rank;
    private Suit suit;

    public PlayingCard(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public Suit getSuit() {
        return suit;
    }

    @Override
    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Card) {
            if (((Card) o).getRank() == this.rank && ((Card) o).getSuit() == this.suit) {
                return true;
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return ((suit.getValue()-1)*13) + rank.getValue();
    }

    @Override
    public int compareTo(Card o) {
        return this.hashCode() - o.hashCode();
    }

    @Override
    public String toString() {
        return this.rank.getText() + " of " + this.suit.getText();
    }

    public static void main(String[] args) {
        new PlayingCard(Rank.ACE, Suit.DIAMONDS);

    }
}
