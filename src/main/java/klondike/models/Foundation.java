package klondike.models;

import java.util.Stack;

public class Foundation {
	
    private Stack<Card> cards;

    private Suit suit;

    public Foundation(Suit suit) {
        this.cards = new Stack<Card>();
        this.suit = suit;
    }

    public boolean empty() {
        return this.cards.empty();
    }

    public Card peek() {
    	assert !this.cards.isEmpty();
        return this.cards.peek();
    }

    public Card pop() {
    	assert !this.cards.isEmpty();
        return this.cards.pop();
    }

    public void push(Card card) {
    	assert card.isFacedUp();
        this.cards.push(card);
    }
    
    public boolean isComplete() {
        return this.cards.size() == Number.values().length;
    }

    public boolean fitsIn(Card card) {
        assert card != null;
        return card.getSuit() == this.suit &&
                (card.getNumber() == Number.AS ||
                        (!this.empty() && card.isNextTo(this.peek())));
    }

    public Suit getSuit() {
        return this.suit;
    }
}
