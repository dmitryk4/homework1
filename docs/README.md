# Homework 1 – Prithee

## Overview
This project implements the Prithee word-prompt game using the opening
sonnet of *Romeo and Juliet*. The program prints the sonnet, replaces a
randomly chosen word with underscores, and prompts the user to supply the
missing word. The game continues until the user provides three correct
answers or three incorrect answers.

## Requirements
- Java 25
- IntelliJ IDEA

## How to Run
1. Open the project in IntelliJ.
2. Ensure the Project SDK is set to Java 25.
3. Run `Main.java`.

## Program Behavior
- The sonnet is printed from the beginning each round.
- One randomly selected word is replaced with underscores.
- The program stops printing after the missing word.
- User input is compared in a case-insensitive manner with punctuation
  ignored.
- The program terminates after three correct or three incorrect guesses.

## Design Notes
- The sonnet text is stored as a class-level constant.
- The program separates display tokens from normalized answer tokens.
- Immediate repetition of the same missing word is avoided.

## Files
- `src/Main.java` – full implementation of the Prithee game.
