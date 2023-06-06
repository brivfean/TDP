/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdp;

import java.util.List;

/**
 *
 * @author bruni
 */
public class Parser {
    private final List<Token> tokens;

    private final Token identificador = new Token(TipoToken.IDENTIFICADOR, "");
    private final Token select = new Token(TipoToken.SELECT, "select");
    private final Token from = new Token(TipoToken.FROM, "from");
    private final Token distinct = new Token(TipoToken.DISTINCT, "distinct");
    private final Token coma = new Token(TipoToken.COMA, ",");
    private final Token punto = new Token(TipoToken.PUNTO, ".");
    private final Token asterisco = new Token(TipoToken.ASTERISCO, "*");
    private final Token finCadena = new Token(TipoToken.EOF, "");

    private int i = 0;
    //CONTADORES DE LOS STACK
    private short QN = 0
            , DN = 0
            , PN = 0
            , AN = 0
            , A1N = 0
            , A2N = 0
            , A3N = 0
            , TN = 0
            , T1N = 0
            , T2N = 0
            , T3N = 0;
    private boolean hayErrores = false;
    //STACK
    private String[] Q = new String[4];
    private String[] D = new String[2];
    private String[] P = new String[1];
    private String[] A = new String[2];
    private String[] A1 = new String[2];
    private String[] A2 = new String[2];
    private String[] A3 = new String[2];
    private String[] T = new String[2];
    private String[] T1 = new String[2];
    private String[] T2 = new String[2];
    private String[] T3 = new String[1];
    

    private Token preanalisis;

    public Parser(List<Token> tokens){
        this.tokens = tokens;
    }

