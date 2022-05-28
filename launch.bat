javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes" game/menu/MenuController.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes" game/gameplay/GameLoopTimer.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes" game/gameplay/KeyPolling.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes" game/gameplay/Renderer.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes" game/entity/entity/Entity.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes" game/entity/entity/player/Player.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes" game/gameplay/GamePlayController.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes" game/Main.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes" game/entity/map/GameMap.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes" game/entity/entity/enemy/Ghost.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes" game/entity/entity/points/Point.java



java --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes" Main
