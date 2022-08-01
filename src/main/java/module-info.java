module test.assignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
     requires java.sql;

    opens test.assignment to javafx.fxml;
    exports test.assignment;
}
