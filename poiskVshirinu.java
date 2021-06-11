import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class last {

    static int[][] field;

    public static void main(String[] args) {

        int count = 4;

        LinkedList<Integer> points = new LinkedList<>();

        int Sn = 0;
        int Sm = 0;
        int Fn = 0;
        int Fm = 0;

        int nRows = 0;
        int mColumns = 0;

        Scanner scan = new Scanner(System.in);
            nRows = scan.nextInt();
            mColumns = scan.nextInt();

        field = new int[nRows][mColumns];

            Sn = scan.nextInt();
            Sm = scan.nextInt();
            Fn = scan.nextInt();
            Fm = scan.nextInt();

        for (short g = 0; g < nRows; g++) {
                for (short x = 0; x < mColumns; x++) {
                    field[g][x] = scan.nextInt();
                }
        }

        field[Sn][Sm] = 3;

        //первая выборка шагов(влево, врпаво, вверх, вниз) из матрицы.
        if (Sm != mColumns - 1) {
            if (field[Sn][Sm + 1] == 0) {
                field[Sn][Sm + 1] = count;
                points.add(Sn);
                points.add(Sm + 1);
            }
        } else {
            if (field[Sn][0] == 0) {
                field[Sn][0] = count;
                points.add(Sn);
                points.add(0);
            }
        }

        if (Sm != (short) 0) {
            if (field[Sn][Sm - 1] == 0) {
                field[Sn][Sm - 1] = count;
                points.add(Sn);
                points.add(Sm - 1);
            }
        } else {
            if (field[Sn][mColumns - 1] == 0) {
                field[Sn][mColumns - 1] = count;
                points.add(Sn);
                points.add(mColumns - 1);
            }
        }

        if (Sn != nRows - 1) {
            if (field[Sn + 1][Sm] == 0) {
                field[Sn + 1][Sm] = count;
                points.add(Sn + 1);
                points.add(Sm);
            }
        } else {
            if (field[0][Sm] == 0) {
                field[0][Sm] = count;
                points.add(0);
                points.add(Sm);
            }
        }

        if (Sn != (short) 0) {
            if (field[Sn - 1][Sm] == 0) {
                field[Sn - 1][Sm] = count;
                points.add(Sn - 1);
                points.add(Sm);
            }
        } else {
            if (field[nRows - 1][Sm] == 0) {
                field[nRows - 1][Sm] = count;
                points.add(nRows - 1);
                points.add(Sm);
            }
        }

        int size = points.size();

        //последующее распространение волны
        while (!points.isEmpty()) {
            count++;

            for (short sh = 0; sh < size; sh += 2) {

                if (points.get(1) != mColumns - 1) {
                    if (field[points.get(0)][points.get(1) + 1] == 0) {
                        field[points.get(0)][points.get(1) + 1] = count;
                        points.add(points.get(0));
                        points.add(points.get(1) + 1);
                    }
                } else {
                    if (field[points.get(0)][0] == 0) {
                        field[points.get(0)][0] = count;
                        points.add(points.get(0));
                        points.add(0);
                    }
                }

                if (points.get(1) != 0) {
                    if (field[points.get(0)][points.get(1) - 1] == 0) {
                        field[points.get(0)][points.get(1) - 1] = count;
                        points.add(points.get(0));
                        points.add(points.get(1) - 1);
                    }
                } else {
                    if (field[points.get(0)][mColumns - 1] == 0) {
                        field[points.get(0)][mColumns - 1] = count;
                        points.add(points.get(0));
                        points.add(mColumns - 1);
                    }
                }

                if (points.get(0) != nRows - 1) {
                    if (field[points.get(0) + 1][points.get(1)] == 0) {
                        field[points.get(0) + 1][points.get(1)] = count;
                        points.add(points.get(0) + 1);
                        points.add(points.get(1));

                    }
                } else {
                    if (field[0][points.get(1)] == 0) {
                        field[0][points.get(1)] = count;
                        points.add(0);
                        points.add(points.get(1));
                    }
                }

                if (points.get(0) != 0) {
                    if (field[points.get(0) - 1][points.get(1)] == 0) {
                        field[points.get(0) - 1][points.get(1)] = count;
                        points.add(points.get(0) - 1);
                        points.add(points.get(1));
                    }
                } else {
                    if (field[nRows - 1][points.get(1)] == 0) {
                        field[nRows - 1][points.get(1)] = count;
                        points.add(nRows - 1);
                        points.add(points.get(1));
                    }
                }

                points.remove(0);
                points.remove(0);

            }
            size = points.size();
        }

        count = 0;

        //Если финишь достигнут то ->
        if (field[Fn][Fm] != 0) {
            StringBuffer str = new StringBuffer();
            //восстановление кратчайшего пути
            for (; ; ) {


                if (Fm != mColumns - 1) {
                    if (field[Fn][Fm + 1] == field[Fn][Fm] - 1) {
                        str.insert(0, "W");
                        Fm++;
                        continue;
                    } else {
                        count++;
                    }
                } else {
                    if (field[Fn][0] == field[Fn][mColumns - 1] - 1) {
                        str.insert(0, "W");
                        Fm = 0;
                        continue;
                    } else {
                        count++;
                    }
                }


                if (Fm != 0) {
                    if (field[Fn][Fm - 1] == field[Fn][Fm] - 1) {
                        str.insert(0, "E");
                        Fm--;
                        continue;
                    } else {
                        count++;
                    }
                } else {
                    if (field[Fn][mColumns - 1] == field[Fn][0] - 1) {
                        str.insert(0, "E");
                        Fm = (short) (mColumns - 1);
                        continue;
                    } else {
                        count++;
                    }
                }


                if (Fn != nRows - 1) {
                    if (field[Fn + 1][Fm] == field[Fn][Fm] - 1) {
                        str.insert(0, "N");
                        Fn++;
                        continue;
                    } else {
                        count++;
                    }
                } else {
                    if (field[0][Fm] == field[nRows - 1][Fm] - 1) {
                        str.insert(0, "N");
                        Fn = 0;
                        continue;
                    } else {
                        count++;
                    }
                }


                if (Fn != 0) {
                    if (field[Fn - 1][Fm] == field[Fn][Fm] - 1) {
                        str.insert(0, "S");
                        Fn--;
                        continue;
                    } else {
                        count++;
                    }
                } else {
                    if (field[nRows - 1][Fm] == field[0][Fm] - 1) {
                        str.insert(0, "S");
                        Fn = (short) (nRows - 1);
                    } else {
                        count++;
                    }
                }

                if (count == 4) {
                    break;
                }
                count = 0;
            }
            System.out.println(str);
        }else{
            System.out.println("-1");
        }
    }
}
