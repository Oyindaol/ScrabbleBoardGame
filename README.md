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
- The AI player is not fully functional. We have not been able to give the player the full functionality that a Human player would have. We aim to fix it completely for the next milestone.

## Future Milestones and Deliverables
### Milestone 4
- Add a multiple-level undo and redo feature.
- Implement Java Serialization and utilize it to add a save and load feature.
- Create custom boards with the alternate placement of premium squares using XML or JSON.
- Update documentation.

## Change Log
- We made the ScrabbleGame class the ScrabbleModel class and the ScrabbleBoard class the ScrabbleBoardFrame class.
- The ScrabbleController and ScrabbleView classes were added to align with the MVC pattern.
- We moved the main method to the MainFrame class which also calls the ScrabbleStart class which begins the game by asking the player names for the game. This was a stylistic choice.
- The ScrabbleModel class was divided into two supporting classes: TileBag and ScrabbleBoard. This was another choice made in alignment with the MVC pattern and to reduce the responsibility of the ScrabbleModel class.
- The Player class was refactored to work better with the model class.
- We created the AIPlayer class to represent the computer player (“ScrabbleBot”). 

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

### December 5, 2022
- Addition of multiple game level undo and redo features.
- Save and load features.
- Creation of custom boards.
