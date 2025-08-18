module sabishiikoto.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens sabishiikoto.tictactoe to javafx.fxml;
    exports sabishiikoto.tictactoe;
}