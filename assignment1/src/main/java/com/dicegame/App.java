package com.dicegame;

public class App {
    private Game game;
    private View view;

    public static void main(String[] args) {
        App app = new App();
        app.startGame();
    }

    public void startGame() {
        view = new View();
        game = new Game("Jon", 1);
    }

    public Game getGame() {
        return game;
    }

    public View getView() {
        return view;
    }
}
