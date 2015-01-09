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
 *  Enummeração 
 *      Define um tipo de pessoa
 *          
 * @author Giovanna Garcia
 * @author Marcos Agnes
 */
public enum TipoPessoa {
    RESPONSAVEL_GERAL,
    RESPONSAVEL_SETOR,
    PROFESSOR; 
    
    public static int gambi(String s) {
        switch (s) {
            case "RESPONSAVEL_GERAL":
                return 3;
            case "RESPONSAVEL_SETOR":
                return 2;
            case "PROFESSOR":
                return 1;
        }
        return 0;
    }
    
    public static String gambi2(String s) {
        switch (s) {
            case "Geral":
                return "RESPONSAVEL_GERAL";
            case "Setor":
                return "RESPONSAVEL_SETOR";
            case "Professor":
                return "PROFESSOR";
        }
        return "";
    }
}

