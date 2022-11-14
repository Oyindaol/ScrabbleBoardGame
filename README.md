Scrabble
A simplified version of the classic word game, Scrabble, built using Java and java.util packages.

Milestone 1
A text-based version of Scrabble where players use a keyboard to play the game via a console.

Authors
Ese Iyamu
Created the Player and Controller class.
Worked on the user manual aspect of documentation.
Added Java Doc comments to classes and methods.

Oyindamola Taiwo-Olupeka
Worked on the ScrabbleBoardFrame and MainFrame classes.
Wrote the design decision document.
Added Java Doc comments to classes and methods.

Oluwatomisin Ajayi
Worked on the ScrabbleModel class and ScrabbleView interface.
Created UML class and sequence diagrams.
Added Java Doc comments to classes and methods.

Edidiong Okon
Worked on the ScrabbleStart classes and the test cases.
Wrote the README file.
Added Java Doc comments to classes and methods.

Known Issues
The play, clear, pass, and end game buttons are not active currently. We believe the issue is with the controller class. We aim to fix it completely for the next milestone.

Future Milestones and Deliverables
Milestone 2
GUI-based version of the Scrabble game.
Add a GUI to the current text-based version of the game using GameView and GameController classes.
Create unit tests for the Model.
Update all UML Diagrams and documentation.

Milestone 3
Add new features and support for blank tiles, premium squares and AI players.
Add a method that returns all the possible legal moves and plays the highest scoring move.
Refine the program to make it robust and smell-free.
Update UML Diagrams and documentation accordingly.

Milestone 4
Add a multiple-level undo and redo feature.
Implement Java Serialization and utilize it to add a save and load feature.
Create custom boards with the alternate placement of premium squares using XML or JSON.
Update documentation.


Change Log
We removed the Tile and Tile rack class and imputed them into the Model class because they did not have too many responsibilities.
We made the ScrabbleGame the the Model class
We made ScrabbleBoard the frame class
We also added the controller and view classes


Roadmap
November 11, 2022
GUI (View and Controller).
Unit Testing for the Model.

November 21, 2022
Creation of blank tiles, premium squares and AI players.
Design of Scrabble move generation algorithm/method.

December 5, 2022
Addition of multiple game level undo and redo features.
Save and load features.
Creation of custom boards.
