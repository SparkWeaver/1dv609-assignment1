package com.dicegame;

public class App {

    private View view = new View();
    private Game game;

    public static void main(String[] args) {
        App app = new App();
        app.initGame();
    }

    public void initGame() {   
        String name = view.promptForPlayerName();

        game = new Game(name);
    }

    /** All methods below would be deleted before deployment, so to not having a back door TODO */
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