    public void parse(){
        i = 0;
        QN = 0;
        DN = 0;
        PN = 0;
        AN = 0;
        A1N = 0;
        A2N = 0;
        A3N = 0;
        TN = 0;
        T1N = 0;
        T2N = 0;
        T3N = 0;
        preanalisis = tokens.get(i);
        if(preanalisis.equals(select)){
            //Q
            coincidir(select);
            Q[QN] = "SELECT";
            QN++;
        }
        if(preanalisis.equals(distinct)){
            //D
            coincidir(distinct);
            D[DN] = "DISTINCT";
            DN++;
        }else{
            if(preanalisis.equals(asterisco)){
            //P
            coincidir(asterisco);
            P[PN] = "*";
            D[DN] = "P";
            PN++;
            DN++;
            }else{
                if(preanalisis.equals(identificador)){
                    //A2
                    coincidir(identificador);
                    A2[A2N] = "ID";
                    A2N++;
                }
                if(preanalisis.equals(punto)){
                    //A3
                    coincidir(punto);
                    A3[A3N] = ".";
                    A3N++;
                    if(preanalisis.equals(identificador)){
                    //A3 p 2
                    coincidir(identificador);
                    A3[A3N] = "ID";
                    A3N++;
                    A2[A2N] = "A3";
                    A2N++;
                    }
                }else if(A3N == 0){
                    //Epsilon
                    A3N=2;
                    A2[A2N] = "A3";
                    A2N++;
                }
                if(preanalisis.equals(coma)){
                    //A1
                    coincidir(coma);
                    A1[A1N] = ",";
                    A1N++;
                    //if A entonces repetir a
                    A1N++;
                }else{
                    //Epsilon
                    A1N=2;
                }
                if(A2N == 2 && (A1N == 2 && A3N == 2)){
                //A
                P[PN] = "A";
                PN++;
                A[AN] = "A2";
                AN++;
                A[AN] = "A1";
                AN++;
                D[DN] = "P";
                DN++;
                Q[QN] = "D";
                QN++;
                }
                
                //---------------------------------------------
                
                AN = 0;
                A1N = 0;
                A2N = 0;
                A3N = 0;
                
                if(preanalisis.equals(identificador)){
                    //A2
                    coincidir(identificador);
                    A2[A2N] = "ID";
                    A2N++;
                }
                if(preanalisis.equals(punto)){
                    //A3
                    coincidir(punto);
                    A3[A3N] = ".";
                    A3N++;
                    if(preanalisis.equals(identificador)){
                    //A3 p 2
                    coincidir(identificador);
                    A3[A3N] = "ID";
                    A3N++;
                    A2[A2N] = "A3";
                    A2N++;
                    }
                }else if(A3N == 0){
                    //Epsilon
                    A3N=2;
                    A2[A2N] = "A3";
                    A2N++;
                }
                if(preanalisis.equals(coma)){
                    //A1
                    coincidir(coma);
                    A1[A1N] = ",";
                    A1N++;
                    //if A entonces repetir a
                    A1N++;
                }else{
                    //Epsilon
                    A1N=2;
                }
                if(A2N == 2 && (A1N == 2 && A3N == 2)){
                //A
                P[PN] = "A";
                PN++;
                A[AN] = "A2";
                AN++;
                A[AN] = "A1";
                AN++;
                
                }
                
                //---------------------------------------------
                
                AN = 0;
                A1N = 0;
                A2N = 0;
                A3N = 0;
                
                if(preanalisis.equals(identificador)){
                    //A2
                    coincidir(identificador);
                    A2[A2N] = "ID";
                    A2N++;
                }
                if(preanalisis.equals(punto)){
                    //A3
                    coincidir(punto);
                    A3[A3N] = ".";
                    A3N++;
                    if(preanalisis.equals(identificador)){
                    //A3 p 2
                    coincidir(identificador);
                    A3[A3N] = "ID";
                    A3N++;
                    A2[A2N] = "A3";
                    A2N++;
                    }
                }else if(A3N == 0){
                    //Epsilon
                    A3N=2;
                    A2[A2N] = "A3";
                    A2N++;
                }
                if(preanalisis.equals(coma)){
                    //A1
                    coincidir(coma);
                    A1[A1N] = ",";
                    A1N++;
                    //if A entonces repetir a
                    A1N++;
                }else{
                    //Epsilon
                    A1N=2;
                }
                if(A2N == 2 && (A1N == 2 && A3N == 2)){
                //A
                P[PN] = "A";
                PN++;
                A[AN] = "A2";
                AN++;
                A[AN] = "A1";
                AN++;
                
                }
                
                //---------------------------------------------
                
                AN = 0;
                A1N = 0;
                A2N = 0;
                A3N = 0;
                
                if(preanalisis.equals(identificador)){
                    //A2
                    coincidir(identificador);
                    A2[A2N] = "ID";
                    A2N++;
                }
                if(preanalisis.equals(punto)){
                    //A3
                    coincidir(punto);
                    A3[A3N] = ".";
                    A3N++;
                    if(preanalisis.equals(identificador)){
                    //A3 p 2
                    coincidir(identificador);
                    A3[A3N] = "ID";
                    A3N++;
                    A2[A2N] = "A3";
                    A2N++;
                    }
                }else if(A3N == 0){
                    //Epsilon
                    A3N=2;
                    A2[A2N] = "A3";
                    A2N++;
                }
                if(preanalisis.equals(coma)){
                    //A1
                    coincidir(coma);
                    A1[A1N] = ",";
                    A1N++;
                    //if A entonces repetir a
                    A1N++;
                }else{
                    //Epsilon
                    A1N=2;
                }
                if(A2N == 2 && (A1N == 2 && A3N == 2)){
                //A
                P[PN] = "A";
                PN++;
                A[AN] = "A2";
                AN++;
                A[AN] = "A1";
                AN++;
                
                }
                
                //---------------------------------------------
                
                AN = 0;
                A1N = 0;
                A2N = 0;
                A3N = 0;
                
                if(preanalisis.equals(identificador)){
                    //A2
                    coincidir(identificador);
                    A2[A2N] = "ID";
                    A2N++;
                }
                if(preanalisis.equals(punto)){
                    //A3
                    coincidir(punto);
                    A3[A3N] = ".";
                    A3N++;
                    if(preanalisis.equals(identificador)){
                    //A3 p 2
                    coincidir(identificador);
                    A3[A3N] = "ID";
                    A3N++;
                    A2[A2N] = "A3";
                    A2N++;
                    }
                }else if(A3N == 0){
                    //Epsilon
                    A3N=2;
                    A2[A2N] = "A3";
                    A2N++;
                }
                if(preanalisis.equals(coma)){
                    //A1
                    coincidir(coma);
                    A1[A1N] = ",";
                    A1N++;
                    //if A entonces repetir a
                    A1N++;
                }else{
                    //Epsilon
                    A1N=2;
                }
                if(A2N == 2 && (A1N == 2 && A3N == 2)){
                //A
                P[PN] = "A";
                PN++;
                A[AN] = "A2";
                AN++;
                A[AN] = "A1";
                AN++;
                
                }
                
                //---------------------------------------------
                
                AN = 0;
                A1N = 0;
                A2N = 0;
                A3N = 0;
                
                if(preanalisis.equals(identificador)){
                    //A2
                    coincidir(identificador);
                    A2[A2N] = "ID";
                    A2N++;
                }
                if(preanalisis.equals(punto)){
                    //A3
                    coincidir(punto);
                    A3[A3N] = ".";
                    A3N++;
                    if(preanalisis.equals(identificador)){
                    //A3 p 2
                    coincidir(identificador);
                    A3[A3N] = "ID";
                    A3N++;
                    A2[A2N] = "A3";
                    A2N++;
                    }
                }else if(A3N == 0){
                    //Epsilon
                    A3N=2;
                    A2[A2N] = "A3";
                    A2N++;
                }
                if(preanalisis.equals(coma)){
                    //A1
                    coincidir(coma);
                    A1[A1N] = ",";
                    A1N++;
                    //if A entonces repetir a
                    A1N++;
                }else{
                    //Epsilon
                    A1N=2;
                }
                if(A2N == 2 && (A1N == 2 && A3N == 2)){
                //A
                P[PN] = "A";
                PN++;
                A[AN] = "A2";
                AN++;
                A[AN] = "A1";
                AN++;
                
                }
                
            }
            
        }
        
        if(preanalisis.equals(from)){
            //T1
            coincidir(from);
            Q[QN] = "FROM";
            QN++;
        }
        if(preanalisis.equals(identificador)){
            //T2
            coincidir(identificador);
            T2[T2N] = "ID";
            T2N++;
        }
        if(preanalisis.equals(identificador)){
            //T3
            coincidir(identificador);
            T3[T3N] = "ID";
            T3N++;
            T2[T2N] = "T3";
            T2N++;
        }else{
            //Epsilon
            T3N++;
            T2[T2N] = "T3";
            T2N++;
        }
        if(preanalisis.equals(coma)){
            //T1
            coincidir(coma);
            T1[T1N] = ",";
            T1N++;
            //If T entonces repetir T
            T1N++;
        }else{
            //Epsilon
            T1N=2;
        }
        
        if(T2N == 2 && T1N == 2 && T3N == 1){
            //T
            T[TN] = "T2";
            TN++;
            T[TN] = "T1";
            TN++;
            Q[QN] = "T";
            QN++;
        }
        
        /*if(QN == 4 && 
                DN == 2 && 
                PN == 1 && 
                AN == 2 && 
                A1N == 2 && 
                A2N == 2 && 
                A3N == 2 && 
                TN == 2 && 
                T1N == 2 && 
                T2N == 2 && 
                T3N == 1){
            
        }else*/ if(!hayErrores && !preanalisis.equals(finCadena)){
            System.out.println("Error en la posición " + preanalisis.posicion + ". No se esperaba el token " + preanalisis.tipo);
        }
        else if(!hayErrores && preanalisis.equals(finCadena)){
            System.out.println("Consulta válida");
        }

    }

    

    void coincidir(Token t){
        if(hayErrores) return;

        if(preanalisis.tipo == t.tipo){
            i++;
            preanalisis = tokens.get(i);
        }
        else{
            hayErrores = true;
            System.out.println("Error en la posición " + preanalisis.posicion + ". Se esperaba un  " + t.tipo);

        }
    }
}
