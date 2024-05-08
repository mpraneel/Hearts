# Hearts
PROJECT TITLE: Hearts Card Game Program

PURPOSE OF PROJECT:
The purpose of this project is to develop a Java implementation of the classic card game Hearts. The project involves designing and implementing several classes including Card, Deck, Player, Hearts, and HeartsGUI, following the standard rules of the game.

HOW TO START THIS PROJECT:
1. Compile the source files (.java files) using the provided commands.
2. Run the HeartsGUI program from the Project4 directory using the provided command, specifying the player name.
3. Optionally, run the HeartsGUI program in testing mode by appending "-t" to the command for testing purposes.

Project Directory Structure:
  -> Project4
  -> src
  -> Card.java
  -> Deck.java
  -> Player.java
  -> Hearts.java
  -> HeartsGUI.java
  -> test
  -> CardTest.java
  -> DeckTest.java
  -> PlayerTest.java
  -> lib
  -> junit-platform-console-standalone-1.6.2.jar
  -> bin
  -> Card.class
  -> Deck.class
  -> Player.class
  -> Hearts.class
  -> HeartsGUI.class
  -> CardTest.class
  -> DeckTest.class
  -> PlayerTest.class
  -> cards (card image files: blank.gif, c2.gif, c3.gif, c4.gif, ..., s14.gif)
  -> project_docs
  -> SystemTestPlan_Hearts.pdf
  
  Compile and Run Source Code:
  $ javac -d bin src/Card.java
  $ javac -d bin -cp bin src/Deck.java
  $ javac -d bin -cp bin src/Player.java
  $ javac -d bin -cp bin src/Hearts.java
  $ javac -d bin -cp bin src/HeartsGUI.java
  $ java -cp bin HeartsGUI <player name>
  $ java -cp bin HeartsGUI <player name> -t (for testing mode)
  
  Compile Test Code on Unix/Mac:
  $ javac -d bin -cp "bin:lib/" test/CardTest.java
  $ javac -d bin -cp "bin:lib/" test/DeckTest.java
  $ javac -d bin -cp "bin:lib/*" test/PlayerTest.java
  
  Compile Test Code on Windows:
  $ javac -d bin -cp "bin;lib/" test/CardTest.java
  $ javac -d bin -cp "bin;lib/" test/DeckTest.java
  $ javac -d bin -cp "bin;lib/*" test/PlayerTest.java
  
  Run Test Code:
  $ java -jar lib/junit-platform-console-standalone-1.6.2.jar -cp bin -c CardTest
  $ java -jar lib/junit-platform-console-standalone-1.6.2.jar -cp bin -c DeckTest
  $ java -jar lib/junit-platform-console-standalone-1.6.2.jar -cp bin -c PlayerTest

AUTHORS:
Praneel Magapu

USER INSTRUCTIONS:
1. Familiarize yourself with the software requirements and rules of the Hearts card game.
2. Design your software by implementing the Card, Deck, and Player classes.
3. Compile and run the source code following the provided instructions.
4. Test your software using unit tests and system test plans provided.
5. Submit your software according to the submission instructions provided.
