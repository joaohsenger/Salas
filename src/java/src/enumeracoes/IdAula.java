/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.enumeracoes;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Marcos
 */
public enum IdAula {

    M1,
    M2,
    M3,
    M4,
    M5,
    T1,
    T2,
    T3,
    T4,
    T5,
    N1,
    N2,
    N3,
    N4,
    N5;

    public static IdAula gambi(int str) {
        IdAula name;
        switch (str) {
            case 0:
                name = IdAula.M1;
                break;
            case 1:
                name = IdAula.M2;
                break;
            case 2:
                name = IdAula.M3;
                break;
            case 3:
                name = IdAula.M4;
                break;
            case 4:
                name = IdAula.M5;
                break;
            case 5:
                name = IdAula.T1;
                break;
            case 6:
                name = IdAula.T2;
                break;
            case 7:
                name = IdAula.T3;
                break;
            case 8:
                name = IdAula.T4;
                break;
            case 9:
                name = IdAula.T5;
                break;
            case 10:
                name = IdAula.N1;
                break;
            case 11:
                name = IdAula.N2;
                break;
            case 12:
                name = IdAula.N3;
                break;
            case 13:
                name = IdAula.N4;
                break;
            case 14:
                name = IdAula.N5;
                break;
        }

        return null;
    }

    public static int converterInt(String s) {
        switch (s) {
            case "M1":
                return 0;
            case "M2":
                return 1;
            case "M3":
                return 2;
            case "M4":
                return 3;
            case "M5":
                return 4;
            case "T1":
                return 5;
            case "T2":
                return 6;
            case "T3":
                return 7;
            case "T4":
                return 8;
            case "T5":
                return 9;
            case "N1":
                return 10;
            case "N2":
                return 11;
            case "N3":
                return 12;
            case "N4":
                return 13;
            case "N5":
                return 14;
        }
        return -1;
    }

    public static boolean dataAula(Date d, String aula, Date programado) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        long seconds = (c.get(Calendar.SECOND)
                + c.get(Calendar.MINUTE) * 60
                + c.get(Calendar.HOUR) * 3600);
        int am_pm = c.get(Calendar.AM_PM);
        switch (converterInt(aula)) {
            case 0:
                if ((am_pm == 0 && seconds < 27000) || d.before(programado)) // manha
                {
                    return true;
            }
                return false;
            case 1:
                if ((am_pm == 0 && seconds < 30000) || d.before(programado)) // manha
                {
                    return true;
            }
                return false;

            case 2:
                if ((am_pm == 0 && seconds < 33000) || d.before(programado)) // manha
                {
                    return true;
            }
                return false;
            case 3:
                if ((am_pm == 0 && seconds < 37200) || d.before(programado)) // manha
                {
                    return true;
            }
                return false;
            case 4:
                if ((am_pm == 0 && seconds < 40200) || d.before(programado)) // manha
                {
                    return true;
            }
                return false;
            case 5:
                if ((am_pm == 1 && seconds < 3600) || d.before(programado) || am_pm == 0) // tarde
                {
                    return true;
            }
                return false;
            case 6:
                if ((am_pm == 1 && seconds < 6600) || d.before(programado) || am_pm == 0) // tarde
                {
                    return true;
            }
                return false;
            case 7:
                if ((am_pm == 1 && seconds < 9600) || d.before(programado) || am_pm == 0) // tarde
                {
                    return true;
            }
                return false;
            case 8:
                if ((am_pm == 1 && seconds < 13800) || d.before(programado) || am_pm == 0) // tarde
                {
                    return true;
            }
                return false;
            case 9:
                if ((am_pm == 1 && seconds < 16800) || d.before(programado) || am_pm == 0) // tarde
                {
                    return true;
            }
                return false;
            case 10:
                if ((am_pm == 1 && seconds < 24000) || d.before(programado) || am_pm == 0) // noite
                {
                    return true;
            }
                return false;
            case 11:
                if ((am_pm == 1 && seconds < 27000) || d.before(programado) || am_pm == 0) // noite
                {
                    return true;
            }
                return false;
            case 12:
                if ((am_pm == 1 && seconds < 30000) || d.before(programado) || am_pm == 0) // noite
                {
                    return true;
            }
                return false;
            case 13:
                if ((am_pm == 1 && seconds < 31200) || d.before(programado) || am_pm == 0) // noite
                {
                    return true;
            }
                return false;
            case 14:
                if ((am_pm == 1 && seconds < 34200) || d.before(programado) || am_pm == 0) // noite
                {
                    return true;
            }
                return false;
        }

        return false;

    }
}
