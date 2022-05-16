javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes" game/menu/MenuController.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes" game/gameplay/GamePlayController.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes" game/Main.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes" game/entity/map/CollisionController.java


java --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes" Main

pause