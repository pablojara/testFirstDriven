package klondike.models;

import java.util.Stack;

public class Waste {
	
    protected Stack<Card> cards;

    public Waste() {
        this.cards = new Stack<Card>();
    }
	
    public boolean empty() {
        return this.cards.empty();
    }

    public Card peek() {
        return this.cards.peek();
    }

    public Card pop() {
        return this.cards.pop();
    }

    public void push(Card card) {
        this.cards.push(card);
    }
}
