/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation;
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package src.enumeracoes;

/**
 * Enummeração Define um tipo de pessoa
 *
 * @author Giovanna Garcia
 * @author Marcos Agnes
 */
public enum TipoSala {

    LAB_MECANICA,
    TEORICA,
    LAB_INFORMATICA,
    LAB_QUIMICA;

    public static String gambi2(String s) {
        switch (s) {
            case "Laboratório de Mecânica":
                return "LAB_MECANICA";
            case "Laboratório de Informática":
                return "LAB_INFORMATICA";
            case "Laboratório de Química":
                return "LAB_QUIMICA";
            case "Teórica":
                return "TEORICA";
        }
        return "";
    }
    
    public static String gambi1(String s) {
        switch (s) {
            case "LAB_MECANICA":
                return "Laboratório de Mecânica";
            case "LAB_INFORMATICA":
                return "Laboratório de Informática";
            case "LAB_QUIMICA":
                return "Laboratório de Química";
            case "TEORICA":
                return "Teórica";
        }
        return "";
    }
}
