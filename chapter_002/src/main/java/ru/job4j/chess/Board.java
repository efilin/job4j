package ru.job4j.chess;

public class Board {

    Figure[] figures = new Figure[32];
    private int index = 0;

   public void add(Figure figure) {
       figures[index++] = figure;
   }

    public boolean move(Cell source, Cell dest)
        throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        if (getFigureIndexByCell(source) == -1) {
            throw new FigureNotFoundException();
        }
        if (!possibleToMove(source, dest)) {
            throw new ImpossibleMoveException();
        }
        if (occupiedWay(source, dest)) {
            throw new OccupiedWayException();
        }
        figures[getFigureIndexByCell(source)] = figures[getFigureIndexByCell(source)].copy(dest);
            return true;
    }
    public int getFigureIndexByCell(Cell cell) {
       int result = -1;
        for (int i = 0; i != index; i++) {
            if (cell.x == figures[i].position.x && cell.y == figures[i].position.y) {
                result = i;
                break;
            }
        }
        return result;
    }

    public boolean possibleToMove(Cell source, Cell dest) {
       return ((source.x) < 8 && (source.x) > -1 && (source.y) < 8 && (source.y) > 0);
        /*if ((source.x) < 8 && (source.x) > -1 && (source.y) < 8 && (source.y) > 0) {
            return true;
        } else {
            return false;
        }*/
    }

    public boolean occupiedWay(Cell source, Cell dest) throws ImpossibleMoveException {
       boolean result = false;
        for (Cell cell: figures[getFigureIndexByCell(source)].way(source, dest)) {
            for (int i = 0; i != index; i++) {
                if (cell.x == figures[i].position.x && cell.y == figures[i].position.y) {
                    result = true;
                }
            }
        } return result;
    }



}

    /*

8. Метод должен проверить
   - Что в заданной ячейки есть фигура. если нет. то выкинуть исключение
   - Если фигура есть. Проверить может ли она так двигаться. Если нет то упадет исключение
   - Проверить что полученный путь. не занят фигурами. Если занят выкинуть исключение
   - Если все отлично. Записать в ячейку новое новое положение Figure figure.copy(Cell dest)

9. Изначально сделайте. только движения фигуры слон и покажите промежуточный результат.*/




