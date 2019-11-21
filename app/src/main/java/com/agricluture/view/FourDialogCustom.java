package com.agricluture.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.agriculture.R;

/**
 * Created by ${ding} on 2018/1/18.
 */

public class FourDialogCustom extends DialogCustom {
    private String genderContent;
    private    OnClickListener  clickListener;
    public FourDialogCustom(Context context, int viewId, String position) {
        super(context, viewId, position);
        ini();
    }
    private void ini(){
         TextView genderOne=findViewById(R.id.one);
         TextView genderTwo=findViewById(R.id.two);
         TextView cancel=findViewById(R.id.cancel);
         setOnClick(genderOne);
         setOnClick(genderTwo);
         setOnClick(cancel);
     }

    private void setOnClick(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){
                    case R.id.one:
                        genderContent = ((TextView)v).getText().toString();
                        clickListener.onClick(0,genderContent);
                        break;
                    case R.id.two:
                        genderContent = ((TextView)v).getText().toString();
                        clickListener.onClick(1,genderContent);
                        break;
                    case  R.id.cancel:
                        clickListener.onCancel();
                        break;
                }
                if (this != null) {
                    dismiss();
                }
            }
        });
    }
   public interface OnClickListener{
        void  onClick(int postion, String str);
        void  onCancel();
    }
   public void setOnClickListener(OnClickListener  cl){
         this.clickListener=cl;
   }

}