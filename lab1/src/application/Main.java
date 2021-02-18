package application;
	
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;


public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		 Group root = new Group();
		 Scene scene = new Scene(root, 400, 235);
		//////////////////////////////////////////////////////
		
		 scene.setFill(Color.rgb(128, 128, 0)); 
		 
		 Rectangle r = new Rectangle(26, 10, 350, 200); 
		 root.getChildren().add(r); 
		 r.setFill(Color.rgb(128, 0, 0));
		 
		 Line l1 = new Line(30, 67, 371, 67);
		 l1.setStrokeWidth(10);
		 l1.setStroke(Color.YELLOW);
		 root.getChildren().add(l1);
		 
		 Line l2 = new Line(30, 140, 371, 140);
		 l2.setStrokeWidth(10);
		 l2.setStroke(Color.YELLOW);
		 root.getChildren().add(l2);
		 
		 Line l3 = new Line(207, 15, 207, 67);
		 l3.setStrokeWidth(10);
		 l3.setStroke(Color.YELLOW);
		 root.getChildren().add(l3);
		 
		 Line l4 = new Line(207, 149, 207, 205);
		 l4.setStrokeWidth(10);
		 l4.setStroke(Color.YELLOW);
		 root.getChildren().add(l4);
		 
		 Line l5 = new Line(120, 75, 120, 140);
		 l5.setStrokeWidth(10);
		 l5.setStroke(Color.YELLOW);
		 root.getChildren().add(l5);
		 
		 Line l6 = new Line(290, 75, 290, 140);
		 l6.setStrokeWidth(10);
		 l6.setStroke(Color.YELLOW);
		 root.getChildren().add(l6);

		//////////////////////////////////////////////////////
		 primaryStage.setScene(scene);
		 primaryStage.show();
	}
}
