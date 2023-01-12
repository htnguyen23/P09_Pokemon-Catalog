//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: PokemonNode.java
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

public class PokemonNode {
  private Pokemon data; // data field of this PokemonNode
  private PokemonNode leftChild; // reference to the left child
  private PokemonNode rightChild; // reference to the right child

  /**
   * A one-argument constructor which sets leftChild and rightChild to null and initializes the data
   * field.
   * 
   * @param data Pokemon for this node
   * @throws IllegalArgumentException if data is null
   */
  public PokemonNode(Pokemon data) throws IllegalArgumentException {
    this.leftChild = null;
    this.rightChild = null;
    if (data == null)
      throw new IllegalArgumentException("IllegalArgumentException - data field is null");
    this.data = data;
  }

  /**
   * Get the left child of specified PokemonNode.
   * 
   * @return a reference to the left child of this PokemonNode
   */
  public PokemonNode getLeftChild() {
    return this.leftChild;
  }

  /**
   * Get the right child of specified PokemonNode.
   * 
   * @return a reference to the right child of this PokemonNode
   */
  public PokemonNode getRightChild() {
    return this.rightChild;
  }

  /**
   * Gets the Pokemon of this node
   * 
   * @return a reference to the Pokemon contained in this PokemonNode
   */
  public Pokemon getPokemon() {
    return this.data;
  }

  /**
   * Sets the left child of this PokemonNode (null values allowed)
   * 
   * @param left the reference to set the node to
   */
  public void setLeftChild(PokemonNode left) {
    this.leftChild = left;
  }

  /**
   * Sets the right child of this PokemonNode (null values allowed)
   * 
   * @param right the reference to set the node to
   */
  public void setRightChild(PokemonNode right) {
    this.rightChild = right;
  }

}
