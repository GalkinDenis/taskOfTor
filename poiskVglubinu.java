package com.example.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class poiskVglubinu {
    static boolean flagFinish = false;

    public static void main(String[] args) {

        String[] pars;
        String out = "";
        String out2 = "";

        short count = 0;


        short[][] field = new short[0][];

        short Sn = 0;
        short Sm = 0;
        short Fn = 0;
        short Fm = 0;

        boolean top = true;
        boolean bottom = true;
        boolean left = true;
        boolean right = true;

        short nRows = 0;
        short mColumns = 0;


        try (
                BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
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
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        String[] print = way(count, field, Sn, Sm, Fn, Fm, nRows, mColumns, top, bottom, left, right, out, out2).split(" ");

        if(flagFinish) {
            for (short i = 1; i <= print.length; i++) {
                System.out.print(print[print.length - i] + " ");
            }
        }else{
            System.out.println("-1");
        }
    }

    public static String way(short count, short[][] field, short Sn, short Sm, short Fn, short Fm, short nRows, short mColumns, boolean top, boolean bottom, boolean left, boolean right, String out, String out2) {

        if(count != 0){
            count++;
        }
        if (count == 0) {
            count++;

            if (Sm != field[0].length - 1) {
                if (Sn == Fn && Sm + 1 == Fm) {
                    flagFinish = true;
                    return "right";
                }
                if (field[Sn][Sm + 1] == 0) {
                    field[Sn][Sm + 1] = 1;

                    out2 = way(count, field, Sn, (short) (Sm + 1), Fn, Fm, nRows, mColumns, false, false, false, true, out, out2);
                    if (!out2.equals("")) {
                        out = out2.concat(" right");
                        return out;
                    } else {
                        out = out2.concat("");
                        return out;
                    }
                }
            } else if (Sm != field[0].length - mColumns) {

                if (Sn == Fn && field[0].length - mColumns == Fm) {
                    flagFinish = true;
                    return "right";
                }
                if (field[Sn][field[0].length - mColumns] == 0) {
                    field[Sn][field[0].length - mColumns] = 1;
                    out2 = way(count, field, Sn, (short) (field[0].length - mColumns), Fn, Fm, nRows, mColumns, false, false, false, true, out, out2);
                    if (!out2.equals("")) {
                        out = out2.concat(" right");
                        return out;
                    } else {
                        out = out2.concat("");
                        return out;
                    }
                }
            }


            if (Sm != field[0].length - mColumns) {
                if (Sn == Fn && Sm - 1 == Fm) {
                    flagFinish = true;
                    return "left";
                }
                if (field[Sn][Sm - 1] == 0) {
                    field[Sn][Sm - 1] = 1;

                    out2 = way(count, field, Sn, (short) (Sm - 1), Fn, Fm, nRows, mColumns, false, false, true, false, out, out2);
                    if (!out2.equals("")) {
                        out = out2.concat(" left");
                        return out;
                    } else {
                        out = out2.concat("");
                        return out;
                    }
                }
            } else if (Sm != field[0].length - 1) {
                if (Sn == Fn && field[0].length - 1 == Fm) {
                    flagFinish = true;
                    return "left";
                }
                if (field[Sn][field[0].length - 1] == 0) {
                    field[Sn][field[0].length - 1] = 1;

                    out2 = way(count, field, Sn, (short) (field[0].length - 1), Fn, Fm, nRows, mColumns, false, false, true, false, out, out2);
                    if (!out2.equals("")) {
                        out = out2.concat(" left");
                        return out;
                    } else {
                        out = out2.concat("");
                        return out;
                    }
                }
            }

            if (Sn != field.length - nRows) {
                if (Sn - 1 == Fn && Sm == Fm) {
                    flagFinish = true;
                    return "top";
                }
                if (field[Sn - 1][Sm] == 0) {
                    field[Sn - 1][Sm] = 1;

                    out2 = way(count, field, (short) (Sn - 1), Sm, Fn, Fm, nRows, mColumns, true, false, false, false, out, out2);
                    if (!out2.equals("")) {
                        out = out2.concat(" top");
                        return out;
                    } else {
                        out = out2.concat("");
                        return out;
                    }
                }
            } else if (Sn != field.length - 1) {

                if (field.length - 1 == Fn && Sm == Fm) {
                    flagFinish = true;
                    return "top";
                }
                if (field[field.length - 1][Sm] == 0) {
                    field[field.length - 1][Sm] = 1;

                    out2 = way(count, field, (short) (field.length - 1), Sm, Fn, Fm, nRows, mColumns, true, false, false, false, out, out2);
                    if (!out2.equals("")) {
                        out = out2.concat(" top");
                        return out;
                    } else {
                        out = out2.concat("");
                        return out;
                    }
                }
            }


            if (Sn != field.length - 1) {

                if (Sn + 1 == Fn && Sm == Fm) {
                    flagFinish = true;
                    return "bottom";
                }
                if (field[Sn + 1][Sm] == 0) {
                    field[Sn + 1][Sm] = 1;

                    out2 = way(count, field, (short) (Sn + 1), Sm, Fn, Fm, nRows, mColumns, false, true, false, false, out, out2);
                    if (!out2.equals("")) {
                        out = out2.concat(" bottom");
                        return out;
                    } else {
                        out = out2.concat("");
                        return out;
                    }
                }
            } else if (Sn != field.length - nRows) {
                if (field.length - nRows == Fn && Sm == Fm) {
                    flagFinish = true;
                    return "bottom";
                }
                if (field[field.length - nRows][Sm] == 0) {
                    field[field.length - nRows][Sm] = 1;

                    out2 = way(count, field, (short) (field.length - nRows), Sm, Fn, Fm, nRows, mColumns, false, true, false, false, out, out2);
                    if (!out2.equals("")) {
                        out = out2.concat(" bottom");
                        return out;
                    } else {
                        out = out2.concat("");
                        return out;
                    }
                }
            }

        }


        if (!left) {
            if (Sm != field[0].length - 1) {
                if (Sn == Fn && Sm + 1 == Fm) {
                    flagFinish = true;
                    return "right";
                }
                if (field[Sn][Sm + 1] == 0) {

                    field[Sn][Sm + 1] = count;

                    out2 = way(count, field, Sn, (short) (Sm + 1), Fn, Fm, nRows, mColumns, false, false, false, true, out, out2);
                    if (!out2.equals("")) {
                        out = out2.concat(" right");
                        return out;
                    } else {
                        out = out2.concat("");
                    }
                }
            } else if (Sm != field[0].length - mColumns) {
                if (Sn == Fn && field[0].length - mColumns == Fm) {
                    flagFinish = true;
                    return "right";
                }
                if (field[Sn][field[0].length - mColumns] == 0) {
                    field[Sn][field[0].length - mColumns] = count;
                    out2 = way(count, field, Sn, (short) (field[0].length - mColumns), Fn, Fm, nRows, mColumns, false, false, false, true, out, out2);
                    if (!out2.equals("")) {
                        out = out2.concat(" right");
                        return out;
                    } else {
                        out = out2.concat("");
                    }
                }
            }
        }


        if (!right) {
            if (Sm != field[0].length - mColumns) {
                if (Sn == Fn && Sm - 1 == Fm) {
                    flagFinish = true;
                    return "left";
                }
                if (field[Sn][Sm - 1] == 0) {
                    field[Sn][Sm - 1] = count;
                    out2 = way(count, field, Sn, (short) (Sm - 1), Fn, Fm, nRows, mColumns, false, false, true, false, out, out2);
                    if (!out2.equals("")) {
                        out = out2.concat(" left");
                        return out;
                    } else {
                        out = out2.concat("");
                    }
                }
            } else if (Sm != field[0].length - 1) {
                if (Sn == Fn && field[0].length - 1 == Fm) {
                    flagFinish = true;
                    return "left";
                }
                if (field[Sn][field[0].length - 1] == 0) {

                    field[Sn][field[0].length - 1] = count;

                    out2 = way(count, field, Sn, (short) (field[0].length - 1), Fn, Fm, nRows, mColumns, false, false, true, false, out, out2);
                    if (!out2.equals("")) {
                        out = out2.concat(" left");
                        return out;
                    } else {
                        out = out2.concat("");
                        //return out;
                    }
                }
            }
        }

        if (!bottom) {
            if (Sn != field.length - nRows) {
                if (Sn - 1 == Fn && Sm == Fm) {
                    flagFinish = true;
                    return "top";
                }
                if (field[Sn - 1][Sm] == 0) {
                    field[Sn - 1][Sm] = count;
                    out2 = way(count, field, (short) (Sn - 1), Sm, Fn, Fm, nRows, mColumns, true, false, false, false, out, out2);
                    if (!out2.equals("")) {
                        out = out2.concat(" top");
                        return out;
                    } else {
                        out = out2.concat("");
                    }
                }
            } else if (Sn != field.length - 1) {
                if (field.length - 1 == Fn && Sm == Fm) {
                    flagFinish = true;
                    return "top";
                }
                if (field[field.length - 1][Sm] == 0) {
                    field[field.length - 1][Sm] = count;
                    out2 = way(count, field, (short) (field.length - 1), Sm, Fn, Fm, nRows, mColumns, true, false, false, false, out, out2);
                    if (!out2.equals("")) {
                        out = out2.concat(" top");
                        return out;
                    } else {
                        out = out2.concat("");
                    }
                }
            }
        }


        if(!top){
            if (Sn != field.length - 1) {
                if (Sn + 1 == Fn && Sm == Fm) {
                    flagFinish = true;
                    return "bottom";
                }
                if (field[Sn + 1][Sm] == 0) {
                    field[Sn + 1][Sm] = count;
                    out2 = way(count, field, (short) (Sn + 1), Sm, Fn, Fm, nRows, mColumns, false, true, false, false, out, out2);
                    if (!out2.equals("")) {
                        out = out2.concat(" bottom");
                        return out;
                    } else {
                        out = out2.concat("");
                        //return out;
                    }
                }
            } else if (Sn != field.length - nRows) {
                if (field.length - nRows == Fn && Sm == Fm) {
                    return "bottom";
                }
                if (field[field.length - nRows][Sm] == 0) {
                    field[field.length - nRows][Sm] = count;
                    out2 = way(count, field, (short) (field.length - nRows), Sm, Fn, Fm, nRows, mColumns, false, true, false, false, out, out2);
                    if (!out2.equals("")) {
                        out = out2.concat(" bottom");
                        return out;
                    } else {
                        out = out2.concat("");
                    }
                }
            }
        }




        return "";
    }




}
