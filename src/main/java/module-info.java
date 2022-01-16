module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.gb.bcg to javafx.fxml;
    exports ru.gb.bcg;
}