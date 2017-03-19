package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.notelist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cade_a_nota.bigmini.com.br.cade_a_nota.R;

public class ListHolder extends RecyclerView.ViewHolder {
    private final TextView mNameTitle;

    public ListHolder(View itemView) {
        super(itemView);
        mNameTitle = (TextView) itemView.findViewById(R.id.noteNameItem);
    }

    public void setName(String name) {
        mNameTitle.setText(name);
    }

}