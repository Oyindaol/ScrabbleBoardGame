# Scrabble
A simplified version of the classic word game, Scrabble, built using Java and java.util packages.

## Milestone 3
A GUI-based version of Scrabble where players use a mouse to click and place tiles on the board to form words.

### Authors
#### Oyindamola Taiwo-Olupeka
- Worked on the ScrabbleBoardFrame and MainFrame classes as well as the ScrabbleView interface.
- Updated the design decision document to reflect the refined design. 
- Added Java Doc comments to classes and methods.

#### Oluwatomisin Ajayi
- Worked on the ScrabbleModel, ScrabbleBoard, and TileBag classes.
- Created the updated UML class and sequence diagrams.
- Added Java Doc comments to classes and methods.

#### Ese Iyamu
- Created the Player, AIPlayer and Controller classes.
- Worked on the user manual aspect of the updated documentation.
- Added Java Doc comments to classes and methods.

#### Edidiong Okon
- Worked on the ScrabbleStart classes and the test cases.
- Wrote the README file and documentation.
- Added Java Doc comments to classes and methods.

### Known Issues
- Currently the undo/redo method is functional but when we undo all the tiles played, you have to hit the clear button to reset the turn or else you get an index out of bounds error.
- The AI player is not fully functional. We have not been able to give the player the full functionality that a Human player would have. 
- There is an issue with allowing invalid words.



## Change Log
- The ScrabbleController and ScrabbleView classes were added to align with the MVC pattern.
- The Player class was refactored to work better with the model class.
- We created the AIPlayer class to represent the computer player (“ScrabbleBot”).
- We implemented the observable for better MVC communication.
- We fixed the JAR file.
- We implemented Serializable for the frame, model and player classes
- We added a menu bar with menu items (Save Game and Load Game) to the MainFrame class just for better functionality.
- Also added a load button to the first card. 
- We added methods and buttons for the save and load functions for the game.
- We added methods and buttons for the undo and redo functions for the game.


## Roadmap
### October 23, 2022
- Text-Based version of the game played with the keyboard.
- Implementation of the logic of word placement and scoring in Scrabble.

### November 11, 2022.
- GUI (View and Controller).
- Unit Testing for the Model.

### November 21, 2022.
- Creation of blank tiles, premium squares and AI players.
- Design of Scrabble move generation algorithm/method.

### December 9, 2022
- Addition of multiple game level undo and redo features.
- Save and load features.
- Creation of custom boards.
