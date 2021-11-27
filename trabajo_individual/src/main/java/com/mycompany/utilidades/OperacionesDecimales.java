package com.mycompany.utilidades;

public class OperacionesDecimales implements OperacionesInterface{

    @Override
    public String sumar(long a, long b) {
        long resultado=a+b;        
        return ""+resultado;
    }

    @Override
    public String restar(long a, long b) {
        long resultado=a-b;        
        return ""+resultado;
    }

    @Override
    public String multiplicar(long a, long b) {
        long resultado=a*b;        
        return ""+resultado;
    }

    @Override
    public String dividir(long a, long b) {
        int resultado=(Math.round(a/b));        
        return ""+resultado;
    }

    @Override
    public String potenciacion(long a, long b) {
        long resultado=0;
        if(b==0){
            resultado=1;
        }else{
        resultado=(long)Math.pow(a, b);
        }
        return ""+resultado;
    }

    @Override
    public String radicacion(double a, double b) {
        long resultado=0;
        if(b==0){
            resultado=1;
        }else{
        resultado=Math.round(Math.pow(a,1/b));
        }
        return ""+resultado;
    } 
    
}
