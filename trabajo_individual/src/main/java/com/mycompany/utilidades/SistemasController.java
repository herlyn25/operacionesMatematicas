/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utilidades;

public class SistemasController {
    OperacionesDecimales op=new OperacionesDecimales();
    OperacionesBinarias opb=new OperacionesBinarias();
    OperacionesHexadecimales oph=new OperacionesHexadecimales();
    
    public String validarSistemaDecimal(String operation, String system,long a,long b){
        String resultado="";
        if("SUMA".equals(operation) && "DECIMAL".equals(system)){            
            resultado=op.sumar(a, b);
        }
        if("RESTA".equals(operation) && "DECIMAL".equals(system)){            
            resultado=op.restar(a, b);
        }
        if("MULTIPLICACIÓN".equals(operation) && "DECIMAL".equals(system)){            
            resultado=op.multiplicar(a, b);
        }
        if("DIVISIÓN".equals(operation) && "DECIMAL".equals(system)){            
            resultado=op.dividir(a, b);
        }
        if("POTENCIA".equals(operation) && "DECIMAL".equals(system)){            
            resultado=op.potenciacion(a, b);
        }
        if("RADICACIÓN".equals(operation) && "DECIMAL".equals(system)){
            double x=Double.parseDouble(""+a);
            double y=Double.parseDouble(""+b);
            resultado=op.radicacion(x, y);
        }
        return resultado;
    }
    public String validarSistemaBinario(String operation, String system,long a,long b){
        String resultado="";
        if("SUMA".equals(operation) && "BINARIO".equals(system)){            
            resultado=opb.sumar(a, b);            
        }
        if("RESTA".equals(operation) && "BINARIO".equals(system)){            
            resultado=opb.restar(a, b);
        }
        if("MULTIPLICACIÓN".equals(operation) && "BINARIO".equals(system)){            
            resultado=opb.multiplicar(a, b);
        }
        if("DIVISIÓN".equals(operation) && "BINARIO".equals(system)){            
            resultado=opb.dividir(a, b);
        }
        if("POTENCIA".equals(operation) && "BINARIO".equals(system)){            
            resultado=opb.potenciacion(a, b);
        }
        if("RADICACIÓN".equals(operation) && "BINARIO".equals(system)){
            double x=Double.parseDouble(""+a);
            double y=Double.parseDouble(""+b);
            resultado=opb.radicacion(x, y);
        }
        return resultado;
    }
    public String validarSistemaHexadecimal(String operation, String system,long a,long b){
        String resultado="";
        if("SUMA".equals(operation) && "HEXADECIMAL".equals(system)){            
            resultado=oph.sumar(a, b);            
        }
        if("RESTA".equals(operation) && "HEXADECIMAL".equals(system)){            
            resultado=oph.restar(a, b);
        }
        if("MULTIPLICACIÓN".equals(operation) && "HEXADECIMAL".equals(system)){            
            resultado=oph.multiplicar(a, b);
        }
        if("DIVISIÓN".equals(operation) && "HEXADECIMAL".equals(system)){            
            resultado=oph.dividir(a, b);
        }
        if("POTENCIA".equals(operation) && "HEXADECIMAL".equals(system)){            
            resultado=oph.potenciacion(a, b);
        }
        if("RADICACIÓN".equals(operation) && "HEXADECIMAL".equals(system)){
            double x=Double.parseDouble(""+a);
            double y=Double.parseDouble(""+b);
            resultado=oph.radicacion(x, y);
        }
        return resultado;
    }
}