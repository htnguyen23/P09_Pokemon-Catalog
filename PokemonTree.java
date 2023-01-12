//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: PokemonTree.java
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

import java.util.NoSuchElementException;

/**
 * This class implements a binary search tree (BST) which stores a set of Pokemons. The left subtree
 * contains the Pokemons who are less powerful than the Pokemon stored at a parent node. The right
 * subtree contains the Pokemons who are more powerful than the Pokemon stored at a parent node.
 *
 */
public class PokemonTree {
  private PokemonNode root; // root of this binary search tree
  private int size; // total number of Pokemons stored in this tree.

  /**
   * Checks whether this binary search tree (BST) is empty
   * 
   * @return true if this PokemonTree is empty, false otherwise
   */
  public boolean isEmpty() {
    if (size == 0)
      return true;
    return false;
  }

  /**
   * Returns the number of Pokemons stored in this BST.
   * 
   * @return the size of this PokemonTree
   */
  public int size() {
    return this.size;
  }

  /**
   * Adds a new Pokemon to this PokemonTree
   * 
   * @param newPokemon a new Pokemon to add to this BST.
   * @return true if the new was successfully added to this BST, and returns false if there is a
   *         match with this Pokemon already already stored in this BST.
   */
  public boolean addPokemon(Pokemon newPokemon) {
    boolean add = false;
    if (isEmpty()) {
      // add new Pokemon to empty PokemonTree
      this.root = new PokemonNode(newPokemon);
      this.size++;
      add = true;
    } else {
      // add new to an non-empty PokemonTree
      add = addPokemonHelper(newPokemon, this.root);
      if (add)
        this.size++;
    }
    return add;
  }

  /**
   * Recursive helper method to add a new Pokemon to a PokemonTree rooted at current.
   * 
   * @param current    The "root" of the subtree we are inserting new pokemon into.
   * @param newPokemon The Pokemon to be added to a BST rooted at current.
   * @return true if the newPokemon was successfully added to this PokemonTree, false otherwise
   */
  public static boolean addPokemonHelper(Pokemon newPokemon, PokemonNode current) {
    boolean add = false;
    // add Pokemon on right subtree if it's bigger than the current node
    if (newPokemon.compareTo(current.getPokemon()) > 0) {
      if (current.getRightChild() == null) {
        current.setRightChild(new PokemonNode(newPokemon));
        add = true;
      } else {
        // recursion to find spot to add Pokemon in order
        add = addPokemonHelper(newPokemon, current.getRightChild());
      }
    }
    // add Pokemon on left subtree if it's bigger than the current node
    else if (newPokemon.compareTo(current.getPokemon()) < 0) {
      if (current.getLeftChild() == null) {
        current.setLeftChild(new PokemonNode(newPokemon));
        add = true;
      } else {
        add = addPokemonHelper(newPokemon, current.getLeftChild());
      }
    }
    // don't add Pokemon if it's a duplicate
    else if (newPokemon.compareTo(current.getPokemon()) == 0) {
      add = false;
    }
    return add;
  }

  /**
   * Recursive helper method which returns a String representation of the BST rooted at current. An
   * example of the String representation of the contents of a PokemonTree is provided in the
   * description of the above toString() method.
   * 
   * @param current reference to the current PokemonNode within this BST.
   * @return a String representation of all the Pokemons stored in the sub-tree PokemonTree rooted
   *         at current in increasing order with respect to the CP values. Returns an empty String
   *         "" if current is null.
   */
  public static String toStringHelper(PokemonNode current) {
    String result = "";
    if (current != null) {
      // add Pokemons from left side of the subtree
      if (current.getLeftChild() != null) {
        result += toStringHelper(current.getLeftChild()) + "\n";
      }
      result += current.getPokemon().toString();
      // add Pokemons from right side of the subtree
      if (current.getRightChild() != null) {
        result += "\n" + toStringHelper(current.getRightChild());
      }
    }
    return result;
  }

