package br.pro.luis.appGestaoDeEnsino;

import java.text.SimpleDateFormat;

public class GestaoDeEnsino {

    public int id;
    public String nomeAluno;
    public String matricula;



    public String substitutiva = "N";
    public int nota1;
    public int nota2;
    public Double notaFinal;
    public String status;

    public Double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Double notaFinal) {
        this.notaFinal = notaFinal;
    }


    public String getSubstitutiva() {
        return substitutiva;
    }

    public void setSubstitutiva(String substitutiva) {
        this.substitutiva = substitutiva;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public int getNota1() {
        return nota1;
    }

    public void setNota1(int nota1) {
        this.nota1 = nota1;
    }

    public int getNota2() {
        return nota2;
    }

    public void setNota2(int nota2) {
        this.nota2 = nota2;
    }

    public GestaoDeEnsino() {

    }

    public GestaoDeEnsino(int id, String nomeAluno, Double notaFinal, String status, int nota1, int nota2, String substitutiva, String matricula) {
        this.id = id;
        this.nomeAluno = nomeAluno;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.notaFinal = notaFinal;
        this.matricula = matricula;
        this.substitutiva = substitutiva;
        this.status = status;
    }



    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
//        if( matricula >= IDADE_MINIMA)
        this.matricula = matricula;
    }
    
    @Override
    public String toString() {
        return "GestaoDeEnsino{" +
                "id=" + id +
                ", nomeAluno='" + nomeAluno + '\'' +
                ", matricula=" + matricula +
                ", nota1=" + nota1 +
                ", nota2=" + nota2 +
                '}';
    }
}
