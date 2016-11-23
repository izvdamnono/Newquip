package com.izv.dam.newquip.adaptadores;

import android.content.Context;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.android.databinding.library.baseAdapters.BR;
import com.izv.dam.newquip.R;
import com.izv.dam.newquip.contrato.ClickListener;
import com.izv.dam.newquip.contrato.ClickListenerLong;
import com.izv.dam.newquip.databinding.ItemBinding;
import com.izv.dam.newquip.pojo.Nota;
import com.izv.dam.newquip.util.UtilFecha;

import java.util.Random;

import static android.R.attr.radius;

public class AdaptadorNota extends RecyclerView.Adapter<AdaptadorNota.ViewHolder> {

    private ClickListener mItemClickListener;
    private ClickListenerLong mItemClickListenerLong;
    private Cursor mCursor;
    private ItemBinding binding;


    public AdaptadorNota(Cursor c) {
        mCursor = c;
    }

    @Override
    public AdaptadorNota.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new ViewHolder(itemView, mItemClickListener, mItemClickListenerLong);
    }

    @Override
    public void onBindViewHolder(AdaptadorNota.ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        Nota nota = Nota.getNota(mCursor);

        holder.getBinding().setVariable(BR.nota, nota);
        holder.getBinding().executePendingBindings();

        String color = (nota.getColor() != null) ? nota.getColor() : "#FFFFFF";
        holder.bindNota(color);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public Cursor changeCursor(Cursor c) {

        if (mCursor == c) {
            return null;
        }
        this.mCursor = c;
        if (c != null) {
            this.notifyDataSetChanged();
        }
        mCursor = c;

        return mCursor;

    }


    public void setOnItemClickListener(ClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(ClickListenerLong listener) {
        this.mItemClickListenerLong = listener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {


        CardView cardView;

        ClickListener mItemClickListener;
        ClickListenerLong mItemClickListenerLong;

        ItemBinding binding;

        public ViewHolder(View itemView, ClickListener myItemClickListener, ClickListenerLong myItemClickListenerLong) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            /*
            tv_titulo_nota = (TextView)itemView.findViewById(R.id.tvTituloNota);
            texo_nota = (TextView)itemView.findViewById(R.id.tvTexoNota);
            fecha_creacion = (TextView)itemView.findViewById(R.id.tvFecha);
            fecha_recordatorio = (TextView)itemView.findViewById(R.id.tvFechaRecordatorio);
            */
            cardView = (CardView) itemView.findViewById(R.id.id_card_view_item);
            this.mItemClickListener = myItemClickListener;
            this.mItemClickListenerLong = myItemClickListenerLong;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);


        }

        public ItemBinding getBinding() {
            return binding;
        }

        public void bindNota(String color) {
            int androidColor = Color.parseColor(color);

//            int androidColor = Integer.parseInt((color));
//            int[] androidColors = cardView.getResources().getIntArray(R.array.default_rainbow);
//            androidColor = androidColors[new Random().nextInt(androidColors.length)];
            cardView.setBackgroundColor(androidColor);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }

        @Override
        public boolean onLongClick(View arg0) {
            if (mItemClickListenerLong != null) {
                mItemClickListenerLong.onItemLongClick(arg0, getPosition());
            }
            return true;
        }
    }
}