package com.dicegame;

public class App {

    private View view = new View();
    private Game game;

    public static void main(String[] args) {
        App app = new App();
        app.startGame();
    }

    public void startGame() {   
        String name = view.promptForPlayerName();

        game = new Game(name);
    }

    public Game getGame() {
        return game;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
