package klondike.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import klondike.models.builders.CardBuilder;
import klondike.models.builders.CardListBuilder;
import klondike.models.builders.PileBuilder;

public class PileTest {

	private Pile createPile() {
		return new PileBuilder().build();
	}

	private List<Card> getCards() {
		List<Card> cards = new ArrayList<Card>();
		cards.add(new CardBuilder().number(Number.KING).suit(Suit.CLOVERS).facedUp().build());
		cards.add(new CardBuilder().number(Number.QUEEN).suit(Suit.DIAMONDS).facedUp().build());
		return cards;
	}

	@Test
	public void testEmptyWithEmpty() {
		Pile pile = this.createPile();
		assertTrue(pile.empty());
	}
	
	@Test
	public void testEmptyWithNotEmpty() {
		Pile pile = this.createPile();
		pile.push(this.getCards().get(0));
		assertFalse(pile.empty());
	}

	@Test
	public void testPushWithEmpty() {
		Pile pile = this.createPile();
		pile.push(this.getCards().get(0));
		assertEquals(this.getCards().get(0), pile.peek());
	}
	
	@Test
	public void testPushWithNotEmpty() {
		Pile pile = this.createPile();
		pile.push(this.getCards().get(0));
		pile.push(this.getCards().get(1));
		assertEquals(this.getCards().get(1), pile.peek());
	}

	@Test
	public void testPopEmpty() {
		Pile pile = this.createPile();
		pile.push(this.getCards().get(0));
		assertEquals(this.getCards().get(0), pile.pop());
		assertTrue(pile.empty());
	}
	
	@Test
	public void testPopNotEmpty() {
		Pile pile = this.createPile();
		pile.push(this.getCards().get(0));
		pile.push(this.getCards().get(1));
		assertEquals(this.getCards().get(1), pile.pop());
		assertEquals(this.getCards().get(0), pile.peek());
	}
	
	@Test
	public void testFitsInEmpty() {
		Pile pile = new PileBuilder().build();
		assertTrue(pile.fitsIn(new CardBuilder().number(Number.KING).build()));
		assertFalse(pile.fitsIn(new CardBuilder().number(Number.QUEEN).build()));
	}

	@Test
	public void testFitsInNotEmpty() {
		Pile pile = new PileBuilder().card().card(new CardBuilder().number(Number.SIX).suit(Suit.HEARTS).build())
				.build();
		assertTrue(pile.fitsIn(new CardBuilder().number(Number.FIVE).suit(Suit.PIKES).build()));
		assertFalse(pile.fitsIn(new CardBuilder().number(Number.FOUR).suit(Suit.PIKES).build()));
		assertFalse(pile.fitsIn(new CardBuilder().number(Number.FIVE).suit(Suit.DIAMONDS).build()));
		assertFalse(pile.fitsIn(new CardBuilder().number(Number.SEVEN).suit(Suit.CLOVERS).build()));
	}

	@Test(expected = AssertionError.class)
	public void testGetTopOneWrongIsNotFaceUp() {
		Pile pile = new PileBuilder().card().card().build();
		pile.peek(2);
	}

	@Test
	public void testGetTopOne() {
		CardListBuilder cardListBuilder = new CardListBuilder();
		cardListBuilder.card(new CardBuilder().number(Number.AS).suit(Suit.DIAMONDS).facedUp().build());
		Pile pile = new PileBuilder().card(new CardBuilder().number(Number.TWO).suit(Suit.PIKES).build()).build();
		this.testPeek(cardListBuilder.build(), pile);
	}

	@Test
	public void testPeekMany() {
		CardListBuilder cardListBuilder = new CardListBuilder();
		cardListBuilder.card(new CardBuilder().number(Number.NINE).suit(Suit.CLOVERS).facedUp().build());
		cardListBuilder.card(new CardBuilder().number(Number.EIGHT).suit(Suit.DIAMONDS).facedUp().build());
		cardListBuilder.card(new CardBuilder().number(Number.SEVEN).suit(Suit.PIKES).facedUp().build());
		Pile pile = new PileBuilder().card(new CardBuilder().number(Number.TEN).suit(Suit.DIAMONDS).build()).build();
		this.testPeek(cardListBuilder.build(), pile);
	}

	private void testPeek(List<Card> cards, Pile pile) {
		for (Card card : cards) {
			pile.push(card);
		}
		assertEquals(cards, pile.peek(cards.size()));
	}

	@Test
	public void testPush() {
		Pile pile = new PileBuilder().card()
				.card(new CardBuilder().number(Number.TEN).suit(Suit.DIAMONDS).build()).build();
		Card topCard = pile.peek();
		List<Card> cards = new CardListBuilder()
				.card(new CardBuilder().number(Number.NINE).suit(Suit.CLOVERS).facedUp().build())
				.card(new CardBuilder().number(Number.EIGHT).suit(Suit.DIAMONDS).facedUp().build())
				.card(new CardBuilder().number(Number.SEVEN).suit(Suit.PIKES).facedUp().build()).build();
		pile.push(cards);
		cards.add(0, topCard);
		assertEquals(cards, pile.peek(cards.size()));
	}

	@Test
	public void testRemoveTop() {
		Pile pile = new PileBuilder().card()
				.card(new CardBuilder().number(Number.TEN).suit(Suit.DIAMONDS).build()).build();
		pile.push(new CardBuilder().number(Number.NINE).suit(Suit.CLOVERS).facedUp().build());
		pile.push(new CardBuilder().number(Number.EIGHT).suit(Suit.DIAMONDS).facedUp().build());
		pile.push(new CardBuilder().number(Number.SEVEN).suit(Suit.PIKES).facedUp().build());
		pile.removeTop(4);
		assertTrue(pile.pop().isFacedUp());
		assertTrue(pile.empty());
	}

}
