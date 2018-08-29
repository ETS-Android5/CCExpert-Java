package fr.unice.polytech.ccexpert.controller.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.model.Sets;
import fr.unice.polytech.ccexpert.model.Talent;
import fr.unice.polytech.ccexpert.view.TalentsAdapter;

public class TalentsActivity extends BaseActivity {
    private static List<Talent> talents;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talents);

        ((TextView) findViewById(R.id.talentsTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        if (talents == null) {
            talents = Sets.getInstance().getTalents();
        }

        GridView gridTalents = findViewById(R.id.talentsGrid);
        gridTalents.setAdapter(new TalentsAdapter(this, talents));

        gridTalents.setOnItemClickListener((adapterView, view, i, l) -> {
            Talent t = talents.get(i);
            try {
                Intent intent = new Intent(TalentsActivity.this, TalentActivity.class);
                intent.putExtra("talentName", t.getName());
                startActivity(intent);
            } catch (NullPointerException ignored) { }
        });
    }
}
