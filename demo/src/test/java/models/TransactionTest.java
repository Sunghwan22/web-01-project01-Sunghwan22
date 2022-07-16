package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {



  @Test
  void creation() {

    Transaction transaction = new Transaction("입금", 1000);
  }

  @Test
  void equals() {
    Transaction transaction = new Transaction("입금", 1000);
    Transaction transaction2 = new Transaction("입금", 1000);
    assertEquals(transaction.hashCode(), transaction2.hashCode());
    assertEquals(transaction, transaction2);
  }

  @Test
  void hashCodeTest() {
    Transaction transaction = new Transaction("입금", 1000);
    Transaction transaction2 = new Transaction("입금", 1000);

    assertEquals(transaction.hashCode(), transaction2.hashCode());
  }
}

