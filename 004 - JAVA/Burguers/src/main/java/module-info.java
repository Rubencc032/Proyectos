module jovian.Burguers {
    requires javafx.controls;
    requires javafx.fxml;

    opens jovian.Burguers to javafx.fxml;
    exports jovian.Burguers;
}
