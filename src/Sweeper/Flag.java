package Saper;

class Flag {
    private Matrix flagMap;

    private int counOfClosedBoxes;

    void start(){
        flagMap=new Matrix(Box.CLOSED);
        counOfClosedBoxes=Ranges.getSize().x*Ranges.getSize().y;
    }
    Box get(Coord coord){
        return flagMap.get(coord);
    }

    public void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        counOfClosedBoxes--;
    }
    public void getFlag(Coord coord){
        if(Box.FLAGED==flagMap.get(coord))
            flagMap.set(coord, Box.CLOSED);
        else
            flagMap.set(coord, Box.FLAGED);
    }

    int getCountOfClosedBoxes() {
        return counOfClosedBoxes;
    }

    void setBobedToBox(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

    void setOpenToClosedBox(Coord coord) {
        if(flagMap.get(coord)==Box.CLOSED)
            flagMap.set(coord, Box.OPENED);
    }

    void setNobombToFlagedSafeBox(Coord coord) {
        if (flagMap.get(coord)==Box.FLAGED)
            flagMap.set(coord, Box.NOBOMB);
    }
}
