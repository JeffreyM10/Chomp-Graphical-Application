import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Chomp extends Application {
	//loading images
	static final String PATH = "http://cs.gettysburg.edu/~tneller/cs112/chomp/images/";
	static final Image COOKIE_IMAGE = new Image(PATH + "cookie.png");
	static final Image SKULL_IMAGE = new Image(PATH + "cookie-skull.png");
	static final Image BLACK_IMAGE = new Image(PATH + "black.png");

	private boolean Chomps = false; //on and off

	//set the board
	Button[][] board = new Button[9][9];
	Button buttons;

	/**
	 * The board gets filled with cookies and the first cell becomes a skull
	 * @param rows
	 * @param column
	 */
	public void Chomped(int rows, int column) {
		ImageView Skulls = new ImageView(SKULL_IMAGE );	  
		for(int i = 0; i <= rows; i++) {
			for(int j = 0; j <= column; j++) {
				ImageView cookies = new ImageView(COOKIE_IMAGE);
				board[i][j].setGraphic(cookies);
			}
		}
		Chomps = true;
		board[0][0].setGraphic(Skulls);
	}

	/**
	 * Taking from the cookies with a legal chomp move
	 * @param rows
	 * @param column
	 */
	public void legalChomp(int rows, int column) {
		// taking from the cookies
		for(int i = rows; i < 9; i++) {
			for(int j = column; j < 9; j++) {
				ImageView black = new ImageView(BLACK_IMAGE);
				board[i][j].setGraphic(black);
			}
		}
		if (rows == 0 && column == 0) {
			Chomps = false;
		}
	}

	/**
	 * Builds the game in JavaFX
	 */
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Chomp");


		GridPane grid1 = new GridPane();

		grid1.setAlignment(Pos.CENTER);
		grid1.setVgap(1);
		grid1.setHgap(1);


		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				ImageView black = new ImageView(BLACK_IMAGE);
				buttons = new Button("", black);
				buttons.setPrefSize(50,50); //image size
				buttons.setMinSize(50,50);
				grid1.add(buttons, j, i);
				board[i][j] = buttons;
				final int rows = i, cols = j;

				buttons.setOnMousePressed( e -> {
					boolean toStart = ((ImageView) board[0][0].getGraphic()).getImage() == BLACK_IMAGE;
					if(toStart) {
						Chomped(rows,cols);
					}
					else
						legalChomp(rows,cols);

				});
			}
		}
		Scene scenes = new Scene(grid1);
		primaryStage.setScene(scenes);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}


