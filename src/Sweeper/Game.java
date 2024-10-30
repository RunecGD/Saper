package Saper;

public class Game {
    private Flag flag;
    private Bomb bomb;
    private GameState state;

    public GameState getState() {
        return state;
    }

    public Game (int cols, int rows, int bombs){
        Ranges.setSize(new Coord(cols, rows));
        bomb= new Bomb(bombs);
        flag=new Flag();
    }
    public void start(){
        bomb.start();
        flag.start();
        state=GameState.PLAYED;
    }
    public Box getBox (Coord coord){
        if(flag.get(coord)==Box.OPENED)
            return bomb.get(coord);
        else
            return flag.get(coord);
    }

    public void pressRightButton(Coord coord) {
        if (getState()==GameState.PLAYED) {
            openBox(coord);
            winner();
        }
    }

    private void openBox(Coord coord){
        switch (flag.get(coord)){
            case OPENED: return;
            case FLAGED: return;
            case CLOSED:
                switch (bomb.get(coord)){
                    case ZERO: openBoxAround(coord);return;
                    case BOMB: openBombs(coord);return;
                    default: flag.setOpenedToBox(coord); return;
                }

        }
    }

    private void openBombs(Coord bombed) {
        state=GameState.BOMBED;
        flag.setBobedToBox(bombed);
        for(Coord coord:Ranges.getAllCoords())
            if(bomb.get(coord)==Box.BOMB)
                flag.setOpenToClosedBox(coord);
            else
                flag.setNobombToFlagedSafeBox(coord);
    }

    private void openBoxAround(Coord coord) {
        if (getState()==GameState.PLAYED) {
            flag.setOpenedToBox(coord);
            for (Coord around : Ranges.getCoordsAround(coord))
                openBox(around);
        }
    }

    public void pressLeftButton(Coord coord) {
        if (getState()==GameState.PLAYED)
            flag.getFlag(coord);
    }
    private void winner(){
        if(state==GameState.PLAYED){
            if(flag.getCountOfClosedBoxes()==bomb.getTotalBombs()){
                System.out.print("You win");
            }
        }
    }
    public void returnGame(){

    }
}
