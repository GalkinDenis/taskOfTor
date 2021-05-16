package com.example.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class last {

    static short[][] field;

    public static void main(String[] args) {

        String[] pars;

        short count = 4;

        short n;
        short m;

        LinkedList<Short> points = new LinkedList<>();

        short Sn = 0;
        short Sm = 0;
        short Fn = 0;
        short Fm = 0;

        short nRows = 0;
        short mColumns = 0;

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {

            //ввод строки с размером матрицы
            pars = bf.readLine().split(" ");

            nRows = Short.parseShort(pars[0]);
            mColumns = Short.parseShort(pars[1]);

            field = new short[nRows][mColumns];

            //ввод строки с координатами финиша и старта на матрице
            pars = bf.readLine().split(" ");
            Sn = Short.parseShort(pars[0]);
            Sm = Short.parseShort(pars[1]);
            Fn = Short.parseShort(pars[2]);
            Fm = Short.parseShort(pars[3]);

            //заполнение матрицы
            for (short g = 0; g < nRows; g++) {
                pars = bf.readLine().split(" ");
                for (short x = 0; x < mColumns; x++) {
                    field[g][x] = Short.parseShort(pars[x]);
                }
            }

            field[Sn][Sm] = 3;

        } catch (IOException e) {
            e.printStackTrace();
        }

        //первая выборка шагов(влево, врпаво, вверх, вниз) из матрицы.
        if (Sm != mColumns - 1) {
            if (field[Sn][Sm + 1] == 0) {
                field[Sn][Sm + 1] = count;
                points.add(Sn);
                points.add((short) (Sm + 1));
            }
        } else {
            if (field[Sn][0] == 0) {
                field[Sn][0] = count;
                points.add(Sn);
                points.add((short) 0);
            }
        }

        if (Sm != (short) 0) {
            if (field[Sn][Sm - 1] == 0) {
                field[Sn][Sm - 1] = count;
                points.add(Sn);
                points.add((short) (Sm - 1));
            }
        } else {
            if (field[Sn][mColumns - 1] == 0) {
                field[Sn][mColumns - 1] = count;
                points.add(Sn);
                points.add((short) (mColumns - 1));
            }
        }

        if (Sn != nRows - 1) {
            if (field[Sn + 1][Sm] == 0) {
                field[Sn + 1][Sm] = count;
                points.add((short) (Sn + 1));
                points.add(Sm);
            }
        } else {
            if (field[0][Sm] == 0) {
                field[0][Sm] = count;
                points.add((short) 0);
                points.add(Sm);
            }
        }

        if (Sn != (short) 0) {
            if (field[Sn - 1][Sm] == 0) {
                field[Sn - 1][Sm] = count;
                points.add((short) (Sn - 1));
                points.add(Sm);
            }
        } else {
            if (field[nRows - 1][Sm] == 0) {
                field[nRows - 1][Sm] = count;
                points.add((short) (nRows - 1));
                points.add(Sm);
            }
        }

        short size = (short) points.size();

        //последующее распространение волны
        while (!points.isEmpty()) {
            count++;

            for (short sh = 0; sh < size; sh += 2) {

                n = points.get(0);
                points.remove(0);
                m = points.get(0);
                points.remove(0);

                if (m != mColumns - 1) {
                    if (field[n][m + 1] == 0) {
                        field[n][m + 1] = count;
                        points.add(n);
                        points.add((short) (m + 1));
                    }
                } else {
                    if (field[n][0] == 0) {
                        field[n][0] = count;
                        points.add(n);
                        points.add((short) 0);
                    }
                }

                if (m != 0) {
                    if (field[n][m - 1] == 0) {
                        field[n][m - 1] = count;
                        points.add(n);
                        points.add((short) (m - 1));
                    }
                } else {
                    if (field[n][mColumns - 1] == 0) {
                        field[n][mColumns - 1] = count;
                        points.add(n);
                        points.add((short) (mColumns - 1));
                    }
                }

                if (n != nRows - 1) {
                    if (field[n + 1][m] == 0) {
                        field[n + 1][m] = count;
                        points.add((short) (n + 1));
                        points.add(m);

                    }
                } else {
                    if (field[0][m] == 0) {
                        field[0][m] = count;
                        points.add((short) 0);
                        points.add(m);
                    }
                }

                if (n != 0) {
                    if (field[n - 1][m] == 0) {
                        field[n - 1][m] = count;
                        points.add((short) (n - 1));
                        points.add(m);
                    }
                } else {
                    if (field[nRows - 1][m] == 0) {
                        field[nRows - 1][m] = count;
                        points.add((short) (nRows - 1));
                        points.add(m);
                    }
                }
            }
            size = (short) points.size();
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