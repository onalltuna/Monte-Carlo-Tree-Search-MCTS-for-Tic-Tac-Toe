# Monte Carlo Tree Search (MCTS) for Tic-Tac-Toe

This project implements the Monte Carlo Tree Search (MCTS) algorithm for playing the game of Tic-Tac-Toe. MCTS is a search algorithm specialized in solving board games, particularly in cases where the search tree size grows rapidly.

## Introduction

MCTS is employed to make decisions in the game by exploring different possible moves and evaluating their outcomes. The goal of this project is to complete the implementation of MCTS for Tic-Tac-Toe, enhancing the algorithm's performance in playing the game.

## Project Structure

The project consists of the following main components:

- Main Class: The main class executes a specified number of iterations of the MCTS algorithm to "learn" how to play Tic-Tac-Toe. This class is used to test the methods that will be implemented but does not need modification.

- Board Class: The board class describes the Tic-Tac-Toe board, maintaining the current state of the game.

- Node Class: Each node in the tree corresponds to a specific board configuration. Nodes store information about the board, accumulated scores, and the number of games that have passed through that configuration.

## Project Flow

In the beginning, the algorithm starts with an empty Tic-Tac-Toe board. There are nine possible moves the first player can make, representing each square in the 3x3 grid. Initially, a random move is selected. The program then simulates gameplay between two players who choose random squares to play in. After the simulation, the scores achieved are recorded by updating the scores of the nodes in the path from the root node to the leaf node where the simulation began.

