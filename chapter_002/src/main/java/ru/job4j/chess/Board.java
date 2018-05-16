package ru.job4j.chess;

public class Board {

    Figure[] figures = new Figure[32];
    private int index = 0;



   public void add(Figure figure) {
       figures[index++] = figure;
   }



    public boolean move(Cell source, Cell dest)
        throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException{
        if (true) {
            throw new FigureNotFoundException();
        }
        if (true) {
            throw new ImpossibleMoveException();
        }
        if (true) {
            throw new OccupiedWayException();
        }
            return false;
    }


    /*

8. Метод должен проверить
   - Что в заданной ячейки есть фигура. если нет. то выкинуть исключение
   - Если фигура есть. Проверить может ли она так двигаться. Если нет то упадет исключение
   - Проверить что полученный путь. не занят фигурами. Если занят выкинуть исключение
   - Если все отлично. Записать в ячейку новое новое положение Figure figure.copy(Cell dest)

9. Изначально сделайте. только движения фигуры слон и покажите промежуточный результат.*/



}
