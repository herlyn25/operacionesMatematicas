package com.mycompany.utilidades;

public class OperacionesBinarias implements OperacionesInterface{
    @Override
    public String sumar(long a, long b) {
        long resultado=a+b;
        String result=Long.toString(resultado,2);
        return result;
    }
    @Override
    public String restar(long a, long b) {
        long resultado=a-b;
        String result=Long.toString(resultado,2);
        return result;
    }
    @Override
    public String multiplicar(long a, long b) {
        long resultado=a*b;
        String result=Long.toString(resultado,2);
        return result;
    }
    @Override
    public String dividir(long a, long b) {        
        long resultado=a/b;        
        return Long.toString(resultado,2);
    }

    @Override
    public String potenciacion(long a, long b) {
        long resultado;
        if(b==0){
            return "1";
        }else{
        resultado=(long)Math.pow(a, b);
        }
        return Long.toString(resultado,2);
    }

    @Override
    public String radicacion(double a, double b) {
       long resultado;
        if(b==0){
            return "1";
        }else{
        resultado=(long)Math.pow(a,1/b);
        }
        return Long.toString(resultado,16);
    }
     
}
