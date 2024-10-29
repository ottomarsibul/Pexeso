Pexeso Memory Game
Pexeso uses a 6x6 grid of cards where each card has a matching pair. The goal is to find all matching pairs by flipping cards. The game ends when all pairs are found.

Class Summary
MainActivity: Sets up the game grid, handles the logic for flipping cards, matching pairs, and tracking the score.

Main Methods
1. onCreate()
  Sets up the game layout, locks screen orientation, and starts a new game with setupGame().
2. setupGame()
  Pairs each image in cardImages, shuffles them, and assigns each to a button in the grid. Sets button styles and tags for each image.
3. onCardClicked(button: ImageButton)
  Handles card selection: Prevents flipping more than two cards at once.
4. Calls checkForMatch()
  if two cards are selected.
5. checkForMatch() Compares the two selected cards:
  If they match, they stay visible, and the score increases.
  If they don’t match, they flip back after a short delay.
  Displays a win message when all pairs are found.
7. resetTurn()
   Resets selected cards to allow for a new turn.

Notes
Matching Logic: The game compares image tags to find pairs.
Game Over: When all pairs are matched, a "You are WINNER!" message appears.
