package com.encadenadas.td.encadenadas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameOfflinevsIAActivity extends AppCompatActivity {
    private TextView tvPuntuacion, tvUltimaPalabra;
    private EditText etNuevaPalabra;
    private Button bAceptar;
    private int contadorPuntuacion = 0;
    ArrayList<String> palabrasDichas = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_offlinevs_ia);
        tvPuntuacion = (TextView) findViewById(R.id.tvPuntuacionGameOfflineVSIA);
        tvUltimaPalabra = (TextView) findViewById(R.id.tvUltimaPalabraGameOfflineVSIA);
        etNuevaPalabra = (EditText) findViewById(R.id.etNuevaPalabraGameOfflineVSIA);
        bAceptar = (Button) findViewById(R.id.bAceptarGameOfflineVSIA);


        tvPuntuacion.setText(String.valueOf(contadorPuntuacion));
        bAceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (validarEntrada()) {
                    gestionBotonAceptar();
                }
            }
        });
    }

    private void gestionBotonAceptar() {
        if (!tvUltimaPalabra.getText().toString().equalsIgnoreCase(getString(R.string.aun_no_se_ha_escrito_ninguna_palabra))) {
            if (validarPalabra(tvUltimaPalabra.getText().toString(), etNuevaPalabra.getText().toString())) {
                String palabra = etNuevaPalabra.getText().toString().toUpperCase();
                tvUltimaPalabra.setText(palabra);
                contadorPuntuacion += 10;
                tvPuntuacion.setText(String.valueOf(contadorPuntuacion));
                etNuevaPalabra.setText("");
            } else {
                contadorPuntuacion -= 10;
                if (contadorPuntuacion <= 0) {
                    finish();
                } else {
                    etNuevaPalabra.setError(getText(R.string.palabra_no_valida));
                    tvPuntuacion.setText(String.valueOf(contadorPuntuacion));
                }
            }
        } else {
            String palabra = etNuevaPalabra.getText().toString().toUpperCase();
            tvUltimaPalabra.setText(palabra);
            etNuevaPalabra.setText("");
        }
    }

    private String devolverSilaba(String palabra) {
        //Cadena de vuelta.
        String result = "";
        if (palabra.length() > 1) {
            //A??adir letras a la cadena.
            String letrasPosterior = "";
            //Comprobar si es hiato.
            String primeraLetra = "";
            for (int i = 0; i < palabra.length(); i++) {
                //Asignar letra actual y a??adirla a la cadena para el patr??n.
                String letra = String.valueOf(palabra.toUpperCase().charAt(palabra.length() - 1 - i));
                letrasPosterior = letra + letrasPosterior;
                //Patr??n.
                Pattern patron = Pattern.compile("[????AEO][IU][????AEO][A-Z????????????]*|[????][AEOIU][A-Z????????????]*|[AEO][AEO????][A-Z????????????]*|[BCDFGHJKLMN??PQRSTVWXYZ][AEIOU??????????][A-Z????????????]*|G??[EI][A-Z????????????]*");
                Matcher encaja = patron.matcher(letrasPosterior);
                if (encaja.matches()) {
                    //Seleccionar la primera letra para quitarla en caso de hiato.
                    primeraLetra = String.valueOf(palabra.toUpperCase().charAt(palabra.length() - 1 - i));
                    for (int j = palabra.length() - 1 - i; j < palabra.length(); j++) {
                        String l = String.valueOf(palabra.toUpperCase().charAt(j));

                        if (j == palabra.length() - 1 - i) {
                            if (!primeraLetra.equals("A") && !primeraLetra.equals("E") && !primeraLetra.equals("O") && !primeraLetra.equals("??") && !primeraLetra.equals("??")) {
                                result += l;
                            }
                            if (palabra.length() - 2 - i >= 0) {
                                String prePrimeraLetra = String.valueOf(palabra.charAt(palabra.length() - 2 - i));

                                if (primeraLetra.equals("L")) {
                                    if (prePrimeraLetra.equalsIgnoreCase("T") ||
                                            prePrimeraLetra.equalsIgnoreCase("P") ||
                                            prePrimeraLetra.equalsIgnoreCase("D") ||
                                            prePrimeraLetra.equalsIgnoreCase("F") ||
                                            prePrimeraLetra.equalsIgnoreCase("G") ||
                                            prePrimeraLetra.equalsIgnoreCase("L") ||
                                            prePrimeraLetra.equalsIgnoreCase("K") ||
                                            prePrimeraLetra.equalsIgnoreCase("B") ||
                                            prePrimeraLetra.equalsIgnoreCase("C")) {
                                        result = (prePrimeraLetra + l);
                                    }
                                } else {
                                    if (primeraLetra.equalsIgnoreCase("R")) {
                                        if (prePrimeraLetra.equalsIgnoreCase("T") ||
                                                prePrimeraLetra.equalsIgnoreCase("P") ||
                                                prePrimeraLetra.equalsIgnoreCase("D") ||
                                                prePrimeraLetra.equalsIgnoreCase("F") ||
                                                prePrimeraLetra.equalsIgnoreCase("G") ||
                                                prePrimeraLetra.equalsIgnoreCase("K") ||
                                                prePrimeraLetra.equalsIgnoreCase("B") ||
                                                prePrimeraLetra.equalsIgnoreCase("C")) {
                                            result = (prePrimeraLetra + l);
                                        }
                                    } else {
                                        if (primeraLetra.equalsIgnoreCase("H")) {
                                            if (prePrimeraLetra.equalsIgnoreCase("C") ||
                                                    prePrimeraLetra.equalsIgnoreCase("L") ||
                                                    prePrimeraLetra.equalsIgnoreCase("P") ||
                                                    prePrimeraLetra.equalsIgnoreCase("T") ||
                                                    prePrimeraLetra.equalsIgnoreCase("S") ||
                                                    prePrimeraLetra.equalsIgnoreCase("G") ||
                                                    prePrimeraLetra.equalsIgnoreCase("N")) {
                                                result = (prePrimeraLetra + l);
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            //Creo que esto se puede poner como condici??n principal. HAY QUE COMPROBAR
                            if (j != palabra.length() - 1 - i) {
                                result += l;
                            }
                        }

                    }
                    break;
                }
            }
        } else

        {
            result = palabra;
        }

        //return String.valueOf(palabra.toUpperCase().charAt(palabra.length() - 1));
        return

                quitarAcentos(result);

    }

    private boolean validarPalabra(String original, String nueva) {
        Pattern patron = Pattern.compile(devolverSilaba(original) + "[A-Z??????????????]*");
        Matcher encaja = patron.matcher(quitarAcentos(nueva.toUpperCase()));
        return encaja.matches();
    }

    private String quitarAcentos(String palabra) {
        String result = "";
        for (int i = 0; i < palabra.length(); i++) {
            if (String.valueOf(palabra.charAt(i)).equalsIgnoreCase("??")) {
                result += "A";
            } else {
                if (String.valueOf(palabra.charAt(i)).equalsIgnoreCase("??")) {
                    result += "E";
                } else {

                    if (String.valueOf(palabra.charAt(i)).equalsIgnoreCase("??")) {
                        result += "I";
                    } else {

                        if (String.valueOf(palabra.charAt(i)).equalsIgnoreCase("??")) {
                            result += "O";
                        } else {

                            if (String.valueOf(palabra.charAt(i)).equalsIgnoreCase("??")) {
                                result += "U";
                            } else {
                                result += String.valueOf(palabra.charAt(i));
                            }
                        }
                    }
                }
            }
        }

        return result.toUpperCase();
    }

    private boolean validarEntrada() {
        boolean result = false;

        if (etNuevaPalabra.getText().toString().isEmpty()) {
            etNuevaPalabra.setError(getText(R.string.tienes_que_introducir_una_palabra));
        } else {
            if (comprobarNumeroPalabras(etNuevaPalabra.getText().toString())) {
                etNuevaPalabra.setError(getText(R.string.tienes_que_introducir_una_palabra));
            } else {
                result = true;
            }
        }
        return result;
    }

    private boolean comprobarNumeroPalabras(String palabra) {
        boolean masDeUnaPalabra = true;
        String[] nPalabras = palabra.split(" ");
        if (nPalabras.length == 1) {
            //Comprobar si tiene alg??n caracter distinto a una letra
            boolean esTodoLetras = true;
            nPalabras[0] = nPalabras[0].toUpperCase();
            for (int i = 0; i < (nPalabras[0].length() - 1); i++) {
                Pattern patron = Pattern.compile("[A-Z??????????????]");
                Matcher encaja = patron.matcher(String.valueOf(nPalabras[0].charAt(i)));
                if (!encaja.matches()) {
                    esTodoLetras = false;
                }
            }
            if (esTodoLetras) {
                masDeUnaPalabra = false;
                etNuevaPalabra.setText(nPalabras[0]);
            }
        } else {
            if (nPalabras.length == 2 && nPalabras[1].equalsIgnoreCase(" ")) {
                //Comprobar si tiene alg??n caracter distinto a una letra
                boolean esTodoLetras = true;
                nPalabras[0] = nPalabras[0].toUpperCase();
                for (int i = 0; i < (nPalabras[0].length() - 1); i++) {
                    Pattern patron = Pattern.compile("[A-Z??????????????]");
                    Matcher encaja = patron.matcher(String.valueOf(nPalabras[0].charAt(i)));
                    if (!encaja.matches()) {
                        esTodoLetras = false;
                    }
                }
                if (esTodoLetras) {
                    masDeUnaPalabra = false;
                    etNuevaPalabra.setText(nPalabras[0]);
                }
            }
        }

        return masDeUnaPalabra;
    }
}
