javac -d game/classes -sourcepath game game/server/db/DBUtil.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/server/db/PlayerBD.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/server/db/PlayerDAO.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -d game/classes -sourcepath game game/server/serv/Server.java

java --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -cp game/classes;ojdbc11.jar server/serv/Server

pause