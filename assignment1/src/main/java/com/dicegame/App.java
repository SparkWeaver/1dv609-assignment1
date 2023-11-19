package com.dicegame;

public class App {

    private View view;
    private Game game;

    public static void main(String[] args) {
        App app = new App();
        app.initGame();
    }

    public void initGame() {
        Rule rule = new Rule();

        view = new View(rule);
        view.printTitleAndInstructions();
        String name = view.promptForPlayerName();

        game = new Game(name, rule);
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
