package com.encadenadas.td.encadenadas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bModoOffline,bModoOnline;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bModoOffline = (Button) findViewById(R.id.bModoOfflineMain);
        bModoOnline = (Button) findViewById(R.id.bModoOnlineMain);

        bModoOffline.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gestionBotonModoOffline();
            }
        });
        bModoOnline.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gestionBotonModoOnline();
            }
        });
    }

    private void gestionBotonModoOffline() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.cuadro_dialogo_modo_offline, null);

        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();

        Button bNuevaPartidaModoOffline = (Button) view.findViewById(R.id.bNuevaPartidaCuadroDialogoModoOffline);
        Button bContinuarModoOffline = (Button) view.findViewById(R.id.bContinuarCuadroDialogoModoOffline);
        Button bNuevaPartidaModoOfflineVSIA = (Button) view.findViewById(R.id.bNuevaPartidaCuadroDialogoModoOfflineVSIA);
        Button bContinuarModoOfflineVSIA = (Button) view.findViewById(R.id.bContinuarCuadroDialogoModoOfflineVSIA);
        bContinuarModoOffline.setEnabled(false);
        bContinuarModoOffline.setBackgroundColor(getColor(R.color.grey));
        bContinuarModoOfflineVSIA.setEnabled(false);
        bContinuarModoOfflineVSIA.setBackgroundColor(getColor(R.color.grey));
        ImageButton ibSalirModoOffline = (ImageButton) view.findViewById(R.id.ibSalirCuadroDialogoModoOffline);

        bContinuarModoOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(contexto, "No implementado por el momento", Toast.LENGTH_LONG).show();
            }
        });

        bNuevaPartidaModoOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirNuevaPartidaModoOffline();
                dialog.dismiss();
            }
        });
        bContinuarModoOfflineVSIA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(contexto, "No implementado por el momento", Toast.LENGTH_LONG).show();
            }
        });

        bNuevaPartidaModoOfflineVSIA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirNuevaPartidaModoOfflineVSIA();
                dialog.dismiss();
            }
        });

        ibSalirModoOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void abrirNuevaPartidaModoOffline(){
        Intent intent = new Intent(this, GameOfflineActivity.class);
        startActivity(intent);
    }

    private void abrirNuevaPartidaModoOfflineVSIA(){
        Intent intent = new Intent(this, GameOfflinevsIAActivity.class);
        startActivity(intent);
    }


    private void gestionBotonModoOnline() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.cuadro_dialogo_modo_online, null);

        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();

        Button bNuevaPartidaModoOnline = (Button) view.findViewById(R.id.bNuevaPartidaCuadroDialogoModoOnline);
        Button bContinuarModoOnline = (Button) view.findViewById(R.id.bContinuarCuadroDialogoModoOnline);
        bContinuarModoOnline.setEnabled(false);
        bContinuarModoOnline.setBackgroundColor(getColor(R.color.grey));
        ImageButton ibSalirModoOnline = (ImageButton) view.findViewById(R.id.ibSalirCuadroDialogoModoOnline);

        bContinuarModoOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bNuevaPartidaModoOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirNuevaPartidaModoOnline();
                dialog.dismiss();
            }
        });

        ibSalirModoOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void abrirNuevaPartidaModoOnline(){
        Intent intent = new Intent(this, GameOnlineActivity.class);
        startActivity(intent);
    }
}