  /**
   * Returns a String representation of all the Pokemons stored within this BST in the increasing
   * order, separated by a newline "\n". For instance: "[Pikachu CP:123 (A:1 S:2 D:3)]" + "\n" +
   * "[Eevee CP:224 (A:2 S:2 D:4)]" + "\n" + [Lapras CP:735 (A:7 S:3 D:5)] + "\n" + "[Mewtwo CP:999
   * (A:9 S:9 D:9)]" + "\n"
   * 
   * @return a String representation of all the Pokemons stored within this BST sorted in an
   *         increasing order with respect to the CP values. Returns an empty string "" if this BST
   *         is empty.
   */
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Search for a Pokemon (Pokemon) given the CP value as lookup key.
   * 
   * @param cp combat power of a Pokemon
   * @return the Pokemon whose CP value equals our lookup key.
   * @throws a NoSuchElementException with a descriptive error message if there is no Pokemon found
   *           in this BST having the provided CP value
   */
  public Pokemon lookup(int cp) {
    return lookupHelper(cp, root);
  }

  /**
   * Recursive helper method to lookup a Pokemon given a reference Pokemon with the same CP in the
   * subtree rooted at current
   * 
   * @param find    a reference to a Pokemon target we are lookup for a match in the BST rooted at
   *                current.
   * @param current "root" of the subtree we are looking for a match to find within it.
   * @return reference to the Pokemon stored stored in this BST which matches find.
   * @throws NoSuchElementException with a descriptive error message if there is no Pokemon whose CP
   *                                value matches target value, stored in this BST.
   */
  public static Pokemon lookupHelper(int cp, PokemonNode current) {
    Pokemon result = null;
    if (current == null)
      throw new NoSuchElementException("No Pokemon found.");
    if (current != null) {
      if (current.getPokemon().getCP() == cp)
        result = current.getPokemon();
      else if (cp < current.getPokemon().getCP())
        result = lookupHelper(cp, current.getLeftChild());
      else if (cp > current.getPokemon().getCP())
        result = lookupHelper(cp, current.getRightChild());
    }
    return result;
  }


  /**
   * Computes and returns the height of this BST, counting the number of nodes (PokemonNodes) from
   * root to the deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(root);
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current
   * 
   * @param current pointer to the current PokemonNode within a PokemonTree
   * @return height of the subtree rooted at current, counting the number of PokemonNodes
   */
  public static int heightHelper(PokemonNode current) {
    int result = 0;
    if (current == null)
      return result;
    int leftHeight = heightHelper(current.getLeftChild());
    int rightHeight = heightHelper(current.getRightChild());
    // find the maximum of the leftHeigh and rightHeight and then add 1 for the root
    result = 1 + Math.max(leftHeight, rightHeight);
    return result;
  }


  /**
   * Find the Pokemon of the least powerful Pokemon in this BST.
   * 
   * @return the least powerful Pokemon in BST, and null if BST is empty.
   */
  public Pokemon getLeastPowerfulPokemon() {
    if (this.size == 0)
      return null;
    // recursive method
    return leastPowerfulHelper(root);
  }

  /**
   * Private helper method to use recursion to implement finding least powerful Pokemon.
   * 
   * @param current PokemonNode to start search
   * @return Pokemon that's least powerful
   */
  public static Pokemon leastPowerfulHelper(PokemonNode current) {
    Pokemon result = null;
    if (current.getLeftChild() == null)
      result = current.getPokemon();
    else {
      result = leastPowerfulHelper(current.getLeftChild());
    }
    return result;
  }

  /**
   * Find the Pokemon of the most powerful Pokemon in this BST.
   * 
   * @return the most powerful Pokemon in BST, and null if BST is empty.
   */
  public Pokemon getMostPowerfulPokemon() {
    if (this.size == 0)
      return null;
    // recursive method
    return mostPowerfulHelper(root);
  }

  /**
   * Private helper method to use recursion to implement finding most powerful Pokemon.
   * 
   * @param current PokemonNode to start search
   * @return Pokemon that's most powerful
   */
  public static Pokemon mostPowerfulHelper(PokemonNode current) {
    Pokemon result = null;
    if (current.getRightChild() == null)
      result = current.getPokemon();
    else
      result = mostPowerfulHelper(current.getRightChild());
    return result;
  }


}
