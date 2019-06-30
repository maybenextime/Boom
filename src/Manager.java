
public class Manager {
    Map map;
    int[][] listObjs = new int[15][15];
    public Manager(){
        map= new Map("map1");
        init();

    }
    public void init(){
        listObjs=map.listObjs;
    }


}
