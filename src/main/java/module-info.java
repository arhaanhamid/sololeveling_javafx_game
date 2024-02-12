module com.sololeveling {
	requires javafx.controls;
	requires transitive javafx.graphics;
	requires transitive javafx.media;
	requires javafx.fxml;
	requires javafx.base;
	requires java.desktop;

    opens com.sololeveling to javafx.media, javafx.graphics, javafx.fxml;
    exports com.sololeveling;
}
