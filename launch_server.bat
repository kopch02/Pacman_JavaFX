javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes;.\ojdbc11.jar" game/server/serv/Server.java
javac -classpath "game/classes;.\ojdbc11.jar" game/server/db/DBUtil.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes" game/server/db/PlayerBD.java
javac --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes" game/server/db/PlayerDAO.java




java --module-path game\javafx\lib --add-modules=javafx.controls,javafx.fxml -classpath "game/classes;.\ojdbc11.jar" server.serv.Server
