module AreaTrinagulo {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	
	opens areaTriangulo to javafx.graphics, javafx.fxml;
}
