import java.util.NoSuchElementException;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: PokemonTreeTester.java
// Course: CS 300 Fall 2020
//
// Author: Huong Nguyen
// Email: htnguyen23@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * PokemonTree.
 *
 */

public class PokemonTreeTester {

  /**
   * Checks the correctness of the implementation of both addPokemon() and toString() methods
   * implemented in the PokemonTree class. This unit test considers at least the following
   * scenarios. (1) Create a new empty PokemonTree, and check that its size is 0, it is empty, and
   * that its string representation is an empty string "". (2) try adding one Pokemon and then check
   * that the addPokemon() method call returns true, the tree is not empty, its size is 1, and the
   * .toString() called on the tree returns the expected output. (3) Try adding another Pokemon
   * which is more powerful than the one at the root, (4) Try adding a third Pokemon which is less
   * powerful than the one at the root, (5) Try adding at least two further Pokemons such that one
   * must be added at the left subtree, and the other at the right subtree. For all the above
   * scenarios, and more, double check each time that size() method returns the expected value, the
   * add method call returns true, and that the .toString() method returns the expected string
   * representation of the contents of the binary search tree in an ascendant order from the most
   * powerful Pokemon to the least powerful one. (6) Try adding a Pokemon whose CP value was used as
   * a key for a Pokemon already stored in the tree. Make sure that the addPokemon() method call
   * returned false, and that the size of the tree did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddPokemonToStringSize() {
    // (1) create a new empty PokemonTree
    PokemonTree pokeTree = new PokemonTree();
    if (!pokeTree.isEmpty() || !pokeTree.toString().equals("")) {
      System.out.println("fail: case (1)");
      return true;
    }
    // (2) add one Pokemon
    if (!pokeTree.addPokemon(new Pokemon("Pikachu", "1,2,3")))
      return true;
    if (pokeTree.isEmpty() || pokeTree.size() != 1
      || !pokeTree.toString().equals("[Pikachu CP:123 (A:1 S:2 D:3)]")) {
      System.out.println("fail: case (2)");
      return true;
    }
    // (3) add another Pokemon which is more powerful than the one at the root
    if (!pokeTree.addPokemon(new Pokemon("Eevee", "2,2,4")))
      return true;
    if (pokeTree.size() != 2 || !pokeTree.toString()
      .equals("[Pikachu CP:123 (A:1 S:2 D:3)]\n[Eevee CP:224 (A:2 S:2 D:4)]")) {
      System.out.println("fail: case (3)\n" + pokeTree.toString());
      return true;
    }
    // (4) add a third Pokemon which is less powerful than the one at the root
    if (!pokeTree.addPokemon(new Pokemon("Huong", "1,1,1")))
      return true;
    if (pokeTree.size() != 3 || !pokeTree.toString().equals(
      "[Huong CP:111 (A:1 S:1 D:1)]\n[Pikachu CP:123 (A:1 S:2 D:3)]\n[Eevee CP:224 (A:2 S:2 D:4)]")) {
      System.out.println("fail: case (4)\n" + pokeTree.toString());
      return true;
    }
    // (5) add at least two further Pokemons such that one must be added at the left subtree,
    // and the other at the right subtree
    if (!pokeTree.addPokemon(new Pokemon("Charmander", "3,2,1")))
      return true;
    if (pokeTree.size() != 4 || !pokeTree.toString().equals(
      "[Huong CP:111 (A:1 S:1 D:1)]\n[Pikachu CP:123 (A:1 S:2 D:3)]\n[Eevee CP:224 (A:2 S:2 D:4)]\n[Charmander CP:321 (A:3 S:2 D:1)]")) {
      System.out.println("fail: case (5)\n" + pokeTree.toString());
      return true;
    }
    // (6) add an existing Pokemon
    if (pokeTree.addPokemon(new Pokemon("Pikachu", "1,2,3")))
      return true;
    return false;
  }

  /**
   * This method checks mainly for the correctness of the PokemonTree.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new PokemonTree. Then, check that
   * calling the lookup() method with any valid CP value must throw a NoSuchElementException. (2)
   * Consider a PokemonTree of height 3 which consists of at least 5 PokemonNodes. Then, try to call
   * lookup() method to search for the Pokemon at the root of the tree, then a Pokemon at the right
   * and left subtrees at different levels. Make sure that the lookup() method returns the expected
   * output for every method call. (3) Consider calling .lookup() method on a non-empty PokemonTree
   * with a CP value not stored in the tree, and ensure that the method call throws a
   * NoSuchElementException.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddPokemonAndLookup() {
    // (1) call lookup() on a new PokemonTree
    PokemonTree pokeTree = new PokemonTree();
    try {
      pokeTree.lookup(111);
      return true;
    } catch (NoSuchElementException nse) {
      System.out.println(nse.getMessage());
    }
    // (2) call lookup() method to search for the Pokemon at the root of the tree, then a Pokemon at
    // the right and left subtrees at different levels
    pokeTree.addPokemon(new Pokemon("Pikachu", "1,2,3"));
    pokeTree.addPokemon(new Pokemon("Eevee", "2,2,4"));
    pokeTree.addPokemon(new Pokemon("Huong", "1,1,1"));
    pokeTree.addPokemon(new Pokemon("Snorlax", "4,4,8"));
    pokeTree.addPokemon(new Pokemon("Charmander", "3,2,1"));
    pokeTree.addPokemon(new Pokemon("Bulbasaur", "2,2,3"));
    pokeTree.addPokemon(new Pokemon("Lapras", "7,3,5"));
    Pokemon temp = pokeTree.lookup(123);
    if (temp.compareTo(new Pokemon("Pikachu", "1,2,3")) != 0)
      return true;
    temp = pokeTree.lookup(111);
    if (temp.compareTo(new Pokemon("Huong", "1,1,1")) != 0)
      return true;
    temp = pokeTree.lookup(735);
    if (temp.compareTo(new Pokemon("Lapras", "7,3,5")) != 0)
      return true;
    // (3) call lookup() method on a non-empty PokemonTree with a CP value not stored in the tree
    try {
      pokeTree.lookup(000);
      return true;
    } catch (NoSuchElementException nse) {
      System.out.println(nse.getMessage());
    }
    return false;
  }

  /**
   * Checks for the correctness of PokemonTree.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty Pokemon tree is zero. (2) ensures
   * that the height of a tree which consists of only one node is 1. (3) ensures that the height of
   * a PokemonTree with the following structure for instance, is 4. (*) / \ (*) (*) \ / \ (*)(*) (*)
   * / (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    // (1) height of an empty Pokemon tree
    PokemonTree pokeTree = new PokemonTree();
    if (pokeTree.height() != 0)
      return true;
    // (2) height of Pokemon tree with 1 node
    pokeTree.addPokemon(new Pokemon("Pikachu", "1,2,3"));
    if (pokeTree.height() != 1)
      return true;
    // (3) height of Pokemon tree with multiple nodes
    pokeTree.addPokemon(new Pokemon("Eevee", "2,2,4"));
    pokeTree.addPokemon(new Pokemon("Huong", "1,1,1"));
    pokeTree.addPokemon(new Pokemon("Snorlax", "4,4,8"));
    pokeTree.addPokemon(new Pokemon("Charmander", "3,2,1"));
    pokeTree.addPokemon(new Pokemon("Bulbasaur", "2,2,3"));
    pokeTree.addPokemon(new Pokemon("Lapras", "7,3,5"));
    if (pokeTree.height() != 4)
      return true;
    return false;
  }

  /**
   * Checks for the correctness of PokemonTree.getLeastPowerfulPokemon() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetLeastPowerfulPokemon() {
    PokemonTree pokeTree = new PokemonTree();
    pokeTree.addPokemon(new Pokemon("Pikachu", "1,2,3"));
    pokeTree.addPokemon(new Pokemon("Eevee", "2,2,4"));
    pokeTree.addPokemon(new Pokemon("Huong", "1,1,1"));
    pokeTree.addPokemon(new Pokemon("Snorlax", "4,4,8"));
    pokeTree.addPokemon(new Pokemon("Charmander", "3,2,1"));
    pokeTree.addPokemon(new Pokemon("Bulbasaur", "2,2,3"));
    pokeTree.addPokemon(new Pokemon("Lapras", "7,3,5"));
    if (pokeTree.getLeastPowerfulPokemon().compareTo(new Pokemon("Huong", "1,1,1")) != 0)
      return true;
    return false;
  }

  /**
   * Checks for the correctness of PokemonTree.getMostPowerfulPokemon() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetMostPowerfulPokemon() {
    PokemonTree pokeTree = new PokemonTree();
    pokeTree.addPokemon(new Pokemon("Pikachu", "1,2,3"));
    pokeTree.addPokemon(new Pokemon("Eevee", "2,2,4"));
    pokeTree.addPokemon(new Pokemon("Huong", "1,1,1"));
    pokeTree.addPokemon(new Pokemon("Snorlax", "4,4,8"));
    pokeTree.addPokemon(new Pokemon("Charmander", "3,2,1"));
    pokeTree.addPokemon(new Pokemon("Bulbasaur", "2,2,3"));
    pokeTree.addPokemon(new Pokemon("Lapras", "7,3,5"));
    if (pokeTree.getMostPowerfulPokemon().compareTo(new Pokemon("Lapras", "7,3,5")) != 0)
      return true;
    return false;
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    if (testAddPokemonToStringSize())
      System.out.println("testAddPokemonToStringSize() failed");
    System.out.println("---");
    if (testAddPokemonAndLookup())
      System.out.println("testAddPokemonAndLookup() failed");
    System.out.println("---");
    if (testHeight())
      System.out.println("testHeight() failed");
    System.out.println("---");
    if (testGetLeastPowerfulPokemon())
      System.out.println("testGetLeastPowerfulPokemon() failed");
    System.out.println("---");
    if (testGetMostPowerfulPokemon())
      System.out.println("testGetMostPowerfulPokemon() failed");
    System.out.println("---");
    System.out.println("Done testing!");
  }

}
