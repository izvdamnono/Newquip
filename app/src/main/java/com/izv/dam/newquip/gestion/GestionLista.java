package com.izv.dam.newquip.gestion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.izv.dam.newquip.contrato.ContratoBaseDatos;
import com.izv.dam.newquip.pojo.Lista;

import java.util.ArrayList;

public class GestionLista extends Gestion<Lista> {

    public GestionLista(Context c) {
        super(c);
    }

    public GestionLista(Context c, boolean write) {
        super(c, write);
    }

    @Override
    public int deleteAll() {
        return this.deleteAll(ContratoBaseDatos.TablaLista.TABLA);
    }

    @Override
    public int delete(Lista objeto) {
        String condicion = ContratoBaseDatos.TablaLista._ID + " = ?";
        String[] argumentos = {objeto.getId_lista() + ""};
        return this.delete(ContratoBaseDatos.TablaLista.TABLA, condicion, argumentos);
    }

    public int delete(String condicion, String[] argumentos) {
        return this.delete(ContratoBaseDatos.TablaLista.TABLA, condicion, argumentos);
    }

    public Lista get(long id) {
        String where = ContratoBaseDatos.TablaLista._ID + " = ? ";
        String[] parametros = {id + ""};
        Cursor c = this.getCursor(ContratoBaseDatos.TablaLista.PROJECTION_ALL, where, parametros,
                null, null, ContratoBaseDatos.TablaLista.SORT_ORDER_DEFAULT);
        if (c.getCount() > 0) {
            c.moveToFirst();
            return Lista.getLista(c);
        }
        return null;
    }

    @Override
    public Cursor getCursor() {
        return this.getCursor(ContratoBaseDatos.TablaLista.TABLA,
                ContratoBaseDatos.TablaLista.PROJECTION_ALL,
                ContratoBaseDatos.TablaLista.SORT_ORDER_DEFAULT
        );
    }

    @Override
    public Cursor getCursor(String[] columns, String selection, String[] selectionArgs,
                            String groupBy, String having, String orderBy) {
        return this.getCursor(ContratoBaseDatos.TablaLista.TABLA, columns, selection, selectionArgs,
                groupBy, having, orderBy);
    }

    @Override
    public long insert(ContentValues objeto) {
        return this.insert(ContratoBaseDatos.TablaLista.TABLA, objeto);
    }

    @Override
    public long insert(Lista objeto) {
        return this.insert(ContratoBaseDatos.TablaLista.TABLA, objeto.getContentValues());
    }

    @Override
    public int update(ContentValues valores, String condicion, String[] argumentos) {
        return this.update(ContratoBaseDatos.TablaLista.TABLA, valores, condicion, argumentos);
    }

    @Override
    public int update(Lista objeto) {
        ContentValues valores = objeto.getContentValues();
        String condicion = ContratoBaseDatos.TablaLista._ID + " = ?";
        String[] argumentos = {objeto.getId_lista() + ""};
        return this.update(ContratoBaseDatos.TablaLista.TABLA, valores, condicion, argumentos);
    }

    public ArrayList<Lista> getListas(long id_nota) {
        ArrayList<Lista> listaArrayList = new ArrayList<>();
        String where = ContratoBaseDatos.TablaLista.ID_NOTA + " = ? ";
        String[] parametros = {id_nota + ""};
        Cursor c = this.getCursor(ContratoBaseDatos.TablaLista.PROJECTION_ALL, where, parametros,
                null, null, ContratoBaseDatos.TablaLista.SORT_ORDER_DEFAULT);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            listaArrayList.add(Lista.getLista(c));
        }
        if (listaArrayList.size() > 0) {
            return listaArrayList;
        }
        return null;
    }


}
