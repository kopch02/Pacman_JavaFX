javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/menu/MenuController.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/gameplay/GameLoopTimer.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/gameplay/KeyPolling.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/gameplay/Renderer.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/entity/entity/Entity.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/entity/entity/player/Player.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/entity/map/GameMap.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/menu/scores/ScoresController.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/menu/scores/Net.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/entity/entity/enemy/Ghost.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/entity/entity/points/Point.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/menu/death/DeathController.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/entity/entity/enemy/ghosts/RedGhost.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/entity/entity/enemy/ghosts/PinkGhost.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/entity/entity/enemy/ghosts/BlueGhost.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/gameplay/GamePlayController.java

javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/Main.java

java --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml  -cp game/classes Main

pause

