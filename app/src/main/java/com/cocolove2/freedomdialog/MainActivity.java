package com.cocolove2.freedomdialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.cocolove2.library_freedomdialog.FreedomDialog;
import com.cocolove2.library_freedomdialog.default_effects.Effectstype;
import com.cocolove2.library_freedomdialog.default_effects.FadeIn;
import com.cocolove2.library_freedomdialog.default_effects.Fadeout;
import com.cocolove2.library_freedomdialog.default_effects.FlipH;
import com.cocolove2.library_freedomdialog.listener.AnimListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FreedomDialog.Builder builder;
    FreedomDialog dialog;
    private RecyclerView mRecyclerView;
    private List<String> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.content_main_recyclerview);

        setSupportActionBar(toolbar);
        initFreedomDialog();
        initDatas();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyAdapter(this, mDatas));





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                dialog = builder.build();
                dialog.show();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    private void initDatas() {
        mDatas.add("无动画");
        mDatas.add("补间动画");
        mDatas.add("属性画1");
        mDatas.add("属性画2");
        mDatas.add("属性画3");
    }

    View view;
    private void initFreedomDialog() {
        view = getLayoutInflater().inflate(R.layout.dialog_init, null);

        AnimationSet inAnim = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.modal_in);
        AnimationSet outAnim = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.modal_out);
        builder = new FreedomDialog.Builder(this)
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .setContentView(view)
                .addInAnimListener(inAnimListener)
                .addOutAnimListener(outAnimListener)
//                .setAnimation(inAnim, outAnim)
                .setAnimator(new FadeIn(), new Fadeout())
        ;
        dialog=builder.build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private AnimListenerAdapter inAnimListener = new AnimListenerAdapter() {
        @Override
        public void onAnimStart() {
            Log.e("TAG", "in on anim start");
        }

        @Override
        public void onAnimEnd() {
            Log.e("TAG", "in on anim end");

        }
    };
    private AnimListenerAdapter outAnimListener = new AnimListenerAdapter() {
        @Override
        public void onAnimStart() {
            Log.e("TAG", "out on anim start");
        }

        @Override
        public void onAnimEnd() {
            Log.e("TAG", "out on anim end");
//            dialog.dismiss();
        }
    };


    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private Activity activity;
        private List<String> datas;

        public MyAdapter(Activity activity, List<String> datas) {
            this.activity = activity;
            this.datas = datas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_rv,parent,false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((MyViewHolder) holder).tv.setText(datas.get(position));
            ((MyViewHolder) holder).tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position) {
                        case 0:
                      new FreedomDialog.Builder(activity)
                                    .setCancelable(true)
                                    .setCanceledOnTouchOutside(true)
                                    .setContentView(getLayoutInflater().inflate(R.layout.dialog_init, null)).build().show();
                            break;
                        case 1:
                            AnimationSet inAnim = (AnimationSet) AnimationUtils.loadAnimation(activity, R.anim.modal_in);
                            AnimationSet outAnim = (AnimationSet) AnimationUtils.loadAnimation(activity, R.anim.modal_out);
                    new FreedomDialog.Builder(activity)
                                    .setCancelable(true)
                                    .setCanceledOnTouchOutside(true)
                                    .setContentView(getLayoutInflater().inflate(R.layout.dialog_init, null))
                                    .addInAnimListener(inAnimListener)
                                    .addOutAnimListener(outAnimListener)
                                    .setAnimation(inAnim, outAnim).build().show();
                            break;
                        case 2:
                             new FreedomDialog.Builder(activity)
                                    .setCancelable(true)
                                    .setCanceledOnTouchOutside(true)
                                    .setContentView(getLayoutInflater().inflate(R.layout.dialog_init, null))
                                    .addInAnimListener(inAnimListener)
                                    .addOutAnimListener(outAnimListener)
                                    .setAnimator(new FlipH(), null).build().show();
                            break;
                        case 3:
                             new FreedomDialog.Builder(activity)
                                    .setCancelable(true)
                                    .setCanceledOnTouchOutside(true)
                                    .setContentView(getLayoutInflater().inflate(R.layout.dialog_init, null))
                                    .addInAnimListener(inAnimListener)
                                    .addOutAnimListener(outAnimListener)
                                    .setAnimator(Effectstype.Falling.getAnimator(), null).build().show();
                            break;
                        case 4:
                            AnimationSet outAnim1 = (AnimationSet) AnimationUtils.loadAnimation(activity, R.anim.modal_out);

                           new FreedomDialog.Builder(activity)
                                    .setCancelable(true)
                                    .setCanceledOnTouchOutside(true)
                                    .setContentView(getLayoutInflater().inflate(R.layout.dialog_init, null))
                                    .addInAnimListener(inAnimListener)
                                    .addOutAnimListener(outAnimListener)
                                    .setAnimation(null,outAnim1)
                                    .setAnimator(Effectstype.Newspager.getAnimator(), null).build().show();
                            break;
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return datas == null ? 0 : datas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView tv;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.item_rv_tv);
            }
        }
    }


}
